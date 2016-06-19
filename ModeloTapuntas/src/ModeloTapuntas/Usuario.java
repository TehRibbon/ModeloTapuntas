/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package ModeloTapuntas;

import java.text.DateFormat;
import java.util.*;

/**
 *
 * @author marioanloru
 */
class Usuario {
    private String nombreUsuario;
    private String contraseña;
    private String direccionCorreo;
    private String nombre;
    private String telefono;
    private String breveDescripcionPersonal;
    private boolean visibilidad = false;
    private TipoTransaccion[] preferenciaCobro;
    private boolean pendienteBaja = false;
    // incluir los demás atributos
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<PlanAlquiler> planesAlquiler;
 

    Usuario(String nombreUsuario, String contraseña, String direccionCorreo) {
      this.preferenciaCobro = new TipoTransaccion[4];
      this.nombreUsuario= nombreUsuario;
      this.contraseña = contraseña;
      this.direccionCorreo = direccionCorreo;
      this.nombre = "";
      this.telefono = "";
      this.breveDescripcionPersonal = "";
      this.vehiculos = new ArrayList();
      this.planesAlquiler = new ArrayList();
      this.preferenciaCobro = new TipoTransaccion [4];
      
      int i=0;
      while(i < 4){
          preferenciaCobro[i] = TipoTransaccion.EFECTIVO;
          i++;
      }

    }
    
    String obtenerNombre(){
        return nombre;
    }
    
    String obtenerTelefono(){
        return telefono;
    }
    
    TipoTransaccion[] obtenerPreferenciaCobro(){
        return preferenciaCobro;
    }
    
    String obtenerDescripcion(){
        return breveDescripcionPersonal;
    }
    void modificarVisibilidad(boolean visibilidad){
        this.visibilidad = visibilidad;
    }
    
    void nuevoVehiculo(String matricula, String marca, String modelo, String color,int numeroPlazas, String categoria, String confor){
        Vehiculo nuevoVehiculo = new Vehiculo(matricula,marca, modelo, color, numeroPlazas, categoria, confor);
        vehiculos.add(nuevoVehiculo);
    }
    ArrayList<PlanAlquiler> obtenerPlanesQueCumplanRequisitos(String ciudadRecogida, GregorianCalendar fechaInicio, GregorianCalendar fechaFin){
        ArrayList<PlanAlquiler> resultado = new ArrayList();
        ArrayList<String> datosPAUsuario = new ArrayList();
        ArrayList<String> datosPA = new ArrayList();
        datosPAUsuario.add(this.nombreUsuario);
        datosPAUsuario.add(this.preferenciaCobro.toString());
        
        for(PlanAlquiler pa : planesAlquiler){
            String auxCiudadRecogida = pa.obtenerCiudadRecogida();
            GregorianCalendar auxPrimerDia = pa.primerDiaAlquiler();
            GregorianCalendar auxUltimoDia = pa.ultimoDiaAlquiler();
            
            if( (ciudadRecogida.equals(auxCiudadRecogida) ) && (fechaInicio.after(auxPrimerDia)  ) && (fechaFin.before(auxUltimoDia)))
                
                datosPA = pa.obtenerDatosPA(); 
        }
        
        return resultado;
    }
    void definirPlanAlquiler(String matricula, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida) throws Exception{
        
        Vehiculo vehiculo = buscarVehiculo(matricula);
        boolean disponible = vehiculo.estasDisponible(fechaInicio, fechaFin);
        if(disponible == true) throw new Exception("El vehiculo ya pertenece plan alquiler en esas fechas");
        PlanAlquiler miPlanAlquiler = new PlanAlquiler(vehiculo, fechaInicio, fechaFin, ciudadRecogida);
        vehiculo.incluirPlanAlquiler(miPlanAlquiler);
        
        planesAlquiler.add(miPlanAlquiler);
        
        miPlanAlquiler.modificarVisibilidad(false);
        
    }
    //Elimina un vehiculo por su matricula
    void eliminarVehiculo(String matricula) throws Exception{
        Vehiculo vehiculo = buscarVehiculo(matricula);
        boolean alquilado = vehiculo.comprobarEstadoAlquileres();
    
        if(alquilado) throw new Exception("el vehículo no se puede eliminar, tiene vigentes alquileres o viajes");
        if(!alquilado)
            vehiculo.eliminarVehiculoAlquileres();
        
        vehiculos.remove(vehiculo);
    }
    
    //Introduce el perfil de un usuario en el sistema por sus datos
    void introducirPerfil(String nombre, String telefono, String breveDescripcion, TipoTransaccion[] preferenciasCobro){
        this.nombre = nombre;
        this.telefono = telefono;
        this.breveDescripcionPersonal = breveDescripcion;
        this.preferenciaCobro = preferenciasCobro;
        modificarVisibilidad(true);
    
    }
    ArrayList<PlanAlquiler> obtenerPlanesAlquiler(){
        GregorianCalendar fechaActual = new GregorianCalendar();
        fechaActual.getInstance();
        boolean auxVisible;
        ArrayList<String> datosPlanAlquiler = new ArrayList();
        ArrayList<PlanAlquiler> planesAlq = new ArrayList();
        for(PlanAlquiler plan : this.planesAlquiler){
            auxVisible = plan.obtenerVisible();
            if( !(auxVisible) && fechaActual.before(plan.ultimoDiaAlquiler()) ){
                datosPlanAlquiler = plan.obtenerDatosPlanAlquiler();
                planesAlq.add(plan);
                
                
            }
        //falta incluir(datosPlanAlquiler)
            
        }
        
        return planesAlq;
    }
    
    
    ArrayList<String> consultarPerfil(){
        ArrayList<String> infoPerfil = new ArrayList();
        
        infoPerfil.add(this.nombre);
        infoPerfil.add(this.telefono);
        infoPerfil.add(this.breveDescripcionPersonal);
        infoPerfil.add(Boolean.toString(visibilidad));
        
        return infoPerfil;
    
    }
    
    void ofertarPlanAlquiler(GregorianCalendar fechaInicio, String matricula){
        PlanAlquiler pa = buscarPlanAlquiler(fechaInicio, matricula);
    
    }
    
    
    Vehiculo buscarVehiculo(String matricula){
        Vehiculo auxiliar = new Vehiculo("","","","",0,"","");
        String auxMatricula = "";
        for(Vehiculo vehic : vehiculos){
            auxMatricula = vehic.obtenerMatricula();
            if(auxMatricula.equals(matricula))
                auxiliar = vehic;
        }
        return auxiliar;
    }
    private PlanAlquiler buscarPlanAlquiler(GregorianCalendar fechaInicio, String matricula){
        PlanAlquiler resultado = null;
        String auxMatricula;
        GregorianCalendar auxFechaInicio;
        
        for(PlanAlquiler plan : this.planesAlquiler){
            auxMatricula = plan.obtenerVehiculo().obtenerMatricula();
            auxFechaInicio = plan.primerDiaAlquiler();
            if(auxFechaInicio.equals(fechaInicio) && auxMatricula.equals(matricula) )
                resultado = plan;
        }
        return resultado;
    }
    
    void mostrarVehiculos(){
        int contador = 0;
        for(Vehiculo vehiculo : this.vehiculos){
            System.out.print("\nVehiculo nº: " + contador + vehiculo.toString());
            contador++;
        }
    }
    
    
  
}