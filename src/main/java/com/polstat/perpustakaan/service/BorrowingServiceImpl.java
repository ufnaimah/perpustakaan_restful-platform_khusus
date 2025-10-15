package com.polstat.perpustakaan.service;

import com.polstat.perpustakaan.dto.BorrowingDto;
import com.polstat.perpustakaan.entity.Book;
import com.polstat.perpustakaan.entity.Borrowing;
import com.polstat.perpustakaan.entity.Member;
import com.polstat.perpustakaan.repository.BookRepository;
import com.polstat.perpustakaan.repository.BorrowingRepository;
import com.polstat.perpustakaan.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class BorrowingServiceImpl implements BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public BorrowingDto borrowBook(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Borrowing borrowing = Borrowing.builder()
                .member(member)
                .book(book)
                .borrowDate(new Date())
                .status(Borrowing.BorrowingStatus.BORROWED)
                .build();

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        return mapToDto(savedBorrowing);
    }

    @Override
    public BorrowingDto returnBook(Long borrowingId) {
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));

        if (borrowing.getStatus() == Borrowing.BorrowingStatus.RETURNED) {
            throw new RuntimeException("Book already returned");
        }

        borrowing.setReturnDate(new Date());
        borrowing.setStatus(Borrowing.BorrowingStatus.RETURNED);

        // Hitung keterlambatan (misal: batas pinjam 7 hari)
        long diffInMillis = Math.abs(borrowing.getReturnDate().getTime() - borrowing.getBorrowDate().getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        if (diffInDays > 7) {
            borrowing.setLateDays((int) (diffInDays - 7));
        } else {
            borrowing.setLateDays(0);
        }

        Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
        return mapToDto(updatedBorrowing);
    }

    private BorrowingDto mapToDto(Borrowing borrowing) {
        // Implementasi DTO mapping jika diperlukan
        // Untuk sederhana, kita bisa kembalikan BorrowingDto dengan data yang relevan
        return new BorrowingDto(
                borrowing.getId(),
                borrowing.getMember().getId(),
                borrowing.getBook().getId(),
                borrowing.getBorrowDate(),
                borrowing.getReturnDate(),
                borrowing.getStatus().toString(),
                borrowing.getLateDays()
        );
    }
}