package meltanben.timberhearth;

import meltanben.timberhearth.blocks.ModBlocks;
import meltanben.timberhearth.entitys.ModEntityType;
import meltanben.timberhearth.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimberHearth implements ModInitializer {
	public static final String MOD_ID = "timber_hearth";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItems.initialize();
		ModBlocks.initialize();
		ModEntityType.initialize();
		ModEntityType.registerAttributes();

		BiomeModifications.addSpawn(//哈斯鱼生成
				context -> context.getBiomeKey().equals(Biomes.LUSH_CAVES),
				MobCategory.AXOLOTLS,
				ModEntityType.HEARTHIAN,
				20,
				1,
				4
		);

		SpawnPlacements.register(
				ModEntityType.HEARTHIAN,
				SpawnPlacementTypes.NO_RESTRICTIONS,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(type, world, reason, pos, random) ->
						true
		);

		LOGGER.info("Hello Fabric world!");
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
