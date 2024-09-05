package com.dp.idgenerator;

/**
 * @author LiWang
 * @description: id生成错误异常
 * @date 2024/9/5 18:00
 */
public class IdGenerationFailureException extends RuntimeException{

    public IdGenerationFailureException(String message) {
        super(message);
    }

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
