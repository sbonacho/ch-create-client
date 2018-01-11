/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.soprasteria.seda.examples.insurance.peproxy.cms.domain;

import java.util.UUID;

public class Client {

	private final UUID id;

	private final String name;

    private String address;

    private String interest;

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Client(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}

	public Client(UUID id, String name) {
		this.id = id;
		this.name = name;
	}

    public Client(UUID id, String name, String address, String interest) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.interest = interest;
    }

}
