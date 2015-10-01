package xavier0014.lyokoenergistics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLE extends TileEntity{

	public int corelv;
	public int speedlv = 0;
	public int storagelv = 0;
	public int Efficiencylv = 0;
	public String playerName = "";
	
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setString("playerName",this.playerName); 
		compound.setShort("corelv",(short)this.corelv); 
		compound.setShort("speedlv",(short)this.speedlv);
		compound.setShort("storagelv",(short)this.storagelv);
		compound.setShort("Efficiencylv",(short)this.Efficiencylv);
	}
	
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.playerName = compound.getString("playerName");
		this.corelv = compound.getShort("corelv");
		this.speedlv = compound.getShort("speedlv");
		this.storagelv = compound.getShort("storagelv");
		this.Efficiencylv = compound.getShort("Efficiencylv");
	}
	
    public void setPlayerName(String playerName){
    	this.playerName = playerName;
    }
    
}
