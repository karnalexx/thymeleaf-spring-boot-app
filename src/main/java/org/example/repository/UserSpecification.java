package org.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class UserSpecification {

	private User user;

	public UserSpecification(User user) {
		this.user = user;
	}

	public Specification<User> matchUserFields() {
		return new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Predicate matchUserName = builder.equal(root.get("username"), user.getUsername());
				Predicate matchPassword = builder.equal(root.get("password"), user.getPassword());
				Predicate matchEmail = builder.equal(root.get("email"), user.getEmail());
				Predicate matchFirstName = builder.equal(root.get("firstName"), user.getFirstName());
				Predicate matchLastName = builder.equal(root.get("lastName"), user.getLastName());
				Predicate matchRole = builder.equal(root.get("role"), user.getRole());
				
				List<Predicate> predicates = new ArrayList<>();
				if (!StringUtils.isEmpty(user.getUsername())){
					predicates.add(matchUserName);				
				} 
				if (!StringUtils.isEmpty(user.getPassword())){
					predicates.add(matchPassword);
				}
				if (!StringUtils.isEmpty(user.getEmail())){
					predicates.add(matchEmail);
				}
				if (!StringUtils.isEmpty(user.getFirstName())){
					predicates.add(matchFirstName);
				}
				if (!StringUtils.isEmpty(user.getLastName())){
					predicates.add(matchLastName);
				}
				if (!StringUtils.isEmpty(user.getRole())){
					predicates.add(matchRole);
				}
				if (predicates.isEmpty()) {
					return builder.or(matchUserName, matchPassword, matchEmail, matchFirstName, matchLastName, matchRole);
				} else {
					return builder.and(predicates.toArray(new Predicate[predicates.size()]));				
				}
			}			
		};
	}

}
