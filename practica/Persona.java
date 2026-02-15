/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.practica;

import java.time.LocalDate;

/**
 *
 * @author angel
 */
public class Persona {
    private String nombre;
    private int edad;
    private String CURP;
    private String ocupacion;
    private String telefono; 

    public Persona(String nombre, int edad, String CURP, String ocupacion, String telefono) {
        this.nombre = nombre;
        this.edad = edad;
        this.CURP = CURP;
        this.ocupacion = ocupacion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public int calcularFechaNacimiento(){
        return 2026 - this.edad;
    }
}
