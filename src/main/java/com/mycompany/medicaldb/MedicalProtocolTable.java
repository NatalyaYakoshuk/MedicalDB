/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.medicaldb;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author yakoshuk
 */
@Entity
@Table(name="MedicalProtocolTable")
public class MedicalProtocolTable implements Serializable {

    public MedicalProtocolTable(String nameOfResearch, String content, String dateOfVisit) {
        this.nameOfProtocol = nameOfResearch;
        this.content = content;
        this.dateOfVisit = dateOfVisit;
    }
    
    public MedicalProtocolTable(){
        this.nameOfProtocol = null;
        this.content = null;
        this.dateOfVisit = null;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="NameOfProtocol")
    private String nameOfProtocol;
    
     @Column(name="Content")
    private String content;
     
     @Column(name="DateOfVisit")
    private String dateOfVisit;

     
       public void setContent(String text)
    {
        this.content = text;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setNameOfResearch(String name){
        this.nameOfProtocol = name;
    }
    
    public String getNameOfResearch(){
        return this.nameOfProtocol;
    }
    
    public void setDateOfVisit(String date){
        this.dateOfVisit = date;
    }
    
    public String getDateOfVisit(){
        return this.dateOfVisit;
    }
     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalProtocolTable)) {
            return false;
        }
        MedicalProtocolTable other = (MedicalProtocolTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return dateOfVisit.toString()+"@"+nameOfProtocol+"@"+content;
    }
    
}
