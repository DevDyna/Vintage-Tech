package com.synergy.vintagetech.api.factories.trees;

public class TreeOptions {

    public enum WoodType {
        TREE(0, "_log", "_wood"),
        FUNGUS(0, "_stem", "_hyphae"),
        OTHER(0, "", "");

        private int id;
        private String suffix4;
        private String suffix6;

        public static final String STRIPPED = "stripped_";
        //TODO maybe change location
        public static final String BEAM = "_beam";

        WoodType(int id, String suffix4, String suffix6) {
            this.id = id;
            this.suffix4 = suffix4;
            this.suffix6 = suffix6;
        }

        public String suffix4() {
            return suffix4;
        }

        public String suffix6() {
            return suffix6;
        }

        public int id() {
            return id;
        }

        public boolean isTree() {
            return this.equals(WoodType.TREE);
        }

        public boolean isFungus() {
            return this.equals(WoodType.FUNGUS);
        }

        public boolean isSpecial() {
            return this.equals(WoodType.OTHER);
        }

    }

    public enum TreeParticleLeaves {
        DEFAULT(0.01f),
        PALE(0.02f);

        private float v;

        TreeParticleLeaves(float v) {
            this.v = v;
        }

        public float get() {
            return v;
        }

    }

}
