package com.soprasteria.peproxy.cms;

import com.soprasteria.peproxy.cms.domain.Item;

import java.util.List;

public interface ItemCms {

	List<Item> findAll();

	Item findOne(Long id);

}
