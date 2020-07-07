package br.com.murilo.petz.dto.request;

import java.util.Objects;

public class PetRequest {

    private Long id;
    private String nome;
    private String raca;

    public PetRequest(final Long id, final String nome, final String raca) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
    }

    public PetRequest() {
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
        if (!(o instanceof PetRequest)) return false;
        final PetRequest that = (PetRequest) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getRaca(), that.getRaca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getRaca());
    }
}
