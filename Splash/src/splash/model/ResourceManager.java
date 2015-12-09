package splash.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ResourceManager {

    private static ArrayList<Tool> tools = new ArrayList<>();
    private static HashMap<String, Class<? extends Brush>> brushes = new HashMap<>();

    public static ArrayList<Tool> getTools() {
        return tools;
    }

    public static HashMap<String, Class<? extends Brush>> getBrushes() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param brushes
     */
    public static void setBrushes(int brushes) {
        throw new UnsupportedOperationException();
    }

    public static void loadDrawables(String path) {
        // Pre-installed
        DrawableFactory.Drawables.put("Rectangle", Rectangle.class);
        DrawableFactory.Drawables.put("Triangle", Triangle.class);
        DrawableFactory.Drawables.put("Ellipse", Ellipse.class);
        DrawableFactory.Drawables.put("Polygon", Polygon.class);
        DrawableFactory.Drawables.put("Line", Line.class);
        // External
        // TODO: Load external classes
    }

    /**
     *
     * @param path
     */
    public static void loadTools() {
        // Predefined tools
        tools.add(new PointerTool());
        tools.add(new MarqueTool());
        tools.add(new ResizeTool());
        // Shape based tools
        Iterator<String> it = DrawableFactory.Drawables.keySet().iterator();
        while (it.hasNext()) {
            tools.add(new DrawableTool(it.next()));
        }
        // TODO: Include brushes as tools
    }

    /**
     *
     * @param path
     */
    public static void loadBrushes(String path) {
        //TODO: load brushes
    }

    /**
     *
     * @param path
     */
    public static void saveProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public static void loadProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public static void exportImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     * @return
     */
    public BitmapDrawable loadImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Layer
     * @param path
     */
    public static void exportAnimation(int[] Layer, String path) {
        throw new UnsupportedOperationException();
    }

    private ResourceManager() {
        throw new UnsupportedOperationException();
    }

}
