/*
 * Copyright 2012-2017 the original author or authors.
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

package com.soprasteria.peproxy.api;

import com.soprasteria.connector.domain.Response;
import com.soprasteria.connector.service.MultipleRace;
import com.soprasteria.peproxy.cms.domain.Item;
import com.soprasteria.peproxy.cms.ItemCms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/items")
@ExposesResourceFor(Item.class)
public class ItemController {


	private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

	private final ItemCms repository;
	private final MultipleRace<List> race;
	private final EntityLinks entityLinks;

	public ItemController(ItemCms repository, EntityLinks entityLinks, MultipleRace<List> race) {
		this.repository = repository;
		this.entityLinks = entityLinks;
		this.race = race;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<Resources<Item>> showItems(@RequestParam(required=false) Long id) {

		//False call to CMS
		Response<List> response = race.run(this.repository.findAll());

		Resources<Item> resources = new Resources<>(response.getContent());
		resources.add(this.entityLinks.linkToCollectionResource(Item.class));
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	HttpEntity<Resource<Item>> showItem(@PathVariable Long id, @RequestParam(required=false) Long idPerson) {
		Resource<Item> resource = new Resource<>(this.repository.findOne(id));
		resource.add(this.entityLinks.linkToSingleResource(Item.class, id));
		return new ResponseEntity<>(resource, HttpStatus.OK);
	}

}
