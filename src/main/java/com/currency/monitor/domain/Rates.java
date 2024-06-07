package com.currency.monitor.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String no;
    private String effectiveDate;
    private double mid;

    @ToString.Exclude
    @ManyToOne()
    @JoinColumn(name = "currency_id")
    private Currency currency;

}
