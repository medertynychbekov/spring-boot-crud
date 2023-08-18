package com.example.springbootcrud.service.impl;

import com.example.springbootcrud.exception.UserNotFoundException;
import com.example.springbootcrud.model.User;
import com.example.springbootcrud.repository.UserRepository;
import com.example.springbootcrud.service.ServiceLayer;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements ServiceLayer<User> {

    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->
                new UserNotFoundException(String.format("User with id=%d not found!!!", id)));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(Long id, User user) {
        User oldUser = findById(id);
        oldUser.setName(user.getName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setAge(user.getAge());

        userRepository.save(user);

        return oldUser;
    }

    @Override
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "User successfully deleted!!!";
    }
}
