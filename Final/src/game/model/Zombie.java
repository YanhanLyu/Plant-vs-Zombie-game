package game.model;

/**
 * Created by yanhanlyu on 28/05/2017.
 * Interface that lists the general characters and behaviors of a Zombie
 */
public interface Zombie {
    /**
     * set the image position of the zombie. We need this since the zombie
     * is not moving based on column, but their real position
     * @param x the x position
     * @param y the y position
     */
    public void setImagePosition(double x, double y);

    /**
     * get the real position x of zombie, not its column
     * @return the real x position of the zombie
     */
    public double getImagePositionX();

    /**
     * get the row of the zombie
     *@return the row of the zombie
     */
    public int getRow();

    /**
     * get the column of the zombie
     * @return the column of the zombie
     */
    public int getColumn();

    /**
     * get the health of the zombie
     * @return the health of the zombie
     */
    public int getHealth();

    /**
     * set the health value of the zombie
     * @param health the health value of the zombie
     */
    public void setHealth(int health);
    /**
     * get the power value of the zombie
     * @return the power value of the zombie
     */
    public int getPower();

    /**
     * get the initial speed value of the zombie. We need this to set the speed of zombie back to
     * the initial speed if it stops and does other weird movements.
     * @return the the initial speed value of the zombie
     */
    public double getISpeed();

    /**
     * set the speed value of the zombie
     * @param speed the health value of the zombie
     */
    public void setSpeed(double speed);

    /**
     * set the column value of the zombie
     * @param column the health value of the zombie
     */
    public void setColumn (int column);

    /**
     * set the movement of the zombie
     */
    public void step();

    /**
     * remove the image
     */
    public void removeImage();




}
