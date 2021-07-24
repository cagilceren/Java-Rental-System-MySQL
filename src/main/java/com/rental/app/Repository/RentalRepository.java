package com.rental.app.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rental.app.model.Rental;

@Repository
@Transactional
public interface RentalRepository extends JpaRepository<Rental, Integer>, JpaSpecificationExecutor<Rental>{
	@SuppressWarnings("serial")
	public default List<Rental> findAllByInventoryId (Integer inventoryId, boolean isReturned) {
		return this.findAll(new Specification<Rental>() {
			@Override
			public Predicate toPredicate(Root<Rental> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("inventoryId"), inventoryId)));
				if(isReturned) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.isNull(root.get("returnDate"))));
				} else {
					predicates.add(criteriaBuilder.and(criteriaBuilder.isNotNull(root.get("returnDate"))));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
}
