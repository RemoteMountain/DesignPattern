package com.dp.virtualWallet.mvc.repository;

/**
 * @Description :   //描述
 * @Author : Liwang  //作者
 * @Date: 2020-03-19 20:31  //时间
 */
public enum Status {

    TO_BE_EXECUTED(""), CLOSED(""), FAILED(""), EXECUTED("");

    private String status;


    Status(String status) {
    }
}
