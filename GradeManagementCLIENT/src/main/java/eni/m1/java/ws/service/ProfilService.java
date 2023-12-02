/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Utilisateur;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class ProfilService {
    private final Service service;
    
    public ProfilService(){
        service=new Service("utilisateurs");
    }
    
    public int post(Utilisateur user){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(user, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public Utilisateur[] getAll(){
        WebTarget target=service.getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Utilisateur[].class);
    }
    
    public Utilisateur get(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Utilisateur.class);
    }
    
    public int put(String param, Utilisateur user){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .put(
                        Entity.entity(user, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public int delete(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .delete(Integer.class);
    }
}
