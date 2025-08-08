package com.securebank.secure_banking_api.dao;

import com.securebank.secure_banking_api.entity.Account;
import com.securebank.secure_banking_api.entity.Customer;

import java.util.List;

public interface SecureBankingDAO {
    void addCustomer(Customer customer);

    Customer getCustomerById(Long customerId);

    void updateCustomerById(Long customerId, Customer customer);

    void deleteCustomerById(Long customerId);

    void addAccountToCustomer(Long customerId, Account account);

    Account getAccountById(Long accountId);

    List<Account> getAccountsByCustomerId(Long customerId);

    void updateAccountById(Long accountId, Account account);

    void deleteAccountById(Long accountId);
}
