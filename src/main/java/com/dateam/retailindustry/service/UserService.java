package com.dateam.retailindustry.service;

import com.dateam.retailindustry.dto.UserReqDTO;
import com.dateam.retailindustry.dto.UserRespDTO;
import com.dateam.retailindustry.entity.User;
import com.dateam.retailindustry.exception.DataNotFoundException;
import com.dateam.retailindustry.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {

        try {
            Optional<User> optionalUser = userRepository.findById(id);
            return optionalUser.get();
        }
        catch (Exception e) {
            throw new DataNotFoundException("User Not Found");
        }
    }

    public UserReqDTO create(UserReqDTO userReqDTO) {
        User user = new User();
        user.setUserName(userReqDTO.getUserName());
        user.setUserBalance(userReqDTO.getUserBalance());

        userRepository.save(user);

        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setUserId(user.getUserId());
        userRespDTO.setUserName(user.getUserName());
        userRespDTO.setUserBalance(user.getUserBalance());

        return userReqDTO;
    }

    public UserReqDTO update(Long id, UserReqDTO userReqDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) throw new DataNotFoundException("Unregistered Data");

        User user = new User();
        user.setUserId(id);
        user.setUserName(userReqDTO.getUserName());
        user.setUserBalance(userReqDTO.getUserBalance());

        UserRespDTO userRespDTO = new UserRespDTO();
        userRespDTO.setUserId(id);
        userRespDTO.setUserName(user.getUserName());
        userRespDTO.setUserBalance(user.getUserBalance());

        userRepository.save(user);
        return userReqDTO;
    }

    public void delete (Long id) {
        userRepository.deleteById(id);
    }

}