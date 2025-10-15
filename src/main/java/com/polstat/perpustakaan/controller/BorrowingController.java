package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        BorrowingDto borrowingDto = borrowingService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(borrowingDto);
    }

    @PostMapping("/return/{borrowingId}")
    public ResponseEntity<BorrowingDto> returnBook(@PathVariable Long borrowingId) {
        BorrowingDto borrowingDto = borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok(borrowingDto);
    }
}