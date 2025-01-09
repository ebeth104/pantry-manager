package com.example.pantry.Service;

import com.example.pantry.Model.Item;
import com.example.pantry.Repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    public ItemRepository itemRepository;

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
            new ResponseEntity<>(itemRepository.findByName(name), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResponseEntity<String> addItem(Item item) {
        itemRepository.save(item);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateItem(Item item, Long id) {
        Item updatedItem = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        updatedItem.setName(item.getName());
        updatedItem.setQuantity(item.getQuantity());
        updatedItem.setUnit(item.getUnit());
        itemRepository.save(updatedItem);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
        }

    public ResponseEntity<String> deleteItem(Long id) {
        itemRepository.deleteById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
}
