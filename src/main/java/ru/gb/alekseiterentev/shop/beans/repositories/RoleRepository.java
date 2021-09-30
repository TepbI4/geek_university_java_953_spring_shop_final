package ru.gb.alekseiterentev.shop.beans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.alekseiterentev.shop.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
