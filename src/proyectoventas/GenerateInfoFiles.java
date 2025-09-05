package com.proyectoventas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase GenerateInfoFiles
 *
 * Entrega 1 - Semana 3.
 * 
 * Esta clase se encarga de generar archivos de prueba pseudoaleatorios
 * que serviran como entrada para el sistema de ventas.
 *
 * Archivos generados:
 * <ul>
 *   <li>vendedores.txt</li>
 *   <li>productos.txt</li>
 *   <li>ventas_vendedor_X.txt</li>
 * </ul>
 *
 * @author Eliu
 * @author Jenifer Samper
 */
public class GenerateInfoFiles {

    /**
     * Constructor por defecto de la clase.
     * 
     * No recibe parametros y no realiza ninguna inicializacion especial,
     * ya que todos los metodos son estaticos.
     */
    public GenerateInfoFiles() {
        // Constructor vacio (usado solo para documentacion).
    }

    // Listas de ejemplo para generar datos aleatorios
    private static final String[] NOMBRES = {
        "Carlos", "Ana", "Luis", "Sofia", "Pedro", "Maria", "Jorge", "Laura"
    };

    private static final String[] APELLIDOS = {
        "Perez", "Garcia", "Rodriguez", "Lopez", "Martinez", "Hernandez"
    };

    private static final String[] PRODUCTOS = {
        "Camisa", "Pantalon", "Zapatos", "Gorra", "Reloj", "Bolso"
    };

    private static Random random = new Random();

    /**
     * Metodo principal. Ejecuta la generacion de todos los
     * archivos de prueba (vendedores, productos y ventas).
     *
     * @param args argumentos de linea de comandos (no utilizados en este programa)
     */
    public static void main(String[] args) {
        try {
            // Generar archivos de prueba
            createSalesManInfoFile(5); // 5 vendedores
            createProductsFile(6);     // 6 productos
            createSalesMenFiles(5, 10); // 5 archivos de ventas (10 ventas cada uno)

            System.out.println("✅ Archivos generados correctamente.");
        } catch (IOException e) {
            System.err.println("❌ Error al generar archivos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo con informacion de vendedores.
     * 
     * @param salesmanCount cantidad de vendedores a generar
     * @throws IOException si ocurre un error de escritura en el archivo
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("vendedores.txt"))) {
            for (int i = 1; i <= salesmanCount; i++) {
                String tipoDoc = "CC";
                long numDoc = 1000 + random.nextInt(9000);
                String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[random.nextInt(APELLIDOS.length)];

                bw.write(tipoDoc + ";" + numDoc + ";" + nombre + ";" + apellido);
                bw.newLine();
            }
        }
    }

    /**
     * Genera un archivo con informacion de productos.
     * 
     * @param productsCount cantidad de productos a generar
     * @throws IOException si ocurre un error de escritura en el archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt"))) {
            for (int i = 1; i <= productsCount; i++) {
                String idProducto = "P" + i;
                String nombre = PRODUCTOS[random.nextInt(PRODUCTOS.length)];
                double precio = 10000 + random.nextInt(90000); // precios entre 10k y 100k

                bw.write(idProducto + ";" + nombre + ";" + precio);
                bw.newLine();
            }
        }
    }

    /**
     * Genera archivos de ventas para cada vendedor.
     *
     * @param salesmanCount cantidad de vendedores a generar
     * @param ventasPorVendedor cantidad de ventas que tendra cada vendedor
     * @throws IOException si ocurre un error de escritura en el archivo
     */
    public static void createSalesMenFiles(int salesmanCount, int ventasPorVendedor) throws IOException {
        for (int i = 1; i <= salesmanCount; i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("ventas_vendedor_" + i + ".txt"))) {
                String tipoDoc = "CC";
                long numDoc = 1000 + random.nextInt(9000);

                // Primera linea: info del vendedor
                bw.write(tipoDoc + ";" + numDoc);
                bw.newLine();

                // Ventas
                for (int j = 1; j <= ventasPorVendedor; j++) {
                    String idProducto = "P" + (1 + random.nextInt(6)); // asume 6 productos
                    int cantidad = 1 + random.nextInt(5); // entre 1 y 5 unidades
                    bw.write(idProducto + ";" + cantidad);
                    bw.newLine();
                }
            }
        }
    }
}
