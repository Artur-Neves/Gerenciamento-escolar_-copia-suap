/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projeto_lais.Model.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Artur
 */
public class Entity_Manager {
    
    private final EntityManagerFactory em= Persistence.createEntityManagerFactory("Lais");
    public EntityManager ent (){
        return em.createEntityManager();
    }
}
