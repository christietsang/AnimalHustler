package ca.bcit.comp2522.termproject.christiebelal.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.image.Image;

import static com.almasb.fxgl.dsl.FXGL.image;

public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    public PlayerComponent() {
    }

    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-170);
    }

    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(170);
    }

    public void up() {
        getEntity().setScaleY(1);
        physics.setVelocityY(-170);
    }

    public void down() {
        getEntity().setScaleY(-1);
        physics.setVelocityY(170);
    }

    public void stop() {
        physics.setVelocityX(0);
        physics.setVelocityY(0);
    }
}
