package fr.jgully.manalajumper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class PlayerObject extends GameObject {

    private static final int playerWidth = 50;
    private static final int playerHeight = 170;

    private float xVel = 0;
    private float yVel = 0;
    private static final float MAX_VEL_Y = 10;
    private static final float JUMP_VEL = -10;

    private Paint playerPaint;
    private static final float GRAVITY = .1f;

    public PlayerObject() {
        super(200, -1000, playerWidth, playerHeight);
        playerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        playerPaint.setColor(Color.BLUE);
        playerPaint.setStyle(Paint.Style.FILL);
    }

    public void update() {
        yVel += GRAVITY;
        yVel = Math.min(yVel, MAX_VEL_Y);
        y += yVel;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + playerWidth, y + playerHeight, playerPaint);
    }

    public void jump() {
        yVel += JUMP_VEL;
    }
}
