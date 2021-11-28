#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package};

import com.avbravo.jmoordbutils.JsfUtil;
import com.avbravo.model.Customer;
//import com.avbravo.services.CustomerServices;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author avbravo
 */
@Named(value = "customerController")
@ViewScoped
public class CustomerController implements Serializable{
// <editor-fold defaultstate="collapsed" desc="field ">
private Customer customer = new Customer();
private Customer customerSelected = new Customer();
private Customer customerOld = new Customer();
private List<Customer> customerList = new ArrayList<>();
Boolean newRecord= Boolean.TRUE;
private Integer rowPage =15;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="@Inject ">
@Inject
CustomerServices customerServices;
// </editor-fold>



 public Integer getRowPage() {
        return rowPage;
    }

// <editor-fold defaultstate="collapsed" desc="set/get ">
    public void setRowPage(Integer rowPage) {
        this.rowPage = rowPage;
    }

    public Boolean getNewRecord() {
        return newRecord;
    }
    public Customer getCustomerOld() {
        return customerOld;
    }

    public void setCustomerOld(Customer customerOld) {
        this.customerOld = customerOld;
    }
    
    
    public void setNewRecord(Boolean newRecord) {
        this.newRecord = newRecord;
    }

    public Customer getCustomerSelected() {
        return customerSelected;
    }

    public void setCustomerSelected(Customer customerSelected) {
        this.customerSelected = customerSelected;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


// </editor-fold>
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
    }
    
       // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {
            findAll();
        }catch(Exception ex){
              JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+ex.getLocalizedMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="save() ">
    public String save(){
        try {
            if(customerServices.save(customerSelected)){
                JsfUtil.successMessage("Guardao");
            }else{
                JsfUtil.warningMessage("No se pudo guardar");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="update() ">
    public String update(){
        try {
            if(customerServices.update(customerSelected)){
                JsfUtil.successMessage("Guardar");
            }else{
                JsfUtil.warningMessage("No se pudo Actualizar");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="delete() ">
    public String delete(){
        try {
            if(customerServices.delete(customerSelected)){
                JsfUtil.successMessage("Eliminado");
            }else{
                JsfUtil.warningMessage("No se pudo eliminar");
            }
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
  
    // <editor-fold defaultstate="collapsed" desc="String findAll()">
    public String findAll(){
        try {
            customerList = customerServices.findAll();
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    
      // <editor-fold defaultstate="collapsed" desc="String prepareDialog(Boolean newRecord)">
    public String prepareDialog(Boolean newRecord) {
        this.newRecord = newRecord;
        if (newRecord) {
            customerSelected = new Customer();
            
            
        } else {
            JsfUtil.copyBeans(customerOld, customerSelected);
        }

        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="closeDialogEdit() ">
    public String closeDialogEdit(){
        try {
           
               PrimeFaces.current().executeScript("PF('widgetVarEditDialog').hide()");

        } catch (Exception e) {
           JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="closeAddtDialog() ">
    public String closeDialogAdd(){
        try {
           
               PrimeFaces.current().executeScript("PF('widgetVarAddDialog').hide()");

        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " : "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>
    
}
