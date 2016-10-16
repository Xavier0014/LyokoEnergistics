package xavier0014.lyokoenergistics.tileentity;

public class TileVirtualisationScanner extends TileEntityLE {
	
	/* Rotation */
   public float rotation = 0;
   /* Scale */
   public float scale = 0;

   @Override
   public void updateEntity(){
       if (worldObj.isRemote) rotation += 0.5;
       
      if (worldObj.isRemote) scale = 0.5F;
   }
}
