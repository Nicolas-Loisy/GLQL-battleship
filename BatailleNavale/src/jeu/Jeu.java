package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jeu.bateaux.TypeBateaux;
import jeu.bateaux.Bateau;
import jeu.bateaux.BateauFactory;
import jeu.inputOutput.TypeInputOutput;
import jeu.joueurs.iJoueur;
import jeu.serveur.Client;
import jeu.serveur.Serveur;
import jeu.serveur.iConnexion;
import jeu.joueurs.JoueurFactory;
import jeu.joueurs.TypeJoueur;

public class Jeu {
  private iJoueur joueur1;
  private iJoueur joueur2;
  private iJoueur joueurActuel;
  private iJoueur joueurAdverse;

  /**
   * Initialise une partie de bataille navale en créant deux joueurs et leurs
   * plateaux respectifs.
   * 
   * @param config Une Map contenant les paramètres de configuration de la partie.
   */
  public Jeu(Map<TypeParam, Object> config) {
    // Récupération des paramètres de la partie
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


    // SERVEUR
    // Serveur serveur = new Serveur(8080);
    // serveur.connexion();


    // CLIENT
    Client serveur = new Client("localhost", 8080);
    try {
      serveur.connexion();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Création des joueurs
    try {
      // joueur1 = initJoueur(typeJ1, inputOutputType, listeBateaux);
      // joueur2 = initJoueur(typeJ2, inputOutputType, listeBateaux);
      joueur1 = initJoueur(typeJ1, inputOutputType, listeBateaux, serveur);
      joueur2 = initJoueur(typeJ2, inputOutputType, listeBateaux, serveur);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // Le joueur 1 commence à jouer
    joueurActuel = joueur1;
    joueurAdverse = joueur2;
  }

  public void jouer() {
    iJoueur gagnant = null;

    while (gagnant == null) {
      gagnant = tourDeJeu();
    }

    joueur1.msgFinPartie(gagnant.getNom());
    joueur2.msgFinPartie(gagnant.getNom());
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
   * @throws IOException
   */
  private iJoueur initJoueur(TypeJoueur type, TypeInputOutput inputOutputType, List<TypeBateaux> listeBateaux) throws IOException {
    iJoueur joueur;
    joueur = JoueurFactory.creerJoueur(type, inputOutputType);
    List<Bateau> bateaux = creerBateaux(listeBateaux);
    joueur.placerBateaux(bateaux);
    return joueur;
  }

  private iJoueur initJoueur(TypeJoueur type, TypeInputOutput inputOutputType, List<TypeBateaux> listeBateaux, iConnexion connexion) throws IOException {
    iJoueur joueur;
    joueur = JoueurFactory.creerJoueur(type, inputOutputType, connexion);
    List<Bateau> bateaux = creerBateaux(listeBateaux);
    joueur.placerBateaux(bateaux);
    return joueur;
  }

  /**
   * Déroule un tour de jeu pour le joueur actuel
   * 
   * @return le joueur qui a remporté la partie, ou null si la partie n'est pas
   *         encore finie
   */
  public iJoueur tourDeJeu() {
    joueurActuel.msgDebutTour();
    boolean encoreUneAttaque = true;
    while (encoreUneAttaque) {
      joueurActuel.msgDebutAttaque();
      Coordonnee coord = joueurActuel.askUserCoordonnee();
      ResultatAttaque resultat = joueurActuel.attaquer(joueurAdverse, coord);
      joueurActuel.msgFinAttaque(resultat, coord);

      if (resultat == ResultatAttaque.GAMEOVER) {
        return joueurActuel;
      }
      if (resultat == ResultatAttaque.MANQUE) {
        encoreUneAttaque = false;
      }
    }
    joueurActuel.msgFinTour();
    inverserJoueur();
    return null;
  }

  /**
   * Inverse le joueur atuel et le joueur adverse
   */
  private void inverserJoueur() {
    joueurActuel = joueurActuel == joueur1 ? joueur2 : joueur1;
    joueurAdverse = joueurActuel == joueur1 ? joueur2 : joueur1;
  }

}
