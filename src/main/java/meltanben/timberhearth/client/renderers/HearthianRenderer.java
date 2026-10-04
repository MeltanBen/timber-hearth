package meltanben.timberhearth.client.renderers;

import meltanben.timberhearth.TimberHearth;
import meltanben.timberhearth.client.models.HearthianModel;
import meltanben.timberhearth.entitys.Hearthian;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class HearthianRenderer extends MobRenderer<Hearthian, HearthianModel<Hearthian>> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TimberHearth.MOD_ID, "textures/entity/hearthian.png");

    public HearthianRenderer(EntityRendererProvider.Context context) {
        super(context, new HearthianModel<>(context.bakeLayer(HearthianModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Hearthian entity) {
        return TEXTURE;
    }
}
