package com.soprasteria.seda.examples.insurance.events;

import java.util.UUID;

public class ClientCreated extends AbstractEvent {

    public ClientCreated() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public UUID getId() {
        return id;
    }

    private final UUID id;
    private String name;
    private String address;
    private String interest;

}
