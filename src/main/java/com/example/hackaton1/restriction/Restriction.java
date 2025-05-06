package com.example.hackaton1.restriction;

import com.example.hackaton1.company.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restriction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TipoModelo modelo;

    private TipoLimite tipoLimite;

    private Long valor;

    private VentanaTiempo ventanaTiempo;


    // Empresa a la que pertenece esta restricción
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Company company;

}