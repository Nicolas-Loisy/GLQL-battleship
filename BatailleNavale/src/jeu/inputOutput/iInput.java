package jeu.inputOutput;

import jeu.Orientation;
import jeu.exceptions.ReglesException;

public interface iInput {
    /**
     * Demande le nom du joueur.
     * @return le nom du joueur saisi
     * @throws ReglesException si le nom saisi est invalide
     */
    public String getNomJoueur() throws ReglesException;
    
    /**
     * Demande les coordonnées pour placer un bateau.
     * @return les coordonnées saisies
     * @throws ReglesException si les coordonnées saisies sont invalides
     */
    public String getCoordonnee() throws ReglesException;
    
    /**
     * Demande l'orientation pour placer un bateau.
     * @return l'orientation saisie
     * @throws ReglesException si l'orientation saisie est invalide
     */
    public Orientation getOrientation() throws ReglesException;
}
