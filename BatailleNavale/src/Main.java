import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jeu.Jeu;
import jeu.TypeParam;
import jeu.bateaux.TypeBateaux;
import jeu.inputOutput.TypeInputOutput;
import jeu.joueurs.TypeJoueur;

public class Main {

  public static void main(String[] args) {
    Jeu jeu = new Jeu();

    //Choix des paramètres => Rendre ca dynamique par la suite
    TypeInputOutput inputOutputType = TypeInputOutput.CONSOLE;
    TypeJoueur typeJ1 = TypeJoueur.HUMAIN;
    TypeJoueur typeJ2 = TypeJoueur.HUMAIN;
    List<TypeBateaux> bateauxAPlacer = new ArrayList<>();
    bateauxAPlacer.add(TypeBateaux.PORTE_AVION);
    // bateauxAPlacer.add(TypeBateaux.CROISEUR);
    // bateauxAPlacer.add(TypeBateaux.TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.CONTRE_TORPILLEUR);
    // bateauxAPlacer.add(TypeBateaux.SOUS_MARIN);

    Map<TypeParam, Object> config = new HashMap<>();
    config.put(TypeParam.INPUT_OUTPUT_TYPE, inputOutputType);
    config.put(TypeParam.TYPE_J1, typeJ1);
    config.put(TypeParam.TYPE_J2, typeJ2);
    config.put(TypeParam.TYPE_J1, TypeJoueur.HUMAIN);
    config.put(TypeParam.LISTE_BATEAUX, bateauxAPlacer);

    jeu.jouer(config);
  }

}
