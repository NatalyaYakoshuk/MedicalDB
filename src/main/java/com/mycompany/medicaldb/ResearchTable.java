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
@Table(name="ResearchTable")
public class ResearchTable implements Serializable {

    public ResearchTable(byte[] image, LocalDate dateOfVisit) {
        this.image = image;
        this.dateOfVisit = dateOfVisit;
    }

    public ResearchTable(){
        this.image = null;
        this.dateOfVisit = null;
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    
    @Column(name="ImageOfResearch")
    private byte[] image;

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }
    
    @Column(name="DateOfVisit")
    private LocalDate dateOfVisit;

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (!(object instanceof ResearchTable)) {
            return false;
        }
        ResearchTable other = (ResearchTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.medicaldb.ResearchTable[ id=" + id + " ]";
    }
    
}
