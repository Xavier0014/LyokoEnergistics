package xavier0014.lyokoenergistics.items;

import com.google.common.collect.Multimap;
import xavier0014.lyokoenergistics.LyokoEnergistics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class DigitalSabre extends ItemLE{
	
	public float domage;
	private int isuse = 0;
	private IIcon itemIconuse;
	public final ItemSword Sword;

	public DigitalSabre(String name) {
		super(name);
		Sword = (ItemSword) new ItemSword(ToolMaterial.EMERALD);
		this.domage = 10F;
		this.setMaxStackSize(1);
	}
	
	 @SideOnly(Side.CLIENT)
	 public boolean isFull3D(){
	        return true;
	 }
	 
	 public Multimap getItemAttributeModifiers(){
	        Multimap multimap = super.getItemAttributeModifiers();
	        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.domage, 0));
	        return multimap;
	 }
	 
	 public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_){
			 isuse = 60;
	        stack.damageItem(1, p_77644_3_);
	        return true;
	 }
	 
	 @Override
	 @SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister iconRegister){
			 itemIcon = iconRegister.registerIcon(this.unlocalizedname.substring(this.unlocalizedname.indexOf(".") + 1));
			 itemIconuse = iconRegister.registerIcon(String.format("%s1",this.unlocalizedname.substring(this.unlocalizedname.indexOf(".") + 1)));
	 }
	 
	 public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_){
		 return true;
	    }
	 
	 @Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}
	 
	 @Override
	 public IIcon getIcon(ItemStack stack, int pass) {
		 
		 if (isuse > 0) {
			 return this.itemIconuse;
		}else {
			 return this.itemIcon;
		}
	 }
	 
	 public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
		 if (entity instanceof EntityLivingBase) {
			 ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(1, 60, 1, false));
		}
		 if (isuse >= 0) {
			 isuse--;
			 
		}
	 }
	 
	 public ItemStack onItemRightClick(ItemStack stack, World p_77659_2_, EntityPlayer entityplayer){
		 isuse = 30;
	        return stack;
	    }
}
