package com.rmdev.rmexpensetracker.repository;

import com.rmdev.rmexpensetracker.entity.Category;
import com.rmdev.rmexpensetracker.exception.RmBadRequestException;
import com.rmdev.rmexpensetracker.exception.RmResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String SQL_CREATE = "INSERT INTO ET_CATEGORIES (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION) VALUES(NEXTVAL('ET_CATEGORIES_SEQ'), ?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? AND C.CATEGORY_ID = ? GROUP BY C.CATEGORY_ID";

    private static final String SQL_FIND_ALL = "SELECT C.CATEGORY_ID, C.USER_ID, C.TITLE, C.DESCRIPTION, " +
            "COALESCE(SUM(T.AMOUNT), 0) TOTAL_EXPENSE " +
            "FROM ET_TRANSACTIONS T RIGHT OUTER JOIN ET_CATEGORIES C ON C.CATEGORY_ID = T.CATEGORY_ID " +
            "WHERE C.USER_ID = ? GROUP BY C.CATEGORY_ID";

    private static final String SQL_UPDATE = "UPDATE ET_CATEGORIES SET TITLE = ?, DESCRIPTION = ? " +
            "WHERE USER_ID = ? AND CATEGORY_ID = ?";

    private static final String SQL_DELETE_CATEGORY = "DELETE FROM ET_CATEGORIES WHERE USER_ID = ? AND CATEGORY_ID = ?";

    private static final String SQL_DELETE_ALL_TRANSACTION = "DELETE FROM ET_TRANSACTIONS WHERE CATEGORY_ID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll(Integer userId) throws RmResourceNotFoundException {
        return jdbcTemplate.query(SQL_FIND_ALL, new Object[]{userId}, categoryRowMapper);
    }

    @Override
    public Category findById(Integer userId, Integer categoryId) throws RmResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId, categoryId}, categoryRowMapper);
        } catch (Exception e) {
            throw new RmResourceNotFoundException("Category not found");
        }
    }

    @Override
    public Integer create(Integer userId, String title, String description) throws RmBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, userId);
                preparedStatement.setString(2, title);
                preparedStatement.setString(3, description);
                return preparedStatement;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("CATEGORY_ID");
        } catch (Exception e) {
            throw new RmBadRequestException("Invalid Request");
        }
    }

    @Override
    public void update(Integer userId, Integer categoryId, Category category) throws RmBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{category.getTitle(), category.getDescription(), userId, categoryId});
        } catch (Exception e) {
            throw new RmBadRequestException("Invalid Request");
        }
    }

    @Override
    public void removeById(Integer userId, Integer categoryId) {
        this.removeAllCategoryTransactions(categoryId);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, new Object[]{userId, categoryId});
    }

    private void removeAllCategoryTransactions(Integer categoryId) {
        jdbcTemplate.update(SQL_DELETE_ALL_TRANSACTION, new Object[]{categoryId});
    }

    private RowMapper<Category> categoryRowMapper = ((rs, rowNum) -> {
        return new Category(rs.getInt("CATEGORY_ID"),
                rs.getInt("USER_ID"),
                rs.getString("TITLE"),
                rs.getString("DESCRIPTION"),
                rs.getDouble("TOTAL_EXPENSE"));
    });
}
