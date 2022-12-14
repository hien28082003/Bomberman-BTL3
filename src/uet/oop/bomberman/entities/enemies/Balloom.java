package uet.oop.bomberman.entities.enemies;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enemy {
    private static final int BalloomSpeed = 1;
    private boolean balloomCollision = false;

    public Balloom(int x, int y, Sprite sprite) {
        super( x, y, sprite);
        sprite = Sprite.balloom_left1;
    }

    public void move() {
        calculateMove(BalloomSpeed);
        x += dx;
        y += dy;
        changepos(x, y, '1');
        for (int i = 0; i < BombermanGame.stillObjects.size(); i++) {
            if (BombermanGame.stillObjects.get(i) instanceof Wall) {
                if(this.checkCollision(BombermanGame.stillObjects.get(i))) {
                    x -= dx;
                    y -= dy;
                }
            }
        }
        for (int i = 0; i < BombermanGame.entities.size(); i++) {
            if (BombermanGame.entities.get(i) instanceof Brick
                    && this.checkCollision(BombermanGame.entities.get(i))) {
                x -= dx;
                y -= dy;
            }
            /*if (BombermanGame.entities.get(i) instanceof Enemy
                    && this.checkCollision(BombermanGame.entities.get(i))) {
                while (x % 32 != 0 || y % 32 != 0) {
                    x -= dx;
                    y -= dy;
                }
            }*/
        }
    }

    public void setCurrentSprite() {
        if (dx > 0 || dy > 0) {
            this.sprite = Sprite.movingSprite(Sprite.balloom_right1,
                    Sprite.balloom_right2, Sprite.balloom_right3, selfcount, 20);
        }
        if (dx < 0 || dy < 0) {
            this.sprite = Sprite.movingSprite(Sprite.balloom_left1,
                    Sprite.balloom_left2, Sprite.balloom_left3, selfcount, 20);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        setCurrentSprite();
        Image cur = sprite.getFxImage();
        gc.drawImage(cur, x, y);
    }

    @Override
    public void update(Scene scene) {
        move();
    }
}
