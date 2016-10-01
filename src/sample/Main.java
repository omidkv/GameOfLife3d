package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Kevin Omidvaran
 *
 * Main is the class that the application is run from.
 * It extends sample and has two internal classes.
 */

public class Main extends Application
{

  private Xform cube = new Xform();


  private Group root3D = new Group();
  private static final double CAMERA_INITIAL_DISTANCE = -100;
  private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
  private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
  private static final double CAMERA_NEAR_CLIP = 0.1;
  private static final double CAMERA_FAR_CLIP = 10000.0;

  private final Xform world = new Xform();
  final PerspectiveCamera camera = new PerspectiveCamera(true);
  private final Xform cameraXform = new Xform();
  private final Xform cameraXform2 = new Xform();
  private final Xform cameraXform3 = new Xform();
  private BorderPane bp = new BorderPane();

  private Cells[][][] cells = new Cells[32][32][32];

  private Scene scene = new Scene(bp, 1000, 1000, true);
  private SubScene subScene = new SubScene(root3D, 1000, 750);

  private GameEngine gameEngine = new GameEngine();

  Slider slider;
  Slider slider1;
  Slider slider2;
  Slider slider3;
  Slider slider4;

  private MenuItem preset1;
  private MenuItem preset2;
  private MenuItem preset3;
  private MenuItem preset4;
  private MenuItem preset5;

  double updateMultiplier = 1;

  private long lastUpdate = 0;
  private long lastRender = 0;

  /**
   * This is the method that initilizes everything and adds it to the scene
   * It also starts the game loop
   *
   * @param primaryStage The stage for the application
   * @throws Exception  Any exception that can be thrown
   */
  @Override
  public void start(Stage primaryStage) throws Exception
  {

    gameEngine.initial1();
    subScene.setFill(Color.GREY);


    buildControls();
    buildCamera();

    buildCells();
    bp.setCenter(subScene);


    primaryStage.setTitle("3D Game of Life");
    primaryStage.setScene(scene);
    primaryStage.show();
    subScene.setCamera(camera);

    GameLoop gl = new GameLoop();
    gl.start();
  }

  /**
   * Build Camera is taking from the molecule lab. I teaked it a little so that it hads things to the root3D
   */
  private void buildCamera()
  {
    world.setOnScroll(new Scroll(this, gameEngine));
    root3D.getChildren().add(cameraXform);
    root3D.getChildren().add(world);


    cameraXform.getChildren().add(cameraXform2);
    cameraXform2.getChildren().add(cameraXform3);
    cameraXform3.getChildren().add(camera);
    cameraXform3.setRotateZ(180.0);

    camera.setNearClip(CAMERA_NEAR_CLIP);
    camera.setFarClip(CAMERA_FAR_CLIP);
    camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
    cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
    cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
  }

  /**
   * Build Controls is the method where I set up all of the menu bars and sliders inside of them.
   */
  private void buildControls()
  {
    MenuBar controls = new MenuBar();
    Menu controller = new Menu("R1");
    slider = new Slider(1, 26, gameEngine.r1);
    slider.setMajorTickUnit(1);
    slider.setMinorTickCount(0);
    slider.snapToTicksProperty().set(true);
    slider.valueProperty().addListener(new Scroll(this, gameEngine));
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    CustomMenuItem cs = new CustomMenuItem(slider);
    cs.setHideOnClick(false);
    controller.getItems().add(cs);
    controls.getMenus().add(controller);

    Menu controller1 = new Menu("R2");
    slider1 = new Slider(1, 26, gameEngine.r2);
    slider1.setShowTickMarks(true);
    slider1.setShowTickLabels(true);
    slider1.setMajorTickUnit(1);
    slider1.setMinorTickCount(0);
    slider1.snapToTicksProperty().set(true);
    slider1.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs1 = new CustomMenuItem(slider1);
    cs1.setHideOnClick(false);
    controller1.getItems().add(cs1);
    controls.getMenus().add(controller1);

    Menu controller2 = new Menu("R3");
    slider2 = new Slider(1, 26, gameEngine.r3);
    slider2.setShowTickMarks(true);
    slider2.setShowTickLabels(true);
    slider2.setMajorTickUnit(1);
    slider2.setMinorTickCount(0);
    slider2.snapToTicksProperty().set(true);
    slider2.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs2 = new CustomMenuItem(slider2);
    cs2.setHideOnClick(false);
    controller2.getItems().add(cs2);
    controls.getMenus().add(controller2);

    Menu controller3 = new Menu("R4");
    slider3 = new Slider(1, 26, gameEngine.r4);
    slider3.setShowTickMarks(true);
    slider3.setShowTickLabels(true);
    slider3.setMajorTickUnit(1);
    slider3.setMinorTickCount(0);
    slider3.snapToTicksProperty().set(true);
    slider3.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs3 = new CustomMenuItem(slider3);
    cs3.setHideOnClick(false);
    controller3.getItems().add(cs3);
    controls.getMenus().add(controller3);

    Menu controller4 = new Menu("Update Multiplier");
    slider4 = new Slider(1, 5, 1);
    slider4.setShowTickMarks(true);
    slider4.setShowTickLabels(true);
    slider4.setMajorTickUnit(1);
    slider4.setMinorTickCount(0);
    slider4.snapToTicksProperty().set(true);
    slider4.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs4 = new CustomMenuItem(slider4);
    cs4.setHideOnClick(false);
    controller4.getItems().add(cs4);
    controls.getMenus().add(controller4);


    Menu controller5 = new Menu("Presets");
    preset1 = new MenuItem("Presets 1");
    preset1.setOnAction(new MenuListener(this));
    controller5.getItems().add(preset1);
    preset2 = new MenuItem("Presets 2");
    preset2.setOnAction(new MenuListener(this));
    controller5.getItems().add(preset2);
    preset3 = new MenuItem("Presets 3");
    preset3.setOnAction(new MenuListener(this));
    controller5.getItems().add(preset3);
    preset4 = new MenuItem("Presets 4");
    preset4.setOnAction(new MenuListener(this));
    controller5.getItems().add(preset4);
    preset5 = new MenuItem("Presets 5");
    preset5.setOnAction(new MenuListener(this));
    controller5.getItems().add(preset5);

    controls.getMenus().add(controller5);

    bp.setTop(controls);
  }

