package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

/**
 * Created by family on 9/27/16.
 */
public class Cells
{
  Box box;
  boolean isBecomingAlive;
  boolean isDying;
  boolean wasDead;
  boolean wasAlive;

  PhongMaterial darkRed;
  PhongMaterial red;
  PhongMaterial purple;
  PhongMaterial darkBlue;
  PhongMaterial lightBlue;
  public Cells()
  {
    box = new Box(1,1,1);


    darkRed = new PhongMaterial();
    darkRed.setSpecularColor(Color.DARKRED);
    darkRed.setDiffuseColor(Color.DARKRED);

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
    lightBlue.setSpecularColor(Color.LIGHTBLUE);
    lightBlue.setDiffuseColor(Color.LIGHTBLUE);

    box.setMaterial(lightBlue);
  }
  public void update()
  {

    if(isBecomingAlive && !wasAlive)
    {
      box.setVisible(true);

      Material current = box.getMaterial();

      if(current.equals(darkRed))
      {
        box.setVisible(true);
        box.setMaterial(red);
        box.setScaleX(.4);
        box.setScaleY(.4);
        box.setScaleZ(.4);
      }
     else if(current.equals(red))
      {
        box.setMaterial(purple);
        box.setScaleX(.6);
        box.setScaleY(.6);
        box.setScaleZ(.6);
      }
      else if(current.equals(purple))
      {
        box.setMaterial(darkBlue);
        box.setScaleX(.8);
        box.setScaleY(.8);
        box.setScaleZ(.8);
      }
      else if(current.equals(darkBlue))
      {
        box.setMaterial(lightBlue);
        box.setScaleX(1);
        box.setScaleY(1);
        box.setScaleZ(1);
      }

    }
    if(isDying && !wasDead)
    {
      box.setVisible(true);


      Material current = box.getMaterial();

      if(current.equals(darkRed))
      {
        box.setVisible(false);
      }
      else if(current.equals(red))
      {
        box.setMaterial(darkRed);
        box.setScaleX(.2);
        box.setScaleY(.2);
        box.setScaleZ(.2);
      }
      else if(current.equals(purple))
      {
        box.setMaterial(red);
        box.setScaleX(.4);
        box.setScaleY(.4);
        box.setScaleZ(.4);
      }
      else if(current.equals(darkBlue))
      {
        box.setMaterial(purple);
        box.setScaleX(.6);
        box.setScaleY(.6);
        box.setScaleZ(.6);
      }
       else if(current.equals(lightBlue))
      {
        box.setMaterial(darkBlue);
        box.setScaleX(.8);
        box.setScaleY(.8);
        box.setScaleZ(.8);
      }
    }
  }
}

