package br.com.murilo.petz.dto.response;

import java.util.Objects;

public class PetResponse {

    private Long id;
    private String nome;
    private String raca;

    public PetResponse(final Long id, final String nome, final String raca) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
    }

    public PetResponse() {
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
        if (!(o instanceof PetResponse)) return false;
        final PetResponse that = (PetResponse) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getRaca(), that.getRaca());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getRaca());
    }
}
