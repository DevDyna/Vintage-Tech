package com.synergy.vintagetech.api.aspects;
//TODO API : move to api
public interface EnvironmentModifier {
    abstract float getSpeedModifier();

    abstract boolean isRequired();

    default String failDescKey() {
        return "";//TODO IMP : when defaul fall on errored jade
    }
}