package uet.oop.bomberman.entities;

import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    public int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    public int y;
    protected Sprite sprite;
    protected Image img;
    protected static int selfcount = 0;
    public static int vx[] = {1, -1, 0, 0};
    public static int vy[] = {0, 0, 1, -1};

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Sprite sprite) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.sprite = sprite;
        this.img = sprite.getFxImage();
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update(Scene scene);

    public boolean checkCollision(Entity other) {
        int this_top = y;
        int this_bottom = y + sprite.get_realHeight() * 2;
        int this_left = x;
        int this_right = x + sprite.get_realWidth() * 2;
        int other_top = other.y;
        int other_bottom = other.y + other.sprite.get_realHeight() * 2;
        int other_left = other.x;
        int other_right = other.x + other.sprite.get_realWidth() * 2;
        if (this_left < other_right && this_left > other_left) {
            if (this_bottom < other_bottom && this_bottom > other_top) {
                return true;
            }
            if (this_top < other_bottom && this_top > other_top) {
                return true;
            }
        }
        if (this_right < other_right && this_right > other_left) {
            if (this_bottom < other_bottom && this_bottom > other_top) {
                return true;
            }
            if (this_top < other_bottom && this_top > other_top) {
                return true;
            }
        }
        if (this_left == other_left && this_right == other_right) {
            if (this_bottom < other_bottom && this_bottom > other_top) {
                return true;
            }
            if (this_top < other_bottom && this_top > other_top) {
                return true;
            }
        }
        if (this_top == other_top && this_bottom == other_bottom) {
            if (this_left < other_right && this_left > other_left) {
                return true;
            }
            if (this_right < other_right && this_right > other_left) {
                return true;
            }
        }
        return false;
    }

    public void count() {
        selfcount++;
        if (selfcount == 100) selfcount = 0;
    }

    public void setCurrentSprite() {};

    public void changepos(int x, int y, char symbol) {
        if ((x) % 32 == 0 && (y) % 32 == 0) {
            for (int k = 0; k < 4; k++) {
                if (y / 32 + vy[k] >= 0 && y / 32 + vy[k] < BombermanGame.HEIGHT && x / 32 + vx[k] >= 0 && x / 32 + vx[k] < BombermanGame.WIDTH) {
                    if (BombermanGame.getGrid(y / 32 + vy[k], x / 32 + vx[k]) == symbol) {
                        BombermanGame.setGrid(y / 32 + vy[k], x / 32 + vx[k], ' ');
                    }
                }
            }
            if (y / 32 >= 0 && y / 32 < BombermanGame.HEIGHT && x / 32 >= 0 && x / 32 < BombermanGame.WIDTH)BombermanGame.setGrid((y) / 32, (x) / 32, symbol);
        }
    }
}
