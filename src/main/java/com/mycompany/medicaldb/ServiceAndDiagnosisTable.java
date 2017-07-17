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
@Table(name="ServiceAndDiagnosisTable")
public class ServiceAndDiagnosisTable implements Serializable {

    public ServiceAndDiagnosisTable(LocalDate dateOfVisit, String diagnosis, String services) {
        this.dateOfVisit = dateOfVisit;
        this.diagnosis = diagnosis;
        this.services = services;
    }
    
    public ServiceAndDiagnosisTable(){
        this.dateOfVisit = null;
        this.diagnosis = null;
        this.services = null;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="DateOfVisit")
    private LocalDate dateOfVisit;
    
    @Column(name="Diagnosis")
    private String diagnosis;
    
    @Column(name="Services")
    private String services;

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getServices() {
        return services;
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
        if (!(object instanceof ServiceAndDiagnosisTable)) {
            return false;
        }
        ServiceAndDiagnosisTable other = (ServiceAndDiagnosisTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dateOfVisit+" "+diagnosis+" "+services;
    }
    
}
