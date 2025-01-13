package com.example.pantry.Controller;

import com.example.pantry.Model.Item;
import com.example.pantry.Service.ItemService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pantry")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("all")
    public ResponseEntity<List<Item>> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("category")
    public ResponseEntity<List<Item>> getItemsByCategory(@RequestParam(value="category") String category) {
        return itemService.getItemsByCategory(category);
    }

    @GetMapping("name")
    public ResponseEntity<Optional<Item>> getItemByName(@RequestParam(value="name") String name) {
        return itemService.getItemByName(name);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Item>> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateItem(@RequestBody Item item, @PathVariable Long id) {
        return itemService.updateItem(item, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
