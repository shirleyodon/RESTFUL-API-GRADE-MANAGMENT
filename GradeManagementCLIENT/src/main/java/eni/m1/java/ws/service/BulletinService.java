/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Bulletin;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class BulletinService {
    private final Service service;
    
    public BulletinService(){
        service=new Service("bulletins");
    }
    
    public Bulletin get(String param){
        WebTarget target=service.getWebTarget();
        return target.path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Bulletin.class);
    }
}
