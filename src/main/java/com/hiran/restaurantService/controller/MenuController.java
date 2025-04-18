package com.hiran.restaurantService.controller;


import com.hiran.restaurantService.dto.MenuItemDTO;
import com.hiran.restaurantService.entity.MenuItem;
import com.hiran.restaurantService.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(
            @PathVariable String restaurantId,
            @RequestPart("menuItem") MenuItemDTO menuItemDTO) throws IOException {

        return ResponseEntity.ok(menuService.addMenuItem(restaurantId, menuItemDTO));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable String restaurantId,
            @PathVariable String itemId,
            @RequestPart("menuItem") MenuItemDTO menuItemDTO) throws IOException {

        return ResponseEntity.ok(menuService.updateMenuItem(restaurantId, itemId, menuItemDTO));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(
            @PathVariable String restaurantId,
            @PathVariable String itemId) {
        menuService.deleteMenuItem(restaurantId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MenuItem>> getMenuItems(
            @PathVariable String restaurantId) {
        return ResponseEntity.ok(menuService.getMenuItems(restaurantId));
    }

    @PatchMapping("/{itemId}/availability")
    public ResponseEntity<MenuItem> toggleItemAvailability(
            @PathVariable String restaurantId,
            @PathVariable String itemId) {
        try {
            MenuItem updatedItem = menuService.toggleItemAvailability(restaurantId, itemId);
            return ResponseEntity.ok(updatedItem);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
