package com.currency.monitor.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code_key;
    private String table_value;

    @ToString.Exclude
    @OneToMany(mappedBy = "currency")
    private List<Rates> rates = new ArrayList<>();
}
