package com.example.springapp.repositories;

import com.example.springapp.data.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product findByName(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session
                    .createQuery("select product from Product product where product.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> product = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void save(Product product) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateNameById(Long id, String newName) {
//            HQL Example:
//            session.createQuery("update User u set u.name = :name where u.id = :id")
//                    .setParameter("name", newName)
//                    .setParameter("id", id)
//                    .executeUpdate();
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            product.setName(newName);
            session.getTransaction().commit();
        }
    }

    @Override
    public void testCaching() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.getTransaction().commit();
        }
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.get(Product.class, 1L);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delProduct(Long id) {
        Product product = findById(id);
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }
}
