package br.com.murilo.petz.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String nome;

    @Column(name = "raca", nullable = false)
    private String raca;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(final String raca) {
        this.raca = raca;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        final Pet pet = (Pet) o;
        return getId().equals(pet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
