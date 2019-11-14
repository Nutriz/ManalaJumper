package fr.jgully.manalajumper;

import android.graphics.Canvas;
import android.graphics.Paint;

public class PlatformObject extends GameObject {

    public PlatformObject(float x, float y) {
        super(x, y, 160, 30);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRect(x, y, x+width, y+height, paint);
    }
}
