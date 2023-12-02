/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.service;

import eni.m1.java.ws.entities.Note;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author shirleyodon
 */
public class NoteService {
    private final Service service;
    
    public NoteService(){
        service=new Service("notes");
    }
    
    public int post(Note grade){
        WebTarget target=service.getWebTarget();
        return target.request()
                     .post(
                        Entity.entity(grade, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public Note[] getAll(){
        WebTarget target=service.getWebTarget();
        return target.path("all")
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Note[].class);
    }
    
    public Note[] getAll(String param){
        WebTarget target=service.getWebTarget();
        return target.path("all").path(param)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Note[].class);
    }
    
    public Note[] getAll(String param1, String param2){
        WebTarget target=service.getWebTarget();
        return target.path("all").path(param1).path(param2)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Note[].class);
    }
    
    public Note get(String param1, String param2){
        WebTarget target=service.getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .accept(MediaType.APPLICATION_JSON)
                     .get(Note.class);
    }
    
    public int put(String param1, String param2, double note){
        WebTarget target=service.getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .put(
                        Entity.entity(note, MediaType.APPLICATION_JSON),
                        Integer.class);
    }
    
    public int delete(String param1, String param2){
        WebTarget target=service.getWebTarget();
        return target.path(param1).path(param2)
                     .request()
                     .delete(Integer.class);
    }
}
