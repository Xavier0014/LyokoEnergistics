package xavier0014.lyokoenergistics.items.record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xavier0014.lyokoenergistics.LyokoEnergistics;
import xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecordLE extends ItemRecord{
	
	private static final Map map = new HashMap();
	private String name;
	private String unlocalizedname;
	private String description = "";
	

	public RecordLE(String name) {
		super(name);
		this.name = name;
		setUnlocalizedName(Reference.MOD_id +":"+name);
		 map.put("records." + name, this);
		 unlocalizedname = this.getUnlocalizedName();
	}
	
	public RecordLE(String name,String description) {
		super(name);
		this.name = name;
		this.setUnlocalizedName(Reference.MOD_id +":"+name);
		map.put("records." + name, this);
		unlocalizedname = this.getUnlocalizedName();
		this.description = description;	 
	}
	
	 public ResourceLocation getRecordResource(String name){
	        return new ResourceLocation(Reference.MOD_id+":records."+this.name);
	    }
	
	@Override
	 @SideOnly(Side.CLIENT)
	 public void registerIcons(IIconRegister iconRegister){
	     itemIcon = iconRegister.registerIcon(this.unlocalizedname.substring(this.unlocalizedname.indexOf(".") + 1));
	 }
	
	 @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_){
	        list.add(this.description);
	    }

}
