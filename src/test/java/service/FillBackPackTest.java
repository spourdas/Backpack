package service;

import com.power_reviews.backpack.common.Response;
import com.power_reviews.backpack.model.Item;
import com.power_reviews.backpack.repository.ItemRepository;
import com.power_reviews.backpack.service.ItemException;
import com.power_reviews.backpack.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class FillBackPackTest {
    @Mock
    private ItemRepository repository;

    @InjectMocks
    private ItemService itemService;

    @Test
    public void testFillBackpack() throws ItemException {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Bag of Apples", 5));
        items.add(new Item("Bread", 1));
        items.add(new Item("Peanut Butter", 2));
        items.add(new Item("Trail Mix", 3));
        Mockito.when(repository.findAll()).thenReturn(items);
        List<Response> result = itemService.fillBackPack(27);
        Assertions.assertEquals(4, result.size());
        Map<String, Integer> map = new HashMap<>();
        for (Response response : result) {
            map.put(response.getItem(), response.getCount());
        }
        Assertions.assertEquals(4, map.get("Bag of Apples"));
        Assertions.assertEquals(2, map.get("Bread"));
        Assertions.assertEquals(1, map.get("Peanut Butter"));
        Assertions.assertEquals(1, map.get("Trail Mix"));
    }
}
