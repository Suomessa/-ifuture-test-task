package com.server.service;

import com.server.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class AccountServiceImpl implements AccountService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    @Cacheable(value = "amounts")
    public Long getAmount(Integer id) {
        Long amount = 0L;
        try {
            amount = jdbcTemplate.queryForObject("SELECT amount FROM balance_table WHERE balance_id = ?", new Object[]{id}, Long.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        if (amount == null) {
            amount = 0L;
        }
        return amount;
    }

    @Override
    @CachePut(value = "amounts", key = "#id")
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String addAmount(Integer id, Long value) {
        Long amount = 0L;
        String result = "operation completed";
        try {
            amount = jdbcTemplate.queryForObject("SELECT amount FROM balance_table WHERE balance_id = ?", new Object[]{id}, Long.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            result = "error while getting data from db";
        }
        amount = ((amount == null) ? value : amount + value);
        try {
            jdbcTemplate.update("UPDATE balance_table SET amount = ? WHERE balance_id = ?", amount, id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            result = "error while updating data in db";
        }
        return result;
    }


    @CacheEvict(value = "amounts", allEntries = true)
    public void evictCache(Integer id) {

    }
}
