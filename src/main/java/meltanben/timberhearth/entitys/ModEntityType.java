package meltanben.timberhearth.entitys;

import meltanben.timberhearth.TimberHearth;
import meltanben.timberhearth.items.MarshmallowItem;
//import meltanben.timberhearth.items.ThrownMarshmallow;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.projectile.ThrownEgg;

public class ModEntityType {
    public static final EntityType<ThrownMarshmallow> MARSHMALLOW = register(
            "marshmallow", EntityType.Builder.<ThrownMarshmallow>of(ThrownMarshmallow::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
    );
    public static final EntityType<Hearthian> HEARTHIAN = register(
            "hearthian",
            EntityType.Builder.<Hearthian>of(Hearthian::new, MobCategory.AXOLOTLS)
                    .sized(1f, 0.75f).clientTrackingRange(10)
    );

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, name);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, id, builder.build(name));
    }
    public static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(HEARTHIAN, Hearthian.createCubeAttributes());
    }
    public static void initialize() {}
}
