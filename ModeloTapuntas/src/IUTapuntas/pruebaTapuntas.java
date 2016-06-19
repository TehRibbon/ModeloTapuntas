/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package IUTapuntas;

import ModeloTapuntas.PlanAlquiler;
import ModeloTapuntas.Tapuntas;
import ModeloTapuntas.TipoTransaccion;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author marioanloru
 */
public class pruebaTapuntas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     
        // Obtener la única instancia de la clase BuenProvecho (patrón sigleton)
        Tapuntas aViajar = Tapuntas.getInstance(); 
        
        // Definir la variable que nos permite leer String desde teclado
        final Scanner in = new Scanner(System.in);
        int opcion = 0; 
        do{
            try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		 
                 //Terminar de diseñar el menú (usando System.out.println(...)) con las opciones que faltan
		 // Podéis hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
                System.out.println("\n\n*********************************** MENU ***********************************\n" +
                                       "GESTIÓN DE USUARIOS   \n" +
                                     "\t10. Nuevo Usuario \n" +
                                     "\t11. Consultar usuarios del sistema \n" +
                                     "\t12. Incluir Perfil de Usuario \n" +
                                     "\t13. Consultar Perfil de un Usuario \n");	
                                 
                System.out.println("GESTIÓN DE VEHICULOS  \n" +                             
                                    "\t20. Nuevo vehículo \n" +
                                    "\t21. Consultar vehículos de un usuario \n" +
                                    "\t22. Eliminar vehículo\n");
                
                System.out.println("GESTIÓN DE PLANES DE ALQUILER  \n" +
                                    "\t30. Definir nuevo plan de alquiler \n" +
                                    "\t31. Consultar mis planes de alquiler\n" +
                                    "\t32. Ofertar un plan de alquiler \n" +
                                    "\t33. Buscar ofertas de planes de alquiler \n");
                
                System.out.println("\n**********************************************************************");
                		         
                System.out.println("\t0. TERMINAR");
		System.out.println("\n**********************************************************************");
                 
                // Lectura de un int, para darle valor a opcion.
                opcion =Integer.parseInt(in.nextLine()); 
                
                // Estructura switch con todas las opciones de menú. Algunos de ellos ya lo tenéis hecho
                // Tenéis que terminar las opciones que están incompletas y las que no están hechas
                switch(opcion){
                    case 10: //incluir un nuevo usuario en el sistema 
                                            
                        System.out.print("Nombre de Usuario:");
                        String nombreUsuario =in.nextLine();
                                       
                        System.out.print("Clave:");
                        String claveUsuario= in.nextLine();
                        
                        System.out.print("Dirección de correo:");
                        String correoUsuario= in.nextLine();
                        
                        aViajar.altaRegistro(nombreUsuario, claveUsuario, correoUsuario);                                             
                        System.out.print("\n++++++ Usuario introducido con éxito ++++++");
                    break;  
                    
                    case 11:/*Ver usuarios del sistema */
                        System.out.print("Usuarios del sistema: ");
                        aViajar.mostrarUsuariosSistema();
                        
                        System.out.print("\n++++++  Usuarios mostrados con éxito ++++++");
                                                                     
                    break;
                    
                    case 12:/*Incluir Perfil */
                        System.out.print("Nombre de usuario: ");
                        nombreUsuario= in.nextLine();
                        
                        System.out.print("Nombre: ");
                        String nombre= in.nextLine();
                        
                        System.out.print("Teléfono: ");
                        String telefono= in.nextLine();
                        
                        System.out.print("Breve descripción: ");
                        String breveDescripcion= in.nextLine();
                        
                        TipoTransaccion[] trans = new TipoTransaccion [4];
                         
                        int i =0;
                        while(i < 4){
                            System.out.print("preferenciasCobro, con prioridad " + i + "(Las posibilidades son EFECTIVO, PAYPAL, TARJETA y TRANSFERENCIA): ");
                            String entrada= in.nextLine();
                            
                            switch(entrada){
                                case "EFECIVO":
                                    trans[i] = TipoTransaccion.EFECTIVO;
                                    break;
                                    
                                case "TIPO2":        
                                    trans[i] = TipoTransaccion.PAYPAL;
                                    break;
                                    
                                case "TIPO3":
                                    trans[i] = TipoTransaccion.TARJETA;
                                    break;
                                    
                                case "TIPO4":
                                    trans[i] = TipoTransaccion.TRANSFERENCIA;
                                    break;
                            }
                            i++;
                        }
                        
                        aViajar.introducirPerfil(nombreUsuario, nombre, telefono, breveDescripcion, trans);
                                                        
                        System.out.print("\n++++++  Perfil introducido con éxito ++++++");
                    break;
                    case 13:/*Consultar perfil */
                        System.out.print("Nombre del usuario a consultar: ");
                        nombreUsuario= in.nextLine();
                        
                        List consultaPerfil = aViajar.consultarPerfil(nombreUsuario);
                        System.out.print(consultaPerfil.toString());
                        
                        System.out.print("\n++++++  Perfil consultado con éxito ++++++");
                        
                    break;
                
                    case 20: /*Nuevo vehículo */           
                        System.out.print("Nombre de usuario: ");
                        nombreUsuario= in.nextLine();
                        
                        System.out.print("Matricula: ");
                        String matricula= in.nextLine();
                        
                        System.out.print("Marca: ");
                        String marca= in.nextLine();
                        
                        System.out.print("Color: ");
                        String color= in.nextLine(); 
                        
                        System.out.print("Modelo: ");
                        String modelo= in.nextLine();
                        
                        System.out.print("Categoria: ");
                        String categoria= in.nextLine();
                                
                        System.out.print("Confor: ");
                        String confor= in.nextLine();
                        
                        System.out.print("Numero de plazas: ");
                        String numerPlazas= in.nextLine();
                        
                        int numeroPlazas = Integer.parseInt(numerPlazas);
                        
                        aViajar.aniadirVehiculo(nombreUsuario, matricula, marca, modelo, color, numeroPlazas, categoria, confor);
                        
                        System.out.print("\n++++++  Vehículo añadido con éxito ++++++");
                    break;
                  
                    case 21: /* Consultar vehículos de un usuario  */
                        System.out.print("NombreUsuario: ");
                        nombreUsuario= in.nextLine();
                        
                        aViajar.mostrarVehiculosUsuario(nombreUsuario);
                        
                        System.out.print("\n++++++  Consulta de vehículos del usuario " + nombreUsuario + " realizada con éxito ++++++");
                    break;             
                  
                    case 22: /* Eliminar vehículo  */
                        System.out.print("NombreUsuario: ");
                        nombreUsuario= in.nextLine();
                        
                        System.out.print("Matricula: ");
                        matricula= in.nextLine();
                        
                         aViajar.eliminarVehiculoPropietario(nombreUsuario, matricula);
                         
                         System.out.print("\n++++++  Vehículo con matricula " + matricula + " eliminado con éxito++++++");
                    break;
  
    
                    case 30: /* Nuevo plan de alquiler */
                        System.out.print("NombreUsuario: ");
                        nombreUsuario= in.nextLine();
                        
                        System.out.print("Matricula: ");
                        matricula= in.nextLine();
                        
                        System.out.print("Categoria: ");
                        categoria= in.nextLine();
                        
                        System.out.print("Introduzca el dia para la fecha inicial: ");
                        int dia= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca mes para la fecha inicial: ");
                        int mes= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el año para la fecha inicial: ");
                        int anio= Integer.parseInt(in.nextLine());
                        
                        GregorianCalendar fechaInicio = new GregorianCalendar(anio, mes-1, dia);
                        
                        System.out.print("Introduzca el dia para la fecha final: ");
                        dia= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el mes para fecha final: ");
                        mes= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el año para la fecha final: ");
                        anio= Integer.parseInt(in.nextLine());
                        
                        GregorianCalendar fechaFin = new GregorianCalendar(anio, mes-1, dia);
                        
                        
                        aViajar.definirPlanAlquiler(nombreUsuario, matricula, fechaInicio, fechaFin, categoria);
                        
                        System.out.print("\n++++++  Plan alquiler introducido con éxito++++++");
                    break;

                    case 31: /* Consultar planes de alquiler de un usuario */
                        System.out.print("NombreUsuario: ");
                        nombreUsuario= in.nextLine();
                        
                        ArrayList<PlanAlquiler> planes = new ArrayList();
                        planes = aViajar.obtenerPlanesAlquiler(nombreUsuario);
                        
                        System.out.print(planes.toString());
                        System.out.print("\n++++++  Consulta de planes de alquiler del usuario " + nombreUsuario + " realizada con éxito ++++++");
                                                
                    break;

                    case 32: /* Ofertar un plan de alquiler */
                        System.out.print("Nombre usuario: ");
                        nombreUsuario= in.nextLine(); 
                        
                        System.out.print("Introduzca el dia para la fecha inicial: ");
                        dia= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca mes para la fecha inicial: ");
                        mes= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el año para la fecha inicial: ");
                        anio= Integer.parseInt(in.nextLine());
                        
                        fechaInicio = new GregorianCalendar(anio, mes-1, dia);
                        
                        System.out.print("Matricula: ");
                        matricula= in.nextLine();
                        
                        aViajar.ofertarPlanAlquiler(nombreUsuario, fechaInicio, matricula);
                        System.out.print("\n++++++  Operación realizada con éxito ++++++");
                                  
                    break;

                    case 33: /* Buscar ofertas de planes de alquiler  */
                        System.out.print("Categoria: ");
                        categoria= in.nextLine();
                        
                        System.out.print("Introduzca el dia para la fecha inicial: ");
                        dia= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca mes para la fecha inicial: ");
                        mes= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el año para la fecha inicial: ");
                        anio= Integer.parseInt(in.nextLine());
                        
                        fechaInicio = new GregorianCalendar(anio, mes-1, dia);
                        
                        System.out.print("Introduzca el dia para la fecha final: ");
                        dia= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca mes para la fecha final: ");
                        mes= Integer.parseInt(in.nextLine());
                        
                        System.out.print("Introduzca el año para la fecha final: ");
                        anio= Integer.parseInt(in.nextLine());
                        
                        fechaFin = new GregorianCalendar(anio, mes-1, dia);
                        
                        aViajar.buscarOfertasAlquiler(categoria, fechaInicio, fechaFin);
                        System.out.print("++++++  Operación realizada con éxito ++++++");

                    break;                 

                    case 0: /* terminar */
                    break;                        
                                    
                    default:
                        System.out.println("opcion no válida");
                    break;
                }
//               
            }catch(Exception ex){ // captura de la excepción
                System.err.println("se ha producido la siguiente excepcion: "+ ex);
            } 
        }while(opcion !=0); 
        System.exit(0);
    }  

}

    
    

