package splash.model;

class Anchor {

        Object asocObj = null;
        Point location = new Point(0, 0);

        public Anchor() {
        }

        public Anchor(Object obj, Point location) {
            this(location);
            asocObj = obj;
        }

        public Anchor(Point location) {
            this.location = location;
        }

        public Object getAsocObj() {
            return asocObj;
        }

        public void setAsocObj(Object asocobj) {
            this.asocObj = asocobj;
        }
    }
