package com.xavier0014.lyokoenergistics.blocks;

import com.xavier0014.lyokoenergistics.LyokoEnergistics;
import com.xavier0014.lyokoenergistics.creativetabs.LECreativeTabs;
import com.xavier0014.lyokoenergistics.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockLE extends Block{
	
	private String name;

	public BlockLE(String name) {
		super(Material.iron);
		this.name = name;
		setBlockName(Reference.MOD_id +":"+name);
		this.setCreativeTab(LyokoEnergistics.lyokotab);
		super.setHardness(4.0F);
	}
	
	@Override
	 @SideOnly(Side.CLIENT)
	 public void registerBlockIcons(IIconRegister iconRegister){
	     blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	 }
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
	     return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	 }
	
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata){
        TileEntity tileentity = world.getTileEntity(x, y, z);

        if (tileentity instanceof IInventory){
        	IInventory inv = (IInventory)tileentity;
            for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1){
                ItemStack itemstack = inv.getStackInSlot(i1);

                if (itemstack != null){
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)){
                        int j1 = world.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize){
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound()){
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
	

}
