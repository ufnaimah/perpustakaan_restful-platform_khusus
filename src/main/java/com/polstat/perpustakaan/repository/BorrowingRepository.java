package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}