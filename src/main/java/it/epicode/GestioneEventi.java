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

        System.out.println("funz");

        EntityManager em = emf.createEntityManager();
        EventoDAO ed = new EventoDAO(em);


        Evento ev = new Evento("titolo", (LocalDate.parse("2010-01-12")), "descrizione", TipoEvento.PRIVATO, 10);

        ed.save(ev);
        System.out.println(ed.findById(1));
        ed.findByIdAndDelete(1);

    }

}
