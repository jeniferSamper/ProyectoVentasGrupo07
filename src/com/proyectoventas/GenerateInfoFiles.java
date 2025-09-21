package com.proyectoventas;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Clase encargada de generar archivos de informacion de prueba para el sistema de ventas.
 * <p>
 * Permite crear archivos de vendedores, productos y ventas de forma automatica,
 * asi como cargar datos de ejemplo listos para probar el sistema.
 * </p>
 */
public class GenerateInfoFiles {

    /** Generador de numeros aleatorios. */
    private static final Random random = new Random();

    /** Lista de nombres de ejemplo para vendedores. */
    private static final String[] NOMBRES = {
        "Carlos", "Ana", "Luis", "Sofia", "Pedro", "Maria", "Jorge", "Laura"
    };

    /** Lista de apellidos de ejemplo para vendedores. */
    private static final String[] APELLIDOS = {
        "Perez", "Garcia", "Rodriguez", "Lopez", "Martinez", "Hernandez"
    };

    /** Lista de nombres de productos de ejemplo. */
    private static final String[] PRODUCTOS = {
        "Camisa", "Pantalon", "Zapatos", "Gorra", "Reloj", "Bolso"
    };

    /**
     * Metodo principal que genera diferentes archivos de prueba.
     * <p>
     * Flujo:
     * <ul>
     *   <li>Entrega 1: Genera vendedores, productos y archivos de ventas simuladas.</li>
     *   <li>Entrega 2: Genera archivos estaticos con datos de productos, vendedores y ventas.</li>
     *   <li>Ejecuta automaticamente la clase {@link Main} para procesar los archivos creados.</li>
     * </ul>
     * </p>
     *
     * @param args Argumentos de linea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // -----------------------------
            // Entrega 1: Datos generados aleatoriamente
            // -----------------------------
            createSalesManInfoFile(5);
            createProductsFile(6);
            createSalesMenFiles(5, 10);

            // -----------------------------
            // Entrega 2: Datos fijos de ejemplo
            // -----------------------------
            Path dataDir = Paths.get("data");
            Path ventasDir = dataDir.resolve("ventas");
            Files.createDirectories(ventasDir);

            // Crear archivo de productos
            Files.write(dataDir.resolve("productos.txt"), Arrays.asList(
                "P001;Manzanas;1200",
                "P002;Naranjas;800",
                "P003;Peras;1500",
                "P004;Bananas;900"
            ), StandardCharsets.UTF_8);

            // Crear archivo de vendedores
            Files.write(dataDir.resolve("vendedores.txt"), Arrays.asList(
                "CC;111;Juan;Pérez",
                "CC;112;Ana;López",
                "CC;113;Luis;García"
            ), StandardCharsets.UTF_8);

            // Crear archivos de ventas
            Files.write(ventasDir.resolve("ventas_111.txt"), Arrays.asList(
                "VENTAS;111",
                "P001;5;P002;3;P004;2"
            ), StandardCharsets.UTF_8);

            Files.write(ventasDir.resolve("ventas_112.txt"), Arrays.asList(
                "VENTAS;112",
                "P002;6;P003;4"
            ), StandardCharsets.UTF_8);

            Files.write(ventasDir.resolve("ventas_113.txt"), Arrays.asList(
                "VENTAS;113",
                "P001;2;P003;1;P004;7"
            ), StandardCharsets.UTF_8);

            // Ejecutar automaticamente el programa principal para generar reportes
            Main.main(new String[]{});

            System.out.println("✅ Archivos generados correctamente (Entrega 1 + Entrega 2).");
        } catch (IOException e) {
            System.err.println("❌ Error al generar archivos: " + e.getMessage());
        }
    }

    /**
     * Genera un archivo de vendedores con datos aleatorios.
     *
     * @param salesmanCount Numero de vendedores a generar.
     * @throws IOException Si ocurre un error al escribir el archivo.
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
     * Genera un archivo de productos con datos aleatorios.
     *
     * @param productsCount Numero de productos a generar.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public static void createProductsFile(int productsCount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt"))) {
            for (int i = 1; i <= productsCount; i++) {
                String idProducto = "P" + i;
                String nombre = PRODUCTOS[random.nextInt(PRODUCTOS.length)];
                double precio = 10000 + random.nextInt(90000);
                bw.write(idProducto + ";" + nombre + ";" + precio);
                bw.newLine();
            }
        }
    }

    /**
     * Genera archivos de ventas para varios vendedores.
     *
     * @param salesmanCount Numero de vendedores.
     * @param ventasPorVendedor Numero de ventas por cada vendedor.
     * @throws IOException Si ocurre un error al escribir los archivos.
     */
    public static void createSalesMenFiles(int salesmanCount, int ventasPorVendedor) throws IOException {
        for (int i = 1; i <= salesmanCount; i++) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("ventas_vendedor_" + i + ".txt"))) {
                // Cabecera con identificacion del vendedor
                String tipoDoc = "CC";
                long numDoc = 1000 + random.nextInt(9000);
                bw.write(tipoDoc + ";" + numDoc);
                bw.newLine();

                // Ventas simuladas del vendedor
                for (int j = 1; j <= ventasPorVendedor; j++) {
                    String idProducto = "P" + (1 + random.nextInt(6));
                    int cantidad = 1 + random.nextInt(5);
                    bw.write(idProducto + ";" + cantidad);
                    bw.newLine();
                }
            }
        }
    }
}
