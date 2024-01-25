package it.epicode.subclassevento;

import it.epicode.entities.Evento;
import it.epicode.entities.Location;
import it.epicode.enumpack.TipoEvento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "partite_di_calcio")
@NamedQuery(name = "getPartiteVinteInCasa", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraDiCasa=p.squadraVincitore")
@NamedQuery(name = "getPartiteVinteInTrasferta", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraOspite=p.squadraVincitore")
public class PartitaDiCalcio extends Evento {
    @Column(nullable = false)
    private String squadraDiCasa;
    @Column(nullable = false)
    private String squadraOspite;
    private String squadraVincitore;
    @Column(nullable = false)
    private int golSquadraDiCasa;
    @Column(nullable = false)
    private int golSquadraOspite;

    public PartitaDiCalcio() {
    }

    public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento, Integer numeroMassimoPartecipanti, Location location, String squadraDiCasa, String squadraOspite, int golSquadraDiCasa, int golSquadraOspite) {
        super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
        if ((golSquadraDiCasa - golSquadraOspite) > 0) {
            this.squadraVincitore = squadraDiCasa;
        }
        if ((golSquadraDiCasa - golSquadraOspite) < 0) {
            this.squadraVincitore = squadraOspite;
        }
        if ((golSquadraDiCasa - golSquadraOspite) == 0) {
            this.squadraVincitore = null;
        }

        this.squadraDiCasa = squadraDiCasa;
        this.squadraOspite = squadraOspite;

        this.golSquadraDiCasa = golSquadraDiCasa;
        this.golSquadraOspite = golSquadraOspite;
    }

    public String getSquadraDiCasa() {
        return squadraDiCasa;
    }

    public void setSquadraDiCasa(String squadraDiCasa) {
        this.squadraDiCasa = squadraDiCasa;
    }

    public String getSquadraOspite() {
        return squadraOspite;
    }

    public void setSquadraOspite(String squadraOspite) {
        this.squadraOspite = squadraOspite;
    }

    public String getGetSquadraVincitore() {
        return squadraVincitore;
    }

    public void setGetSquadraVincitore(String getSquadraVincitore) {
        this.squadraVincitore = getSquadraVincitore;
    }

    public int getGolSquadraDiCasa() {
        return golSquadraDiCasa;
    }

    public void setGolSquadraDiCasa(int golSquadraDiCasa) {
        this.golSquadraDiCasa = golSquadraDiCasa;
    }

    public int getGolSquadraOspite() {
        return golSquadraOspite;
    }

    public void setGolSquadraOspite(int golSquadraOspite) {
        this.golSquadraOspite = golSquadraOspite;
    }

    @Override
    public String toString() {
        return "PartitaDiCalcio{" +
                "squadraDiCasa='" + squadraDiCasa + '\'' +
                ", squadraOspite='" + squadraOspite + '\'' +
                ", getSquadraVincitore='" + squadraVincitore + '\'' +
                ", golSquadraDiCasa=" + golSquadraDiCasa +
                ", golSquadraOspite=" + golSquadraOspite +
                ", id=" + id +
                ", titolo='" + titolo + '\'' +
                ", dataEvento=" + dataEvento +
                ", descrizione='" + descrizione + '\'' +
                ", tipoEvento=" + tipoEvento +
                ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti +
                ", partecipazioni=" + partecipazioni +
                ", location=" + location +
                '}';
    }
}
