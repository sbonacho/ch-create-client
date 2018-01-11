package com.soprasteria.seda.examples.insurance.connector.producer;

import org.springframework.util.concurrent.ListenableFuture;

public interface Sender<P> {
    ListenableFuture send(P payload);
}
