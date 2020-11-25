package com.fafram.webservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="tb_phone") //table para o banco de dados h2

public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //definindo a chave primária na tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremento de ids no banco
    private Long id;
    private String type;
    private Integer number;

    @JsonIgnore
    @ManyToOne
    private Customer customer;
    //private Set<Customer> customer = new HashSet<>();

    //construtor sem parametros
    public Phone() {
    }

    //construtor com parametros
    public Phone(Long id, String type, Integer number) {
        this.id = id;
        this.type = type;
        this.number = number;

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id.equals(phone.id) &&
                type.equals(phone.type) &&
                number.equals(phone.number) &&
                customer.equals(phone.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
