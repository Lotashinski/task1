package training.ammunition.jacket;

public enum HamletType {
    FULL_FACE {
        @Override
        public String toString() {
            return "full face";
        }
    },
    FLIP_UP {
        @Override
        public String toString() {
            return "flip-up";
        }
    },
    CROSS {
        @Override
        public String toString() {
            return "cross";
        }
    }
}
