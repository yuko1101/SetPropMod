package io.github.yuko1101.setprop.extension;

import io.github.yuko1101.setprop.prop.DefinedProp;
import io.github.yuko1101.setprop.prop.Prop;

import javax.annotation.Nullable;
import java.util.List;

public interface PlayerExtension {
    <T> void setPropMod$putProp(Prop<T> prop, List<String> aliases);
    @Nullable Prop<?> setPropMod$getProp(String alias);
    Prop<?> setPropMod$getProp(DefinedProp definedProp);
    boolean setPropMod$hasProp(String alias);

    boolean setPropMod$getBoolean(DefinedProp definedProp);
    void setPropmod$setBoolean(DefinedProp definedProp, boolean value);
}
