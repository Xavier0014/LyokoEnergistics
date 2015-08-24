package xavier0014.lyokoenergistics.items;

import xavier0014.lyokoenergistics.entity.EntityLaserArrows;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LaserArrows extends ItemLE{
	
	private int arrows = 20;
	
	public LaserArrows(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	 @Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}
	
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		
		 if (arrows > 0) {
			 arrows--;
			 world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	          world.spawnEntityInWorld(new EntityLaserArrows(world, player));
		}
	     
	      return itemStack;	    
	    }
	 
	 private int i;
	 public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		 if (arrows == 0) {
			i++;
			if (i == 100) {
				arrows = 20;
				i = 0;
			}
		}
	 }

}
