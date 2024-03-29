package jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jeu.bateaux.TypeBateaux;
import jeu.bateaux.Bateau;
import jeu.bateaux.BateauFactory;
import jeu.joueurs.iJoueur;
import jeu.reseau.Serveur;
import jeu.joueurs.JoueurFactory;
import jeu.joueurs.TypeClefs;
import jeu.joueurs.TypeCommunication;
import jeu.joueurs.TypeJoueur;

public class Jeu {
  private final int PORT = 8080;
  private iJoueur[] joueurs;
  private int nbrJoueurs;
  private int cptTour;

  /**
   * Initialise une partie de bataille navale en créant deux joueurs et leurs
   * plateaux respectifs.
   * 
   * @param config Une Map contenant les paramètres de configuration de la partie.
   */
  public Jeu(Map<TypeParam, Object> config) {
    cptTour = 0;

    // Récupération des paramètres de la partie
    nbrJoueurs = (int) config.get(TypeParam.NBR_JOUEURS);
    joueurs = new iJoueur[nbrJoueurs];
    
    int cptJoueursHumain = 0;
    List<TypeJoueur> listeJoueurs = new ArrayList<>();
    
    for (Object objet : (List<?>) config.get(TypeParam.TYPE_JOUEURS)) {
      if (objet instanceof TypeJoueur) {
        listeJoueurs.add((TypeJoueur) objet);

        if (objet == TypeJoueur.HUMAIN) {
          cptJoueursHumain++;
        }
      }
    }

    List<TypeBateaux> listeBateaux = new ArrayList<>();
    
    for (Object objet : (List<?>) config.get(TypeParam.LISTE_BATEAUX)) {
      if (objet instanceof TypeBateaux) {
        listeBateaux.add((TypeBateaux) objet);
      }
    }

    // Création du serveur
    Serveur serveur = new Serveur(PORT, cptJoueursHumain);
    JoueurFactory.setServeur(serveur);
    
    // Création des joueurs
    try {
      for (int i = 0; i < nbrJoueurs; i++) {
        joueurs[i] = JoueurFactory.creerJoueur(listeJoueurs.get(i));
        List<Bateau> bateaux = creerBateaux(listeBateaux);
        joueurs[i].placerBateaux(bateaux);
      }
    
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void jouer() {
    iJoueur gagnant = null;
    
    try {
    
      for (iJoueur j : joueurs) {
        j.communiquer(TypeCommunication.AFFICHER, TypeClefs.PARTIE_DEB);
      }
    
      while (gagnant == null) {
        gagnant = tourDeJeu();
        cptTour++;
      }
    
      for (iJoueur j : joueurs) {
        j.communiquer(TypeCommunication.AFFICHER, TypeClefs.PARTIE_FIN, gagnant.getNom());
      }
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

  }

  private List<Bateau> creerBateaux(List<TypeBateaux> bateauxACreer) {
    List<Bateau> bateaux = new ArrayList<>();
    
    for (TypeBateaux type : bateauxACreer) {
      bateaux.add(BateauFactory.creerBateau(type));
    }
    
    return bateaux;
  }

  public iJoueur tourDeJeu() throws IOException {
    iJoueur joueur = joueurs[cptTour % nbrJoueurs];
    iJoueur adversaire = joueurs[(cptTour + 1) % nbrJoueurs];

    afficherDebutTour(joueur);
    afficherPlateauJoueur(joueur);
    afficherDebutTourAdversaires(joueurs, joueur);

    while (effectuerPhaseAttaque(joueur, adversaire)) {
        if (adversaire.getNbrBateauxRestants() == 0) {
            joueurRemportePartie(joueur);
            afficherDefaiteAdversaires(joueurs, joueur);
            return joueur;
        }
    }

    afficherFinTour(joueur);
    afficherFinTourAdversaires(joueurs, joueur);

    return null;
  }

  private void afficherDebutTour(iJoueur joueur) throws IOException {
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.TOUR_DEB);
  }

  private void afficherPlateauJoueur(iJoueur joueur) throws IOException {
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.PLAT, joueur.getPlateau().toString());
  }

  private void afficherDebutTourAdversaires(iJoueur[] joueurs, iJoueur joueur) throws IOException {
      for (iJoueur autreJoueur : joueurs) {
          if (!autreJoueur.equals(joueur)) {
              autreJoueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.TOUR_DEB_ADV, joueur.getNom());
          }
      }
  }

  private boolean effectuerPhaseAttaque(iJoueur joueur, iJoueur adversaire) throws IOException {
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.ATK_DEB);
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.ATK_HIST, joueur.getAttaquesHistorique());
      Coordonnee coord = joueur.saisieCoordonnee();
      ResultatAttaque resultat = joueur.attaquer(adversaire, coord);

      for (iJoueur autreJoueur : joueurs) {
          autreJoueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.ATK_RES, adversaire.toString(), coord.toString(), resultat.name());
      }

      return ResultatAttaque.TOUCHE.equals(resultat) || ResultatAttaque.COULE.equals(resultat);
  }

  private void joueurRemportePartie(iJoueur joueur) throws IOException {
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.PARTIE_GAGNEE);
  }

  private void afficherDefaiteAdversaires(iJoueur[] joueurs, iJoueur joueur) throws IOException {
      for (iJoueur autreJoueur : joueurs) {
          if (!autreJoueur.equals(joueur)) {
              autreJoueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.PARTIE_PERDUE, joueur.getNom());
          }
      }
  }

  private void afficherFinTour(iJoueur joueur) throws IOException {
      joueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.TOUR_FIN);
  }

  private void afficherFinTourAdversaires(iJoueur[] joueurs, iJoueur joueur) throws IOException {
      for (iJoueur autreJoueur : joueurs) {
          if (!autreJoueur.equals(joueur)) {
              autreJoueur.communiquer(TypeCommunication.AFFICHER, TypeClefs.TOUR_FIN_ADV, joueur.getNom());
          }
      }
  }

}
