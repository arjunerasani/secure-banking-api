package com.securebank.secure_banking_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // bigdecimal is optimal for operations involving money

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    // transaction types supported: withdrawal, transfer, deposit

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    // timestamps should be provided for every transaction

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // manytoone relationships require a joincolumn to specify the foreign key to the related account

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private Account senderAccount;

    @ManyToOne()
    @JoinColumn(name = "recipient_id")
    private Account recipientAccount;

    public Transaction() {

    }

    public Transaction(Long id, BigDecimal amount, String description, TransactionType type, LocalDateTime timestamp,
                       Account senderAccount, Account recipientAccount) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.timestamp = timestamp;
        this.senderAccount = senderAccount;
        this.recipientAccount = recipientAccount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Account recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", senderAccount=" + senderAccount +
                ", recipientAccount=" + recipientAccount +
                '}';
    }
}
