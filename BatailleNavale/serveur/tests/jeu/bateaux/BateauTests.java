package jeu.bateaux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BateauTests {

    @Test
    public void testGetTaille() {
        Bateau bateau = new Bateau("Porte Avion", 5, 0);
        int taille = bateau.getTaille();
        Assertions.assertEquals(5, taille);
    }

    @Test
    public void testGetNom() {
        Bateau bateau = new Bateau("Porte Avion", 5, 0);
        String nom = bateau.getNom();
        Assertions.assertEquals("Porte Avion", nom);
    }

    @Test
    public void testToString() {
        Bateau bateau = new Bateau("Porte Avion", 5, 0);
        String representation = bateau.toString();
        Assertions.assertEquals("PA", representation);
    }

    @Test
    public void testToStringWithEmptyName() {
        Bateau bateau = new Bateau("", 3, 1);
        String representation = bateau.toString();
        Assertions.assertEquals("", representation);
    }

    @Test
    public void testToStringWithMultipleWords() {
        Bateau bateau = new Bateau("Croiseur Léger", 4, 0.6f);
        String representation = bateau.toString();
        Assertions.assertEquals("CL", representation);
    }
}
