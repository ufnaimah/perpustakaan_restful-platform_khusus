package com.polstat.perpustakaan.controller;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.service.BorrowingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Borrowing", description = "Endpoint untuk Peminjaman dan Pengembalian Buku")
@SecurityRequirement(name = "bearerAuth") // Menerapkan skema keamanan JWT ke semua endpoint di controller ini
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @Operation(summary = "Meminjam sebuah buku", description = "Membuat catatan peminjaman baru berdasarkan ID member dan ID buku.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Peminjaman berhasil"),
            @ApiResponse(responseCode = "404", description = "Member atau Buku tidak ditemukan")
    })
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingDto> borrowBook(
            @Parameter(description = "ID unik dari member yang meminjam") @RequestParam Long memberId,
            @Parameter(description = "ID unik dari buku yang akan dipinjam") @RequestParam Long bookId) {
        BorrowingDto borrowingDto = borrowingService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(borrowingDto);
    }

    @Operation(summary = "Mengembalikan sebuah buku", description = "Memperbarui catatan peminjaman menjadi 'dikembalikan'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pengembalian berhasil"),
            @ApiResponse(responseCode = "404", description = "Catatan peminjaman tidak ditemukan")
    })
    @PostMapping("/return/{borrowingId}")
    public ResponseEntity<BorrowingDto> returnBook(
            @Parameter(description = "ID unik dari transaksi peminjaman") @PathVariable Long borrowingId) {
        BorrowingDto borrowingDto = borrowingService.returnBook(borrowingId);
        return ResponseEntity.ok(borrowingDto);
    }
}