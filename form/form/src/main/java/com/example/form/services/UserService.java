package com.example.form.services;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.form.allExceptions.DataNotFoundExceptions;
import com.example.form.model.UserData;
import com.example.form.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
  
    public UserData saveFormdata(UserData userdata) {
        log.info("Data is saved");
        return userRepository.save(userdata);
    }

    
    public List<UserData> getAllFormData() {
        log.info("Data is fetched");
        return userRepository.findAll();
    }

    @Cacheable(value="Form",key="#id")
    public UserData getByID(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundExceptions("Data not found with id: " + id));
    }

    @CachePut(value="Form",key="#id")
    public UserData updateUserData(Long id, UserData userData) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userData.getName());
                    user.setEmail(userData.getEmail());
                    user.setMobilenumber(userData.getMobilenumber());
                    user.setAddress(userData.getAddress());
                    log.info("Data is updated");
                    return userRepository.save(user);
                }).orElseThrow(() -> new DataNotFoundExceptions("Data not found with id: " + id));

    }

    public void deleteUserData(Long id) {
        log.info("Data is deleted");
        userRepository.deleteById(id);
    }

}
