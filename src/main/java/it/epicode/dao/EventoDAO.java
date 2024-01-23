package it.epicode.dao;

import it.epicode.baseclass.Evento;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EventoDAO {
    private final EntityManager em;

    public EventoDAO(EntityManager em) {
        this.em = em;
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
