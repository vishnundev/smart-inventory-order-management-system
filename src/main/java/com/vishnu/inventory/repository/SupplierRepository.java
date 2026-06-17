package com.vishnu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.inventory.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
