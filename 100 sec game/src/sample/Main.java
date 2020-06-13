package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.util.Duration;


import java.util.Random;

import static java.lang.Math.*;

public class Main extends Application
{private Pane root=new Pane();

private Sprite player=new Sprite(300,400,30,30,Color.BLUE);
private  Circle circle=new Circle();
 private   Text score=new Text(10,20,"Score:0");
private int count=0,time=0;
private Text Time=new Text(10,40,"Time:0");
private static class Sprite extends Rectangle
{

Sprite(int x, int y,int w,int h, Color color)
    {super (w,h,color);

        setTranslateX(x);
        setTranslateY(y);
    }
    void moveLeft()
    {if (getTranslateX()>=30)
        setTranslateX(getTranslateX()-10);
    }
    void moveRight()
    {if (getTranslateX()<=500)
        setTranslateX(getTranslateX()+10);
    }
    void moveUp()
    {if (getTranslateY()>=30)
        setTranslateY(getTranslateY()-10);
    }
    void moveDown(){
        if (getTranslateY()<=700)
    setTranslateY(getTranslateY()+10);
    }


}


private Parent createcontent()
{
score.setId("score");
Time.setId("Time");
    Random rand=new Random();
    circle.setCenterX(30+rand.nextInt(47)*10);
    circle.setCenterY(30+rand.nextInt(67)*10);
    circle.setRadius(10);
    circle.setFill(Color.YELLOW);
root.getChildren().add(score);
root.getChildren().add(Time);
    root.getChildren().add(player);
root.getChildren().add(circle);
return root;
}
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {Scene scene=new Scene(createcontent(),600,800);
        scene.getStylesheets().add("Login.css");

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               time++;
               Time.setText("Time:"+Integer.toString(time));
                if (time==100)
                { Pane temp=new Pane();
                Button b=new Button("Restart");
                b.setTranslateY(400);
                b.setTranslateX(300);
                Text temptext=new Text(300,350,"Score:"+Integer.toString(count));
                temptext.setId("temptext");
                    temp.getChildren().add(temptext);
temp.getChildren().add(b);
                scene.setRoot(temp);

                    b.setOnAction(e->
                    {scene.setRoot(root);

                        player.setTranslateX(300);
                        player.setTranslateY(400);
                        time=0;count=0;
                        Time.setText("Time:"+Integer.toString(time));
                        score.setText("Score:"+Integer.toString(count));
                        Random rand=new Random();
                        circle.setCenterX(30+rand.nextInt(47)*10);
                        circle.setCenterY(30+rand.nextInt(67)*10);
                        circle.setRadius(10);
                        circle.setFill(Color.YELLOW);

                    }


                );




                }
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
scene.setOnKeyPressed(e->
{if (e.getCode()== KeyCode.A)
    player.moveLeft();
    if (e.getCode()== KeyCode.D)
        player.moveRight();
    if (e.getCode()== KeyCode.S)
        player.moveDown();
    if (e.getCode()== KeyCode.W)
        player.moveUp();
    if (player.getTranslateX()+30>=circle.getCenterX()+10 && player.getTranslateX()<=circle.getCenterX()-10 &&
            player.getTranslateY()+30>=circle.getCenterY()+10  &&  player.getTranslateY()<=circle.getCenterY()-10)

    {Random rand=new Random();
        circle.setCenterX(30+rand.nextInt(47)*10);
        circle.setCenterY(30+rand.nextInt(67)*10);
        circle.setRadius(10);
        circle.setFill(Color.YELLOW);
        count++;
score.setText("Score:"+Integer.toString(count));
    }
});
    stage.setScene(scene);

stage.show();
    }
}
