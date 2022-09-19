package com.example.wallet.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "wallet")
public class Wallet {


    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "id", strategy = "uuid4")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Id
    private UUID id;
    private BigDecimal amount;
    private String email;
    @OneToMany(targetEntity=Ticket.class,cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ticket> tickets_issued=new ArrayList();
}
