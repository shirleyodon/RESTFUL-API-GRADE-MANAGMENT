/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Etudiant;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class EtudiantService {
    private final Service service;
    
    public EtudiantService(){
        service=new Service("etudiants");
    }
    
    public String post(Etudiant et){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(et, MediaType.APPLICATION_JSON),
                        String.class);
    }
    
    public Etudiant[] getAll(){
        WebTarget target=service.getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Etudiant[].class);
    }
    
    public Etudiant[] getAll(String param){
        WebTarget target=service.getWebTarget();
        return target.path("all").path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Etudiant[].class);
    }
    
    public Etudiant[] getKW(String kw){
        WebTarget target=service.getWebTarget();
        return target.queryParam("kw", kw)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Etudiant[].class);
    }
    
    public Etudiant get(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Etudiant.class);
    }
    
    public int put(String param, Etudiant et){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .put(
                        Entity.entity(et, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public int delete(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .delete(Integer.class);
    }
}
