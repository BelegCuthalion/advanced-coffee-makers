package model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private List<Feature> allFeatures = new LinkedList<>();
    private AbstractParent root = null;

    // only works if no feature appears more that once in the same side of the equality
    public void addFeature(String left, FeatureType type, String[] right) {
        List<Feature> children = new LinkedList<>();
        Feature toBeParent = new Feature(left);
        AbstractParent parent;

        int parentIdx = this.allFeatures.indexOf(toBeParent);
        if (parentIdx < 0) {
             parent = toBeParent.toParent(type, children);
             this.allFeatures.add(parent);
        } else {
            parent = this.allFeatures.remove(parentIdx).toParent(type, children);
            this.allFeatures.add(parent);
        }

        if (right != null) // for a possible singleton model
            for (String childName : right) {
                boolean mandatory = !childName.contains("?");
                childName = childName.replace("?", "");
                Feature toBeChild = new Feature(childName, parent, mandatory);
                int childIdx = this.allFeatures.indexOf(toBeChild);
                if (childIdx < 0) {
                    this.allFeatures.add(toBeChild);
                    children.add(toBeChild);
                } else {
                    Feature child = this.allFeatures.get(childIdx);
                    child.setParent(parent);
                    child.setMandatory(mandatory);
                    children.add(child);
                }
            }

        if (this.root == null) // this is the first time
            this.root = parent;
    }

    public boolean validate(List<String> test) {
        boolean result = test.contains(this.root.name);

        for (String testName : test) {
            Feature dummy = new Feature(testName);
            result &= this.allFeatures.contains(dummy);
        }

        if (this.allFeatures != null) {
            for (Feature feature : this.allFeatures)
                if (test.contains(feature.name))
                    result &= feature.validate(test);
        } else
            return false;

        return result;
    }
}
