package com.rental.app.Controller;

import java.util.Date;
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

import com.rental.app.Exceptions.InvalidInputException;
import com.rental.app.Exceptions.ResourceNotFoundException;
import com.rental.app.Repository.InventoryRepository;
import com.rental.app.Repository.RentalRepository;
import com.rental.app.model.Inventory;
import com.rental.app.model.Rental;


@RestController
public class RentalController {
	
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private RentalRepository rentalRepository;
	

	@GetMapping("/inventory/{id}/rental")
	public List<Rental> getAllRentalByInventoryId(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		return rentalRepository.findAllByInventoryId(id, false);
	}
	
	@GetMapping("/rental")
	public List<Rental> getAll() {
		return rentalRepository.findAll();
	}
	
	@GetMapping("/rental/{id}")
	public Rental getById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Rental rental = rentalRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no rental with the id of " + id));
		return rental;
	}
	
	@PostMapping("/rental")
	public Rental create(@Validated @RequestBody Rental rental) throws ResourceNotFoundException, InvalidInputException {
		Inventory inventory = inventoryRepository
				.findById(rental.inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + rental.inventoryId));
		if (inventory.lendability != 1) {
			throw new InvalidInputException("The inventory with the ID of " + inventory.id + " is not lendable!");
		}
		rental.borrowDate = new Date();
		rental.returnDate = null;
		rental.lendingUserId = 1; //TODO Authorization.
		rental.receivingUserId = null;
		rental.setDeposit(rental.deposit);
		inventory.count -= 1;
		inventoryRepository.save(inventory);
		return(rentalRepository.save(rental));		
	}
	
	@PutMapping("/rental/{id}")
	public Rental update(@PathVariable("id") Integer id, @Validated @RequestBody Rental rental) throws ResourceNotFoundException, InvalidInputException {	
		Rental rentalToUpdate = rentalRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no rental with the id of " + id));
		Inventory inventory = inventoryRepository
				 .findById(rentalToUpdate.inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + rentalToUpdate.inventoryId));
		rentalToUpdate.name = rental.name;
		rentalToUpdate.adress = rental.adress;
		rentalToUpdate.email = rental.email;
		rentalToUpdate.setDeposit(rental.deposit);
		rentalToUpdate.phone = rental.phone;
		rentalToUpdate.dueDate = rental.dueDate;
		rentalToUpdate.comment = rental.comment;
		if(rental.returnDate != null) {
			inventory.count += 1;
			inventoryRepository.save(inventory);
		}
		
		return rentalRepository.save(rentalToUpdate);	
	}
	
	@DeleteMapping("/rental/{id}")
	public void delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
		Rental rental = rentalRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("There is no rental with the id of " + id));
		Inventory inventory = inventoryRepository
				.findById(rental.inventoryId)
				.orElseThrow(() -> new ResourceNotFoundException("There is no inventory with the id of " + rental.inventoryId));
		rentalRepository.delete(rental);
		inventory.count += 1;
		inventoryRepository.save(inventory);
	}

}
