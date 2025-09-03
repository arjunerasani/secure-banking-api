package com.securebank.secure_banking_api.service;

import com.securebank.secure_banking_api.dao.SecureBankingDAO;
import com.securebank.secure_banking_api.entity.Account;
import com.securebank.secure_banking_api.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecureBankingServiceImpl implements SecureBankingService {
    private SecureBankingDAO secureBankingDAO;

    @Autowired
    public SecureBankingServiceImpl(SecureBankingDAO secureBankingDAO) {
        this.secureBankingDAO = secureBankingDAO;
    }

    // use transactional annotation here instead of the in the DAO layer so we can keep transactions at the business
    // logic level instead of the persistence detail level

    @Override
    @Transactional
    public void addCustomer(Customer customer) {
        secureBankingDAO.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return secureBankingDAO.getCustomerById(customerId);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return secureBankingDAO.getCustomerByUsername(username);
    }

    @Override
    @Transactional
    public void updateCustomerById(Long customerId, Customer customer) {
        secureBankingDAO.updateCustomerById(customerId, customer);
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long customerId) {
        secureBankingDAO.deleteCustomerById(customerId);
    }

    @Override
    @Transactional
    public void addAccountToCustomer(Long customerId, Account account) {
        secureBankingDAO.addAccountToCustomer(customerId, account);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return secureBankingDAO.getAccountById(accountId);
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return secureBankingDAO.getAccountsByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void updateAccountById(Long accountId, Account account) {
        secureBankingDAO.updateAccountById(accountId, account);
    }

    @Override
    @Transactional
    public void deleteAccountById(Long accountId) {
        secureBankingDAO.deleteAccountById(accountId);
    }
}
