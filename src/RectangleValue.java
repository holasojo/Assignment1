/**
 * This is Value for <Key, Value> RectangleValue stores position of X, Y, width
 * and height.
 * 
 * @author sohyun
 * @author sshumway
 * @version 01/30/2016
 *
 */
public class RectangleValue {
    private int posX;
    private int posY;
    private int width;
    private int height;

    /**
     * Constructor
     * 
     * @param posX
     *            is the x-position of rectangle
     * @param posY
     *            is the y-position of rectangle
     * @param width
     *            is the width of rectangle
     * @param height
     *            is the height of rectangle
     */
    public RectangleValue(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    /**
     * getter for posX
     * 
     * @return x position of rectangle
     */
    public int getPosX() {
        return posX;

    }

    /**
     * getter for posY
     * 
     * @return y position of rectangle
     */
    public int getPosY() {
        return posY;

    }

    /**
     * getter for width
     * 
     * @return width of rectangle
     */
    public int getWidth() {
        return width;

    }

    /**
     * getter for height
     * 
     * @return height of rectangle
     */
    public int getHeight() {
        return height;

    }

    /**
     * Prints out string represenation of a RectangleValue object.
     * 
     * @return string value
     */
    public String toString() {
        return posX + ", " + posY + ", " + width + ", " + height;
    }

    /**
     * Compares this with rec for equality.
     * 
     * @param obj
     *            Rectangle compared to this
     * 
     * @return true if equal, otherwise false
     */
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == RectangleValue.class) {
            RectangleValue rec = (RectangleValue) obj;
            return this.posX == ((RectangleValue) rec).getPosX()
                    && this.posY == rec.getPosY()
                    && this.width == rec.getWidth()
                    && this.height == rec.getHeight();
        }
        return false;
    }

    /**
     * compares two rectangles and see if they intersect
     * 
     * @param rec
     *            the one gets compared to.
     * @return true if they intersect
     */
    public boolean intersect(RectangleValue rec) {

        return !(this.posX + this.width <= rec.posX
                || rec.posX + rec.width <= this.posX
                || this.posY + this.height <= rec.posY || rec.posY
                + rec.height <= this.posY);
    }
}
