package com.power_reviews.backpack.controller;

import com.power_reviews.backpack.common.Response;
import com.power_reviews.backpack.model.Item;
import com.power_reviews.backpack.service.ItemException;
import com.power_reviews.backpack.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class ViewController {
    private final ItemService itemService;

    public ViewController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping (value="/")
    public String welcome(Model model) {
        List<Item> itemsList = itemService.list();
        model.addAttribute("items", itemsList);
        return "home";
    }

    @PostMapping (value="/fill")
    public String fillBackPack(@RequestParam Integer totalWeight, Model model) {
        try {
            List<Item> itemsList = itemService.list();
            model.addAttribute("items", itemsList);
            model.addAttribute("totalWeight", totalWeight);

            List<Response> responseList = itemService.fillBackPack(totalWeight);
            model.addAttribute("results", responseList);

        } catch (ItemException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "home";
    }
}
