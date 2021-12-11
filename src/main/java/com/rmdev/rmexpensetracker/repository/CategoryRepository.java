package com.rmdev.rmexpensetracker.repository;

import com.rmdev.rmexpensetracker.entity.Category;
import com.rmdev.rmexpensetracker.exception.RmBadRequestException;
import com.rmdev.rmexpensetracker.exception.RmResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws RmResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws RmResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws RmBadRequestException;

    void update(Integer userId, Integer categoryId, Category category) throws RmBadRequestException;

    void removeById(Integer userId, Integer categoryId);

}
