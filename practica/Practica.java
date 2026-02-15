/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.practica;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angel
 */
public class Practica {
    
    
    
    

    public static void main(String[] args) {
        Persona Angel = new Persona("Angel",21,"BEDA213123","Carpintero","6442254377");
        System.out.println("La fecha de naciemiento de " + Angel.getNombre() + " Es de " + Angel.calcularFechaNacimiento());
        
    }
}
