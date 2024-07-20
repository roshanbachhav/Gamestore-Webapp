package com.adminportal.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.domain.UserBilling;

@Repository
public interface UserBillingRepository extends CrudRepository<UserBilling, Long>{

}
