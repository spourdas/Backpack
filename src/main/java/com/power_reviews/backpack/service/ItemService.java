package com.power_reviews.backpack.service;

import com.power_reviews.backpack.common.Response;
import com.power_reviews.backpack.model.Item;
import com.power_reviews.backpack.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAllByOrderByWeightDesc();
    }

    public List<Response> fillBackPack(int totalWeight) throws ItemException {
        List<Response> response = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new ItemException("There are no items in the database");
        }
        // Requirement specifies there has to be at least one of each item
        Map<String, Integer> map = new HashMap<>();
        int total = 0;
        for (Item item : items) {
            total += item.getWeight();
            map.put(item.getName(), 1);
        }
        // Make sure the user specify enough weight to cover all items
        if (totalWeight < total) {
            throw new ItemException("Insufficient Weight Limit");
        }
        // Requirements specifies the least number of items picked
        // So, spread the remaining weights between all items starting with the heaviest objects
        int weightLeft = totalWeight - total;
        items.sort((a, b) -> b.getWeight().compareTo(a.getWeight()));
        for (Item item : items) {
            if (item.getWeight() <= weightLeft) {
                int count = weightLeft / item.getWeight();
                map.put(item.getName(), map.getOrDefault(item.getName(), 0)+count);
                weightLeft = weightLeft % item.getWeight();
            }
        }
        // Requirements specifies that all the weights given should be used
        if (weightLeft != 0) {
            throw new ItemException("Unable to use all the weight allowed");
        }
        // Represents the response as a list
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            response.add(new Response(entry.getKey(), entry.getValue()));
        }
        // Sort the least with the most items picked to make it clear on the user interface
        response.sort((a,b)->b.getCount().compareTo(a.getCount()));
        return response;
    }
}
