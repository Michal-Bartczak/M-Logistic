package pl.coderslab.magazyn.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.entity.ShipmentDimensions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
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

        // Sortowanie
        Expression<Object> orderByCase = cb.selectCase()
                .when(cb.equal(orderRoot.get("status"), OrderStatus.MAGAZYN), 1)
                .when(cb.equal(orderRoot.get("status"), OrderStatus.DOSTAWA), 2)
                .when(cb.equal(orderRoot.get("status"), OrderStatus.DOSTARCZONO), 3)
                .otherwise(4);

        query.orderBy(cb.asc(orderByCase));

        return entityManager.createQuery(query).getResultList();
    }


    @Override
    public Long countOrdersByStatusForCurrentMonth(OrderStatus status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Order> orderRoot = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        // Pobierz bieżący miesiąc i rok
        LocalDate now = LocalDate.now();
        int currentMonth = now.getMonthValue();
        int currentYear = now.getYear();

        predicates.add(cb.equal(cb.function("MONTH", Integer.class, orderRoot.get("creationDate")), currentMonth));
        predicates.add(cb.equal(cb.function("YEAR", Integer.class, orderRoot.get("creationDate")), currentYear));

        if (status != null) {
            predicates.add(cb.equal(orderRoot.get("status"), status));
        }

        query.select(cb.count(orderRoot)).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

}

