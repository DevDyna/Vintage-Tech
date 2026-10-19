package com.synergy.vintagetech.api;

import com.devdyna.cakesticklib.api.primitive.Ticker;

//TODO deprecate SimpleDelayTicker , EnvironmentalModifier and TimeredRecipeProvider
public interface TickerRecipe {
    Ticker getTicker();

    default float getMultiplier() {
        return 1.0f;
    }

    default boolean hideMultiplier(){
        return false;
    }

}
