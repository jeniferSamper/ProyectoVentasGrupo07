package com.proyectoventas;

/**
 * Representa un item dentro de una venta.
 * <p>
 * Un item esta compuesto por el identificador de un producto y la cantidad
 * vendida de ese producto.
 * </p>
 */
public class SaleItem {

    /** Identificador del producto asociado al item. */
    private String productId;

    /** Cantidad de unidades vendidas del producto. */
    private int quantity;

    /**
     * Constructor que inicializa un item de venta con el producto y la cantidad.
     *
     * @param productId Identificador del producto.
     * @param quantity  Cantidad de unidades vendidas.
     */
    public SaleItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Obtiene el identificador del producto de este item.
     *
     * @return ID del producto.
     */
    public String getProductId() { return productId; }

    /**
     * Obtiene la cantidad vendida del producto en este item.
     *
     * @return Cantidad vendida.
     */
    public int getQuantity() { return quantity; }
}
