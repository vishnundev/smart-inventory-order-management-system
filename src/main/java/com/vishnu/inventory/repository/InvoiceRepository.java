package com.vishnu.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishnu.inventory.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
