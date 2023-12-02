/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author shirleyodon
 */
public class Service {
    private String baseURI="http://localhost:8080/GradeManagementREST/rest/resources/";

    public Service(String name) {
        baseURI+=name;
    }
    
    public WebTarget getWebTarget(){
        ClientConfig config=new ClientConfig();
        Client client=ClientBuilder.newClient(config);
        return client.target(baseURI);
    }
/*
    public void get(String param){
        WebTarget target=getWebTarget();
        target.path(param)
              .request()
              .accept(MediaType.APPLICATION_JSON)
              .get();
    }
    
    public Object get(String param){
        WebTarget target=getWebTarget();
        return target.path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object.class);
    }
    
    public Object get(String param1, String param2){
        WebTarget target=getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object.class);
    }
    
    public Object[] getKW(String kw){
        WebTarget target=getWebTarget();
        return target.queryParam("kw", kw)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object[].class);
    }
    
    public Object[] getAll(){
        WebTarget target=getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object[].class);
    }
    
    public Object[] getAll(String param){
        WebTarget target=getWebTarget();
        return target.path("all").path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object[].class);
    }
    
    public Object[] getAll(String param1, String param2){
        WebTarget target=getWebTarget();
        return target.path("all").path(param1).path(param2)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Object[].class);
    }
    
    public Object post(Object obj){
        WebTarget target=getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(obj, MediaType.APPLICATION_JSON),
                        Object.class);
    }
    
    public Object put(String param, Object obj){
        WebTarget target=getWebTarget();
        return target.path(param)
                     .request()
                     .put(
                        Entity.entity(obj, MediaType.APPLICATION_JSON),
                        Object.class);
    }
    
    public Object put(String param1, String param2, Object obj){
        WebTarget target=getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .put(
                        Entity.entity(obj, MediaType.APPLICATION_JSON),
                        Object.class);
    }
    
    public Object delete(String param){
        WebTarget target=getWebTarget();
        return target.path(param)
                     .request()
                     .delete(Object.class);
    }
    
    public Object delete(String param1, String param2){
        WebTarget target=getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .delete(Object.class);
    }
    
    public Object deleteAll(){
        WebTarget target=getWebTarget();
        return target.path("all")
                     .request()
                     .delete(Object.class);
    }
*/
}
