package com.example.CrudAppSecurBoor.service;
//
//import org.example.crud.dao.UserDAO;
//import org.example.crud.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//
//    private final UserDAO userDAO;
//
//    public UserServiceImpl(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    public List<User> index() {
//        return userDAO.index();
//    }
//
//    @Override
//    public User show(int id) {
//        return userDAO.show(id);
//    }
//
//    @Override
//    public void save(User user) {
//        userDAO.save(user);
//    }
//
//    @Override
//    public void update(int id, User updatedUser) {
//        userDAO.update(id, updatedUser);
//    }
//
//    @Override
//    public void delete(int id) {
//        userDAO.delete(id);
//    }
//}



import com.example.CrudAppSecurBoor.models.User;
import com.example.CrudAppSecurBoor.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    public UserServiceImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    public User show(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
    userRepository.save(user);
    }

    @Override
    public void delete(int id) {
    userRepository.deleteById(id);
    }

    @Override
    public void update(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }
}