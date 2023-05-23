package jeu.joueurs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;

public class OrdiHasardTests {

    @Test
    public void testSaisieNom() {
        OrdiHasard ordiHasard = new OrdiHasard();
        
        String nom = ordiHasard.saisieNom();
        
        Assertions.assertNotNull(nom);
        Assertions.assertFalse(nom.isEmpty());
    }

    @Test
    public void testSaisieCoordonnee() {
        OrdiHasard ordiHasard = new OrdiHasard();
        
        Coordonnee coordonnee = ordiHasard.saisieCoordonnee();
        
        Assertions.assertNotNull(coordonnee);
        Assertions.assertTrue(coordonnee.getLigne() >= 0 && coordonnee.getLigne() < Plateau.getNombreLigne());
        Assertions.assertTrue(coordonnee.getColonne() >= 0 && coordonnee.getColonne() < Plateau.getNombreColonne());
    }

    @Test
    public void testSaisieOrientation() {
        OrdiHasard ordiHasard = new OrdiHasard();
        
        Orientation orientation = ordiHasard.saisieOrientation();
        
        Assertions.assertNotNull(orientation);
        Assertions.assertTrue(orientation == Orientation.HORIZONTAL || orientation == Orientation.VERTICAL);
    }

    @Test
    public void testPlacerBateaux() throws ReglesException {
        OrdiHasard ordiHasard = new OrdiHasard();
    
        Bateau bateau1 = new Bateau("Bateau1", 3, 0.1f);
        Bateau bateau2 = new Bateau("Bateau2", 4, 0.2f);
        
        // Vérifier si les bateaux ont été placés correctement sur le plateau
        Plateau plateau = ordiHasard.getPlateau(); // Obtenez le plateau de jeu de l'OrdiHasard


        ordiHasard.placerBateau(bateau1, new Coordonnee("A1"), Orientation.HORIZONTAL);

        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee(1, 1)));
        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee("A2")));
        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee("A3")));

        Assertions.assertTrue(plateau.isCoordonneeLibre(new Coordonnee("H8")));

        ordiHasard.placerBateau(bateau2, new Coordonnee("E1"), Orientation.VERTICAL);

        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee(5, 1)));
        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee("F1")));
        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee("G1")));
        Assertions.assertFalse(plateau.isCoordonneeLibre(new Coordonnee("H1")));

        Assertions.assertTrue(plateau.isCoordonneeLibre(new Coordonnee("I8")));

    }
    
}
