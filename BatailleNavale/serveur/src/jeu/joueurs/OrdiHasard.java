package jeu.joueurs;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;

public class OrdiHasard extends aJoueur {

  protected OrdiHasard() {
    super();
    super.setNom(saisieNom());
  }

  @Override
  public String saisieNom() {
    String[] prenoms = {
        "José",
        "Jean",
        "Marie",
        "Lucie",
        "Antoine",
        "Céline",
        "Emilie",
        "Pierre",
        "Sophie",
        "Thomas",
        "Camille",
        "Florence",
        "François",
        "Isabelle",
        "Julien",
        "Laure",
        "Léo",
        "Manon",
        "Maxime",
        "Nathalie",
        "Olivier",
        "Pauline",
        "Quentin",
        "Rachel",
        "Sébastien",
        "Thierry",
        "Valérie",
        "Vincent",
        "Xavier",
        "Yann",
        "Zoé"
    };

    Random rand = new Random();
    int randomIndex = rand.nextInt(prenoms.length);
    
    return prenoms[randomIndex];
  }

  @Override
  public Coordonnee saisieCoordonnee() {
    int maxLigne = Plateau.getNombreLigne();
    int maxColonne = Plateau.getNombreColonne();
    Random rand = new Random();
    int ligne = rand.nextInt(maxLigne);
    int colonne = rand.nextInt(maxColonne);
    Coordonnee coord = new Coordonnee(ligne, colonne);
    
    return coord;
  }

  @Override
  public Orientation saisieOrientation() {
    Random rand = new Random();
    int choice = rand.nextInt(2);
    
    if (choice == 0) {
      return Orientation.HORIZONTAL;
    } else {
      return Orientation.VERTICAL;
    }
  }

  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) {
    for (Bateau bateau : bateauxAPlacer) {
      Boolean bateauPlace = false;
    
      while (!bateauPlace) {
        Coordonnee coordDepart = saisieCoordonnee();
        Orientation orientation = saisieOrientation();
    
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {}
      }
    }
  }

  @Override
  public String communiquer(TypeCommunication typeCom, TypeClefs clef, String... params) throws IOException {
    return null;
  }
}
