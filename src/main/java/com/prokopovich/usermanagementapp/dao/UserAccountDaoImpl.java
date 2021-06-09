package com.prokopovich.usermanagementapp.dao;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.exception.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserAccountDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public UserAccount create(UserAccount newUser) {
        return null;
    }

    @Override
    public boolean update(UserAccount user) throws DaoException {
        return false;
    }

    @Override
    public UserAccount findOne(int id) {
        return null;
    }

    @Override
    public Collection<UserAccount> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager
                    .createQuery("select e from UserAccount e")
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<UserAccount> findAllByUsername(String username) throws DaoException {
        return null;
    }
}
