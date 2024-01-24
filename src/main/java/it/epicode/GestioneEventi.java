package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.dao.LocationDAO;
import it.epicode.dao.PartecipazioneDAO;
import it.epicode.dao.PersonaDAO;
import it.epicode.entities.Evento;
import it.epicode.entities.Location;
import it.epicode.entities.Partecipazione;
import it.epicode.entities.Persona;
import it.epicode.enumpack.Sesso;
import it.epicode.enumpack.Stato;
import it.epicode.enumpack.TipoEvento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class GestioneEventi {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {


        EntityManager em = emf.createEntityManager();
        try {
            EventoDAO eventoDAO = new EventoDAO(em);
            LocationDAO locationDAO = new LocationDAO(em);
            PersonaDAO personaDAO = new PersonaDAO(em);
            PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);

            Location loc1 = new Location("Grezzan", "Verona");
            Evento ev1 = new Evento("Orman show", LocalDate.now(), "Bellisimo", TipoEvento.PRIVATO, 20, loc1);
            Persona ps1 = new Persona("Luca", "Fava", "fava@iammi.it", LocalDate.parse("1995-01-01"), Sesso.M);
            Partecipazione pa1 = new Partecipazione(ps1, ev1, Stato.CONFERMATO);

            locationDAO.save(loc1);
            eventoDAO.save(ev1);
            personaDAO.save(ps1);
            partecipazioneDAO.save(pa1);
            eventoDAO.findByIdAndDelete(15);
            eventoDAO.findByIdAndDelete(11);
        } catch (Exception ex) {
            System.err.println("L'errore è " + ex);
        } finally {
            em.close();
            emf.close();
        }


    }

}
