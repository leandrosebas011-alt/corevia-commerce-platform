package com.portfolio.comercial.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos", schema = "portfolio_web")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "categoria_id")
    private Long categoriaId;

    @Column(name = "sku", length = 40)
    private String sku;

    @Column(name = "nome", length = 150)
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "preco", precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(name = "estoque_atual")
    private Integer estoqueAtual;

    @Column(name = "ativo")
    private Boolean ativo = true;
}
