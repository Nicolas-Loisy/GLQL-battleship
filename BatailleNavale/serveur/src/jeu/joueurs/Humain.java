package jeu.joueurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;


import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.exceptions.TypeException;

public class Humain extends aJoueur {
  private BufferedReader in;
  private PrintWriter out;

  public Humain(Socket socket) throws IOException {
    super();

    this.out = new PrintWriter(socket.getOutputStream(), false);
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    communiquer(TypeCommunication.AFF, TypeClefs.SERV_CONF);
    super.setNom(saisieNom());

  }

  @Override
  public String saisieNom() throws IOException {
    String nom = null;

    do {
      nom = communiquer(TypeCommunication.ACT, TypeClefs.NOM);
      if (nom.equals("")) {
        communiquer(TypeCommunication.AFF, TypeClefs.NOM_NUL);
      }
    } while (nom.equals(""));
    
    return nom;
  }

  @Override
  public Coordonnee saisieCoordonnee() throws IOException {
    Coordonnee coord = null;

    do {
      String str = communiquer(TypeCommunication.ACT, TypeClefs.COORD);
      try {
        coord = new Coordonnee(str);
        if (coord.getLigne() > Plateau.getNombreLigne() || coord.getColonne() > Plateau.getNombreColonne()){
          coord = null;
          throw new ReglesException(TypeException.HORS_PLATEAU_ERROR);
        } 
      } catch(ReglesException e) {
        switch(e.getErrorCode()) {
          case LETTRES_CONSECUTIVES_ERROR :
            communiquer(TypeCommunication.AFF, TypeClefs.COORD_LETTRES_CONS);
            break;
          case CHIFFRES_CONSECUTIVES_ERROR :
            communiquer(TypeCommunication.AFF, TypeClefs.COORD_CHIFFRES_CONS);
            break;
          case STRCOORD_MINIMUM_ERROR :
            communiquer(TypeCommunication.AFF, TypeClefs.COORD_STR_MINIMUM);
            break;
          case HORS_PLATEAU_ERROR :
            communiquer(TypeCommunication.AFF, TypeClefs.COORD_HORS_PLAT);
            break;
          default :
            communiquer(TypeCommunication.AFF, TypeClefs.COORD_INV);
        }
      }
    } while (coord == null);

    return coord;
  }

  @Override
  public Orientation saisieOrientation() throws IOException {
    Orientation orient = null;

    do {
      String str = communiquer(TypeCommunication.ACT, TypeClefs.ORIENT).toUpperCase();
      if (str.equals("V")) {
        orient = Orientation.VERTICAL;
      }
      else if (str.equals("H")) {
        orient = Orientation.HORIZONTAL;
      }
      else {
        communiquer(TypeCommunication.AFF, TypeClefs.ORIENT_INV);
      }
    } while (orient == null);
    return orient;
  }

  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) throws IOException {
    communiquer(TypeCommunication.AFF, TypeClefs.PLACER_DEB);
    for (Bateau bateau : bateauxAPlacer) {
      String plateau = super.getPlateau().toString();
      if (plateau.length() == 0) {
        communiquer(TypeCommunication.AFF, TypeClefs.PLAT_VIDE);
      } else {
        communiquer(TypeCommunication.AFF, TypeClefs.PLAT, plateau);
      }

      communiquer(TypeCommunication.AFF, TypeClefs.BATEAU, bateau.getNom(), Integer.toString(bateau.getTaille()));

      Boolean bateauPlace = false;
      while (!bateauPlace) {
        Coordonnee coordDepart = saisieCoordonnee();
        Orientation orientation = saisieOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
          communiquer(TypeCommunication.AFF, TypeClefs.BATEAU_CONF);
        } catch (ReglesException e) {
          switch (e.getErrorCode()) {
            case COORD_OCCUPEE_ERROR :
              communiquer(TypeCommunication.AFF, TypeClefs.COORD_OCCUPEE, coordDepart.toString());
              break;
            case HORS_PLATEAU_ERROR :
              communiquer(TypeCommunication.AFF, TypeClefs.COORD_HORS_PLAT);
              break;
            default :
              communiquer(TypeCommunication.AFF, TypeClefs.BATEAU_INV);
          }
        }
      }
    }
    communiquer(TypeCommunication.AFF, TypeClefs.PLACER_FIN);
  }

  public String communiquer(TypeCommunication typeCom, TypeClefs clef, String... params) throws IOException {
    if (params.length != 0) {
      out.println(typeCom.name() + "#" + clef.name() +  "#" + String.join("&", params));
    } else {
      out.println(typeCom.name() + "#" + clef.name());
    }
    out.flush();
    if (typeCom == TypeCommunication.ACT){
      return in.readLine();
    }
    return null;
  }
}

  