  /**
   * BuildCells is where all of the cells are initialized and placed in the cube xform that is then added to world.
   */
  public void buildCells()
  {

    for (int i = 1; i < 31; i++)
    {
      for (int j = 1; j < 31; j++)
      {

        for (int k = 1; k < 31; k++)
        {

          cells[i][j][k] = new Cells();
          cells[i][j][k].box.setTranslateX(i - 15);
          cells[i][j][k].box.setTranslateY(j - 15);
          cells[i][j][k].box.setTranslateZ(k - 15);
          if (gameEngine.board[i][j][k])
          {
            if (gameEngine.checkSurrounding(i, j, k) != 26)
            {
              cells[i][j][k].box.setVisible(true);
            }

            cells[i][j][k].wasAlive = true;
          }
          else
          {

            cells[i][j][k].box.setVisible(false);
            cells[i][j][k].wasDead = true;
            cells[i][j][k].setDead();

          }
          cube.getChildren().add(cells[i][j][k].box);

        }
      }
    }
    world.getChildren().addAll(cube);
  }

  /**
   *GameLoop is a class that extends AnimationTimer. This is where the clock ticks and updates the application.
   *
   */
  private class GameLoop extends AnimationTimer
  {

    /**
     *  Handle is an overriden method. Every second it updates the gameEngine. In everyother tick it updates the cells
     *  It also rotates the camera every tick.
     * @param now Now is a given param that is a timestamp.
     */
    @Override
    public void handle(long now)
    {

      if (now - lastUpdate > updateMultiplier * 1_000_000_000)
      {
        lastUpdate = now;

        gameEngine.upDate();
        for (int i = 1; i < 31; i++)
        {
          for (int j = 1; j < 31; j++)
          {
            for (int k = 1; k < 31; k++)
            {
              if (!gameEngine.board[i][j][k])
              {
                cells[i][j][k].isDying = true;
                cells[i][j][k].isBecomingAlive = false;
                cells[i][j][k].update();
              }
              else
              {
                cells[i][j][k].isBecomingAlive = true;
                cells[i][j][k].isDying = false;
                cells[i][j][k].update();
              }
            }
          }
        }
      }
      if (now - lastRender >= updateMultiplier * 100_000_000)
      {
        lastRender = now;
        for (int i = 1; i < 31; i++)
        {
          for (int j = 1; j < 31; j++)
          {
            for (int k = 1; k < 31; k++)
            {
              cells[i][j][k].update();
            }
          }
        }
      }
      cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 1);
      cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 1);
    }
  }

  /**
   * Restart removes the cube from world and then changes the cube pointer to a different xform and then calls an initil
   * from gameEngine and then builds the cells.
   * @param preset Is a param that is a flag for which preset is selected.
   */
  private void restart(int preset)
  {
    world.getChildren().removeAll(cube);
    cube = new Xform();
    if (preset == 1)
    {
      gameEngine.initial1();
    }
    if (preset == 2)
    {
      gameEngine.initial2();
    }
    if (preset == 3)
    {
      gameEngine.initial3();
    }
    if (preset == 4)
    {
      gameEngine.initial4();
    }
    if (preset == 5)
    {
      gameEngine.initial5();
    }

    buildCells();

  }

  /**
   * MenuListener
   * This is a private class that is the EventHandler for the preset menu.
   */
  private class MenuListener implements EventHandler<ActionEvent>
  {

    Main main;

    /*
     * Constructor to pass the object a Main object
     */
   private MenuListener(Main main)
    {
      this.main = main;
    }

    /**
     * Takes an event and based on what it is equal to it sets a restart param.
     * @param event Event is an action in the menu.
     */
    @Override
    public void handle(ActionEvent event)
    {
      MenuItem mI = (MenuItem) event.getSource();
      if (mI == main.preset1)
      {
        main.restart(1);
      }
      else if (mI == main.preset2)
      {
        main.restart(2);
      }
      else if (mI == main.preset3)
      {
        main.restart(3);
      }
      else if (mI == main.preset4)
      {
        main.restart(4);
      }
      else if (mI == main.preset5)
      {
        main.restart(5);
      }
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
