package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.scene.shape.Box;


public class Main extends Application
{

  final Xform cube = new Xform();


  Group root3D = new Group();
  private static final double CAMERA_INITIAL_DISTANCE = -100;
  private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
  private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
  private static final double CAMERA_NEAR_CLIP = 0.1;
  private static final double CAMERA_FAR_CLIP = 10000.0;

  final Xform world = new Xform();
  final PerspectiveCamera camera = new PerspectiveCamera(true);
  final Xform cameraXform = new Xform();
  final Xform cameraXform2 = new Xform();
  final Xform cameraXform3 = new Xform();
  BorderPane bp = new BorderPane();

  Cells[][][] cells = new Cells[32][32][32];

  Scene scene = new Scene(bp, 1000, 1000, true);
  SubScene subScene = new SubScene(root3D, 750, 750);

  GameEngine gameEngine = new GameEngine();

  Slider slider;
  Slider slider1;
  Slider slider2;
  Slider slider3;

  long lastUpdate = 0;

  @Override
  public void start(Stage primaryStage) throws Exception
  {

    gameEngine.initil();
    scene.setFill(Color.WHITE);
    buildControls();
    buildCamera();

    buildCells();
    bp.setCenter(subScene);
//        world.setOnScroll(new EventHandler<ScrollEvent>()
//        {
//          @Override
//          public void handle(ScrollEvent event)
//          {
//            if (event.getDeltaY() < 0)
//            {
//              camera.setTranslateZ();
//
//            }
//          }
//        });

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(scene);
    primaryStage.show();
    subScene.setCamera(camera);

    GameLoop gl = new GameLoop();
    gl.start();
  }

  private void buildCamera()
  {
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

  public void buildControls()
  {
    MenuBar controls = new MenuBar();
    Menu controler = new Menu("R1");
    slider = new Slider(1, 26, 5);
    slider.setMajorTickUnit(1);
    slider.setMinorTickCount(0);
    slider.snapToTicksProperty().set(true);
    slider.valueProperty().addListener(new Scroll(this, gameEngine));
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    CustomMenuItem cs = new CustomMenuItem(slider);
    cs.setHideOnClick(false);
    controler.getItems().add(cs);
    controls.getMenus().add(controler);

    Menu controler1 = new Menu("R2");
    slider1 = new Slider(1, 26, 10);
    slider1.setShowTickMarks(true);
    slider1.setShowTickLabels(true);
    slider1.setMajorTickUnit(1);
    slider1.setMinorTickCount(0);
    slider1.snapToTicksProperty().set(true);
    slider1.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs1 = new CustomMenuItem(slider1);
    cs1.setHideOnClick(false);
    controler1.getItems().add(cs1);
    controls.getMenus().add(controler1);

    Menu controler2 = new Menu("R3");
    slider2 = new Slider(1, 26, 20);
    slider2.setShowTickMarks(true);
    slider2.setShowTickLabels(true);
    slider2.setMajorTickUnit(1);
    slider2.setMinorTickCount(0);
    slider2.snapToTicksProperty().set(true);
    slider2.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs2 = new CustomMenuItem(slider2);
    cs2.setHideOnClick(false);
    controler2.getItems().add(cs2);
    controls.getMenus().add(controler2);

    Menu controler3 = new Menu("R4");
    slider3 = new Slider(1, 26, 26);
    slider3.setShowTickMarks(true);
    slider3.setShowTickLabels(true);
    slider3.setMajorTickUnit(1);
    slider3.setMinorTickCount(0);
    slider3.snapToTicksProperty().set(true);
    slider3.valueProperty().addListener(new Scroll(this, gameEngine));
    CustomMenuItem cs3 = new CustomMenuItem(slider3);
    cs3.setHideOnClick(false);
    controler3.getItems().add(cs3);
    controls.getMenus().add(controler3);

    bp.setTop(controls);
  }

  public void buildCells()
  {
    PhongMaterial aqua = new PhongMaterial();
    aqua.setSpecularColor(Color.BLUE);
    aqua.setDiffuseColor(Color.BLUE);
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
          cells[i][j][k].box.setMaterial(aqua);
          cube.getChildren().add(cells[i][j][k].box);

        }
      }
    }
    world.getChildren().addAll(cube);
  }

  class GameLoop extends AnimationTimer
  {

    @Override
    public void handle(long now)
    {
      PhongMaterial aqua = new PhongMaterial();
      aqua.setSpecularColor(Color.GREEN);
      aqua.setDiffuseColor(Color.GREEN);

      PhongMaterial red = new PhongMaterial();
      red.setSpecularColor(Color.RED);
      red.setDiffuseColor(Color.RED);
      System.out.println(now);
      if (now - lastUpdate > 1_000_000_000 )
      {
        lastUpdate = now;
         System.out.println("NuMber1");
        for (int i = 1; i < 31; i++)
        {
          for (int j = 1; j < 31; j++)
          {
            for (int k = 1; k < 31; k++)
            {
              if (!gameEngine.board[i][j][k])
              {
                cells[i][j][k].wasDead = true;
              }
              else
              {
                cells[i][j][k].wasAlive = true;

              }
            }
          }
        }
        gameEngine.upDate();
        for ( int i = 1; i < 31; i++)
        {
          for ( int j = 1; j < 31; j++)
          {
            for ( int k = 1; k < 31; k++)
            {
              if (!gameEngine.board[i][j][k])
              {
                cells[i][j][k].isDying = true;
              }
              else
              {
                cells[i][j][k].isBecomingAlive = true;

              }
            }
          }
        }
      }
      for ( int i = 1; i < 31; i++)
      {
        for ( int j = 1; j < 31; j++)
        {
          for ( int k = 1; k < 31; k++)
          {
          cells[i][j][k].update();
          }
        }
      }

      cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 5);
      cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 5);
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
