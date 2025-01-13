package com.example.pantry.Repository;

import com.example.pantry.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    public Optional<Item> findByName(String name);

    public List<Item> findByCategory(String category);
}
