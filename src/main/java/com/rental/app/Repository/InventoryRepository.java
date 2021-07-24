package com.rental.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.app.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{}
