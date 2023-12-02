/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.entities;

/**
 *
 * @author shirleyodon
 */
public class Registre {
    private int id;
    private String date, heure, login, action, cible;

    public Registre() {}

    public Registre(String date, String heure, String login, String action, String cible) {
        this.date = date;
        this.heure = heure;
        this.login = login;
        this.action = action;
        this.cible = cible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    @Override
    public String toString() {
        return "Registre{" + "id=" + id + ", date=" + date + ", heure=" + heure + ", login=" + login + ", action=" + action + ", cible=" + cible + '}';
    }
}
