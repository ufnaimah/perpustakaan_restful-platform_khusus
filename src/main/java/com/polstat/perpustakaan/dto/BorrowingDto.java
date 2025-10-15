package com.polstat.perpustakaan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class BorrowingDto {
    private Long id;
    private Long memberId;
    private Long bookId;
    private Date borrowDate;
    private Date returnDate;
    private String status;
    private int lateDays;
}