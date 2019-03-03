/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: NodoCola.java,v 1.13 2008/09/30 16:09:00 alf-mora Exp $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Framework: Cupi2Collections
 * Autor: Jorge Villalobos - Abr 4, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package model.data_structures;

import java.io.Serializable;

/**
 * Nodo de la cola encadenada
 * @param <T> Tipo de elemento que va a contener la cola
 */
public class NodoCola<T extends Comparable<T>> implements Serializable
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
    /**
	 * Constante para la serialización
	 */
	private static final long serialVersionUID = 1L;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Elemento del nodo
     */
    private T elemento;

    /**
     * Siguiente elemento encadenado
     */
    private NodoCola<T> sigNodo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor por parámetros del nodo. <br>
     * <b>post: </b> Se construyó el nodo con el elemento especificado, sigNodo=null y elemento = pElemento.<br>
     * @param pElemento Elemento que va a ser almacenado en el nodo. Diferente de null<br>
     */
    public NodoCola( T pElemento )
    {
        elemento = pElemento;
        sigNodo = null;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el elemento del nodo. <br>
     * <b>post: </b> Se retornó el elemento contenido en el nodo.<br>
     * @return El elemento contenido en el nodo. Diferente de null<br>
     */
    public T darElemento( )
    {
        return elemento;
    }

    /**
     * Desconecta el nodo de la cola suponiendo que es el primero. <br>
     * <b>pre: </b> El nodo actual es el primer nodo de la cola. <br>
     * <b>post: </b> Se retornó el nodo con el cual comienza la cola ahora, sigNodo=null.<br>
     * @return Nodo con el cual comienza la cola ahora.<br>
     */
    public NodoCola<T> desconectarPrimero( )
    {
        NodoCola<T> p = sigNodo;
        sigNodo = null;
        return p;
    }
    
    

    /**
     * Inserta el nodo especificado después del nodo actual. <br>
     * <b>post: </b> Se insertó el nodo después del nodo actual lo que implica que sigNodo=nodo.<br>
     * @param nodo El nodo a ser insertado<br>
     * @return Nodo que se insertó después del nodo actual<br>
     */
    public NodoCola<T> insertarDespues( NodoCola<T> nodo )
    {
        sigNodo = nodo;
        return nodo;
    }

    /**
     * Retorna el siguiente nodo. <br>
     * <b>post: </b> Se retornó el siguiente nodo.<br>
     * @return El nodo siguiente<br>
     */
    public NodoCola<T> darSiguiente( )
    {
        return sigNodo;
    }

    /**
     * Convierte el nodo a un String. <br>
     * <b>post: </b> Se retornó la representación en String del nodo. Dicha representación corresponde a la representación en String del elemento que contiene.
     * @return La representación en String del nodo
     */
    @Override
    public String toString( )
    {
        return elemento.toString( );
    }
}
