
package com.proyectoventas;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

/**
 * Clase utilitaria para escribir archivos CSV o de texto plano.
 * <p>
 * Provee un metodo estatico para guardar listas de cadenas en un archivo,
 * creando automaticamente los directorios necesarios si no existen.
 * </p>
 */
public class CsvWriter {

    /**
     * Escribe una lista de lineas en el archivo especificado.
     * <p>
     * Si la carpeta destino no existe, se crea automaticamente.
     * El archivo se escribe usando la codificacion UTF-8.
     * </p>
     *
     * @param path  Ruta del archivo de salida.
     * @param lines Lista de lineas de texto a escribir.
     * @throws IOException Si ocurre un error al crear directorios o escribir el archivo.
     */
    public static void writeLines(Path path, List<String> lines) throws IOException {
        // Crear directorio padre si no existe
        Files.createDirectories(path.getParent());

        // Abrir escritor de texto con codificacion UTF-8
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            // Recorrer cada linea y escribirla en el archivo
            for (String ln : lines) {
                bw.write(ln);
                bw.newLine(); // salto de linea
            }
        }
    }
}
