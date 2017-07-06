package com.amastigote.mailservice.service.util;

import java.util.List;

public class Page {
    private String title;
    private String url;
    private List<String> tags;

    String getTitle() {
        return title;
    }

    public Page setTitle(String title) {
        this.title = title;
        return this;
    }

    String getUrl() {
        return url;
    }

    public Page setUrl(String url) {
        this.url = url;
        return this;
    }

    List<String> getTags() {
        return tags;
    }

    public Page setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
