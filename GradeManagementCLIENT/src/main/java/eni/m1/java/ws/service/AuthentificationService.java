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
public class AuthentificationService {
    private final Service service;
    
    public AuthentificationService(){
        service=new Service("authentifications");
    }
    
    public Utilisateur post(Utilisateur user){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(user, MediaType.APPLICATION_JSON),
                        Utilisateur.class);
    }
}
