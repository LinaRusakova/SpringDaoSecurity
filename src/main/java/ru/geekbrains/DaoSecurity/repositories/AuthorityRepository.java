package ru.geekbrains.DaoSecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.DaoSecurity.entities.Authority;
import ru.geekbrains.DaoSecurity.entities.Role;

@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
