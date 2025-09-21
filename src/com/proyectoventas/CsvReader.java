package com.proyectoventas;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Clase utilitaria para leer diferentes tipos de archivos de datos (productos, vendedores y ventas).
 * <p>
 * Cada metodo devuelve una estructura de datos lista para ser utilizada por el sistema.
 * </p>
 */
public class CsvReader {

    /**
     * Lee un archivo de productos y los carga en un mapa.
     * <p>
     * Formato esperado de cada linea: <code>ID;Nombre;Precio</code>
     * </p>
     *
     * @param path Ruta del archivo de productos.
     * @return Mapa con clave = ID del producto, valor = objeto {@link Product}.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static Map<String, Product> readProducts(Path path) throws IOException {
        Map<String, Product> map = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                map.put(p[0], new Product(p[0], p[1], Double.parseDouble(p[2])));
            }
        }
        return map;
    }

    /**
     * Lee un archivo de vendedores y los carga en un mapa.
     * <p>
     * Formato esperado de cada linea: <code>TipoDoc;NumeroDoc;Nombre;Apellido</code>
     * </p>
     *
     * @param path Ruta del archivo de vendedores.
     * @return Mapa con clave = Numero de documento, valor = objeto {@link Salesman}.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static Map<String, Salesman> readSalesmen(Path path) throws IOException {
        Map<String, Salesman> map = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                map.put(p[1], new Salesman(p[0], p[1], p[2], p[3]));
            }
        }
        return map;
    }

    /**
     * Lee un archivo de ventas y devuelve la lista de items vendidos.
     * <p>
     * Formato esperado:
     * <ul>
     *   <li>Primera linea: <code>VENTAS;ID_Vendedor</code></li>
     *   <li>Siguientes lineas: pares <code>IDProducto;Cantidad</code></li>
     * </ul>
     * </p>
     *
     * @param path Ruta del archivo de ventas.
     * @param salesmanDoc StringBuilder donde se guarda el ID del vendedor.
     * @return Lista de {@link SaleItem} representando los productos vendidos.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<SaleItem> readSalesFile(Path path, StringBuilder salesmanDoc) throws IOException {
        List<SaleItem> items = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            // Leer cabecera con informacion del vendedor
            String header = br.readLine();
            String[] h = header.split(";");
            salesmanDoc.append(h[1]);

            // Leer productos vendidos
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                for (int i = 0; i < p.length; i += 2) {
                    items.add(new SaleItem(p[i], Integer.parseInt(p[i+1])));
                }
            }
        }
        return items;
    }
}
