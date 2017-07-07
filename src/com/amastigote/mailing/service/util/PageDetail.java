package com.amastigote.mailing.service.util;

import java.util.List;

public class PageDetail {
    private String title;
    private String url;
    private List<String> tags;

    String getTitle() {
        return title;
    }

    public PageDetail setTitle(String title) {
        this.title = title;
        return this;
    }

    String getUrl() {
        return url;
    }

    public PageDetail setUrl(String url) {
        this.url = url;
        return this;
    }

    List<String> getTags() {
        return tags;
    }

    public PageDetail setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
}
