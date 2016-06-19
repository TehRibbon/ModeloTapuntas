/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package ModeloTapuntas;

import java.text.*;
import java.util.*;

/**
 *
 * @author marioanloru
 */
public class PlanAlquiler {
    private boolean visible;
    private GregorianCalendar primerDiaAlquiler;
    private GregorianCalendar ultimoDiaAlquiler;
    private double costeAlquilerAlDia;
    private String ciudadRecogida;
    private Vehiculo vehiculo;
    
    PlanAlquiler(Vehiculo unVehiculo, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida){
        this.primerDiaAlquiler = fechaInicio;
        this.ultimoDiaAlquiler = fechaFin;
        this.ciudadRecogida = ciudadRecogida;
        this.vehiculo = unVehiculo;
    }
    
    boolean obtenerVisible(){
        return visible;
    }
    
    String obtenerCiudadRecogida(){
        return this.ciudadRecogida;
    }
    
    Vehiculo obtenerVehiculo(){
        return this.vehiculo;
    }
    
    GregorianCalendar primerDiaAlquiler(){
        return this.primerDiaAlquiler;
    }
    
    GregorianCalendar ultimoDiaAlquiler(){
        return this.ultimoDiaAlquiler;
    }
    ArrayList<String> obtenerDatosPA(){
        ArrayList<String> resultado = new ArrayList();
        resultado.add(String.valueOf(this.costeAlquilerAlDia));
        
        resultado.add(vehiculo.obtenerDatosVehiculos().toString());
        return resultado;
    }
    
    void eliminarVehiculo(){
        vehiculo.eliminarVehiculoAlquileres();
    }
    
    ArrayList<String> obtenerDatosPlanAlquiler(){
        ArrayList<String> resultado = new ArrayList();
        String matricula = vehiculo.obtenerMatricula();
        
        
        resultado.add(matricula);
        resultado.add(this.primerDiaAlquiler.toString());
        resultado.add(this.ultimoDiaAlquiler.toString());
        resultado.add(String.valueOf(this.costeAlquilerAlDia));
        resultado.add(this.ciudadRecogida);
        
        return resultado;
    
    }
    
    void modificarVisibilidad(boolean visible){
        this.visible = visible;
    }
    
    @Override
    public String toString(){
        return  "\n\n>Primer dia alquiler: " +this.primerDiaAlquiler
                + "\n>Ultimo dia alquiler: " + this.ultimoDiaAlquiler
                + "\nCoste alquiler al dia: " + this.costeAlquilerAlDia
                + "\nCiudad recogida: " + this.ciudadRecogida
                + "\nVehiculo: " + this.vehiculo + "\n";
    }
    
}
