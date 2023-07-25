package com.example.mockmvcintframework.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Accommodation {
    private Long id;
    private Long addressId;
    private Long personId;
    private LocalDate accommodationDate;
    private boolean isSingleOwned;
}
