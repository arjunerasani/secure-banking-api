package com.securebank.secure_banking_api.service;

import com.securebank.secure_banking_api.dao.SecureBankingDAO;
import com.securebank.secure_banking_api.entity.Customer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private SecureBankingDAO secureBankingDAO;

    public CustomUserDetailsService(SecureBankingDAO secureBankingDAO) {
        this.secureBankingDAO = secureBankingDAO;
    }

    // this actually tells spring how to find the username and password of someone who is logging in

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = secureBankingDAO.getCustomerByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // spring security expects a user details object

        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword()) // must be encoded if using a password encoder
                .roles("USER") // can add roles later maybe
                .build();
    }
}
