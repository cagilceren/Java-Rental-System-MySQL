package com.rental.app.model;


import java.util.Date;

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

@Table(name= "rental", schema = "rental")

public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	
	@Column
	public Integer inventoryId;
	
	@Column
	public String name;
	
	@Column
	public String adress;
	
	@Column
	public String email;
	
	@Column
	public Integer deposit;
	
	@Column
	public String phone;
	
	@Column
	public Date borrowDate;
	
	@Column
	public Date dueDate;
	
	@Column
	public Date returnDate;
	
	@Column
	public String comment;
	
	@Column
	public Integer lendingUserId;
	
	@Column
	public Integer receivingUserId;

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) throws InvalidInputException {
		if(!checkDeposit(deposit))
			throw new InvalidInputException("Deposit can not be a negative number!");
		this.deposit = deposit;
	}
	
	private boolean checkDeposit(Integer deposit) {
		return (deposit >= 0);
	}

	
}
