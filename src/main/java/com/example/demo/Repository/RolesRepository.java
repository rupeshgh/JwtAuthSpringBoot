package com.example.demo.Repository;

import com.example.demo.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends CrudRepository<Roles,Integer> {

    @Query("select r from Roles r where r.name=:role")
    Roles findByName(@Param("role") String role);
}
