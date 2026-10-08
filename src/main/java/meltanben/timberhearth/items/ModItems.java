package meltanben.timberhearth.items;

import meltanben.timberhearth.TimberHearth;
import meltanben.timberhearth.blocks.ModBlocks;
import meltanben.timberhearth.entitys.ModEntityType;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluids;

import java.util.function.Function;

import static net.minecraft.world.item.Items.registerItem;

public class ModItems {

    public static final Item MARSHMALLOW = register(//棉花糖
            new Item(new Item.Properties().food(ModFoods.MARSHMALLOW)),
            "marshmallow"
    );
    public static final Item MARSHMALLOW_SHOOTER = register(//棉花糖发射器？？
            new MarshmallowItem(new Item.Properties()),
            "marshmallow_shooter"
    );
    public static final Item HEARTHIAN_SPAWN_EGG = register(//哈斯刷怪蛋
            new SpawnEggItem(ModEntityType.HEARTHIAN, 11590137, 9021404, new Item.Properties()),"hearthian_spawn_egg"
    );
    public static final Item HEARTHIAN_BUCKET = register(
            new MobBucketItem(
                    ModEntityType.HEARTHIAN,
                    Fluids.WATER,
                    SoundEvents.BUCKET_EMPTY_AXOLOTL,
                    new Item.Properties().stacksTo(1).component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)
            ),"hearthian_bucket"
    );

    //加入模组物品标签
    public static final ResourceKey<CreativeModeTab> CUSTOM_ITEM_GROUP_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, "item_group"));
    public static final CreativeModeTab CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.MARSHMALLOW))
            .title(Component.translatable("item_group.timber_hearth"))
            .build();


    public static Item register(Item item, String id) {
        // Create the identifier for the item.
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, id);

        // Register the item.
        Item registeredItem = Registry.register(BuiltInRegistries.ITEM, itemID, item);

        // Return the registered item!
        return registeredItem;
    }



    public static void initialize() {

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.accept(ModItems.MARSHMALLOW);
            itemGroup.accept(ModItems.MARSHMALLOW_SHOOTER);
            itemGroup.accept(ModItems.HEARTHIAN_SPAWN_EGG);
            itemGroup.accept(ModBlocks.MARSHMALLOW_JAR);
            itemGroup.accept(ModItems.HEARTHIAN_BUCKET);
        });

    }
}
