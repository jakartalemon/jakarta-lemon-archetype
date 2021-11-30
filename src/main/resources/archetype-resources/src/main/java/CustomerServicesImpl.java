#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package};

import com.avbravo.model.Customer;
import com.avbravo.repositories.CustomerRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
@Stateless
public class CustomerServicesImpl implements CustomerServices {

    // <editor-fold defaultstate="collapsed" desc="@Inject ">    
    @Inject
    CustomerRepository customerRepository;
// </editor-fold>
    
    @Override
    public Boolean save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Boolean update(Customer customer) {
        return customerRepository.update(customer);
    }

    @Override
    public Boolean delete(Customer customer) {
        return customerRepository.delete(customer);
    }

    @Override
    public List<Customer> findAll(Document...docSort) {
        return customerRepository.findAll(docSort);
    }
    
}
