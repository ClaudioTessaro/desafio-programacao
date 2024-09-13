package com.br.magalu.desafio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_id_gen")
    @SequenceGenerator(name = "purchase_id_gen", sequenceName = "purchase_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,  cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "purchaser_id", nullable = false)
    private Purchaser purchaser;

    @Column(name = "item_description", nullable = false)
    private String itemDescription;

    @Column(name = "item_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal itemPrice;

    @Column(name = "purchase_count", nullable = false)
    private Integer purchaseCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

}