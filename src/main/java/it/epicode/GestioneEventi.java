package it.epicode;

import it.epicode.baseclass.Evento;
import it.epicode.baseclass.TipoEvento;
import it.epicode.dao.EventoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class GestioneEventi {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {


        EntityManager em = emf.createEntityManager();
        EventoDAO ed = new EventoDAO(em);


        Evento ev = new Evento("titolo", (LocalDate.now()), "descrizione", TipoEvento.PRIVATO, 10);

        ed.save(ev);


        System.out.println(ed.findById(2));

        ed.findByIdAndDelete(1);

        em.close();
        emf.close();

    }

}
