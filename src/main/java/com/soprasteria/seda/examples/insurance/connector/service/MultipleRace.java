package com.soprasteria.seda.examples.insurance.connector.service;

import com.soprasteria.seda.examples.insurance.connector.domain.Response;

public interface MultipleRace<T> {
    public Response<T> run(T payload);
}
