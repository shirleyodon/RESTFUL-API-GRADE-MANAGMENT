/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Registre;
import java.util.Calendar;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class RegistreService {
    private final Service service;
    
    public RegistreService(){
        service=new Service("registres");
    }
    
    private String toChaine(Integer d){
        return (d<10)? "0"+d.toString(): d.toString();
    }
    
    private String obtenirHeure(Calendar cal){
        String h=toChaine(cal.get(Calendar.HOUR_OF_DAY)),
               m=toChaine(cal.get(Calendar.MINUTE)),
               s=toChaine(cal.get(Calendar.SECOND));
        return h+":"+m+":"+s;
    }
    
    private String obtenirDate(Calendar cal){
        String dd=toChaine(cal.get(Calendar.DAY_OF_MONTH)),
               mm=toChaine(cal.get(Calendar.MONTH)+1),
               yy=toChaine(cal.get(Calendar.YEAR));
        return dd+"-"+mm+"-"+yy;
    }
    
    private int post(Registre reg){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(reg, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public void saveToLog(String login, String action, String cible){
        Calendar cal=Calendar.getInstance();
        post(new Registre(obtenirDate(cal), obtenirHeure(cal), login, action, cible));
    }
    
    public Registre[] getAll(){
        WebTarget target=service.getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Registre[].class);
    }
    
    public void get(String param){
        WebTarget target=service.getWebTarget();
        target.path(param)
              .request()
              .accept(MediaType.APPLICATION_JSON)
              .get();
    }
    
    public int delete(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .delete(Integer.class);
    }
}
