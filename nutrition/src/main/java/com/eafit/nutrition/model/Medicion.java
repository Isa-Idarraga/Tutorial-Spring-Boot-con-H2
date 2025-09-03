package com.eafit.nutrition.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicion")
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha", nullable=false)
    private LocalDate fecha;

    @Column(name="peso", nullable=false)
    private Double peso;               // kg

    @Column(name="altura", nullable=false)
    private Double altura;             // cm

    @Column(name="circunferencia_cintura")
    private Double circunferenciaCintura;   // cm

    @Column(name="circunferencia_cadera")
    private Double circunferenciaCadera;    // cm

    @Column(name="porcentaje_grasa_corporal")
    private Double porcentajeGrasaCorporal;

    // >>> RELACIONES (con getters/setters) <<<
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="paciente_id", nullable=false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="nutricionista_id", nullable=false)
    private Nutricionista nutricionista;

    public Medicion() {}

    // --- Lógica simple (del tutorial) ---
    public Double calcularIMC() {
        if (altura == null || peso == null || altura <= 0) return null;
        double alturaM = altura / 100.0;
        return peso / (alturaM * alturaM);
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getCircunferenciaCintura() { return circunferenciaCintura; }
    public void setCircunferenciaCintura(Double c) { this.circunferenciaCintura = c; }

    public Double getCircunferenciaCadera() { return circunferenciaCadera; }
    public void setCircunferenciaCadera(Double c) { this.circunferenciaCadera = c; }

    public Double getPorcentajeGrasaCorporal() { return porcentajeGrasaCorporal; }
    public void setPorcentajeGrasaCorporal(Double p) { this.porcentajeGrasaCorporal = p; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Nutricionista getNutricionista() { return nutricionista; }
    public void setNutricionista(Nutricionista nutricionista) { this.nutricionista = nutricionista; }
}
