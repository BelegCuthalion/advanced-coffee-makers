package model;

import java.util.List;

abstract class AbstractParent extends Feature {
    List<Feature> children;

    AbstractParent(String name, AbstractParent parent, boolean isMandatory, List<Feature> children) {
        super(name, parent, isMandatory);
        this.children = children;
    }
}
