package com.satc.satcdisciplinabackend.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BooleanBuilderUtil {


    public static BooleanBuilder buildPredicateFromFilter(String filter, Class<?> classe) {
        if (filter == null || filter.isEmpty()) {
            return new BooleanBuilder();
        }

        BooleanBuilder predicate = new BooleanBuilder();

        // Separa todos "or" do filtro
        // adicionei o "+" para nao precisar tratar strings vazias
        String[] orClauses = filter.split("or\\+");

        for (String orPart : orClauses) {
            // Separa todos "and"
            String[] andClauses = orPart.split("and\\+");

            BooleanBuilder andPredicate = new BooleanBuilder();
            for (String clause : andClauses) {
                try {
                    String[] parts = clause.split("\\+");

                    String field = parts[0];
                    String operator = parts[1];
                    String firstValue = parts[2];

                    EntityPath<?> fieldPath = buildPath(field, classe);
                    // pega o tipo do campo usando a classe pois nao encontrei jeito de pegar o tipo atraves do EntityPath
                    Class<?> fieldType = getNestedFieldType(classe, field);

                    List<Expression> expressions = new ArrayList<>();
                    expressions.add(fieldPath);
                    expressions.add(createConstant(firstValue, fieldType));

                    if (parts.length == 4) { // cai aqui caso a expressao tenha 2 parametros. Ex: between
                        String secondValue = parts[3];
                        expressions.add(createConstant(secondValue, fieldType));
                    }

                    // aqui daria para verificar se o DslOperator é um IN e criar uma lista com os valores

                    Operator dslOperator = getDslOperatorFromString(operator);
                    andPredicate.and(Expressions.predicate(dslOperator, expressions.toArray(new Expression[0])));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
            }
            predicate.or(andPredicate);
        }

        return predicate;
    }


    public static Expression createConstant(String value, Class<?> type) {
        return Expressions.constant(castToType(value, type));
    }


    public static Object castToType(String value, Class<?> type) {
        if (String.class.equals(type)) {
            return value;
        }

        if (type.equals(LocalDate.class)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(value, formatter);
        } else if (type == LocalDateTime.class) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            return LocalDateTime.parse(value, formatter);
        } else if (type.isEnum()) {
            return Enum.valueOf(type.asSubclass(Enum.class), value);
        } else {
            if (isConvertibleType(type)) {
                try {
                    Constructor<?> constructor = type.getConstructor(String.class);
                    return constructor.newInstance(value);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Nao foi possivel converter para o tipo: " + type.getSimpleName());
                }
            } else {
                throw new IllegalArgumentException("Tipo nao suportado: " + type.getSimpleName());
            }
        }
    }


    private static <T> boolean isConvertibleType(Class<T> type) {
        try {
            type.getConstructor(String.class);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private static EntityPath<?> buildPath(String path, Class entityClass) {
        String[] paths = path.split("\\.");
        if (paths.length > 0) {
            PathBuilder builder = new PathBuilder(entityClass, paths[0], PathBuilderValidator.FIELDS);
            for (int i = 1; i < paths.length; i++) {
                builder = builder.get(paths[i]);
            }
            return builder;
        } else {
            throw new IllegalArgumentException("The specified path is incorrect : " + path);
        }
    }


    public static Class<?> getNestedFieldType(Class<?> entityClass, String nestedField) throws NoSuchFieldException {
        String[] fieldNames = nestedField.split("\\.");
        Class<?> currentClass = entityClass;

        for (String fieldName : fieldNames) {
            try {
                Field field = currentClass.getDeclaredField(fieldName);
                currentClass = field.getType();
            } catch (NoSuchFieldException | SecurityException e) {
                if (currentClass.getSuperclass() != null) {
                    return getNestedFieldType(currentClass.getSuperclass(), fieldName);
                } else {
                    throw e;
                }
            }
        }

        return currentClass;
    }


    private static Operator getDslOperatorFromString(String param) {
        return switch (param) {
            case "equal" -> Ops.EQ;
            case "notequal" -> Ops.NE;
            case "greater" -> Ops.GT;
            case "lesser" -> Ops.LT;
            case "greaterequal" -> Ops.GOE;
            case "lesserequal" -> Ops.LOE;
            case "between" -> Ops.BETWEEN;
            case "in" -> Ops.IN;
            case "like" -> Ops.STRING_CONTAINS_IC;
            default -> throw new IllegalArgumentException("Operador " + param + " desconhecido.");
        };
    }
}
