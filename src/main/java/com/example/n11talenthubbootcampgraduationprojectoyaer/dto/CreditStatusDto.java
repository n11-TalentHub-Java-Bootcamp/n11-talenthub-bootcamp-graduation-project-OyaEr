package com.example.n11talenthubbootcampgraduationprojectoyaer.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditStatusDto {
    private String idNum;
    private LocalDate birthDate;
}
