package jeu.bateaux;

/**
 * La classe BateauFactory est une usine de création de bateaux.
 * Elle contient une méthode statique pour créer un bateau à partir d'un type de bateau donné.
 */
public class BateauFactory {
    /**
     * Cette méthode statique crée un bateau à partir du type de bateau spécifié.
     * @param type le type de bateau à créer.
     * @return le bateau créé.
     */
    public static Bateau creerBateau(TypeBateaux type) {
        return new Bateau(type.getNom(), type.getTaille());
    }
}
