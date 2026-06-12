package com.synergy.vintagetech.api.aspects;
//TODO move to api
public interface EnvironmentModifier {
    abstract float getSpeedModifier();

    abstract boolean isRequired();

    default String failDescKey() {
        return "";//TODO when defaul fall on errored jade
    }
}