package xavier0014.lyokoenergistics.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import xavier0014.lyokoenergistics.entity.EntityEnergyFields;
import xavier0014.lyokoenergistics.entity.EntityLaserArrows;

public class EnergyFields extends ItemLE{

	public EnergyFields(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	 @Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}
	
	 private int i = 0;
	 private int j;
	 public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player){
		
		 i++;
	      	return itemStack;	   
	      }
	 private int k;
	 public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		 if (!world.isRemote) {
			 k++;
			 if (k == 4) {
				 k=0;
				 if (j != i) {
					 j = i;
				}else {
					if (i != 0){
				        world.playSoundAtEntity(entity, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
				        world.spawnEntityInWorld(new EntityEnergyFields(world, (EntityPlayer)entity, i));
				        i = 0;
				        j = 0;
				      }
				}
			}
		}
	 }
}
