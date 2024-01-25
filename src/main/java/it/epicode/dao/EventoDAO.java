package it.epicode.dao;

import it.epicode.entities.Evento;
import it.epicode.enumpack.Genere;
import it.epicode.subclassevento.Concerto;
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
