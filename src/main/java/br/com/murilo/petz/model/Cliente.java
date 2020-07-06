package br.com.murilo.petz.model;

import br.com.murilo.petz.model.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, nullable = false)
    private Long cpf;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "idade")
    private int idade;

    @Column(name = "telefone")
    private int telefone;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(targetEntity = Pet.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Pet> pets;

    public Cliente() {
        this.pets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(final Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(final int idade) {
        this.idade = idade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(final int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(final List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(final Pet pet) {
        this.pets.add(pet);
    }

    public void removePet(final Pet pet) {
        this.pets.remove(pet);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        final Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId()) &&
                getCpf().equals(cliente.getCpf()) &&
                getEmail().equals(cliente.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf(), getEmail());
    }
}
