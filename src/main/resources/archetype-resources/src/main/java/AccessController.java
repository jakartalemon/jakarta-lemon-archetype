#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${package};
// <editor-fold defaultstate="collapsed" desc=" import()">


import com.avbravo.jmoordb.configuration.JmoordbContext;
import com.avbravo.jmoordbutils.DateUtil;
import com.avbravo.jmoordbutils.JmoordbLanguages;
import com.avbravo.jmoordbutils.JmoordbResourcesFiles;
//import static com.avbravo.jmoordbsecurity.SessionListener.isUserLogged;

import com.avbravo.jmoordbutils.JsfUtil;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;
//import com.avbravo.jmoordbsecurity.*;
// </editor-fold>

/**
 *
 */
@Named
@SessionScoped
public class AccessController implements Serializable {
//public class AccessController implements Serializable  {

    // <editor-fold defaultstate="collapsed" desc="fields">
    private static final long serialVersionUID = 1L;
    
    Boolean loggedIn = false;
    private String notificationsIcons = "notifications_none";
    private Integer notificationsNumber = 0;
    private String username = "";
    private String password = "";
    private String passwordText = "";
    private Boolean haveNotifications = false;
    private Integer segundosInactividad=0;


    // private String path = "/asistencia";
    private Boolean isvalidUsername = false;
    private Boolean isForgetPassword = false;
    private Boolean isValidOtp = false;
    private Boolean isSendOtp = false;
    private Boolean showPassword = false;
    Integer otpRecibido = 0;

    private String email;
    private String newPassword;
    private String repeatPassword;
    private String sex="Masculino";

  

// </editor-fold>
   
  


    // <editor-fold defaultstate="collapsed" desc=" @Inject">

   


    @Inject
    JmoordbLanguages jmoordbLanguages;
    @Inject
    JmoordbResourcesFiles rf;
    
    

