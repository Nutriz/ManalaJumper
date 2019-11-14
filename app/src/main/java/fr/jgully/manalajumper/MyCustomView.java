package fr.jgully.manalajumper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyCustomView extends View {

    // paint object shared across all platforms instances
    private static final Paint platformPainter = new Paint(Paint.ANTI_ALIAS_FLAG);

    private PlayerObject player;

    private static final long REFRESH_RATE = 20;
    private boolean isHardMode;

    private List<PlatformObject> platforms = new ArrayList<>();
    private int screenWidth;
    private int screenHeight;

    private float step = 0.1f;

    public MyCustomView(Context context) {
        super(context);
        init();
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        platformPainter.setColor(Color.BLACK);
        player = new PlayerObject();

        screenWidth = Utils.getScreenSize(getContext()).x;
        screenHeight = Utils.getScreenSize(getContext()).y;

        generatePlatforms();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        player.update();
                        checkCollisions();
                        postInvalidate();
                        Thread.sleep(REFRESH_RATE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                player.jump();
            }
        });
    }

    private void checkCollisions() {
        for (PlatformObject platform : platforms) {
            boolean collision = player.isCollision(platform);
            if (collision) {
                player.jump();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        step += 2f;
        canvas.save();
        canvas.translate(0, screenHeight + step);
        drawPlatforms(canvas);
        player.draw(canvas);
        canvas.restore();
    }

    private void drawPlatforms(Canvas canvas) {
        for (PlatformObject platform : platforms) {
            platform.draw(canvas, platformPainter);
        }
    }

    private void generatePlatforms() {
        int yStep = 150;
        if (isHardMode)
            yStep *= 2;

        for (int i = 0; i < 1000; i++) {
            float nextPosX = Utils.getRandomInRange(0, screenWidth-160);
            float nextPosY = yStep*i + Utils.getRandomInRange(0, 200);
            PlatformObject platformObject = new PlatformObject(nextPosX, -nextPosY);
            platforms.add(platformObject);
        }
    }

    public void setHardMode(boolean isHardMode) {
        this.isHardMode = isHardMode;
    }
}
