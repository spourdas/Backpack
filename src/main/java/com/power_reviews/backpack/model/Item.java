package com.power_reviews.backpack.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String name;
    private Integer weight;
}
