package example.dao;

import example.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAO {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public List<Users> findAll() {
        return entityManager.createQuery("select u from Users u", Users.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Users findById(int id) {
        return entityManager.find(Users.class, id);
    }

    @Transactional
    public void save(Users user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Transactional
    public void update(Users updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Transactional
    public void delete(int id) {
        entityManager.remove(findById(id));
    }
}