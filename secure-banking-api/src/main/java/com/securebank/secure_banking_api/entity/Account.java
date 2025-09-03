package com.securebank.secure_banking_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id")
    private long id;

    // using the enumerated annotation makes sure that enums are stored as strings in the db
    // account types supported: Checkings and Savings

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    // bigdecimal is optimal for transactions involving money

    @Column(name = "balance")
    private BigDecimal balance;

    // account status supported: Active, Frozen, Closed

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    // manytoone relationships require a joincolumn to specify the foreign key to the related account

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Account() {

    }

    public Account(long id, AccountType type, BigDecimal balance, AccountStatus status, Customer customer) {
        this.id = id;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type=" + type +
                ", balance=" + balance +
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }
}
