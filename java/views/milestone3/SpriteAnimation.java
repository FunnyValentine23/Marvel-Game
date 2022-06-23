package views.milestone3;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private int count;
    private int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY, int width, int height){
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        setCycleDuration(duration);

        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setOffsetX(int x){
        this.offsetX = x;
    }
    public void setOffsetY(int y){
        this.offsetY = y;
    }
    public void setCount(int c){
        this.count = c;
    }
    public void setColumn(int c){
        this.count = c;
    }
    public void setWidth(int w){
        this.width = w;
    }
    public void setHeight(int h){
        this.height = h;
    }

    protected void interpolate(double frac){
        final int index = Math.min((int)Math.floor(count*frac), count-1);

        final int x = (index%columns)*width+offsetX;
        final int y = (index/columns)*height+offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));

        }
}
