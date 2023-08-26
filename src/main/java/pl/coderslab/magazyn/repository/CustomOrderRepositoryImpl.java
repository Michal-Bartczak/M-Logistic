package pl.coderslab.magazyn.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.entity.ShipmentDimensions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository {
    @PersistenceContext
  private EntityManager entityManager;

    @Override
    public List<Order> orderFilter(FilterOrderDTO filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        // Filter by trackingNumber
        if (filter.getFilterText() != null && !filter.getFilterText().matches(".*[a-zA-Z]+.*")) {
            predicates.add(cb.like(orderRoot.get("trackingNumber"), "%" + filter.getFilterText() + "%"));
        }

        // Filter by date
        if (filter.getFilterData() != null) {
            predicates.add(cb.equal(orderRoot.get("creationDate"), filter.getFilterData()));
        }

        // Filter by status
        if (!"WSZYSTKIE".equals(filter.getStatus())) {
            OrderStatus status = OrderStatus.valueOf(filter.getStatus());
            predicates.add(cb.equal(orderRoot.get("status"), status));
        }

        // Filter by kind
        if (filter.getKindEur() == ShipmentDimensions.EUR && filter.getKindHp() != ShipmentDimensions.HP) {
            predicates.add(cb.equal(orderRoot.get("dimensions"), ShipmentDimensions.EUR));
        } else if (filter.getKindEur() != ShipmentDimensions.EUR && filter.getKindHp() == ShipmentDimensions.HP) {
            predicates.add(cb.equal(orderRoot.get("dimensions"), ShipmentDimensions.HP));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
