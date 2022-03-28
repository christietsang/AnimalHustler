package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGLForKtKt.image;

public class AnimalComponent {

    private PhysicsComponent physics;

    private AnimatedTexture texture;

    private AnimationChannel animIdle;

    public AnimalComponent(){

        Image animIdle = image("brownCowIdle.png");


        this.animIdle = new AnimationChannel(animIdle, 4, 17, 17,Duration.seconds(1), 0, 0);

    }


}
