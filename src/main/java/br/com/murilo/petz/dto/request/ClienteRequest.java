package br.com.murilo.petz.dto.request;

import br.com.murilo.petz.dto.response.PetResponse;

import java.util.List;
import java.util.Objects;

public class ClienteRequest {

    private Long id;
    private String cpf;
    private String nome;
    private String sexo;
    private int idade;
    private int telefone;
    private String email;
    private List<PetRequest> pets;

    public ClienteRequest(final Long id, final String cpf, final String nome, final String sexo, final int idade, final int telefone, final String email, final List<PetRequest> pets) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(final String cpf) {
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

    public List<PetRequest> getPets() {
        return pets;
    }

    public void setPets(final List<PetRequest> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ClienteRequest)) return false;
        final ClienteRequest that = (ClienteRequest) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
