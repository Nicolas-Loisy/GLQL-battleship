package jeu;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import jeu.Coordonnee;
import jeu.exceptions.ReglesException;
import jeu.exceptions.TypeException;

public class CoordonneeTests {

  @Test
  public void testCoordonnee() throws ReglesException {
    Coordonnee c1 = new Coordonnee(2, 3);
    assertEquals("B3", c1.toString());
    
    Coordonnee c2 = new Coordonnee("A1");
    assertEquals(1, c2.getLigne());
    assertEquals(1, c2.getColonne());

    Coordonnee c3 = new Coordonnee("C12");
    assertEquals(3, c3.getLigne());
    assertEquals(12, c3.getColonne());

    Coordonnee c4 = new Coordonnee("A0");
    assertEquals(1, c4.getLigne());
    assertEquals(0, c4.getColonne());

    try {
      new Coordonnee("A");
      fail("Une ReglesException devrait être levée si la coordonnée est invalide.");
    } catch (ReglesException e) {
      assertEquals(TypeException.STRCOORD_MINIMUM_ERROR, e.getErrorCode());
    }

    try {
      new Coordonnee("1");
      fail("Une ReglesException devrait être levée si la coordonnée est invalide.");
    } catch (ReglesException e) {
      assertEquals(TypeException.STRCOORD_MINIMUM_ERROR, e.getErrorCode());
    }

    try {
      new Coordonnee("A12B");
      fail("Une ReglesException devrait être levée si les chiffres sont écrits consécutivement.");
    } catch (ReglesException e) {
      assertEquals(TypeException.CHIFFRES_CONSECUTIVES_ERROR, e.getErrorCode());
    }

    try {
      new Coordonnee("A-1");
      fail("Une ReglesException devrait être levée si la coordonnée est invalide.");
    } catch (ReglesException e) {
      assertEquals(TypeException.CHIFFRES_CONSECUTIVES_ERROR, e.getErrorCode());
    }
  }

  @Test
  public void testConstructeurCoordonnee() {
    Coordonnee coord = new Coordonnee(3, 4);
    assertEquals(3, coord.getLigne());
    assertEquals(4, coord.getColonne());
  }

  @Test
  public void testConstructeurCoordonneeAvecStringValide() throws ReglesException {
    Coordonnee coord = new Coordonnee("A1");
    assertEquals(1, coord.getLigne());
    assertEquals(1, coord.getColonne());
  }

  @Test
  public void testConstructeurCoordonneeAvecStringLigneInvalide() {
    try {
      new Coordonnee("1A");
      fail("Le constructeur aurait dû lever une exception");
    } catch (ReglesException e) {
      assertEquals(TypeException.CHIFFRES_CONSECUTIVES_ERROR, e.getErrorCode());
    }
  }

  @Test
  public void testConstructeurCoordonneeAvecStringColonneInvalide() {
    try {
      new Coordonnee("AB");
      fail("Le constructeur aurait dû lever une exception");
    } catch (ReglesException e) {
      assertEquals(TypeException.STRCOORD_MINIMUM_ERROR, e.getErrorCode());
    }
  }

  @Test
  public void testConstructeurCoordonneeAvecStringCoordInvalide() {
    try {
      new Coordonnee("A");
      fail("Le constructeur aurait dû lever une exception");
    } catch (ReglesException e) {
      assertEquals(TypeException.STRCOORD_MINIMUM_ERROR, e.getErrorCode());
    }
  }

  @Test
  public void testToString() {
    Coordonnee coord = new Coordonnee(1, 1);
    assertEquals("A1", coord.toString());
    coord = new Coordonnee(2, 26);
    assertEquals("B26", coord.toString());
  }

  @Test
  public void testEquals() {
    Coordonnee coord1 = new Coordonnee(1, 1);
    Coordonnee coord2 = new Coordonnee(1, 1);
    Coordonnee coord3 = new Coordonnee(2, 2);

    assertTrue(coord1.equals(coord2));
    assertFalse(coord1.equals(coord3));
  }

  @Test
  public void testHashCode() {
    Coordonnee coord1 = new Coordonnee(1, 1);
    Coordonnee coord2 = new Coordonnee(1, 1);
    Coordonnee coord3 = new Coordonnee(2, 2);

    assertEquals(coord1.hashCode(), coord2.hashCode());
    assertNotEquals(coord1.hashCode(), coord3.hashCode());
  }

}
