package com.example.zues.healthok.util;

/**
 * Created by Abhay-Jaiswal on 3/25/2016.
 */
public enum StatusCode {
    UnknownError(-2),
    Error(-1),
    Success(0);

    private final int statusCode;

    StatusCode(int status) {

        statusCode = status;
    }

    public int getStatusCode() {

        return this.statusCode;
    }

}

