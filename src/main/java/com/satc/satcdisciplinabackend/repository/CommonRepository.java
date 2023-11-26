package com.satc.satcdisciplinabackend.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.satc.satcdisciplinabackend.enterprise.BooleanBuilderUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<T> extends JpaRepository<T, Integer>, QuerydslPredicateExecutor<T> {
    @Override
    List<T> findAll(Predicate predicate);

    default List<T> findAll(String filter,Class<T> entityType){
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter,entityType);
        return this.findAll(booleanBuilder);
    }

    default Page<T> findAll(String filter, Class<T> entityType, Pageable pageable){
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter,entityType);
        return this.findAll(booleanBuilder, pageable);
    }
}
