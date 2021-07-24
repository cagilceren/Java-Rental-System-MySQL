package com.rental.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rental.app.Exceptions.ResourceNotFoundException;
import com.rental.app.Repository.InventoryRepository;
import com.rental.app.model.Inventory;

@RestController
public class InventoryController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@GetMapping("/inventory")
	public List<Inventory> getAll() {
		return inventoryRepository.findAll();
	}
	
	@GetMapping("/inventory/{id}")
	public Inventory getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + id));
		return inventory;
	}
	
	@PostMapping("/inventory")
	public Inventory create(@Validated @RequestBody Inventory inventory) {
		return inventoryRepository.save(inventory);
	}
	
	@PutMapping("/inventory/{id}")
	public Inventory update(@PathVariable("id") Integer id, @Validated @RequestBody Inventory inventory) throws ResourceNotFoundException {
		Inventory inventoryToUpdate = inventoryRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + id));
		
		inventoryToUpdate.description = inventory.description;
		inventoryToUpdate.count = inventory.count;
		inventoryToUpdate.condition = inventory.condition;
		inventoryToUpdate.serialNo = inventory.serialNo;
		inventoryToUpdate.lendability = inventory.lendability;
		inventoryRepository.save(inventoryToUpdate);
		return inventoryToUpdate;	
	}
	
	@DeleteMapping("/inventory/{id}")
	public void delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Inventory inventory = inventoryRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + id));
		inventoryRepository.delete(inventory);
	}

}
