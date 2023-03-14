package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jeu.bateaux.TypeBateaux;
import jeu.bateaux.Bateau;
import jeu.bateaux.BateauFactory;
import jeu.inputOutput.InputOutputFactory;
import jeu.inputOutput.TypeInputOutput;
import jeu.inputOutput.iInput;
import jeu.inputOutput.iOutput;
import jeu.joueurs.iJoueur;
import jeu.joueurs.JoueurFactory;
import jeu.joueurs.TypeJoueur;

public class Jeu {

  private iInput input;
  private iOutput output;
  private iJoueur joueur1;
  private iJoueur joueur2;
  private iJoueur joueurActuel;
  private iJoueur joueurAdverse;

  /**
   * Lance une partie de bataille navale en utilisant la configuration spécifiée.
   * 
   * @param config Une Map contenant les paramètres de configuration de la partie.
   *               Les clés doivent correspondre à l'une des valeurs de
   *               l'énumération TypeParam.
   *               Les valeurs associées à ces clés doivent être du type
   *               correspondant au TypeParam correspondant.
   *               Les paramètres requis sont:
   *               - INPUT_OUTPUT_TYPE: Le type d'entrée/sortie utilisé par
   *               l'application. Doit être une valeur de InputOutputType.
   *               - TYPE_J1: Le type de joueur utilisé pour le joueur 1. Doit
   *               être une valeur de TypeJoueur.
   *               - TYPE_J2: Le type de joueur utilisé pour le joueur 2. Doit
   *               être une valeur de TypeJoueur.
   *               - LISTE_BATEAUX: La liste des bateaux utilisés pour la partie.
   *               Doit être une liste contenant des valeurs de TypeBateaux.
   */
  public void jouer(Map<TypeParam, Object> config) {
    initPartie(config);

    iJoueur gagnant = null;
    while (gagnant == null) {
      gagnant = tourDeJeu();
    }

    output.msgVictoire(gagnant);
  }

  /**
   * Initialise une partie de bataille navale en créant deux joueurs et leurs
   * plateaux respectifs.
   * 
   * @param config Une Map contenant les paramètres de configuration de la partie.
   */
  public void initPartie(Map<TypeParam, Object> config) {

    TypeInputOutput inputOutputType = (TypeInputOutput) config.get(TypeParam.INPUT_OUTPUT_TYPE);
    TypeJoueur typeJ1 = (TypeJoueur) config.get(TypeParam.TYPE_J1);
    TypeJoueur typeJ2 = (TypeJoueur) config.get(TypeParam.TYPE_J2);
    List<TypeBateaux> listeBateaux = new ArrayList<>();
    Object listeObjets = config.get(TypeParam.LISTE_BATEAUX);
    if (listeObjets instanceof List<?>) {
      for (Object objet : (List<?>) listeObjets) {
        if (objet instanceof TypeBateaux) {
          listeBateaux.add((TypeBateaux) objet);
        }
      }
    }

    input = InputOutputFactory.getInput(inputOutputType);
    output = InputOutputFactory.getOutput(inputOutputType);

    joueur1 = initJoueur(typeJ1, listeBateaux);
    joueur2 = initJoueur(typeJ2, listeBateaux);

    // Le joueur 1 commence en premier
    joueurActuel = joueur1;
    joueurAdverse = joueur2;
  }

  /**
   * Crée une liste de bateaux à partir d'une liste de types de bateaux.
   *
   * @param bateauxACreer La liste de types de bateaux à créer.
   * @return La liste de bateaux créée.
   */
  private List<Bateau> creerBateaux(List<TypeBateaux> bateauxACreer) {
    List<Bateau> bateaux = new ArrayList<>();
    for (TypeBateaux type : bateauxACreer) {
      bateaux.add(BateauFactory.creerBateau(type));
    }
    return bateaux;
  }

  /**
   * Initialise un joueur en créant ses bateaux, les plaçant sur son plateau et
   * retournant l'objet correspondant.
   *
   * @param type         Le type de joueur à initialiser.
   * @param listeBateaux La liste de types de bateaux du joueur à initialiser.
   * @return Le joueur initialisé.
   */
  private iJoueur initJoueur(TypeJoueur type, List<TypeBateaux> listeBateaux) {
    iJoueur joueur = JoueurFactory.getJoueur(type, input, output);
    List<Bateau> bateaux = creerBateaux(listeBateaux);
    joueur.placerBateaux(bateaux);
    output.changementTour();
    return joueur;
  }

  /**
   * Déroule un tour de jeu pour le joueur actuel
   * 
   * @return le joueur qui a remporté la partie, ou null si la partie n'est pas
   *         encore finie
   */
  public iJoueur tourDeJeu() {
    output.msgDebutTour(joueurActuel);
    output.afficherPlateau(joueurActuel.getPlateau());
    boolean encoreUneAttaque = true;
    while(encoreUneAttaque) {
      output.afficherAttaques(joueurActuel.getAttaques());
      Coordonnee coord = joueurActuel.askUserCoordonnee();
      ResultatAttaque resultat = joueurActuel.attaquer(joueurAdverse.getPlateau(), coord);
      output.msgResultatAttaque(resultat);
      if (resultat == ResultatAttaque.GAMEOVER) {
        return joueurActuel;
      }
      if (resultat == ResultatAttaque.MANQUE) {
        encoreUneAttaque = false;
      }
    }

    inverserJoueur();
    return null;
  }

  /**
   * Inverse le joueur atuel et le joueur adverse
   */
  private void inverserJoueur(){
    joueurActuel = joueurActuel == joueur1 ? joueur2 : joueur1;
    joueurAdverse = joueurActuel == joueur1 ? joueur2 : joueur1;
    output.changementTour();
  }

}
