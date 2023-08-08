package pl.coderslab.magazyn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.magazyn.entity.CustomerDetails;
import pl.coderslab.magazyn.repository.CustomerDetailsRepository;

@Service
public class CustomerDetailsService {
    private final CustomerDetailsRepository customerDetailsRepository;

    @Autowired
    public CustomerDetailsService(CustomerDetailsRepository customerDetailsRepository){
        this.customerDetailsRepository=customerDetailsRepository;
    }
}
