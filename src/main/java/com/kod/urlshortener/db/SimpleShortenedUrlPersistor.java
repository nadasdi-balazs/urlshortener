package com.kod.urlshortener.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
public class SimpleShortenedUrlPersistor {
    private static final String INSERT_INTO_URL_SHORTENED_TEMPLATE = "INSERT INTO url_shortened (url, shortened) " +
                                                                     " VALUES ('{0}', '{1}')";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertUrlShortenedUrlPair(String url, String shortenedUrl) {
        Object[] params = new Object[]{url, shortenedUrl};
        String sql = MessageFormat.format(INSERT_INTO_URL_SHORTENED_TEMPLATE, params);
        System.out.println("-- insertUrlShortenedUrlPair, sql will be: '" + sql + "'");

        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
    }
}
