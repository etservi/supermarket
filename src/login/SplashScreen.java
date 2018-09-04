package login;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.animation.FadeTransition;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;

class SplashScreen {

  public SplashScreen() { }

  public static Stage render (double[] dimensions, Image ... img) {
    /* Splash Screen*/
    Stage splash = new Stage();

    //Add icon
    splash.getIcons().add(img[0]);

    //Root Node
    StackPane splashRootNode = new StackPane(new ImageView(img[1]));
    //Set a background image
    Background background = new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY));

    //Background -> Root Node
    splashRootNode.setBackground(background);

    FadeTransition inAnim = new FadeTransition(Duration.seconds(1.5), splashRootNode);
    inAnim.setFromValue(0);
    inAnim.setToValue(1);
    inAnim.setCycleCount(1);

    FadeTransition outAnim = new FadeTransition(Duration.seconds(1.5), splashRootNode);
    outAnim.setFromValue(1);
    outAnim.setToValue(0);
    outAnim.setCycleCount(1);

    splash.setScene(new Scene(splashRootNode, 480, 615));

    inAnim.play();
    inAnim.setOnFinished((e) -> outAnim.play() );
    outAnim.setOnFinished((e) -> splash.close() );	



    return splash;
  }
}


