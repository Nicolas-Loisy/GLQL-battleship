package jeu.joueurs;

import java.util.List;
import java.util.Random;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.iOutput;

public class OrdiHasard extends aJoueur {

  protected OrdiHasard(iOutput output) {
    super(output);
    String nom = askUserNom();
    super.setNom(nom);
  }

  @Override
  public String askUserNom() {
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
  public Coordonnee askUserCoordonnee() {
    int maxLigne = Plateau.getNombreLigne();
    int maxColonne = Plateau.getNombreColonne();
    Random rand = new Random();
    int ligne = rand.nextInt(maxLigne);
    int colonne = rand.nextInt(maxColonne);
    Coordonnee coord = new Coordonnee(ligne,colonne);
    return coord;
  }

  @Override
  public Orientation askUserOrientation() {
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
      super.getOutput().afficherPlateau(super.getPlateau());
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        super.getOutput().msgPlacerBateau(bateau);
        Coordonnee coordDepart = askUserCoordonnee();
        Orientation orientation = askUserOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {
          super.getOutput().msgError(e.getMessage());
        }
      }
    }
  }
}
