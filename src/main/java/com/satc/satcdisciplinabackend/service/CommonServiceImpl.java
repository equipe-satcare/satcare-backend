package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.repository.CommonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public abstract class CommonServiceImpl<Entity> implements CommonService<Entity> {

    protected final CommonRepository<Entity> repository;
    protected final ModelMapper modelMapper;
    private final Class<Entity> clazz;

    public CommonServiceImpl(CommonRepository<Entity> repository, Class<Entity> clazz, ModelMapper modelMapper) {
        this.repository = repository;
        this.clazz = clazz;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Entity> findAll(String filter) {
        return repository.findAll(filter, clazz);
    }

    @Override
    public Page<Entity> findPaginated(int page, int size, String filter) {
        return repository.findAll(filter, clazz, PageRequest.of(page, size));
    }

    @Override
    public Entity findById(Integer id) {
        return this.findById(id, false);
    }

    public Entity findById(Integer id, boolean throwException) {
        Optional<Entity> optionalEntity = repository.findById(id);
        if (optionalEntity.isEmpty() && throwException) {
            throw new NotFoundException();
        }
        return optionalEntity.orElse(null);
    }

    @Override
    public Entity save(Entity entity) {
        return repository.save(entity);
    }

    @Override
    public Entity update(Integer id, Entity entity) {
        Optional<Entity> optional = repository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException();
        }

        Entity existingEntity = optional.get();
        modelMapper.map(entity, existingEntity);
        return repository.save(existingEntity);
    }

    @Override
    public void deleteOne(Integer id) {
        repository.deleteById(id);
    }
}
