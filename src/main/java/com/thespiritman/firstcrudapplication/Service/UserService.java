package com.thespiritman.firstcrudapplication.Service;

import com.thespiritman.firstcrudapplication.CustomException.UserNotFoundException;
import com.thespiritman.firstcrudapplication.Entity.User;
import com.thespiritman.firstcrudapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public User get(Integer id) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent())
            return  optionalUser.get();
        throw  new UserNotFoundException("Could not find any user bt ID:" + id);
    }

    public void  delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0)
            throw  new UserNotFoundException("Could not find any user bt ID:" + id);
        userRepository.deleteById(id);
    }
}
