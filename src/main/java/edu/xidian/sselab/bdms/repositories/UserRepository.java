package edu.xidian.sselab.bdms.repositories;

import edu.xidian.sselab.bdms.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
