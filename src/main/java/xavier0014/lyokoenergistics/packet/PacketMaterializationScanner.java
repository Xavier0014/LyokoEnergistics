package xavier0014.lyokoenergistics.packet;

import xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketMaterializationScanner implements IMessage{
	
	private int craft;
	
	public PacketMaterializationScanner() {
		
	}
	
	public PacketMaterializationScanner(int craft) {
		this.craft = craft;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.craft =buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(craft);
	}
	
	public static class Handler implements IMessageHandler<PacketMaterializationScanner, IMessage>{
		
		@Override
		public IMessage onMessage(PacketMaterializationScanner message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			Container c = player.openContainer;
				TileMaterializationScanner tile = ((ContainerMaterializationScanner)c).getTileMaterializationScanner();
				tile.craft = (message.craft);
				player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
			return null;
		}
		
	}

}
