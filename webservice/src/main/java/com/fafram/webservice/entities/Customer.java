package com.fafram.webservice.entities;

import jdk.jfr.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

//implementando a classe
@Entity
@Table(name ="tb_customer") //table para o banco de dados h2
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //definindo a chave primária na tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremento de ids no banco
    private Long id;
    private String name;
    private String email;
    private String cpf;

    @OneToMany()
    @JoinTable(name = "tb_customer_phone",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "phone_id"))
    private Set<Phone> phones = new HashSet<>();

    //construtor sem parametro
    public Customer(){
    }

    //construtor com parametros
    public Customer(Long id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                name.equals(customer.name) &&
                email.equals(customer.email) &&
                cpf.equals(customer.cpf) &&
                phones.equals(customer.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,email,cpf,phones);
    }
}
