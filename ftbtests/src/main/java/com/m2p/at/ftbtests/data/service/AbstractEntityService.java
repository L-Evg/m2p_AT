package com.m2p.at.ftbtests.data.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractEntityService<E> {

    private static final int PAGE_SIZE = 5;

    protected abstract JpaRepository<E, Long> getRepository();

    protected abstract String[] getSortByProperties();

    protected abstract NoSuchElementException buildEntityNotFoundException(long id);

    protected NoSuchElementException buildEntityNotFoundException(String entityPrettyName, long id) {
        return new NoSuchElementException(String.format("%s was not found by id=%s", entityPrettyName, id));
    }

    //@Transactional
    public Page<E> getAllPaged(int pageNum) {
        return getRepository().findAll(PageRequest.of(pageNum, PAGE_SIZE, Sort.by(getSortByProperties())));
    }

    //@Transactional
    public Page<E> getPaged(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    //@Transactional
    public List<E> getAll() {
        return getRepository().findAll();
    }

    //@Transactional
    public E getById(Long entityId) {
        if (null == entityId) throw new IllegalArgumentException("Entity ID shall not be null.");

        return getRepository().findById(entityId).orElseThrow(() -> buildEntityNotFoundException(entityId));
    }

    //@Transactional
    public Optional<E> getOptionallyById(Long entityId) {
        if (null == entityId) throw new IllegalArgumentException("Entity ID shall be null.");

        return getRepository().findById(entityId);
    }

    //@Transactional
    public List<E> getAllById(List<Long> entityIds) {
        if (null == entityIds) throw new IllegalArgumentException("List of Entity IDs shall be null.");

        return getRepository().findAllById(entityIds);
    }

    @Transactional
    public E save(E entity) {
        if (null == entity) throw new IllegalArgumentException("Cannot save null!");
        return getRepository().save(entity);
    }

    @Transactional
    public void deleteById(Long entity) {
        getRepository().deleteById(entity);
    }

}
