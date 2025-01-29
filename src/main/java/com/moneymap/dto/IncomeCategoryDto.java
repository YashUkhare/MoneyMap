package com.moneymap.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeCategoryDto {
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
}