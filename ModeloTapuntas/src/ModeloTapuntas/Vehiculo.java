/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package ModeloTapuntas;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author marioanloru
 */
public class Vehiculo {
    
    private String marca;
    private String modelo;
    private String confor;
    private int numeroPlazas;
    private String color;
    private String categoria;
    private String matricula;
    private ArrayList<PlanAlquiler> planesAlquiler;
    
    Vehiculo(String matricula, String marca, String modelo, String color, int numeroPlazas, String categoria, String confor){
        
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.numeroPlazas = numeroPlazas;
        this.categoria = categoria;
        this.confor = confor;
        this.planesAlquiler = new ArrayList();
    }
    
    ArrayList<String> obtenerDatosVehiculos(){
        ArrayList<String> datos = null;
        datos.add(matricula);
        datos.add(marca);
        datos.add(modelo);
        datos.add(color);
        datos.add(String.valueOf(this.numeroPlazas));
        datos.add(categoria);
        datos.add(confor);
        return datos;
    }
    
    boolean estasDisponible(GregorianCalendar fechaInicio, GregorianCalendar fechaFin){
        boolean disponible = false;
        GregorianCalendar auxInicio;
        GregorianCalendar auxFinal;
        for(PlanAlquiler plan : planesAlquiler){
            auxInicio = plan.primerDiaAlquiler();
            auxFinal = plan.ultimoDiaAlquiler();
            
            if(fechaInicio.before(auxInicio) && fechaFin.after(fechaFin))
                disponible = plan.obtenerVisible();
        }
        
        return disponible;
    }
    
    void incluirPlanAlquiler(PlanAlquiler miPlanAlquiler){
        planesAlquiler.add(miPlanAlquiler);
    
    }
    
    boolean comprobarEstadoAlquileres(){
        boolean resultado = false;
        GregorianCalendar fechaActual = new GregorianCalendar();
        fechaActual.getInstance();
        
        for(PlanAlquiler plan : this.planesAlquiler){
            if( ( plan.primerDiaAlquiler().before(fechaActual) && plan.ultimoDiaAlquiler().after(fechaActual) ) || fechaActual.before(plan.primerDiaAlquiler()) && plan.obtenerVisible() == true)
                resultado = true;
            
        }
        
        return resultado;
    }
    
    void eliminarVehiculoAlquileres(){
        
        for(PlanAlquiler plan : planesAlquiler){
            plan.eliminarVehiculo();
        }
        
    }
    
    String obtenerMatricula(){
        return matricula;
    }
    
    @Override
    public String toString(){
        return  "\n\n>Matricula: " + this.matricula
                + "\n>Modelo: " + this.modelo
                + "\nConfor: " + this.confor
                + "\nNumero de plazas: " + this.numeroPlazas
                + "\nColor: " + this.color
                + "\nCategoría: " + this.categoria
                + "\nMarca: " + this.marca;
    }
}
