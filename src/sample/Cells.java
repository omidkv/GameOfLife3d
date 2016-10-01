package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Kevin Omidvaran
 * Class used to hold all information of a cell
 */
class Cells
{
  Box box;
  boolean isBecomingAlive;
  boolean isDying;
  boolean wasDead;
  boolean wasAlive;

  private PhongMaterial darkRed;
  private PhongMaterial red;
  private PhongMaterial purple;
  private PhongMaterial darkBlue;
  private PhongMaterial lightBlue;
  private PhongMaterial yellow;
  private PhongMaterial green;
  private PhongMaterial lightGreen;

  /**
   * Contructor for the cells.
   * initializes all of the fields in the cell.
   */
  Cells()
  {
    box = new Box(1, 1, 1);
    wasAlive = false;
    wasDead = false;
    isBecomingAlive = false;
    isDying = false;

    darkRed = new PhongMaterial();
    darkRed.setSpecularColor(Color.DARKRED);
    darkRed.setDiffuseColor(Color.DARKRED);

    lightGreen = new PhongMaterial();
    lightGreen.setSpecularColor(Color.GREEN);
    lightGreen.setDiffuseColor(Color.GREEN);

    yellow = new PhongMaterial();
    yellow.setSpecularColor(Color.GOLD);
    yellow.setDiffuseColor(Color.GOLD);

    green = new PhongMaterial();
    green.setSpecularColor(Color.DARKGREEN);
    green.setDiffuseColor(Color.DARKGREEN);

    red = new PhongMaterial();
    red.setSpecularColor(Color.RED);
    red.setDiffuseColor(Color.RED);

    purple = new PhongMaterial();
    purple.setSpecularColor(Color.PURPLE);
    purple.setDiffuseColor(Color.PURPLE);

    darkBlue = new PhongMaterial();
    darkBlue.setSpecularColor(Color.DARKBLUE);
    darkBlue.setDiffuseColor(Color.DARKBLUE);

    lightBlue = new PhongMaterial();
    lightBlue.setSpecularColor(Color.BLUE);
    lightBlue.setDiffuseColor(Color.BLUE);

    box.setMaterial(lightBlue);
  }

  /**
   * update checks the cells status and updates according to that status
   */
  void update()
  {

    if (isBecomingAlive && !wasAlive)
    {


      Material current = box.getMaterial();

      if (current.equals(darkRed))
      {
        box.setMaterial(yellow);
        wasDead = false;
        box.setVisible(true);
        box.setScaleX(.4);
        box.setScaleY(.4);
        box.setScaleZ(.4);
      }
      else if (current.equals(yellow))
      {
        box.setMaterial(green);
        box.setScaleX(.6);
        box.setScaleY(.6);
        box.setScaleZ(.6);
      }
      else if (current.equals(green))
      {
        box.setMaterial(lightGreen);
        box.setScaleX(.8);
        box.setScaleY(.8);
        box.setScaleZ(.8);
      }
      else if (current.equals(lightGreen))
      {
        box.setMaterial(lightBlue);
        box.setScaleX(1);
        box.setScaleY(1);
        box.setScaleZ(1);
      }
      else if (current.equals(lightBlue))
      {
        wasAlive = true;
      }

    }
    else if (isDying && !wasDead)
    {
      box.setVisible(true);


      Material current = box.getMaterial();

      if (current.equals(darkRed))
      {
        box.setVisible(false);
        wasDead = true;

      }
      else if (current.equals(red))
      {
        box.setMaterial(darkRed);
        box.setScaleX(.2);
        box.setScaleY(.2);
        box.setScaleZ(.2);
      }
      else if (current.equals(purple))
      {
        box.setMaterial(red);
        box.setScaleX(.4);
        box.setScaleY(.4);
        box.setScaleZ(.4);
      }
      else if (current.equals(darkBlue))
      {
        box.setMaterial(purple);
        box.setScaleX(.6);
        box.setScaleY(.6);
        box.setScaleZ(.6);
      }
      else if (current.equals(lightBlue))
      {
        box.setMaterial(darkBlue);
        box.setScaleX(.8);
        box.setScaleY(.8);
        box.setScaleZ(.8);
        wasAlive = false;
      }
    }
  }

  /**
   * setDead sets the cell to dead for idolization
   */
  void setDead()
  {
    this.box.setMaterial(darkRed);
    wasDead = true;
  }
}

