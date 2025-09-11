package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Category;
import com.learntocodewithluis.spring_boot_demo_store.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        // from products
        Root<Product> root = cq.from(Product.class);

        //dynamic filters or conditions

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // name like %name%
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (min != null) {
            // price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), min));
        }
        if (max != null) {
            // price <= maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), max));
        }

        //select from products where name like '%nameVar%' and price >= min and price <= max
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Product> findProductsByCategory(Byte category_id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        // from products
        Root<Product> root = cq.from(Product.class);

        //dynamic filters or conditions

        List<Predicate> predicates = new ArrayList<>();
        if(category_id != null) {
            predicates.add(cb.equal(root.get("category").get("id"), category_id));
        }
        //select from products where category_id = category_id
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(cq).getResultList();
    }
}
