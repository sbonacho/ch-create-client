package com.soprasteria.connector.domain;

public class Response<T> {
    private final T content;

    public Response(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
