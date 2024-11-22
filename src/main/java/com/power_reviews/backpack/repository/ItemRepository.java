package com.power_reviews.backpack.repository;

import com.power_reviews.backpack.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findAllByOrderByWeightDesc();
}
