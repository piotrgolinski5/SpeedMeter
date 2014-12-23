package pl.golinski.piotr.speedmeter.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class PGTextView extends TextView {

    private static Typeface mTypeface;

    public PGTextView(final Context context) {
        this(context, null);
    }

    public PGTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PGTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(), "RadioLand.ttf");
        }
        setTypeface(mTypeface);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.translate(getWidth(), 0);
//        canvas.scale(-1, 1);
        super.onDraw(canvas);
    }

}