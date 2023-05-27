package jeu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PlateauTests {
    private Plateau plateau;
    private Bateau bateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau();
        bateau = new Bateau("Bateau Test", 3, 0.1f); // Crée un bateau de taille 3
    }

    @Test
    public void testPlacerBateau() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };

        assertDoesNotThrow(() -> plateau.placerBateau(bateau, coordonnees));
        assertEquals(1, plateau.getBateaux().size());
    }

    @Test
    public void testPlacerBateau_tailleInvalide() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2)
        };

        assertThrows(ReglesException.class, () -> plateau.placerBateau(bateau, coordonnees));
    }

    @Test
    public void testPlacerBateau_horsPlateau() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 10),
                new Coordonnee(1, 11),
                new Coordonnee(1, 12)
        };

        assertThrows(ReglesException.class, () -> plateau.placerBateau(bateau, coordonnees));
    }

    @Test
    public void testPlacerBateau_coordonneeOccupee() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };

        try {
            plateau.placerBateau(bateau, coordonnees);
        } catch (ReglesException e) {
            // L'exception est attendue
            System.out.println("Erreur: " + e.getMessage());
            return;
        }

        assertThrows(ReglesException.class, () -> plateau.placerBateau(bateau, coordonnees));
    }

    @Test
    public void testRecevoirAttaque_touche() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };
        try {
            plateau.placerBateau(bateau, coordonnees);
        } catch (ReglesException e) {
            // L'exception est attendue
            System.out.println("Erreur: " + e.getMessage());
            return;
        }

        ResultatAttaque resultat = plateau.recevoirAttaque(new Coordonnee(1, 2));

        ResultatAttaque[] expectedResponses = {ResultatAttaque.TOUCHE, ResultatAttaque.EVITE};
        assertTrue(Arrays.asList(expectedResponses).contains(resultat));
    }

    @Test
    public void testRecevoirAttaque_coule() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };

        try {
            plateau.placerBateau(bateau, coordonnees);
        } catch (ReglesException e) {
            // L'exception est attendue
            System.out.println("Erreur: " + e.getMessage());
            return;
        }

        ResultatAttaque resultat1 = plateau.recevoirAttaque(new Coordonnee(1, 1));
        ResultatAttaque resultat2 = plateau.recevoirAttaque(new Coordonnee(1, 2));
        ResultatAttaque resultat3 = plateau.recevoirAttaque(new Coordonnee(1, 3));


        ResultatAttaque[] expectedResponses = {ResultatAttaque.TOUCHE, ResultatAttaque.EVITE};
        ResultatAttaque[] expectedResponsesCoule = {ResultatAttaque.TOUCHE, ResultatAttaque.EVITE, ResultatAttaque.COULE};
        
        assertTrue(Arrays.asList(expectedResponses).contains(resultat1));
        assertTrue(Arrays.asList(expectedResponses).contains(resultat2));
        assertTrue(Arrays.asList(expectedResponsesCoule).contains(resultat3));
    }

    @Test
    public void testRecevoirAttaque_manque() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };

        try {
            plateau.placerBateau(bateau, coordonnees);
        } catch (ReglesException e) {
            // L'exception est attendue
            System.out.println("Erreur: " + e.getMessage());
            return;
        }

        ResultatAttaque resultat = plateau.recevoirAttaque(new Coordonnee(2, 2));
        assertEquals(ResultatAttaque.MANQUE, resultat);
    }

    @Test
    public void testIsCoordonneeLibre() {
        Coordonnee[] coordonnees = {
                new Coordonnee(1, 1),
                new Coordonnee(1, 2),
                new Coordonnee(1, 3)
        };
        try {
            plateau.placerBateau(bateau, coordonnees);
        } catch (ReglesException e) {
            // L'exception est attendue
            System.out.println("Erreur: " + e.getMessage());
            return;
        }

        assertFalse(plateau.isCoordonneeLibre(new Coordonnee(1, 2)));
        assertTrue(plateau.isCoordonneeLibre(new Coordonnee(2, 2)));
    }
}
