package com.prokopovich.usermanagementapp.dao;

import com.prokopovich.usermanagementapp.entity.UserAccount;
import com.prokopovich.usermanagementapp.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    private static final Logger LOGGER = LogManager.getLogger(UserAccountDaoImpl.class);
    private static final String SQL_SELECT_FILTER = "SELECT e FROM UserAccount e " +
            "WHERE e.username LIKE :username AND e.role LIKE :role AND e.status LIKE :status";

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserAccountDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public UserAccount create(UserAccount newUser) throws DaoException {
        LOGGER.trace("create userAccount method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newUser);
            entityManager.getTransaction().commit();
            entityManager.close();
            return newUser;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean update(UserAccount user) throws DaoException {
        LOGGER.trace("update user method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public UserAccount findOne(int id) throws DaoException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(UserAccount.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<UserAccount> findAll() throws DaoException {
        LOGGER.trace("findAll method is executed");
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
    public UserAccount findByUsername(String username) throws DaoException {
        LOGGER.trace("findAllByUsername method is executed");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UserAccount> criteriaQuery = criteriaBuilder.createQuery(UserAccount.class);
            Root<UserAccount> tRoot = criteriaQuery.from(UserAccount.class);
            Predicate predicate = criteriaBuilder.equal(tRoot.get("username"), username);
            criteriaQuery.where(predicate);
            if(entityManager.createQuery(criteriaQuery).getResultList().isEmpty()) {
                return null;
            }
            return entityManager.createQuery(criteriaQuery).getResultList().iterator().next();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Collection<UserAccount> findByUsernameAndRoleAndStatus(String username, String role,
                                                                  String status) throws DaoException {
        LOGGER.info("findByUsernameAndRoleAndStatus method is executed - username = " + username +
                ", role = " + role + ", status = " + status);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery(SQL_SELECT_FILTER);
            query.setParameter("username", "%"+username+"%");
            query.setParameter("role", "%"+role+"%");
            query.setParameter("status", "%"+status+"%");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
