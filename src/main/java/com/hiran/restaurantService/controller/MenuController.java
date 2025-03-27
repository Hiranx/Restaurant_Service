package com.hiran.restaurantService.controller;


import com.hiran.restaurantService.dto.MenuItemDTO;
import com.hiran.restaurantService.entity.MenuItem;
import com.hiran.restaurantService.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(
            @PathVariable String restaurantId,
            @RequestBody MenuItemDTO menuItemDTO) {
        return ResponseEntity.ok(menuService.addMenuItem(restaurantId, menuItemDTO));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable String restaurantId,
            @PathVariable String itemId,
            @RequestBody MenuItemDTO menuItemDTO) {
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
}
