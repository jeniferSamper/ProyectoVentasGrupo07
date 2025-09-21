package com.proyectoventas;

/**
 * Representa a un vendedor dentro del sistema.
 * <p>
 * Un vendedor se identifica por un tipo y numero de documento,
 * ademas de sus nombres y apellidos.
 * </p>
 */
public class Salesman {

    /** Tipo de documento del vendedor (CC, TI, Pasaporte, etc.). */
    private String docType;

    /** Numero de documento del vendedor. */
    private String docNumber;

    /** Primer nombre del vendedor. */
    private String firstName;

    /** Apellido del vendedor. */
    private String lastName;

    /**
     * Constructor que inicializa un vendedor con su informacion basica.
     *
     * @param docType   Tipo de documento.
     * @param docNumber Numero de documento.
     * @param firstName Primer nombre.
     * @param lastName  Apellido.
     */
    public Salesman(String docType, String docNumber, String firstName, String lastName) {
        this.docType = docType;
        this.docNumber = docNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Obtiene el numero de documento del vendedor.
     *
     * @return Numero de documento.
     */
    public String getDocNumber() { return docNumber; }

    /**
     * Obtiene el nombre completo del vendedor.
     *
     * @return Nombre y apellido concatenados.
     */
    public String getFullName() { return firstName + " " + lastName; }
}
