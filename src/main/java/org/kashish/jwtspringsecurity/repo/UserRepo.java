package org.kashish.jwtspringsecurity.repo;

import org.kashish.jwtspringsecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
        Users findByUsername(String username);
}
