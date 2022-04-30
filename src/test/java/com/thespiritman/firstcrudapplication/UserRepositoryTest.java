package com.thespiritman.firstcrudapplication;

import com.thespiritman.firstcrudapplication.Entity.User;
import com.thespiritman.firstcrudapplication.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("boafafc@gmail.com");
        user.setPassword("CCCCCCCCCC");
        user.setFirstname("TTTTTTTTTTT");
        user.setLastname("CCCCCCCCCCCCCCCC");

        User savingUser = userRepository.save(user);
        Assertions.assertThat(savingUser).isNotNull();
        Assertions.assertThat(savingUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdateUser(){
        Integer userID = 1;
        Optional<User> optionalUser = userRepository.findById(userID);
        User user = optionalUser.get();
        user.setPassword("HeheBoyyyafa");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userID).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("HeheBoyyy");
    }

    @Test
    public void testGet(){
        Integer userID = 5;
        Optional<User> optionalUser = userRepository.findById(userID);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userID = 1;
        userRepository.deleteById(userID);

        Optional<User> optionalUser = userRepository.findById(userID);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
