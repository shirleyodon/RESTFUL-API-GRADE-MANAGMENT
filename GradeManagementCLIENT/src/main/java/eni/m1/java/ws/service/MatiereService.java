/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Matiere;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class MatiereService {
    private final Service service;
    
    public MatiereService(){
        service=new Service("matieres");
    }
    
    public String post(Matiere mat){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(mat, MediaType.APPLICATION_JSON),
                        String.class);
    }
    
    public Matiere[] getAll(){
        WebTarget target=service.getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Matiere[].class);
    }
    
    public Matiere[] getKW(String kw){
        WebTarget target=service.getWebTarget();
        return target.queryParam("kw", kw)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Matiere[].class);
    }
    
    public Matiere[] getAll(String param){
        WebTarget target=service.getWebTarget();
        return target.path("all").path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Matiere[].class);
    }
    
    public Matiere get(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Matiere.class);
    }
    
    public int put(String param, Matiere mat){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .put(
                        Entity.entity(mat, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public int delete(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .delete(Integer.class);
    }
}
