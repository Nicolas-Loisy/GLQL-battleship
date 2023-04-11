import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jeu.Jeu;
import jeu.TypeParam;
import jeu.bateaux.TypeBateaux;
import jeu.inputOutput.TypeInputOutput;
import jeu.joueurs.TypeJoueur;

public class Main_hote {

  public static void main(String[] args) {
    //Choix des paramètres => Rendre ca dynamique par la suite
    TypeInputOutput inputOutputType = TypeInputOutput.CONSOLE;
    
    
    // TypeJoueur typeJ1 = TypeJoueur.ORDI_HASARD;
    // TypeJoueur typeJ2 = TypeJoueur.ORDI_HASARD;
    
    // TEST RESEAU SERVEUR
    // TypeJoueur typeJ1 = TypeJoueur.HUMAIN_JOUEUR;
    // TypeJoueur typeJ2 = TypeJoueur.HUMAIN_RESEAU;

    // TEST RESEAU CLIENT
    TypeJoueur typeJ1 = TypeJoueur.HUMAIN_RESEAU;
    TypeJoueur typeJ2 = TypeJoueur.HUMAIN_JOUEUR;



    List<TypeBateaux> bateauxAPlacer = new ArrayList<>();
    bateauxAPlacer.add(TypeBateaux.SOUS_MARIN);
    // bateauxAPlacer.add(TypeBateaux.CROISEUR);
    // bateauxAPlacer.add(TypeBateaux.TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.CONTRE_TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.PORTE_AVION);

    Map<TypeParam, Object> config = new HashMap<>();
    config.put(TypeParam.INPUT_OUTPUT_TYPE, inputOutputType);
    config.put(TypeParam.TYPE_J1, typeJ1);
    config.put(TypeParam.TYPE_J2, typeJ2);
    config.put(TypeParam.LISTE_BATEAUX, bateauxAPlacer);

    Jeu jeu = new Jeu(config);
    jeu.jouer();
  }

}
