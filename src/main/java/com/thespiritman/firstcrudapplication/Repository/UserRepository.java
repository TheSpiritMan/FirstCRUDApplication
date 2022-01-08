package com.thespiritman.firstcrudapplication.Repository;

import com.thespiritman.firstcrudapplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Long countById(Integer id);
}

