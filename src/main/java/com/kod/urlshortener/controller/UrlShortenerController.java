package com.kod.urlshortener.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerController {
    @Value("${base.url}")
    private String basePath;


    @GetMapping("/shorten/{url}")
    public String shortenUrl(@PathVariable String url) {
        System.out.println("-- shortenUrl called with url: '" + url + "'");
        String shortened = basePath + "/" + url;
        System.out.println("-- shortenUrl will return with: '" + shortened + "'");
        return shortened;
    }
}
