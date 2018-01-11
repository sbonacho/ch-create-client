package com.soprasteria.seda.examples.insurance.connector.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Message<T> {
    /**
     *
     */
    private T content;
    private UUID uuid;

    public Message(UUID uuid, T content) {
        this.uuid = uuid == null ? UUID.randomUUID() : uuid;
        this.content = content;
    }

    public Message(T content) {
        this.uuid = UUID.randomUUID();
        this.content = content;
    }

    public boolean isRelated(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message<?> message = (Message<?>) o;

        return uuid.equals(message.uuid);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid( UUID uuid) {
        this.uuid = uuid;
    }

}