package com.mosofty.qcm.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mosofty.qcm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
