package com.dp.lod.spider;

public class Document {

    private Html html;
    private String url;

    /*public Document(String url) {
        this.url = url;
        HtmlDownloader downloader = new HtmlDownloader();
        this.html = downloader.downloadHtml(url);
    }*/

    public Document(String url, Html html) {
        this.html = html;
        this.url = url;
    }

}
