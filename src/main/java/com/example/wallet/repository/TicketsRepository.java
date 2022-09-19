package com.example.wallet.repository;

import com.example.wallet.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Ticket, Long> {
}
