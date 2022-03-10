package com.haufe.test.beer.catalogue.repository;

import com.haufe.test.beer.catalogue.domain.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String userName);
}
