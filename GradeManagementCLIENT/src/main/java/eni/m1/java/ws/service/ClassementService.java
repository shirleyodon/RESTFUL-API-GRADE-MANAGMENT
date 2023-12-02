/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.LigneClassement;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class ClassementService {
    private final Service service;
    
    public ClassementService(){
        service=new Service("classements");
    }
    
    public LigneClassement[] getAll(String param){
        WebTarget target=service.getWebTarget();
        return target.path("all").path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(LigneClassement[].class);
    }
}
