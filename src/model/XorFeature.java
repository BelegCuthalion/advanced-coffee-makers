package model;

import java.util.List;

public class XorFeature extends AbstractParent {
    XorFeature(String name, AbstractParent parent, boolean isMandatory, List<Feature> children) {
        super(name, parent, isMandatory, children);
    }

    public boolean validate(List<String> test) {
        boolean oneChildSeen = false;
        for (Feature child : this.children)
            if (test.contains(child.name))
                if (oneChildSeen)
                    return false;
                else
                    oneChildSeen = true;
        return oneChildSeen && super.validate(test);
    }
}
