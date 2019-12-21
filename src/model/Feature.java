package model;

import java.util.List;

public class Feature {
    String name;
    private AbstractParent parent;
    boolean isMandatory;

    public boolean validate(List<String> test) {
        return !test.contains(this.name) || (this.parent == null || test.contains(this.parent.name));
    }

    Feature(String name) {
        this.name = name;
    }

    Feature(String name, AbstractParent parent, boolean isMandatory) {
        this.name = name;
        this.parent = parent;
        this.isMandatory = isMandatory;
    }

    void setParent(AbstractParent parent) {
        this.parent = parent;
    }

    void setMandatory(boolean mandatory) {
        this.isMandatory = mandatory;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Feature) &&
                this.name.equals(((Feature) obj).name);
    }

    AbstractParent toParent(FeatureType type, List<Feature> children) {
        AbstractParent grownMe = null;
        switch (type) {
            case SIMPLE:
                grownMe = new SimpleFeature(this.name, this.parent, this.isMandatory, children);
                break;
            case OR:
                grownMe =  new OrFeature(this.name, this.parent, this.isMandatory, children);
                break;
            case XOR:
                grownMe = new XorFeature(this.name, this.parent, this.isMandatory, children);
                break;
        }
        if (this.parent != null) { // this might be root
            this.parent.children.remove(this);
            this.parent.children.add(grownMe);
        }
        return grownMe;
    }
}
