package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;
    private final EntityManagerFactory entityManager;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory, EntityManagerFactory entityManager) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> allUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User show(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void update(int id, User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        User userToBeUpdated = session.get(User.class, id);

        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }
}
