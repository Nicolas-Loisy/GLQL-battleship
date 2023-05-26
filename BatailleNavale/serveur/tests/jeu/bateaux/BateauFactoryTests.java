package jeu.bateaux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BateauFactoryTests {

    @Test
    public void testCreerBateau_PorteAvion() {
        Bateau bateau = BateauFactory.creerBateau(TypeBateaux.PORTE_AVION);
        Assertions.assertEquals("Porte-Avion", bateau.getNom());
        Assertions.assertEquals(5, bateau.getTaille());
    }

    @Test
    public void testCreerBateau_Croiseur() {
        Bateau bateau = BateauFactory.creerBateau(TypeBateaux.CROISEUR);
        Assertions.assertEquals("Croiseur", bateau.getNom());
        Assertions.assertEquals(4, bateau.getTaille());
    }

    @Test
    public void testCreerBateau_ContreTorpilleur() {
        Bateau bateau = BateauFactory.creerBateau(TypeBateaux.CONTRE_TORPILLEUR);
        Assertions.assertEquals("Contre-Torpilleur", bateau.getNom());
        Assertions.assertEquals(5, bateau.getTaille());
    }

    @Test
    public void testCreerBateau_SousMarin() {
        Bateau bateau = BateauFactory.creerBateau(TypeBateaux.SOUS_MARIN);
        Assertions.assertEquals("Sous-Marin", bateau.getNom());
        Assertions.assertEquals(3, bateau.getTaille());
    }

    @Test
    public void testCreerBateau_Torpilleur() {
        Bateau bateau = BateauFactory.creerBateau(TypeBateaux.TORPILLEUR);
        Assertions.assertEquals("Torpilleur", bateau.getNom());
        Assertions.assertEquals(4, bateau.getTaille());
    }
}
