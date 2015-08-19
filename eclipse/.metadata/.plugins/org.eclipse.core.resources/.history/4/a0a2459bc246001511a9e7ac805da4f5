package com.xavier0014.lyokoenergistics.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityFan  extends EntityThrowable{

	public EntityFan(World world) {
		super(world);
	}
	
	public EntityFan(World world, EntityLivingBase entityLivingBase){
        super(world, entityLivingBase);
    }

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition) {
		if (movingObjectPosition.entityHit != null){
			movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10);
		}
		this.setDead();
	}
}
