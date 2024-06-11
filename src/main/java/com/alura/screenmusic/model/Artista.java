package com.alura.screenmusic.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Carreira carreia;

    public Artista(String nome, Carreira carreiraArtista) {
        this.nome = nome;
        this.carreia =carreiraArtista;
    }

    public Artista() {

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public Carreira getCarreia() {
        return carreia;
    }

    public void setCarreia(Carreira carreia) {
        this.carreia = carreia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "Nome='" + nome + '\'' +
                ", musicas=" + musicas +
                ", carreia=" + carreia;
    }
}
