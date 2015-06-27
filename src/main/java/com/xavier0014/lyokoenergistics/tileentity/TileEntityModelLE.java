package com.xavier0014.lyokoenergistics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityModelLE extends TileEntityLE{
	
	protected byte direction;
	
	public void setDirection(byte direction){
        this.direction = direction;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
    }
	
	 public byte getDirection(){
	        return direction;
	    }
	 
	  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt){
	        this.readFromNBT(pkt.func_148857_g());
	        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
	    }
	  
	   @Override
	    public void readFromNBT(NBTTagCompound compound){
	        super.readFromNBT(compound);
	        this.direction = compound.getByte("Direction");
	    }

	    @Override
	    public void writeToNBT(NBTTagCompound compound){
	        super.writeToNBT(compound);
	        compound.setByte("Direction", this.direction);
	    }

	    
	    public Packet getDescriptionPacket(){
	        NBTTagCompound nbttagcompound = new NBTTagCompound();
	        this.writeToNBT(nbttagcompound);
	        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
	    }

}
