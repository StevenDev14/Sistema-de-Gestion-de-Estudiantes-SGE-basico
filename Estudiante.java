// ================================================================
//  Archivo: Estudiante.java
//  Descripción: Clase modelo que representa un estudiante
// ================================================================
public class Estudiante {
    // ── Atributos de la clase ──────────────────────────────────
    private int    id;
    private String nombre;
    private double calificacion;

    // ── Constructor ────────────────────────────────────────────
    public Estudiante(int id, String nombre, double calificacion) {
        this.id           = id;
        this.nombre       = nombre;
        this.calificacion = calificacion;
    }

    // ── Atributos ────────────────────────────────────────────────
    public int    getId()            { return id; }
    public String getNombre()        { return nombre; }
    public double getCalificacion()  { return calificacion; }

    // ── Representación legible del objeto ──────────────────────
    @Override
    public String toString() {
        return String.format("%-8d %-30s %10.1f", id, nombre, calificacion);
    }
}