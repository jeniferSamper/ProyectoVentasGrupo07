package com.proyectoventas;

/**
 * Representa un producto disponible para la venta.
 * <p>
 * Cada producto tiene un identificador unico, un nombre y un precio unitario.
 * </p>
 */
public class Product {

    /** Identificador unico del producto. */
    private String id;

    /** Nombre del producto. */
    private String name;

    /** Precio unitario del producto. */
    private double unitPrice;

    /**
     * Constructor que inicializa un producto con sus datos basicos.
     *
     * @param id        Identificador unico del producto.
     * @param name      Nombre del producto.
     * @param unitPrice Precio unitario del producto.
     */
    public Product(String id, String name, double unitPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    /**
     * Obtiene el identificador del producto.
     *
     * @return ID del producto.
     */
    public String getId() { return id; }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre del producto.
     */
    public String getName() { return name; }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return Precio unitario.
     */
    public double getUnitPrice() { return unitPrice; }
}
