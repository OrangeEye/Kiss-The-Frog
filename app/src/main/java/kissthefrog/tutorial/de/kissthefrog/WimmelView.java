package kissthefrog.tutorial.de.kissthefrog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class WimmelView extends View {
    private static final int images[] = {R.drawable.frog};
    private Random rnd;
    private long randomSeed = 1;
    private Paint paint = new Paint(); //Virtueller Pinsel

    public WimmelView(Context context) {
        super(context);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rnd = new Random(randomSeed);

        for (int imageID : images) {
            //BitmapFactory läd Dateien
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageID);
            for (int i = 0; i < imageCount / images.length; i++) {
                float left = rnd.nextFloat() * (getWidth() - bitmap.getWidth()); //Abstand von links
                float top = rnd.nextFloat() * (getHeight() - bitmap.getHeight()); //Abstand von oben
                canvas.drawBitmap(bitmap, left, top, paint);

            }
            //Garbage Collector funktioniert in Android nicht immer gut, darum Grafiker selber aufräumen
            bitmap.recycle();
        }
    }

    private int imageCount;

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
        //Mit jedem neuem Bild wird ein neuer Startwert für den Zufallsgenerator gegeben
        randomSeed = System.currentTimeMillis();
        //Kennzeichnet den Sichtbaren Bereich der aktuellen View als veraltet und veranlasst ein Neuzeichnen
        invalidate();
    }
}
