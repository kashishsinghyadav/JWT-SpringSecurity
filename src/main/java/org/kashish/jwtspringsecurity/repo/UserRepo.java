package org.kashish.jwtspringsecurity.repo;

import org.kashish.jwtspringsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
        User findByUsername(String username);
}
