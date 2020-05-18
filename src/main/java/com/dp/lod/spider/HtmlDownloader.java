package com.dp.lod.spider;

public class HtmlDownloader {

    private NetworkTransporter transporter;

    //通过构造函数或IOC注入
    public Html downloadHtml(String url) {
        HtmlRequest htmlRequest = new HtmlRequest(url);
        Byte[] rawHtml = transporter.send(htmlRequest.getUrl(), htmlRequest.getContent().getBytes());
        return new Html(rawHtml);
    }
}
