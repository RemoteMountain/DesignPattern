package com.dp.lod.spider;

public class HtmlRequest {

    private String url;
    private String content;

    public HtmlRequest(String url) {
        this.url = url;
    }

    public HtmlRequest(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }


}
