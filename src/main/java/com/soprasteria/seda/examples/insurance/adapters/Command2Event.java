package com.soprasteria.seda.examples.insurance.adapters;

import com.soprasteria.seda.examples.insurance.api.model.CreateClient;
import com.soprasteria.seda.examples.insurance.events.ClientCreated;

public class Command2Event {

    /**
     * Add some transformation between commands and events here.
     * @param command
     * @return Generated Event
     */
    public static ClientCreated CreateClient2Event(CreateClient command){
        ClientCreated event = new ClientCreated();
        event.setAddress(command.getAddress());
        event.setInterest(command.getInterest());
        event.setName(command.getName());
        return event;
    }
}
