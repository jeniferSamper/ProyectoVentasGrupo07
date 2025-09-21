package com.proyectoventas;

import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase principal del sistema de ventas.
 * <p>
 * Se encarga de leer los archivos de entrada (productos, vendedores y ventas),
 * procesar la informacion y generar reportes en formato CSV.
 * </p>
 */
public class Main {

    /**
     * Metodo principal que ejecuta el flujo del programa:
     * <ul>
     *   <li>Crea los directorios de salida si no existen.</li>
     *   <li>Lee los productos y vendedores desde archivos de texto.</li>
     *   <li>Procesa los archivos de ventas para calcular:
     *     <ul>
     *       <li>Cantidad total vendida por producto.</li>
     *       <li>Total de dinero generado por cada vendedor.</li>
     *     </ul>
     *   </li>
     *   <li>Genera dos reportes en formato CSV:
     *     <ul>
     *       <li><b>reporte_vendedores.csv</b>: listado de vendedores ordenados por ventas totales.</li>
     *       <li><b>reporte_productos.csv</b>: listado de productos ordenados por cantidad vendida.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param args Argumentos de linea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // Directorios base, data (entrada) y output (salida de reportes)
            Path base = Paths.get("").toAbsolutePath();
            Path dataDir = base.resolve("data");
            Path outDir = base.resolve("output");
            Files.createDirectories(outDir);

            // Lectura de productos y vendedores
            Map<String, Product> products = CsvReader.readProducts(dataDir.resolve("productos.txt"));
            Map<String, Salesman> salesmen = CsvReader.readSalesmen(dataDir.resolve("vendedores.txt"));

            // Mapas para acumular cantidades y totales de dinero
            Map<String, Integer> productQty = new HashMap<>();
            Map<String, Double> salesmanMoney = new HashMap<>();

            // Procesar todos los archivos de ventas (*.txt)
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(dataDir.resolve("ventas"), "*.txt")) {
                for (Path f : ds) {
                    StringBuilder doc = new StringBuilder();
                    List<SaleItem> items = CsvReader.readSalesFile(f, doc);

                    // Acumular cantidades y montos por vendedor
                    for (SaleItem it : items) {
                        Product p = products.get(it.getProductId());
                        if (p == null) continue;
                        productQty.merge(p.getId(), it.getQuantity(), Integer::sum);
                        salesmanMoney.merge(doc.toString(), p.getUnitPrice() * it.getQuantity(), Double::sum);
                    }
                }
            }

            // Generar reporte de vendedores (ordenado por mayor ventas en dinero)
            List<String> linesSalesmen = salesmanMoney.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .map(e -> salesmen.get(e.getKey()).getFullName() + ";" +
                          String.format(Locale.US,"%.2f", e.getValue()))
                .collect(Collectors.toList());
            CsvWriter.writeLines(outDir.resolve("reporte_vendedores.csv"), linesSalesmen);

            // Generar reporte de productos (ordenado por cantidad vendida)
            List<String> linesProducts = productQty.entrySet().stream()
                .sorted((a, b) -> Integer.compare(b.getValue(), a.getValue()))
                .map(e -> products.get(e.getKey()).getName() + ";" +
                          String.format(Locale.US,"%.2f", products.get(e.getKey()).getUnitPrice()))
                .collect(Collectors.toList());
            CsvWriter.writeLines(outDir.resolve("reporte_productos.csv"), linesProducts);

            System.out.println("✅ Reportes generados en: " + outDir.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
