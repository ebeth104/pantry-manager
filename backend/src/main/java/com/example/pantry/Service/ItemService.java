package com.example.pantry.Service;

import com.example.pantry.Model.Item;
import com.example.pantry.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ResponseEntity<List<Item>> getAllItems() {
        try {
            return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Item>> getItemById(Long id) {
        try {
            return new ResponseEntity<>(itemRepository.findById(id), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<Optional<Item>> getItemByName(String name) {
        try {
            return new ResponseEntity<>(itemRepository.findByName(name), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<List<Item>> getItemsByCategory(String category) {
        try {
            List<Item> items = itemRepository.findByCategory(category);
            if (items.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addItem(Item item) {
        try {
            itemRepository.save(item);
            return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateItem(Item item, Long id) {
        try {
            Item updatedItem = itemRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            updatedItem.setName(item.getName());
            updatedItem.setQuantity(item.getQuantity());
            updatedItem.setUnit(item.getUnit());

            itemRepository.save(updatedItem);

            return new ResponseEntity<>("Item updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to update item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteItem(Long id) {
        try {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isPresent()) {
                itemRepository.deleteById(id);
                return new ResponseEntity<>("Item deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete item", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
