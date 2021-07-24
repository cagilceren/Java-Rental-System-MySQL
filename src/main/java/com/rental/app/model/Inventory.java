package com.rental.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.rental.app.Exceptions.InvalidInputException;

@Entity
@EntityListeners(AuditingEntityListener.class)

@Table(name= "inventory", schema = "rental")

public class Inventory {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column
	public String inventoryNo;
	
	@Column
	public String description;
	
	@Column(name="`count`") //The words count and condition are reserved by SQL, this is why, we defined them in ``.
	public Integer count;
	
	@Column(name="`condition`") //The words count and condition are reserved by SQL, this is why, we defined them in ``.
	public Integer condition;
	
	@Column
	public String serialNo;
	
	@Column
	public Integer lendability;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) throws InvalidInputException {
		if(!checkCount(count))
			throw new InvalidInputException("Count should be a positive number!");
		this.count = count;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) throws InvalidInputException {
		if(!checkCondition(condition))
			throw new InvalidInputException("Condition should be number between 1 and 5!");
		this.condition = condition;
	}
	
	private boolean checkCount(Integer count) {
		return (count >= 0);
	}
	
	private boolean checkCondition(Integer condition) {
		return (condition > 0 && condition < 6);
	}


}
