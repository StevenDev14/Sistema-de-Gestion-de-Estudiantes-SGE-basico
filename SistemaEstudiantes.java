// ================================================================
//  Archivo: SistemaEstudiantes.java
//  Descripción: Lógica principal del SGE con menú y funciones
// ================================================================
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaEstudiantes {

    // ── Arreglo dinámico de estudiantes (almacenamiento) ───────
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    // ── Variables especiales de control ────────────────────────
    private int     contadorIds = 1;  // Contador autoincremental de IDs
    private boolean ejecutando  = true;  // Bandera de control del bucle
    private Scanner scanner     = new Scanner(System.in);

    // ── Método principal: menú con bucle while ─────────────────
    public void iniciar() {
        while (ejecutando) {            // Estructura repetitiva (bandera)
            mostrarMenu();
            String opcion = scanner.nextLine().trim();
            procesarOpcion(opcion);     // Delega a estructura condicional
        }
    }

    // ── Muestra el menú principal (E/S) ───────────────────────
    private void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE ESTUDIANTES   ");
        System.out.println("========================================");
        System.out.println("  1. Agregar nuevo estudiante");
        System.out.println("  2. Mostrar lista completa");
        System.out.println("  3. Buscar estudiante por ID");
        System.out.println("  4. Salir del sistema");
        System.out.println("----------------------------------------");
        System.out.print("Seleccione una opción (1-4): ");
    }

    // ── Procesa la opción elegida (switch = estructura selectiva)
    private void procesarOpcion(String opcion) {
        switch (opcion) {
            case "1": agregarEstudiante();   break;
            case "2": mostrarEstudiantes();  break;
            case "3": buscarPorId();         break;
            case "4":
                System.out.println("\nCerrando el sistema. ¡Hasta pronto!");
                ejecutando = false;          // Cambia bandera → termina bucle
                break;
            default:
                System.out.println("[Error] Opción inválida. Ingrese 1, 2, 3 o 4.");
        }
    }

    // ── Función: Agregar nuevo estudiante ──────────────────────
    private void agregarEstudiante() {
        System.out.println("\n--- Agregar Nuevo Estudiante ---");

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("[Error] El nombre no puede estar vacío.");
            return;
        }

        double calificacion;
        try {
            System.out.print("Calificación (0-100): ");
            calificacion = Double.parseDouble(scanner.nextLine().trim());
            if (calificacion < 0 || calificacion > 100) {
                System.out.println("[Error] La calificación debe estar entre 0 y 100.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Ingrese un número válido.");
            return;
        }

        Estudiante nuevo = new Estudiante(contadorIds, nombre, calificacion);
        estudiantes.add(nuevo);
        contadorIds++;

        System.out.printf("[OK] Estudiante '%s' agregado con ID %d.%n",
                          nombre, nuevo.getId());
    }

    // ── Función: Mostrar todos los estudiantes ─────────────────
    private void mostrarEstudiantes() {
        System.out.println("\n--- Lista de Estudiantes ---");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.printf("%-8s %-30s %10s%n", "ID", "Nombre", "Calificación");
        System.out.println("-".repeat(50));
        for (Estudiante est : estudiantes) {
            System.out.println(est.toString());
        }
        System.out.printf("%nTotal de registros: %d%n", estudiantes.size());
    }

    // ── Función: Buscar estudiante por ID ──────────────────────
    private void buscarPorId() {
        System.out.println("\n--- Buscar Estudiante por ID ---");
        int idBuscar;
        try {
            System.out.print("Ingrese el ID a buscar: ");
            idBuscar = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("[Error] El ID debe ser un número entero.");
            return;
        }

        boolean encontrado = false;         // Variable bandera de búsqueda
        for (Estudiante est : estudiantes) {
            if (est.getId() == idBuscar) {
                encontrado = true;
                System.out.println("\n[Resultado encontrado]");
                System.out.println("  ID          : " + est.getId());
                System.out.println("  Nombre      : " + est.getNombre());
                System.out.printf( "  Calificación: %.1f%n", est.getCalificacion());
                break;
            }
        }
        if (!encontrado) {
            System.out.println("[Info] No se encontró ningún estudiante con ID " + idBuscar + ".");
        }
    }
}