    public String getSex() {
        return sex;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" set/get()">
    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSegundosInactividad() {
        segundosInactividad=0;
        try {
            /**
             * Convertir a segundos el tiempo de sesion
             * luego hacer que el idle se muestre los
             * segundos especificados en $secondWarnindDialogInactivate antes de cerrase la sesion
             */
            // (minutos *60) *(1000)
//      segundosInactividad =((sessionTimeout.get() * 60)-secondWarnindDialogInactivate.get())* (1000);


        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
   
        return segundosInactividad;
    }

    public void setSegundosInactividad(Integer segundosInactividad) {
        this.segundosInactividad = segundosInactividad;
    }
    
    
    
    
   
    public String getPasswordText() {
        return passwordText;
    }

    public void setPasswordText(String passwordText) {
        this.passwordText = passwordText;
    }
   

    /**
     * Verifica si tiene notificaciones
     *
     * @return
     */
    public Boolean getHaveNotifications() {
        try{
        
        } catch (Exception e) {
            JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }

        return haveNotifications;
    }

    public String getNotificationsIcons() {
        try {

            if (JmoordbContext.get("notificationsIcons") == null) {

            } else {
                notificationsIcons = (String) JmoordbContext.get("notificationsIcons");

            }
        } catch (Exception e) {
           JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }

        return notificationsIcons;
    }

    public Integer getNotificationsNumber() {
        try {

            if (JmoordbContext.get("notificationsNumber") == null) {

            } else {
                notificationsNumber = (Integer) JmoordbContext.get("notificationsNumber");
            }
        } catch (Exception e) {
          JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return notificationsNumber;
    }

    public void setNotificationsNumber(Integer notificationsNumber) {
        this.notificationsNumber = notificationsNumber;
    }

    public void setNotificationsIcons(String notificationsIcons) {
        this.notificationsIcons = notificationsIcons;
    }

    public void setHaveNotifications(Boolean haveNotifications) {
        this.haveNotifications = haveNotifications;
    }

   

    public Boolean getShowPassword() {
        return showPassword;
    }

    public void setShowPassword(Boolean showPassword) {
        this.showPassword = showPassword;
    }

    public Boolean getIsSendOtp() {
        return isSendOtp;
    }

    public void setIsSendOtp(Boolean isSendOtp) {
        this.isSendOtp = isSendOtp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public Integer getOtpRecibido() {
        return otpRecibido;
    }

    public void setOtpRecibido(Integer otpRecibido) {
        this.otpRecibido = otpRecibido;
    }

    public Boolean getIsValidOtp() {
        return isValidOtp;
    }

    public void setIsValidOtp(Boolean isValidOtp) {
        this.isValidOtp = isValidOtp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsForgetPassword() {
        return isForgetPassword;
    }

    public void setIsForgetPassword(Boolean isForgetPassword) {
        this.isForgetPassword = isForgetPassword;
    }

   

    public Boolean getIsvalidUsername() {
        return isvalidUsername;
    }

    public void setIsvalidUsername(Boolean isvalidUsername) {
        this.isvalidUsername = isvalidUsername;
    }

    public JmoordbLanguages getJmoordbLanguages() {
        return jmoordbLanguages;
    }

    public void setJmoordbLanguages(JmoordbLanguages jmoordbLanguages) {
        this.jmoordbLanguages = jmoordbLanguages;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        if (username == null) {

            username = new String("");
        }

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public JmoordbResourcesFiles getRf() {
        return rf;
    }

    public void setRf(JmoordbResourcesFiles rf) {
        this.rf = rf;
    }

// </editor-fold>
    /**
     * Creates a new instance of AccessController
     */
    public AccessController() {

    }

    // <editor-fold defaultstate="collapsed" desc="init">
    @PostConstruct
    public void init() {
        try {
            sex="Masculino";
            username = "demo";
            password = "demo";
            passwordText = "";
            loggedIn = Boolean.TRUE;
            isvalidUsername = false;
            isForgetPassword = false;
            isValidOtp = false;
            otpRecibido = 0;
            isSendOtp = false;
            //Obtiene del Environment el tiempo de expiración


        } catch (Exception e) {
  JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String goLogin()">
    public String goLogin() {
//        return "/faces/login";
        return "/login";
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" next()">
    /**
     * verifica el username
     *
     * @return
     */
    public String next() {
        try {
            
            isvalidUsername = false;
                isvalidUsername = true;

        } catch (Exception e) {
    JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
           
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="back()">
    /**
     * verifica el username
     *
     * @return
     */
    public String back() {
        try {
            isvalidUsername = false;

        } catch (Exception e) {
   JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String login()">
    public String login() {
        try {
            loggedIn = true;

            if (username == null || password == null) {
                JsfUtil.warningMessage(rf.getAppMessage("login.usernamenotvalid"));
                return "";
            }
            if (password.isEmpty() || password.equals("") || password == null) {
                JsfUtil.successMessage(rf.getAppMessage("warning.passwordisempty"));
                return "";
            }

         
        
        } catch (Exception e) {

        JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return "";
    }

    // </editor-fold>
  

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String logout()">
    public String logout() {
        String path = "applicacion";
        return logout(path + "/faces/login.xhtml?faces-redirect=true");
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String logout(String path)">
    public String logout(String path) {
        Boolean loggedIn = false;
        try {

           

       
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            String url = (path);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect(url);
            return path;
        } catch (Exception e) {
  JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return path;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String loginDirect()">
    public String loginDirect() {
        return "/faces/index.xhtml?faces-redirect=true";
    }
// </editor-fold>

  


    // <editor-fold defaultstate="collapsed" desc=" onProfileChange()">
    public String onProfileChange() {
        try {


        } catch (Exception e) {
JsfUtil.errorMessage(JsfUtil.nameOfMethod() + " "+e.getLocalizedMessage());
        }
        return "";
    }
// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="String expired()">
    public String expired() {
//        isForgetPassword = false;
//        isvalidUsername = false;
//        user = new User();
//        isForgetPassword = false;
//        isvalidUsername = false;
//        return "faces/pages/expired.xhtml?faces-redirect=true";
        return "faces/login.xhtml";
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="int getMaxInactiveInterval()">
    public int getMaxInactiveInterval() {
        int tiempo = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
                .getSession().getMaxInactiveInterval();

        return tiempo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String inicializa()">
    public String inicializa() {

        username = "";
        isForgetPassword = false;
        isvalidUsername = false;
        isForgetPassword = false;
        isvalidUsername = false;
        loggedIn = false;

        return "";
    }
    // </editor-fold>

   
    
    // <editor-fold defaultstate="collapsed" desc="changeForgetPassword()">
    public String changeForgetPassword() {

        isSendOtp = false;
        isValidOtp = false;
        return "";
    }
    // </editor-fold>
   

  

    // <editor-fold defaultstate="collapsed" desc="String help()">
    public String help() {
        return "";
    }
    // </editor-fold>

   

    // <editor-fold defaultstate="collapsed" desc="void handleKeyEvent()">
    public void handleKeyEvent() {

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String verificarIsLogin()">
    public String verificarIsLogin() {

        if (loggedIn) {
            return "/faces/index.xhtml?faces-redirect=true";
        }
        return "";
    }
    // </editor-fold>

  



    
    public void onIdle() {
        System.out.println("--->onIdle() at "+DateUtil.fechaHoraActual());
    }

     public void onActive() {
         System.out.println("--->onActive() at "+DateUtil.fechaHoraActual());
         
     }
     
     public void onTimeout() {
         System.out.println("......desde del onTimeOut..."+ DateUtil.fechaHoraActual());
     logout();
    }
}
