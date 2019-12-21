package model;

import java.util.List;

public class SimpleFeature extends AbstractParent {
    SimpleFeature(String name, AbstractParent parent, boolean isMandatory, List<Feature> children) {
        super(name, parent, isMandatory, children);
    }

    public boolean validate(List<String> test) {
        if (this.children != null)
            for(Feature child : this.children)
                if (child.isMandatory && !test.contains(child.name))
                    return false;
        return super.validate(test);
    }
}
