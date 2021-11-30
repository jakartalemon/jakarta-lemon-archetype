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
import java.util.List;
import org.bson.Document;

/**
 *
 * @author avbravo
 */
public interface CustomerServices {
    public Boolean save(Customer customer);
    public Boolean update(Customer customer);
    public Boolean delete(Customer customer);
    public List<Customer> findAll(Document...docSort);
}
