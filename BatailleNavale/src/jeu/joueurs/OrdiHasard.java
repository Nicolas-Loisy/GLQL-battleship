package jeu.joueurs;

import java.util.List;
import java.util.Random;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.TypeInputOutput;

public class OrdiHasard extends aJoueur {

  protected OrdiHasard(TypeInputOutput inOutType) {
    super(inOutType);
    super.setNom(askUserNom());
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
    Coordonnee coord = new Coordonnee(ligne, colonne);
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
    super.getOutput().msgDebutPlacerBateaux(getNom());
    for (Bateau bateau : bateauxAPlacer) {
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        Coordonnee coordDepart = askUserCoordonnee();
        Orientation orientation = askUserOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {}
      }
    }
    super.getOutput().msgFinPlacerBateaux();
  }

  public void msgDebutPartie(){}
  
  public void msgFinPartie(String nomGagnant){}

  public void msgDebutTour(){
    super.getOutput().msgDebutDeTour(getNom());
  }

  public void msgFinTour(){
    super.getOutput().msgFinDeTour();
  }

  public void msgDebutAttaque(){}
  
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord){
    System.out.println("Résultat de l'attaque en " + coord + " : " + resultat);
  }
}
