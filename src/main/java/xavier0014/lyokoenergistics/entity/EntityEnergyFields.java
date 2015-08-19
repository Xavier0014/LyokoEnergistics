package xavier0014.lyokoenergistics.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnergyFields extends EntityThrowable{
	
	private int domage;
	public EntityEnergyFields(World world) {
		super(world);
	}
	
	public EntityEnergyFields(World world, EntityLivingBase entityLivingBase, int domage){
        super(world, entityLivingBase);
        this.domage = domage;
    }

	@Override
	protected void onImpact(MovingObjectPosition movingObjectPosition) {
		if (movingObjectPosition.entityHit != null){
			movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), domage);
		}
		this.setDead();
	}
}
