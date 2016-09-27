package main;

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
  PhongMaterial
  public Cells()
  {
    box = new Box(1,1,1);
  }
  public void update()
  {
    if(isBecomingAlive && !wasAlive)
    {
      box.setVisible(true);
      box.setScaleX(1.5);
      box.setScaleY(1.5);
      box.setScaleZ(1.5);
//      Material current = box.getMaterial();


    }
    if(isDying&& !wasDead)
    {
      box.setVisible(true);
      box.setScaleX(.5);
      box.setScaleY(.5);
      box.setScaleZ(.5);
    }
  }
}

