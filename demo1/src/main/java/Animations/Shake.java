package Animations;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;

public class Shake {
    private final TranslateTransition tT;

    public Shake (Node node) {
        tT = new TranslateTransition(Duration.millis(70), node);
        tT.setFromX(0);
        tT.setByX(10);
        tT.setCycleCount(3);
        tT.setAutoReverse(true);

    }
    public void playAnimation () {
        tT.playFromStart();
    }
}
