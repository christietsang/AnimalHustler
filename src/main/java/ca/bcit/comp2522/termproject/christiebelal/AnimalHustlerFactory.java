package ca.bcit.comp2522.termproject.christiebelal;

import ca.bcit.comp2522.termproject.christiebelal.components.AnimalComponent;
import ca.bcit.comp2522.termproject.christiebelal.components.PlayerComponent;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.ui.ProgressBar;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.COW;
import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.PLAYER;
import static ca.bcit.comp2522.termproject.christiebelal.AnimalHustlerType.WALL;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static com.almasb.fxgl.dsl.FXGLForKtKt.play;
import static com.almasb.fxgl.dsl.FXGLForKtKt.texture;

/**
 * Handles the properties of game entities.
 *
 * @author Christie Tsang
 * @author Belal Kourkmas
 * @version 2022
 */
public class AnimalHustlerFactory implements EntityFactory {
    /**
     * Spawns a player entity.
     *
     * @param data contains essential spawn data
     * @return a player entity
     */
    @Spawns("player")
    public Entity newPlayer(final SpawnData data) {
        final int hitBoxOffsetX = 22;
        final int hitBoxOffsetY = 32;
        final int boundingBoxWidth = 30;
        final int boundingBoxHeight = 30;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder(data)
                .type(PLAYER)
                .with(physics)
                .bbox(new HitBox(new Point2D(hitBoxOffsetX, hitBoxOffsetY), BoundingShape.box(boundingBoxWidth,
                        boundingBoxHeight)))
                .with(new CollidableComponent(true))
                .with(new PlayerComponent())
                .build();
    }

    /**
     * Spawns a cow entity.
     *
     * @param data contains essential spawn data
     * @return a cow entity
     */
    @Spawns("cow")
    public Entity newCow(final SpawnData data) {
        final int cowHealth = 10;
        final int healthBarWidth = 85;
        final int healthBarOffsetX = -9;
        final int healthBarOffsetY = -15;
        final int cowHitBoxOffsetX = -5;
        final int cowHitBoxOffsetY = -5;
        final int cowBoundingBoxWidth = 76;
        final int cowBoundingBoxHeight = 51;
        var hp = new HealthIntComponent(cowHealth);
        var hpView = new ProgressBar(false);
        hpView.setFill(Color.LIGHTGREEN);
        hpView.setMaxValue(cowHealth);
        hpView.setWidth(healthBarWidth);
        hpView.setTranslateX(healthBarOffsetX);
        hpView.setTranslateY(healthBarOffsetY);
        hpView.currentValueProperty().bind(hp.valueProperty());

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder(data)
                .type(COW)
                .with(physics)
                .bbox(new HitBox(new Point2D(cowHitBoxOffsetX, cowHitBoxOffsetY),
                        BoundingShape.box(cowBoundingBoxWidth, cowBoundingBoxHeight)))
                .with(new CollidableComponent(true))
                .with(new AnimalComponent())
                .view(hpView)
                .with(hp)
                .build();
    }

    /**
     * Spawns a wall entity.
     *
     * @param data contains essential spawn data
     * @return a wall entity
     */
    @Spawns("wall")
    public Entity newWall(final SpawnData data) {
        return entityBuilder(data)
                .type(WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .with(new CollidableComponent(true))
                .build();
    }

    /**
     * Spawns an explosion entity.
     *
     * @param data contains essential spawn data
     * @return an explosion entity
     */
    @Spawns("explosion")
    public Entity newExplosion(final SpawnData data) {
        final int explosionRadius = 350;
        final int maxExplosionEmissions = 1;
        final int minExplosionSize = 2;
        final int maxExplosionSize = 10;
        final int explosionSpawnPointOffsetX = 64;
        final int explosionSpawnPointOffsetY = 64;
        final int explosionFrameCount = 16;
        final double explosionDurationSeconds = 0.66;
        play("explosion.wav");
        var emitter = ParticleEmitters.newExplosionEmitter(explosionRadius);
        emitter.setMaxEmissions(maxExplosionEmissions);
        emitter.setSize(minExplosionSize, maxExplosionSize);
        emitter.setStartColor(Color.WHITE);
        emitter.setEndColor(Color.BLUE);
        emitter.setSpawnPointFunction(i -> new Point2D(explosionSpawnPointOffsetX, explosionSpawnPointOffsetY));

        return entityBuilder(data)
                .view(texture("explosion.png").toAnimatedTexture(explosionFrameCount,
                        Duration.seconds(explosionDurationSeconds)).play())
                .with(new ExpireCleanComponent(Duration.seconds(explosionDurationSeconds)))
                .with(new ParticleComponent(emitter))
                .build();
    }
}
