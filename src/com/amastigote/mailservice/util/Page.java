package com.amastigote.mailservice.util;

import java.util.List;

public class Page {
    private String title;
    private String url;
    private List<String> tags;

    public String getTitle() {
        return title;
    }

    public Page setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Page setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Page setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
