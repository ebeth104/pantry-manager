package com.example.pantry.Controller;

import com.example.pantry.Model.Item;
import com.example.pantry.Service.ItemService;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
public class ItemController {

    public ItemService itemService;

    @GetMapping("all")
    public ResponseEntity<List<Item>> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("name")
    public ResponseEntity<Optional<Item>> getItemByName(@RequestBody Item item) {
        String name = item.getName();
        return itemService.getItemByName(name);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Item>> getItemById(@RequestBody Long id) {
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
