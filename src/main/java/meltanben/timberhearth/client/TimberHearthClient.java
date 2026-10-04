package meltanben.timberhearth.client;

import meltanben.timberhearth.client.models.HearthianModel;
import meltanben.timberhearth.client.renderers.HearthianRenderer;
import meltanben.timberhearth.entitys.ModEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//import net.minecraft.client.renderer.entity.EntityRenderers;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class TimberHearthClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HearthianModel.LAYER_LOCATION, HearthianModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntityType.MARSHMALLOW, ThrownItemRenderer::new);
        EntityRendererRegistry.register(ModEntityType.HEARTHIAN, HearthianRenderer::new);
    }
}