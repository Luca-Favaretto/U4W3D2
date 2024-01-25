package it.epicode;

import it.epicode.dao.EventoDAO;
import it.epicode.dao.LocationDAO;
import it.epicode.dao.PartecipazioneDAO;
import it.epicode.dao.PersonaDAO;
import it.epicode.entities.Evento;
import it.epicode.entities.Location;
import it.epicode.entities.Partecipazione;
import it.epicode.entities.Persona;
import it.epicode.enumpack.Genere;
import it.epicode.enumpack.Sesso;
import it.epicode.enumpack.Stato;
import it.epicode.enumpack.TipoEvento;
import it.epicode.subclassevento.Concerto;
import it.epicode.subclassevento.GaraDiAtletica;
import it.epicode.subclassevento.PartitaDiCalcio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            Concerto cv1 = new Concerto("Orman show", LocalDate.now(), "Bellisimo", TipoEvento.PRIVATO, 20, loc1, Genere.ROCK, true);
            Persona ps1 = new Persona("Luca", "Fava", "fava@iammi.it", LocalDate.parse("1995-01-01"), Sesso.M);
            Persona ps2 = new Persona("Marta", "Fava", "fava@iammi.it", LocalDate.parse("1995-01-01"), Sesso.F);

            PartitaDiCalcio par1 = new PartitaDiCalcio("serieA", LocalDate.now(), "roma-napoli", TipoEvento.PUBBLICO, 3000, loc1, "Roma", "Napoli", 2, 0);
            PartitaDiCalcio par2 = new PartitaDiCalcio("serieB", LocalDate.now(), "milano-monza", TipoEvento.PUBBLICO, 3000, loc1, "Milano", "Monza", 2, 0);
            PartitaDiCalcio par3 = new PartitaDiCalcio("serieC", LocalDate.now(), "inter-juve", TipoEvento.PUBBLICO, 3000, loc1, "Inter", "Juve", 0, 0);
            PartitaDiCalcio par4 = new PartitaDiCalcio("serieD", LocalDate.now(), "verona-fiorentina", TipoEvento.PUBBLICO, 3000, loc1, "Verona", "Fiorentina", 1, 3);

            GaraDiAtletica gar1 = new GaraDiAtletica("Gara1", LocalDate.now(), "veloce", TipoEvento.PUBBLICO, 2, loc1);
            GaraDiAtletica gar2 = new GaraDiAtletica("Gara2", LocalDate.now(), "lento", TipoEvento.PUBBLICO, 2, loc1);
            Partecipazione pa1 = new Partecipazione(ps1, gar1, Stato.DA_CONFERMARE);
            Partecipazione pa2 = new Partecipazione(ps2, gar1, Stato.DA_CONFERMARE);

            Set<Persona> atleti = new HashSet<>() {
            };
            atleti.add(ps1);
            atleti.add(ps2);

            gar1.setAtleti(atleti);
            gar1.setVincitore(ps1);

            gar2.setAtleti(atleti);
            gar2.setVincitore(ps2);

            locationDAO.save(loc1);
            eventoDAO.save(cv1);
            personaDAO.save(ps1);
            personaDAO.save(ps2);

            eventoDAO.save(par1);
            eventoDAO.save(par2);
            eventoDAO.save(par3);
            eventoDAO.save(par4);
            eventoDAO.save(gar1);
            eventoDAO.save(gar2);
            partecipazioneDAO.save(pa1);
            partecipazioneDAO.save(pa2);

            List<Concerto> inStreaming = eventoDAO.getConcertiInStreaming(true);
            List<Concerto> notStreaming = eventoDAO.getConcertiInStreaming(false);
            List<Concerto> rock = eventoDAO.getConcertiPerGenere(Genere.ROCK);
            System.out.println("eventi in streaming");
            inStreaming.forEach(System.out::println);
            System.out.println("eventi non streaming");
            notStreaming.forEach(System.out::println);
            System.out.println("eventi in streaming");
            rock.forEach(System.out::println);

            List<PartitaDiCalcio> vinteInCasa = eventoDAO.vinteInCasa();
            List<PartitaDiCalcio> vinteInTrasferta = eventoDAO.vinteInTrasferta();
            List<PartitaDiCalcio> pareggi = eventoDAO.pareggi();

            List<GaraDiAtletica> vinteDAps1 = eventoDAO.getGareDiAtleticaPerVincitore(ps1);
            List<GaraDiAtletica> partecipateDAps2 = eventoDAO.getGareDiAtleticaPerPartecipante(ps2);

            List<Evento> eventiSoulOut = eventoDAO.getEventiSoldOut();
            List<Partecipazione> gara1DaConfermare = eventoDAO.getPartecipazioniDaConfermarePerEvento(gar1);


            System.out.println("gare di calcio vinte in casa");
            vinteInCasa.forEach(System.out::println);
            System.out.println("gare di calcio vinte in trasferta");
            vinteInTrasferta.forEach(System.out::println);
            System.out.println("gara in pareggio");
            pareggi.forEach(System.out::println);

            System.out.println("gare dove ha vinto " + ps1);
            vinteDAps1.forEach(System.out::println);
            System.out.println("gare dove ha partecipato " + ps2);
            partecipateDAps2.forEach(System.out::println);

            System.out.println("eventi Soul-Out");
            eventiSoulOut.forEach(System.out::println);
            System.out.println("partecipanti gara 1 da confermare");
            gara1DaConfermare.forEach(System.out::println);


        } catch (Exception ex) {
            System.err.println("L'errore è " + ex);
        } finally {
            em.close();
            emf.close();
        }


    }

}
