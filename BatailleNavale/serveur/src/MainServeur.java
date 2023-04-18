import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jeu.Jeu;
import jeu.TypeParam;
import jeu.bateaux.TypeBateaux;
import jeu.joueurs.TypeJoueur;

public class MainServeur {
  public static void main(String[] args) {
    System.out.println("[DEBUT SERVEUR]\n");
    int nbrJoueurs = 2;

    // CREATION DES JOUEURS
    List<TypeJoueur> typeJoueurs = new ArrayList<>();
    typeJoueurs.add(TypeJoueur.HUMAIN);
    typeJoueurs.add(TypeJoueur.ORDI_HASARD);

    // CREATION DES JOUEURS
    List<TypeBateaux> bateauxAPlacer = new ArrayList<>();
    bateauxAPlacer.add(TypeBateaux.SOUS_MARIN);
    bateauxAPlacer.add(TypeBateaux.CROISEUR);
    // bateauxAPlacer.add(TypeBateaux.TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.CONTRE_TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.PORTE_AVION);

    Map<TypeParam, Object> config = new HashMap<>();

    // CREATION DE LA VARIABLE DE CONFIGURATION
    config.put(TypeParam.NBR_JOUEURS, nbrJoueurs);
    config.put(TypeParam.TYPE_JOUEURS, typeJoueurs);
    config.put(TypeParam.LISTE_BATEAUX, bateauxAPlacer);

    Jeu jeu = new Jeu(config);
    jeu.jouer();
  }

}
