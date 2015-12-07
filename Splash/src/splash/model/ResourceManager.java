package splash.model;

public class ResourceManager {

    private Tool[] tools;
    private Brush[] brushes;

    public Tool[] getTools() {
        return this.tools;
    }

    /**
     *
     * @param tools
     */
    public void setTools(int tools) {
        throw new UnsupportedOperationException();
    }

    public void getBrushes() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param brushes
     */
    public void setBrushes(int brushes) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void loadTools(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void loadBrushes(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void saveProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void loadProject(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public void exportImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param path
     */
    public BitmapDrawable loadImage(String path) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param Layer
     * @param path
     */
    public void exportAnimation(int[] Layer, String path) {
        throw new UnsupportedOperationException();
    }

    private ResourceManager() {
        throw new UnsupportedOperationException();
    }

}
