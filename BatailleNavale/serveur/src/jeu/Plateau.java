package jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jeu.bateaux.Bateau;
import jeu.exceptions.TypeException;
import jeu.exceptions.ReglesException;

public class Plateau {
  private static final int MAX_LIGNE = 10;
  private static final int MAX_COLONNE = 10;

  private final List<Map<Bateau, Coordonnee[]>> bateaux = new ArrayList<>();

  public static int getNombreLigne() {
    return MAX_LIGNE;
  }

  public static int getNombreColonne() {
    return MAX_COLONNE;
  }

  public List<Map<Bateau, Coordonnee[]>> getBateaux() {
    return bateaux;
  }

  /**
   * Ajoute un bateau sur le plateau
   * 
   * @param bateau      Le bateau à ajouter sur le plateau
   * @param coordonnees Les coordonnées où placer le bateau sur le plateau
   * @throws ReglesException si les coordonnées ne sont pas valides
   */
  public void placerBateau(Bateau bateau, Coordonnee[] coordonnees) throws ReglesException {
    // Vérifie que le nombre de coordonnées correspond à la taille du bateau
    if (coordonnees.length != bateau.getTaille()) {
      throw new ReglesException(TypeException.TAILLE_BATEAU_ERROR);
    }
    
    for (Coordonnee coord : coordonnees) {
      // Vérifie que la coordonnée est sur le plateau
      if (!isCoordonneeSurPlateau(coord)) {
        throw new ReglesException(TypeException.HORS_PLATEAU_ERROR);
      }
    
      // Vérifie que la coordonnée est disponible
      if (!isCoordonneeLibre(coord)) {
        throw new ReglesException(TypeException.COORD_OCCUPEE_ERROR);
      }
    }
    
    // Vérifie que les coordonnées du bateau sont adjacentes en ligne ou en colonne
    for (int i = 1; i < coordonnees.length; i++) {
      if (!isAdjacentes(coordonnees[i - 1], coordonnees[i])) {
        throw new ReglesException(TypeException.COORDONNEES_ADJACENTES_ERROR);
      }
    }
    
    // Ajoute le bateau sur le plateau
    Map<Bateau, Coordonnee[]> bateauCoords = new HashMap<>();
    bateauCoords.put(bateau, coordonnees);
    bateaux.add(bateauCoords);
  }

  /**
   * Reçoit une attaque à une coordonnée donnée et retourne le résultat de
   * l'attaque.
   *
   * @param coord La coordonnée de l'attaque.
   * @return Le résultat de l'attaque (TOUCHE, COULE, MANQUE ou GAMEOVER).
   */
  public ResultatAttaque recevoirAttaque(Coordonnee coord) {
    Optional<Map<Bateau, Coordonnee[]>> bateauOptional = trouverBateau(coord);

    if (bateauOptional.isPresent()) {
      Map<Bateau, Coordonnee[]> bateau = bateauOptional.get();
      Coordonnee[] coordonnees = bateau.get(bateau.keySet().iterator().next());
      int index = trouverIndexCoordonnee(coordonnees, coord);

      if (index != -1) {
        coordonnees = supprimerCoordonnee(coordonnees, index);
        bateau.put(bateau.keySet().iterator().next(), coordonnees);

        if (coordonnees.length > 0) {
          return ResultatAttaque.TOUCHE;
        } else {
          supprimerBateau(bateau);
    
          return ResultatAttaque.COULE;
        }
      }
    }

    return ResultatAttaque.MANQUE;
  }

  public boolean isCoordonneeLibre(Coordonnee coordonnee) {
    for (Map<Bateau, Coordonnee[]> bateau : bateaux) {
      for (Coordonnee[] coordonnees : bateau.values()) {
        if (Arrays.stream(coordonnees).anyMatch(c -> c.equals(coordonnee))) {
          return false;
        }
      }
    }
    
    return true;
  }

  private boolean isAdjacentes(Coordonnee coord1, Coordonnee coord2) {
    return coord1.getLigne() == coord2.getLigne() || coord1.getColonne() == coord2.getColonne();
  }

  /**
   * Recherche un bateau qui contient une coordonnée donnée.
   *
   * @param coord La coordonnée à chercher dans les bateaux.
   * @return Un objet Optional contenant le bateau contenant la coordonnée, ou une
   *         valeur vide si la coordonnée n'est pas trouvée.
   */
  private Optional<Map<Bateau, Coordonnee[]>> trouverBateau(Coordonnee coord) {
    for (Map<Bateau, Coordonnee[]> bateau : bateaux) {
    
      // Récupère le tableau de coordonnées du bateau
      Coordonnee[] coordonnees = bateau.values().iterator().next();
    
      // Teste si la coordonnée est dans le tableau
      for (Coordonnee c : coordonnees) {
        if (c.equals(coord)) {
          // Retourne le bateau qui contient la coordonnée
          return Optional.of(bateau);
        }
      }
    }
    
    return Optional.empty();
  }

  /**
   * Recherche l'index d'une coordonnée dans un tableau de coordonnées.
   * 
   * @param coordonnees le tableau de coordonnées
   * @param coord       la coordonnée à rechercher
   * @return l'index de la coordonnée dans le tableau ou -1 si elle n'est pas
   *         présente
   */
  private int trouverIndexCoordonnee(Coordonnee[] coordonnees, Coordonnee coord) {
    for (int i = 0; i < coordonnees.length; i++) {
      if (coordonnees[i].equals(coord)) {
        return i;
      }
    }
    
    return -1;
  }

  /**
   * Supprime une coordonnée à un index donné dans un tableau de coordonnées.
   * 
   * @param coordonnees le tableau de coordonnées
   * @param index       l'index de la coordonnée à supprimer
   * @return un nouveau tableau de coordonnées sans la coordonnée à l'index donné
   */
  private Coordonnee[] supprimerCoordonnee(Coordonnee[] coordonnees, int index) {
    // Convertit le tableau en une liste modifiable
    List<Coordonnee> listeCoord = new ArrayList<>(Arrays.asList(coordonnees));
    
    listeCoord.remove(index);
    
    // Convertit la liste en un nouveau tableau de coordonnées
    return listeCoord.toArray(new Coordonnee[0]);
  }

  private void supprimerBateau(Map<Bateau, Coordonnee[]> bateau) {
    bateaux.remove(bateau);
  }

  public boolean isCoordonneeSurPlateau(Coordonnee coordonnee) {
    int ligne = coordonnee.getLigne();
    int colonne = coordonnee.getColonne();
    return ligne >= 1 && ligne <= MAX_LIGNE && colonne >= 1 && colonne <= MAX_COLONNE;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int numBateaux = 0;
    
    for (Map<Bateau, Coordonnee[]> map : bateaux) {
      numBateaux += map.size();
    // TODO stream
      for (Map.Entry<Bateau, Coordonnee[]> entry : map.entrySet()) {
        Bateau bateau = entry.getKey();
        Coordonnee[] coordonnees = entry.getValue();
        sb.append(bateau).append(":");
    
        for (Coordonnee coord : coordonnees) {
          sb.append(coord).append(",");
        }
        sb.deleteCharAt(sb.length() - 1); // Supprimer la virgule en trop
        
        sb.append("&");
      }
    }
    
    if (numBateaux > 0) {
      sb.deleteCharAt(sb.length() - 1); // Supprimer le dernier &
    }
    
    return sb.toString();
  }

}
