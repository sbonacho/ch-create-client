package com.soprasteria.connector.service;

import com.soprasteria.connector.domain.Response;

public interface MultipleRace<T> {
    public Response<T> run(T payload);
}
