package ru.gb.alekseiterentev.shop.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.alekseiterentev.shop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
