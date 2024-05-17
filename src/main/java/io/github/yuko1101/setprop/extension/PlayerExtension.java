package io.github.yuko1101.setprop.extension;

import io.github.yuko1101.setprop.prop.Prop;
import io.github.yuko1101.setprop.prop.PropMap;

import javax.annotation.Nullable;

public interface PlayerExtension {
    PropMap setPropMod$getProps();
    @Nullable Prop<?> setPropMod$setProp(String name, int value);
    @Nullable Prop<?> setPropMod$setProp(String name, boolean value);
}
