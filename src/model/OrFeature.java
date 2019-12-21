package model;

import java.util.List;

public class OrFeature extends AbstractParent {
    OrFeature(String name, AbstractParent parent, boolean isMandatory, List<Feature> children) {
        super(name, parent, isMandatory, children);
    }

    public boolean validate(List<String> test) {
        for(Feature child : this.children)
            if (test.contains(child.name))
                return super.validate(test);
        return false;
    }
}
