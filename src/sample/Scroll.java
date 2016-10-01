package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

/**
 * Kevin Omidvaran
 * Scroll is the slider and scroll handler for the Main class
 */
class Scroll implements ChangeListener<Number>, EventHandler<ScrollEvent>
{
  private Main main;
  private GameEngine gameEngine;

  /*
    Scroll constructor to assign a sample class and a game engine to the object.
   */
  Scroll(Main main, GameEngine gameEngine)
  {
    this.main = main;
    this.gameEngine = gameEngine;
  }

  /**
   * Takes the slider values when one is changed and assigns them to r1, r2, r3, r4
   */
  @Override
  public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
  {
    gameEngine.r1 = main.slider.getValue();
    gameEngine.r2 = main.slider1.getValue();
    gameEngine.r3 = main.slider2.getValue();
    gameEngine.r4 = main.slider3.getValue();
    main.updateMultiplier = main.slider4.getValue();


  }
  /*
   * Taken from the molecule project to use as a zoom attached to the scroll.
   */

  @Override
  public void handle(ScrollEvent event)
  {
    double mouseDeltaX = event.getDeltaY();
    double z = main.camera.getTranslateZ();
    double newZ = z + mouseDeltaX;
    if (newZ < -43 && newZ > -200)
    {

      main.camera.setTranslateZ(newZ);
    }
  }


}


