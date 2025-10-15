package com.polstat.perpustakaan.repository;

import com.polstat.perpustakaan.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // cari berdasarkan judul
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // cari berdasarkan penulis
    List<Book> findByAuthorContainingIgnoreCase(String keyword);
}
