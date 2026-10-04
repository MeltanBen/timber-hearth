package meltanben.timberhearth.entitys;

import meltanben.timberhearth.items.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ThrownMarshmallow extends ThrowableItemProjectile {
    private static final EntityDimensions ZERO_SIZED_DIMENSIONS = EntityDimensions.fixed(0.0F, 0.0F);

    public ThrownMarshmallow(EntityType<? extends ThrownMarshmallow> entityType, Level level) {
        super(entityType, level);
    }

    public ThrownMarshmallow(Level level, LivingEntity livingEntity) {
        super(ModEntityType.MARSHMALLOW, livingEntity, level);
    }

    public ThrownMarshmallow(Level level, double d, double e, double f) {
        super(ModEntityType.MARSHMALLOW, d, e, f, level);
    }


    @Override
    public void handleEntityEvent(byte b) {
        if (b == 3) {
            double d = 0.08;

            for (int i = 0; i < 8; i++) {
                this.level()
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, this.getItem()),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                (this.random.nextFloat() - 0.5) * 0.08,
                                (this.random.nextFloat() - 0.5) * 0.08,
                                (this.random.nextFloat() - 0.5) * 0.08
                        );
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        entityHitResult.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
        Level level= level();
        if (!this.level().isClientSide) {
            ItemEntity drop = new ItemEntity(
                    level,
                    this.getX(), this.getY(), this.getZ(),
                    new ItemStack(this.getDefaultItem())
            );
            level.addFreshEntity(drop);
        }

        this.level().broadcastEntityEvent(this, (byte)3);
        this.discard();

    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.MARSHMALLOW;
    }

}
