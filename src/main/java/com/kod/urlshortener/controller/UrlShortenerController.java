package com.kod.urlshortener.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class UrlShortenerController {
    @Value("${base.url}")
    private String basePath;

    private MessageDigest hashAlgorithm;

    public UrlShortenerController() {
        try {
            hashAlgorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("COULD NOT INSTANTIATE MD5 HASH. Can't instantiate application without shortening algorithm, exiting...");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/shorten/{url}")
    public String shortenUrl(@PathVariable String url) {
        System.out.println("-- shortenUrl called with url: '" + url + "'");
        byte[] hashed = hashAlgorithm.digest(url.getBytes());
        String shortened = convertToHex(hashed);
        System.out.println("---- length of shortened parth: " + shortened.length());

        String shortenedFullPath = basePath + "/" + shortened;
        System.out.println("-- shortenUrl will return with: '" + shortenedFullPath + "'");
        return shortenedFullPath;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
}
