package xavier0014.lyokoenergistics.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import xavier0014.lyokoenergistics.tileentity.TileSuperComputerControler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketSuperComputer implements IMessage{
	private boolean onoff;
	
	public PacketSuperComputer() {
		
	}
	
	public PacketSuperComputer(boolean onoff) {
		this.onoff = onoff;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.onoff =buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(onoff);
	}
	
	public static class Handler implements IMessageHandler<PacketSuperComputer, IMessage>{
		
		@Override
		public IMessage onMessage(PacketSuperComputer message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			Container c = player.openContainer;
			if (c instanceof ContainerSuperComputer) {
				TileSuperComputerControler tile = ((ContainerSuperComputer)c).getTileSuperComputer();
				tile.onoff = (message.onoff);
				player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
			}
				
			return null;
		}
		
	}

}
