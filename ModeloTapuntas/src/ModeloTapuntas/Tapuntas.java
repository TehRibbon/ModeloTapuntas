/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package ModeloTapuntas;

import java.util.HashMap;
import java.util.Map;
import java.text.DateFormat;
import java.util.*;
/**
 *
 * @author marioanloru
 */
public class Tapuntas {
    private static Tapuntas instance = null;
    private final Map<String,Usuario> usuarios = new HashMap();

    //Patrón Singleton
    public static Tapuntas getInstance(){
        if(instance == null){
            instance = new Tapuntas();
        }
        return instance;
    }

    public void altaRegistro(String nombreUsuario, String contraseña, String direccionCorreo) throws Exception {
       if(usuarios.containsKey(nombreUsuario)) throw new Exception("ya existe un usuario con ese nombre de usuario");
       Usuario nuevoUsuario = new Usuario(nombreUsuario, contraseña, direccionCorreo);       
       nuevoUsuario.modificarVisibilidad(false);
       usuarios.put(nombreUsuario, nuevoUsuario);
       
    }
    
    public void aniadirVehiculo(String nombreUsuario, String matricula, String marca, String modelo, String color, int numeroPlazas, String categoria, String confor) throws Exception {
       
           boolean existe = existeVehiculo(matricula);
           if(existe) throw new Exception("Ya existe otro vehiculo  en el sistema con esa matricula");
           Usuario usuario = buscarUsuario(nombreUsuario);
           usuario.nuevoVehiculo(matricula, marca, modelo, color, numeroPlazas, categoria,  confor);
           
          
                
       
    }
    
    
    public ArrayList<PlanAlquiler> buscarOfertasAlquiler(String ciudadRecogida, GregorianCalendar fechaInicio, GregorianCalendar fechaFin){
        ArrayList<PlanAlquiler> datosPAUsuario = new ArrayList();
        Usuario usuario = null;
        for( Map.Entry<String, Usuario> entry : usuarios.entrySet()){
            String nombre = entry.getKey();
            usuario = entry.getValue();
           
            datosPAUsuario = (ArrayList<PlanAlquiler>) usuario.obtenerPlanesQueCumplanRequisitos(ciudadRecogida, fechaInicio, fechaFin);
            
        }
        return datosPAUsuario;
    
    }
    
    
    public void definirPlanAlquiler(String nombreUsuario, String matricula, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, String ciudadRecogida) throws Exception{
        Usuario usuario = buscarUsuario(nombreUsuario);
        usuario.definirPlanAlquiler(matricula, fechaInicio, fechaFin, ciudadRecogida);
    
    }
    
    
    public void eliminarVehiculoPropietario(String nombreUsuario, String matricula) throws Exception{
        Usuario usuario = buscarUsuario(nombreUsuario);
        usuario.eliminarVehiculo(matricula);
        
    
    }
    public void introducirPerfil(String nombreUsuario, String nombre,  String telefono, String breveDescripcion, TipoTransaccion[] preferenciasCobro) throws Exception{
        Usuario usuario = buscarUsuario(nombreUsuario);
        if( !(usuario.obtenerNombre().equals("")) ) throw new Exception("El usuario ya tiene un perfil definido.");
        
        usuario.introducirPerfil(nombre, telefono, breveDescripcion, preferenciasCobro);
    }
    
    public ArrayList<PlanAlquiler> obtenerPlanesAlquiler(String nombreUsuario) throws Exception{
        Usuario usuario = usuarios.get(nombreUsuario);
        ArrayList<PlanAlquiler> misPlanesAlquiler = new ArrayList();
        misPlanesAlquiler = usuario.obtenerPlanesAlquiler();
        
        usuario.introducirPerfil(nombreUsuario, usuario.obtenerTelefono(), usuario.obtenerDescripcion(), usuario.obtenerPreferenciaCobro());
        usuario.modificarVisibilidad(true);
        return misPlanesAlquiler;
    }
    
    public ArrayList<String> consultarPerfil(String nombreUsuario){
        Usuario usuario = buscarUsuario(nombreUsuario);
        ArrayList<String> infoPerfil;
        infoPerfil = usuario.consultarPerfil();
        return infoPerfil;
    }
    
    
    public void ofertarPlanAlquiler(String nombreUsuario, GregorianCalendar fechaInicio, String matricula){
        Usuario usuario = buscarUsuario(nombreUsuario);
        usuario.ofertarPlanAlquiler(fechaInicio, matricula);
    }
    
    private boolean existeUsuario(String nombreUsuario){
        boolean resultado = false;
        if(usuarios.containsKey(nombreUsuario))
            resultado = true;
        return resultado;
    }
    
    
    private Usuario buscarUsuario(String nombreUsuario){
        Usuario resultado = new Usuario("", "", "");
        for( Map.Entry<String, Usuario> entry : usuarios.entrySet()){
            String nombre = entry.getKey();
            Usuario user = entry.getValue();
           
            if(nombre.equals(nombreUsuario))
                resultado = user;
        }
        return resultado;
    
    }
    
    private void ordenarOfertas(String [] listaOfertas){}
    
    private boolean existeVehiculo(String matricula){
        boolean existe = false;
        for( Map.Entry<String, Usuario> entry : usuarios.entrySet()){
           Usuario user = entry.getValue();
           Vehiculo aux = user.buscarVehiculo(matricula);
           String auxMatricula = aux.obtenerMatricula();
           if(matricula.equals(auxMatricula))
               existe = true;
        }  
        return existe;
    }
    
    public void mostrarUsuariosSistema(){
        Iterator it = usuarios.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            System.out.println(e.getKey() + " " + e.getValue());
        }
        
    }
    
    public void mostrarVehiculosUsuario(String nombreUsuario){
        Usuario usuario = buscarUsuario(nombreUsuario);
        usuario.mostrarVehiculos();
    
    }
    
}
