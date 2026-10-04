package meltanben.timberhearth.client.models;

import meltanben.timberhearth.entitys.Hearthian;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class HearthianModel<T extends Hearthian> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("timber_hearth", "hearthian"), "main");

    private final ModelPart hearthian;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart left_arm;
    private final ModelPart left_leg;
    private final ModelPart right_arm;
    private final ModelPart right_leg;

    public HearthianModel(ModelPart root) {
        this.hearthian = root.getChild("hearthian");
        this.head = this.hearthian.getChild("head");
        this.body = this.hearthian.getChild("body");
        this.tail = this.hearthian.getChild("tail");
        this.left_arm = this.hearthian.getChild("left_arm");
        this.left_leg = this.hearthian.getChild("left_leg");
        this.right_arm = this.hearthian.getChild("right_arm");
        this.right_leg = this.hearthian.getChild("right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hearthian = partdefinition.addOrReplaceChild("hearthian", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        hearthian.addOrReplaceChild("head", CubeListBuilder.create().texOffs(31, 0).addBox(-5.0F, -5.0F, -4.0F, 10.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -5.0F));

        hearthian.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        hearthian.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(12, 18).addBox(0.0F, -1.0F, 3.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(6, 18).addBox(0.0F, 0.0F, 5.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 6.0F));

        hearthian.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 18).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, -2.0F, -4.5F));

        hearthian.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 18).addBox(-0.6F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.6F, -2.0F, 2.5F));

        hearthian.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 18).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -2.0F, -4.5F));

        hearthian.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 18).addBox(-0.6F, 0.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.4F, -2.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        hearthian.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.yRot = netHeadYaw * ((float) Math.PI / 360F);
        this.head.xRot = headPitch * ((float) Math.PI / 360F);

        this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        this.tail.yRot = Mth.cos(ageInTicks * 0.1F) * 0.2F;

    }
}
