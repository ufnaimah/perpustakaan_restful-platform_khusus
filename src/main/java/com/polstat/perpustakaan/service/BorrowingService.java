package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BorrowingDto;

public interface BorrowingService {
    BorrowingDto borrowBook(Long memberId, Long bookId);
    BorrowingDto returnBook(Long borrowingId);
}