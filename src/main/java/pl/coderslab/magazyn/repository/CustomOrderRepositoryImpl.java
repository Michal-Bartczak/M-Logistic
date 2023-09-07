package pl.coderslab.magazyn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import pl.coderslab.magazyn.dto.FilterOrderDTO;
import pl.coderslab.magazyn.entity.Order;
import pl.coderslab.magazyn.entity.OrderStatus;
import pl.coderslab.magazyn.entity.ShipmentDimensions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public Page<Order> orderFilterWithPagination(FilterOrderDTO filter, Pageable pageable) {
        System.out.println("Pageable Page: " + pageable.getPageNumber());
        System.out.println("Pageable Size: " + pageable.getPageSize());

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        System.out.println("dodanie tracking");
        // Filter by trackingNumber
        if (filter.getFilterText() != null && !filter.getFilterText().matches(".*[a-zA-Z]+.*")) {
            predicates.add(cb.like(orderRoot.get("trackingNumber"), "%" + filter.getFilterText() + "%"));
        }
        System.out.println("dodanie daty");
        // Filter by date
        if (filter.getFilterData() != null) {
            predicates.add(cb.equal(orderRoot.get("creationDate"), filter.getFilterData()));
        }
        System.out.println("dodanie status");
        // Filter by status
        if (!"WSZYSTKIE".equals(filter.getStatus())) {
            OrderStatus status = OrderStatus.valueOf(filter.getStatus());
            predicates.add(cb.equal(orderRoot.get("status"), status));
        }
        System.out.println("dodanie rodzaj");
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

        TypedQuery<Order> typedQuery = entityManager.createQuery(query);


        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Order> orderList = typedQuery.getResultList();
        Long count = 0L;  // inicjalizacja przed blokiem try-catch

        System.out.println("Wykonanie zapytania countQuery...");
        try {
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);

            // Utworzenie nowego Root dla countQuery
            Root<Order> countRoot = countQuery.from(Order.class);

            countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
            count = entityManager.createQuery(countQuery).getSingleResult();  // przypisanie wartości w bloku try
            System.out.println("Wynik zapytania countQuery: " + count);
        } catch (Exception e) {
            System.out.println("Błąd podczas wykonania zapytania countQuery: " + e.getMessage());
            e.printStackTrace();
        }

        return new PageImpl<>(orderList, pageable, count);
    }

    @Override
    public Long countOrdersByStatusForCurrentMonth(OrderStatus status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Order> orderRoot = query.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

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

