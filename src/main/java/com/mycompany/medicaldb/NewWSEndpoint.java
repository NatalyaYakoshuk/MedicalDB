/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medicaldb;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    
    /*List<MedicalProtocolTable> mpt;
    List<ResearchTable> rt;
    List<ServiceAndDiagnosisTable> sadt;*/

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
        /*TypedQuery<MedicalProtocolTable> query1 = em.createQuery("SELECT * FROM MedicalProtocolTable", MedicalProtocolTable.class);
         mpt = query1.getResultList();
        
        TypedQuery<ResearchTable> query2 = em.createQuery("SELECT * FROM ResearchTable", ResearchTable.class);
        rt = query2.getResultList();
        
        TypedQuery<ServiceAndDiagnosisTable> query3 = em.createQuery("SELECT * FROM ServiceAndDiagnosisTable", ServiceAndDiagnosisTable.class);
        sadt = query3.getResultList();*/
    
        MedicalHistory merge = em.merge(new MedicalHistory());
        System.out.println(merge.getId());
    }

    @OnMessage
    public String onMessage(String message) {
        /*switch (message) {
            case ("MedicalProtocolTable"):
                for (MedicalProtocolTable protocol : mpt) {
                    return protocol.toString();
                }

                break;
            case ("ResearchTable"):
                for (ResearchTable research : rt) {
                    return research.toString();
                }

                break;
            case ("ServiceAndDiagnosisTable"):
                for (ServiceAndDiagnosisTable service : sadt) {
                    return service.toString();
                }
                break;
            default:
                return "Wrong request";
        }*/
        
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

