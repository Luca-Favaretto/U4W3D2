package it.epicode.entities;

import it.epicode.enumpack.Sesso;
import it.epicode.subclassevento.GaraDiAtletica;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNasciata;
    @Enumerated(EnumType.STRING)
    private Sesso sesso;

    @ManyToMany
    @JoinTable(name = "gara_persona", joinColumns = @JoinColumn(name = "persona_id"), inverseJoinColumns = @JoinColumn(name = "gara_id"))
    private Set<GaraDiAtletica> gareDiAtletica;
    @OneToMany(mappedBy = "persona")
    private Set<Partecipazione> partecipazioni = new HashSet<>();

    public Persona() {
    }


    public Persona(String nome, String cognome, String email, LocalDate dataDiNasciata, Sesso sesso) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNasciata = dataDiNasciata;
        this.sesso = sesso;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataDiNasciata() {
        return dataDiNasciata;
    }

    public void setDataDiNasciata(LocalDate dataDiNasciata) {
        this.dataDiNasciata = dataDiNasciata;
    }

    public Sesso getSesso() {
        return sesso;
    }

    public void setSesso(Sesso sesso) {
        this.sesso = sesso;
    }


    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNasciata=" + dataDiNasciata +
                ", sesso=" + sesso +
                ", Partecipazione=" + partecipazioni +
                '}';
    }
}
