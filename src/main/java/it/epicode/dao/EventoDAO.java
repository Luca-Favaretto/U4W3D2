package it.epicode.dao;

import it.epicode.entities.Evento;
import it.epicode.entities.Partecipazione;
import it.epicode.entities.Persona;
import it.epicode.enumpack.Genere;
import it.epicode.enumpack.Stato;
import it.epicode.subclassevento.Concerto;
import it.epicode.subclassevento.GaraDiAtletica;
import it.epicode.subclassevento.PartitaDiCalcio;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventoDAO {
    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
    }

    public List<Partecipazione> getPartecipazioniDaConfermarePerEvento(Evento evento) {
        TypedQuery<Partecipazione> query = em.createQuery("SELECT p FROM Partecipazione p WHERE p.stato = :statoDaConfermare AND p.evento = :evento", Partecipazione.class);
        query.setParameter("statoDaConfermare", Stato.DA_CONFERMARE);
        query.setParameter("evento", evento);
        return query.getResultList();
    }

    public List<Evento> getEventiSoldOut() {
        TypedQuery<Evento> query = em.createQuery("SELECT e FROM Evento e WHERE e.numeroMassimoPartecipanti=SIZE(e.partecipazioni)", Evento.class);

        return query.getResultList();
    }

    public List<GaraDiAtletica> getGareDiAtleticaPerPartecipante(Persona atleta) {
        TypedQuery<GaraDiAtletica> query = em.createQuery("SELECT g FROM GaraDiAtletica g WHERE :atleta MEMBER OF g.atleti", GaraDiAtletica.class);
        query.setParameter("atleta", atleta);
        return query.getResultList();
    }

    public List<GaraDiAtletica> getGareDiAtleticaPerVincitore(Persona vincitore) {
        TypedQuery<GaraDiAtletica> query = em.createQuery("SELECT g FROM GaraDiAtletica g WHERE g.vincitore=:vincitore", GaraDiAtletica.class);
        query.setParameter("vincitore", vincitore);
        return query.getResultList();
    }

    public List<PartitaDiCalcio> pareggi() {
        TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("getPareggi", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<PartitaDiCalcio> vinteInCasa() {
        TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("getPartiteVinteInCasa", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<PartitaDiCalcio> vinteInTrasferta() {
        TypedQuery<PartitaDiCalcio> query = em.createNamedQuery("getPartiteVinteInTrasferta", PartitaDiCalcio.class);
        return query.getResultList();
    }

    public List<Concerto> getConcertiInStreaming(boolean bool) {
        TypedQuery<Concerto> getAllStreamingConcert = em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming= :bool", Concerto.class);
        getAllStreamingConcert.setParameter("bool", bool);
        return getAllStreamingConcert.getResultList();
    }

    public List<Concerto> getConcertiPerGenere(Genere genere) {
        TypedQuery<Concerto> getAllConcertType = em.createQuery("SELECT c FROM Concerto c WHERE c.genere=:genere", Concerto.class);
        getAllConcertType.setParameter("genere", genere);
        return getAllConcertType.getResultList();
    }

    public void save(Evento evento) {

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(evento);

        transaction.commit();

        System.out.println("Evento " + evento.getTitolo() + " aggiunto correttamente!");
    }

    public Evento findById(long id) {

        return em.find(Evento.class, id);
    }

    public void findByIdAndDelete(long id) {

        Evento found = this.findById(id);

        if (found != null) {

            EntityTransaction transaction = em.getTransaction();


            transaction.begin();


            em.remove(found);


            transaction.commit();

            System.out.println("Evento " + found.getTitolo() + " eliminato correttamente!");

        } else {

            System.out.println("L'evento con l'id " + id + " non è stato trovato");
        }
    }

}
