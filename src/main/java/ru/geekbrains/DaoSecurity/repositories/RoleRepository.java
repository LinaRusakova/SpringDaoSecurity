package ru.geekbrains.DaoSecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.DaoSecurity.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
