package xavier0014.lyokoenergistics.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDescriptionLE extends ItemLE{
	
	private String description = "";

	public ItemDescriptionLE(String name,String description) {
		super(name);
		this.description = description;
	}
	
	 @SideOnly(Side.CLIENT)
	 public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_){
		 list.add(this.description);
	 }
}
