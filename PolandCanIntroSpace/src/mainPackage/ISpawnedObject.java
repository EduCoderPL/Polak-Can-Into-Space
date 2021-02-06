package mainPackage;

import objectsToCollide.Rocket;

import java.awt.*;

public interface ISpawnedObject {
    /**
    @param g
     *       The graphics object where we will draw something.
     *       Just draw object.
     */
    void draw(Graphics2D g);

    /**
     * Place a animal on the map.
     *
     * @param rocket
     *            The rocket that will react to collision
     */
    void interactRocket(Rocket rocket);

    void update();
}
