package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by family on 9/23/16.
 */
public class Scroll implements ChangeListener<Number>
{
  Main main;
  GameEngine gameEngine;

  public Scroll(Main main, GameEngine gameEngine)
  {
    this.main = main;
    this.gameEngine = gameEngine;
  }


  @Override
  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
  {
    gameEngine.r1 = main.slider.getValue();
    gameEngine.r2 = main.slider1.getValue();
    gameEngine.r3 = main.slider2.getValue();
    gameEngine.r4 = main.slider3.getValue();


  }
//    System.out.println("HERE");
//    if(observable == main.slider)
//    {
//      gameEngine.r1 = main.slider.getValue();
//      System.out.println("Value changed : 1" );
//    }
//    if(observable == main.slider1)
//    {
//      gameEngine.r2 = main.slider.getValue();
//      System.out.println("Value changed : 2" );
//
////      System.out.println("Value changed :" + gameEngine.r2);
//    }
//    if(observable == main.slider2)
//    {
//      gameEngine.r3 = main.slider.getValue();
//      System.out.println("Value changed : 3" );
//
////      System.out.println("Value changed :" + gameEngine.r3);
//    }
//    if(observable == main.slider3)
//    {
//      gameEngine.r4 = main.slider.getValue();
//      System.out.println("Value changed : 4" );
//
////      System.out.println("Value changed :" + gameEngine.r4);
//    }
//  }
  }

