/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medicaldb;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author yakoshuk
 */
@Startup
@Singleton
@ServerEndpoint("/endpoint")
public class NewWSEndpoint {

    @Resource(name = "java:app/testDS")
    private DataSource datasource;

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        try (Connection connection = datasource.getConnection()) {
            out.println(
                connection.getMetaData().getDatabaseProductName() + "-" +
                connection.getCatalog()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        MedicalHistory merge = em.merge(new MedicalHistory());
        System.out.println(merge.getId());
    }

    @OnMessage
    public String onMessage(String message) {
        System.out.println("sleep");
        System.out.println(Thread.currentThread());
        System.out.println(message);
        try {            
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NewWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        MedicalHistory find = em.find(MedicalHistory.class, 1L);
        return Objects.toString(find);
    }

}

