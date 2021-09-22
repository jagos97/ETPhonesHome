package etphoneshome.managers;

import etphoneshome.entities.enemies.Scientist;
import etphoneshome.objects.Flask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is responsible for handling the flasks thrown by the enemies.
 * Using addFlask will add the flask to the arrraylist
 * Using removeFlask will remove the flask from the arraylist
 * Using clearFlasks will remove all the flasks in the arraylist
 */
public class FlaskManager {

    /**
     * Arraylist that keeps all the flasks
     */
    private final HashMap<Scientist, Flask> flaskMap = new HashMap<Scientist, Flask>();
    private final List<Flask> flasks = new ArrayList<Flask>();

    /**
     * add a flask to the {@code FlaskManager}
     *
     * @param scientist The scientist that is throwing the flask
     * @param flask flask to add to the {@code FlaskManager}
     */
    public void addFlask(Scientist scientist, Flask flask) {
        this.flaskMap.put(scientist, flask);
        this.flasks.add(flask);
        scientist.setThrownFlask(true);
    }

    /**
     * remove a flask from the {@code FlaskManager}
     *
     * @param flask flask to remove from {@code FlaskManager}
     */
    public void removeFlask(Flask flask) {
        for (Scientist scientist : this.flaskMap.keySet()) {
            if (this.flaskMap.get(scientist) == flask) {
                this.flaskMap.remove(scientist);
                scientist.setThrownFlask(false);
                break;
            }
        }
        this.flasks.remove(flask);
    }

    /**
     * Clear all flasks from the {@code FlaskManager}
     */
    public void clearFlasks() {
        this.flaskMap.clear();
        this.flasks.clear();
    }

    /**
     * returns the arraylist of {@code FlaskManager}
     *
     * @return the {@code flasks} array list of {@code FlaskManager}
     */
    public List<Flask> getFlaskList() {
        return this.flasks;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
