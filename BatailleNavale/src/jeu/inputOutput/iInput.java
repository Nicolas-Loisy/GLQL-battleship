package jeu.inputOutput;


import jeu.Orientation;
import jeu.exceptions.ReglesException;

public interface iInput {

    public String getNomJoueur() throws ReglesException;
    public String getCoordonnee() throws ReglesException;
    public Orientation getOrientation() throws ReglesException;
}
