/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medicaldb;

import com.google.gson.Gson;
import java.awt.Image;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    //
    List<MedicalProtocolTable> mpt;
    List<ResearchTable> rt;
    List<ServiceAndDiagnosisTable> sadt;    
      
    
    @Resource(name = "java:app/testDS")
    private DataSource datasource;

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        try (Connection connection = datasource.getConnection()) { 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        MedicalProtocolTable protocol1 = em.merge(new MedicalProtocolTable("Осмотр хирурга", "Обширное кровоизлияние в область ушиба", "2017-12-13"));
        MedicalProtocolTable protocol2 = em.merge(new MedicalProtocolTable("Осмотр хирурга", "Опухоль в области ушиба спала. Кожные покровы нормальные", "2017-12-20"));
        MedicalProtocolTable protocol3 = em.merge(new MedicalProtocolTable("Осмотр отоларинголога", "Больной жалуется на боль в горле, миндалины увеличенные, слизистая отечная", "2016-10-20"));
        ServiceAndDiagnosisTable service1 = em.merge(new ServiceAndDiagnosisTable("2017-12-13", "Сильный ушиб", "Первичный осмотр, рентген коленного сустава"));
        ServiceAndDiagnosisTable service2 = em.merge(new ServiceAndDiagnosisTable("2017-12-20", "Сильный ушиб", "Повторный осмотр"));
        ServiceAndDiagnosisTable service3 = em.merge(new ServiceAndDiagnosisTable("2016-10-20", "Ангина", "Первичный осмотр"));
        mpt = em.createQuery("SELECT t FROM MedicalProtocolTable t", MedicalProtocolTable.class).getResultList();
        sadt = em.createQuery("SELECT t FROM ServiceAndDiagnosisTable t", ServiceAndDiagnosisTable.class).getResultList();
    }

    @OnMessage
    public String onMessage(String message) throws SQLException {
        Gson mygson = new Gson();
        String answer;
        switch (message) {
            case ("MedicalProtocolTable"):
                answer = mygson.toJson(mpt);
                return "1"+answer;
            case ("ResearchTable"):
                return "now its not working";
            case ("ServiceAndDiagnosisTable"):
                answer = mygson.toJson(sadt);
                return "2"+answer;
            case ("Hello"):
                return "Ready to work!";           
            default:
                return "Badrequest";
        }
        
    }

}

