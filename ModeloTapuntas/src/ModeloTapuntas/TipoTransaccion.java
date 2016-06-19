/*
 * Práctica 5(opcional). Codificación
 * Autor: Mario Antonio López Ruiz
 * Curso: 2ºB
 * Grupo de prácticas: B3
 * Profesor de prácticas: Salvador Villena Morales
 */
package ModeloTapuntas;

/**
 *
 * @author marioanloru
 */
public enum TipoTransaccion {
        TARJETA, PAYPAL, EFECTIVO, TRANSFERENCIA;
    @Override
    public String toString(){
    String solucion = "";
    switch (this) {
        case TARJETA: solucion = "TARJETA";
                break;
        case PAYPAL : solucion = "PAYPAL";
                break;
        case EFECTIVO : solucion = "EFECTIVO";
                break;
        case TRANSFERENCIA : solucion = "TRANSEFERENCIA";
                break;
    }
    return solucion;
    
    }
}
