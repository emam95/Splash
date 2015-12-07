package splash.model;

import java.util.HashMap;

public class DrawableFactory {

    public static HashMap<String, Class<? extends Drawable>> Drawables = new HashMap<>();

    /**
     *
     * @param type
     * @return
     */
    public Drawable createDrawable(String type) {
        if (Drawables.containsKey(type)) {
            try {
                return Drawables.get(type).newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                System.out.println("DrawableFactory failed to construct object.");
                ex.printStackTrace();
            }
        }
        return null;
    }
}
