package com.example.wallet.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String origin;
    private String destination;
    private BigDecimal fare=new BigDecimal(1);

}
