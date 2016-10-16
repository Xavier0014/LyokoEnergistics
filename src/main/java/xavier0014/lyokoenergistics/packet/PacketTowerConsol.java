package xavier0014.lyokoenergistics.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import xavier0014.lyokoenergistics.container.ContainerMaterializationScanner;
import xavier0014.lyokoenergistics.container.ContainerSuperComputer;
import xavier0014.lyokoenergistics.container.ContainerTowerConsol;
import xavier0014.lyokoenergistics.tileentity.TileMaterializationScanner;
import xavier0014.lyokoenergistics.tileentity.TileTowerConsol;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketTowerConsol implements IMessage{
	
	private int towerid;
	
	public PacketTowerConsol() {
		
	}
	
	public PacketTowerConsol(int towerid) {
		this.towerid = towerid;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.towerid =buf.readInt();
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(towerid);
		
	}
	
	public static class Handler implements IMessageHandler<PacketTowerConsol, IMessage>{
		
		@Override
		public IMessage onMessage(PacketTowerConsol message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;
			Container c = player.openContainer;
			if (c instanceof ContainerSuperComputer) {
				TileTowerConsol tile = ((ContainerTowerConsol)c).getTileTowerConsol();
				tile.towerid = (message.towerid);
				player.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
			}
			return null;
		}
		
	}
}
