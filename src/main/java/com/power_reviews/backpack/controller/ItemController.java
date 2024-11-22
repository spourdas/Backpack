package com.power_reviews.backpack.controller;

import com.power_reviews.backpack.common.Response;
import com.power_reviews.backpack.model.Item;
import com.power_reviews.backpack.service.ItemException;
import com.power_reviews.backpack.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("list")
    public List<Item> list() {
        return itemService.list();
    }

    @GetMapping("/fill")
    public List<Response> fillBackPack(@RequestParam("weight")int totalWeight) throws ItemException {

        return itemService.fillBackPack(totalWeight);
    }

}
