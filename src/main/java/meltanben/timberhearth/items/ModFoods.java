package meltanben.timberhearth.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties MARSHMALLOW = new FoodProperties.Builder()
            .alwaysEdible()
            .fast()
            .nutrition(2)
            .saturationModifier(0.3f)
            .build();

}
