package splash.model;

public class LayerGroup extends Layer {

    private Layer[] sublayers;

    public Layer[] getSublayers() {
        return this.sublayers;
    }

    /**
     *
     * @param sublayers
     */
    public void setSublayers(Layer[] sublayers) {
        this.sublayers = sublayers;
    }

    /**
     *
     * @param val
     */
    public void addLayer(Layer val) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param id
     * @return
     */
    public Layer remove(int id) {
        throw new UnsupportedOperationException();
    }

    public Layer[] disband() {
        throw new UnsupportedOperationException();
    }

}
