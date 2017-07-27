package game.model;

/**
 * @author YanhanLyu, Lucy Wu
 * Interface for the characters of Plant
 */
public interface Plant{

    /**
     * set the position of the plant
     */
    public void setPosition(int x, int y);
    /**
     * return the position of the plant
     */
    /**
     * return the health of the plant
     */
    public int getHealth();
    /**
     * set the health of the plant
     */
    public void setHealth(int health);
    /**
     * return the power of the plant
     */
    public int getPower();
    /**
     * set the power of the plant
     */
    public void setPower(int power);
    /**
     * get the price of the plant
     */
    public int getPrice();

    /**
     * set the movement of the plant
     */
    public void step();

    /**
     * remove the image of the plant
     */
    public void removeImage();

    /**
     * get the row of the plant
     * @return row
     */
    public int getRow();

    /**
     * get the column of the plant
     * @return row
     */
    public int getColumn();

    /**
     * get the name of the plant
     * @return row
     */
    public String getName();


}
