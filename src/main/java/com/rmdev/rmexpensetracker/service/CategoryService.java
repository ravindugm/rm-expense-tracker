package com.rmdev.rmexpensetracker.service;

import com.rmdev.rmexpensetracker.entity.Category;
import com.rmdev.rmexpensetracker.exception.RmBadRequestException;
import com.rmdev.rmexpensetracker.exception.RmResourceNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);

    Category fetchCategoryById(Integer userId, Integer categoryId) throws RmResourceNotFoundException;

    Category addCategory(Integer userId, String title, String description) throws RmBadRequestException;

    void updateCategory(Integer userId, Integer categoryId, Category category) throws RmBadRequestException;

    void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws RmResourceNotFoundException;

}
