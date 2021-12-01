package com.lanebulosadeqwerty.puntajes_ms.models;

import org.springframework.data.annotation.Id;

// import java.sql.Double;
import java.util.Date;

import javax.persistence.*;

@Entity // Le indicamos a la clase que es una entidad, para administrarla y realizar las relaciones 
@Table(name="puntaje") // Nombrar tabla de la bd
public class Puntaje {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private String id;
    private String usuario; // Nombre de usuario
    private String leccionId;
    private double precision;
    private Integer cpm_e;
    private Double segundos;
    private Date fecha;
    

    public Puntaje(String usuario, double precision, Integer cpm_e, Double segundos, Date fecha, String leccionId) {
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