package com.lanebulosadeqwerty.puntajes_ms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// import java.sql.Double;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity // Le indicamos a la clase que es una entidad, para administrarla y realizar las relaciones 
@Document(collection = "puntaje") // Nombrar tabla de la bd
public class Puntaje {
    @Id
    private String id;
    @NotNull 
    private String usuario; // Nombre de usuario
    @NotNull (message = "leccionId no puede ser nulo")
    private String leccionId;
    @NotNull (message = "precision no puede ser nula")
    private double precision;
    @NotNull (message = "cpm_e no puede ser nulo")
    private Integer cpm_e;
    @NotNull (message = "El número de segundos no puede ser nulo.")
    private Double segundos;
    private Date fecha;
    
    public Puntaje(String id, String usuario, double precision, Integer cpm_e, Double segundos, Date fecha, String leccionId) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = "id_temporal";
        }  
        this.precision = precision;
        this.usuario = usuario;
        this.cpm_e = cpm_e;
        this.segundos = segundos;
        this.fecha = fecha;
        this.leccionId = leccionId;
    }

    public String getId() {
        return id;
    }
        
    public void setId(String id) {
        this.id = id;
    }

    public double getPrecision() {
        return precision;
    }
            
    public void setPrecision(double precision) {
        this.precision = precision;
    }  

    public String getUsuario() {
        return usuario;
    }
                
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLeccionId() {
        return this.leccionId;
    }

    public void setLeccionId(String leccionId) {
        this.leccionId = leccionId;
    }
        
    public Integer getCpm_e() {
        return cpm_e;
    }
            
    public void setCpm_e(Integer cpm_e) {
        this.cpm_e = cpm_e;
    }        
        
    public Double getSegundos() {
        return segundos;
    }
                
    public void setSegundos(Double segundos) {
        this.segundos = segundos;
    }          

    public Date getFecha() {
        return fecha;
    }
                    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }  
}