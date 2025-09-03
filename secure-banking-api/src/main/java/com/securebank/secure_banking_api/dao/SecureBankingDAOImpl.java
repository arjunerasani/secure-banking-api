package com.securebank.secure_banking_api.dao;

import com.securebank.secure_banking_api.entity.Account;
import com.securebank.secure_banking_api.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class SecureBankingDAOImpl implements SecureBankingDAO {
    private EntityManager entityManager;

    // inject entityManager into the program, done automatically by spring

    @Autowired
    public SecureBankingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCustomer(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        String query = "SELECT c FROM Customer c WHERE c.username=:username";
        Query query1 = entityManager.createQuery(query, Customer.class);
        query1.setParameter("username", username);
        Customer customer = (Customer) query1.getSingleResult();
        return entityManager.find(Customer.class, customer.getId());
    }

    @Override
    public void updateCustomerById(Long customerId, Customer customer) {
        Customer existingCustomer = entityManager.find(Customer.class, customerId);

        // id's are primary keys that shouldn't be changed once set

        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());

            entityManager.merge(existingCustomer);
        }
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        entityManager.remove(entityManager.find(Customer.class, customerId));
    }

    @Override
    public void addAccountToCustomer(Long customerId, Account account) {
        Customer customer = entityManager.find(Customer.class, customerId);

        customer.addAccount(account);
        entityManager.merge(customer);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return entityManager.find(Account.class, accountId);
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        Customer customer = entityManager.find(Customer.class, customerId);

        return customer != null ? customer.getAccounts() : Collections.emptyList();
    }

    @Override
    public void updateAccountById(Long accountId, Account account) {
        Account accountToUpdate = entityManager.find(Account.class, accountId);

        // id's are primary keys that shouldn't be changed once set

        if (accountToUpdate != null) {
            accountToUpdate.setBalance(account.getBalance());
            accountToUpdate.setType(account.getType());
            accountToUpdate.setStatus(account.getStatus());
            accountToUpdate.setCustomer(account.getCustomer());

            entityManager.merge(accountToUpdate);
        }
    }

    @Override
    public void deleteAccountById(Long accountId) {
        entityManager.remove(entityManager.find(Account.class, accountId));
    }
}
