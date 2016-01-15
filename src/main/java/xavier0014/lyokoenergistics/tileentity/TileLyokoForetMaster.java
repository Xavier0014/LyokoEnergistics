package xavier0014.lyokoenergistics.tileentity;

import java.util.ArrayList;
import java.util.Random;

import xavier0014.lyokoenergistics.blocks.BlockLE;
import xavier0014.lyokoenergistics.init.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

public class TileLyokoForetMaster extends TileEntityLE {
	
	private Random random = new Random();
	private int i = -32;
	private int j = -32;
	private int progress = 0;
	private int subpprogress = 0;
	private ArrayList<int[]> coordArray = new ArrayList<int[]>();
	private int towerNumb;
	
	
	public void updateEntity(){
		if (towerNumb == 10) {
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
		}
		if (!worldObj.isRemote) {
		switch (progress) {
		case 0:
			//generateDigitalSea();
			progress =1;
			break;
		case 1:
			spawnPlatforme(0, 60, 0);
			progress=2;
			break;
		case 2:
			generateLyoko();
			break;
		case 3:
			//generateTree();
			progress = 4;
			break;
		case 4:
			worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
			break;
		default:
			break;
			}
		}
	}
	
	private void generateLyoko() {
		if (coordArray.size() != subpprogress) {
			int[] coord = coordArray.get(subpprogress);
			if (coord[4] == 1) {
				//System.out.println(1);
				generateWay(coord[0],coord[1],coord[2],coord[3],random.nextInt(50));
			}else if (coord[4] == 2) {
				//System.out.println(2);
				spawnInter1(coord[0], coord[1], coord[2], coord[3]);
			}else if (coord[4] == 3) {
				//System.out.println(3);
				generateWayLv2(coord[0],coord[1],coord[2],coord[3],random.nextInt(50));
			}else if (coord[4] == 4) {
				int rand = random.nextInt(2);
				if (rand == 0) {
					if (towerNumb <= 10) {
						towerPlatforme(coord[0],coord[1],coord[2],coord[3],random.nextInt(15));
					}else {
						EndPlatforme(coord[0],coord[1],coord[2],coord[3],random.nextInt(10));
					}
					
				}else {
					if (coord[0] < 350 && coord[2] < 350) {
						spawnInter1(coord[0], coord[1], coord[2], coord[3]);
					}else {
						EndPlatforme(coord[0],coord[1],coord[2],coord[3],random.nextInt(10));
					}
					
				}
			}else {
				progress = 3;
			}
		}else {
			progress = 3;
		}
		subpprogress++;
	}
	
	private void generateTree() {
		generateChunkTree(i*16,j*16);
		if (i >= 32) {
			i = -32;
			if (j >= 32) {
				j = -32;
				progress = 4;
			}
			j++;
		}
		i++;
	}
	
	private void generateChunkTree(int x, int z) {
		for (int k = 0; k < 2; k++) {
			int xC;
			int yC;
			int zC;
			xC = random.nextInt(16);
			yC = random.nextInt(12);
			zC = random.nextInt(16);
			if (canSpawnTree(x+xC, 10+yC, z+zC)) {
				oneTree(x+xC, 10+yC, z+zC);
			}
		}
	}
	
	private boolean canSpawnTree(int x, int y, int z) {
		for (int k = x-2; k < x+2; k++) {
			for (int l = y-3; l < y; l++) {
				for (int m = z-2; m < z+2; m++) {
					if (worldObj.getBlock(k, l, m) instanceof BlockLE) {
						return false;
					}
				}
			}
		}
		for (int i = 10; i < 150; i++) {
			if (worldObj.getBlock(x, i, z) instanceof BlockLE) {
				return false;
			}
		}
		return true;
	}
	
	private void spawnPlatforme(int x,int y, int z) {
		drawDisk(17,x, y, z, ModBlock.lyokoGrass);
		drawDisk(17,x, y+1, z, ModBlock.lyokoGrass);
		drawDisk(15,x, y-1, z, ModBlock.lyokoGrass);
		drawDisk(10,x, y+1, z, Blocks.water);
		drawDisk(5,x, y+1, z, ModBlock.lyokoGrass);
		drawDisk(5,x, y+2, z, ModBlock.lyokoGrass);
		worldObj.setBlock(x+5, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+6, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+7, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+8, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+8, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+9, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+10, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x+11, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-5, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-6, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-7, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-8, y+3, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-8, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-9, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-10, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x-11, y+2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z+5, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z+6, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z+7, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z+8, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z+8, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z+9, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z+10, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z+11, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z-5, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z-6, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z-7, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+3, z-8, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z-8, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z-9, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z-10, ModBlock.lyokoTree);
		worldObj.setBlock(x, y+2, z-11, ModBlock.lyokoTree);
		coordArray.add(new int[]{x,y,z-17,1,1});
		coordArray.add(new int[]{x+18,y,z,2,1});
		coordArray.add(new int[]{x,y,z+18,3,1});
		coordArray.add(new int[]{x-17,y,z,4,1});
	}
	
	
	private void towerPlatforme(int x, int y, int z, int side, int rand) {
		drawDisk(rand+10, x, y, z, ModBlock.lyokoGrass);
		drawDisk(rand+10, x, y+1, z, ModBlock.lyokoGrass);
		
	}
	
	private void EndPlatforme(int x, int y, int z, int side, int rand) {
		drawDisk(rand+5, x, y, z, ModBlock.lyokoGrass);
		drawDisk(rand+5, x, y+1, z, ModBlock.lyokoGrass);
		
	}
	
	private void spawnInter1(int x, int y, int z, int side) {
		drawDisk(8, x, y, z, ModBlock.lyokoGrass);
		drawDisk(8, x, y+1, z, ModBlock.lyokoGrass);
		if (side == 1) {
			coordArray.add(new int[]{x,y,z+8,1,3});
			coordArray.add(new int[]{x+8,y,z,2,3});
			coordArray.add(new int[]{x-8,y,z,4,3});
		}else if (side == 2) {
			coordArray.add(new int[]{ x,y,z+8,1,3});
			coordArray.add(new int[]{x+8,y,z,2,3});
			coordArray.add(new int[]{x,y,z-8,3,3});
		}else if (side ==3) {
			coordArray.add(new int[]{x+8,y,z,2,3});
			coordArray.add(new int[]{x,y,z-8,3,3});
			coordArray.add(new int[]{x-8,y,z,4,3});
		}else if (side == 4) {
			coordArray.add(new int[]{x,y,z+8,1,3});
			coordArray.add(new int[]{x,y,z-8,3,3});
			coordArray.add(new int[]{x-8,y,z,4,3});
		}
		
	}
	
	private void generateWayLv2(int x, int y, int z, int side,int rint){
		int mint = rint+50;
		double i;
		int Coord = 0;
		if (side == 1) {
			if (canspawnWay(x-4, y, z-rint, x+4, y+1, z)) {
				System.out.println(1);
				for (i = z; i > z-mint; i--) {
					Coord = (int) Math.round(3*Math.sin(i/5));
					for (int j = Coord-4; j < Coord+4; j++) {
						worldObj.setBlock(j+x, y, (int) i, ModBlock.lyokoGrass);
						worldObj.setBlock(j+x, y+1, (int) i, ModBlock.lyokoGrass);
					}
				}
				coordArray.add(new int[]{Coord+x,y,(int) i,side,4});
			}
		}else if (side == 2) {
			if (canspawnWay(x, y, z-4, x+rint, y+1, z+4)) {
				System.out.println(2);
				for (i = x; i < x+mint; i++) {
					Coord = (int) Math.round(3*Math.sin(i/5));
					for (int j = Coord-4; j < Coord+4; j++) {
						worldObj.setBlock((int) i, y, (int) j+z, ModBlock.lyokoGrass);
						worldObj.setBlock((int) i, y+1, (int) j+z, ModBlock.lyokoGrass);
					}
				}
				coordArray.add(new int[]{(int) i,y,Coord+z,side,4});
			}
		}else if (side == 3) {
			if (canspawnWay(x-4, y, z, x+4, y+1, z+rint)) {
				System.out.println(3);
				for (i = z; i < z+mint; i++) {
					Coord = (int) Math.round(3*Math.sin(i/5));
					for (int j = Coord-4; j < Coord+4; j++) {
						worldObj.setBlock(j+x, y, (int) i, ModBlock.lyokoGrass);
						worldObj.setBlock(j+x, y+1, (int) i, ModBlock.lyokoGrass);
					}
				}
				coordArray.add(new int[]{Coord+x,y,(int) i,side,4});
			}
		}else if (side == 4) {
			if (canspawnWay(x-rint, y, z-4, x, y+1, z+4)) {
				System.out.println(4);
				for (i = x; i > x-mint; i--) {
					Coord = (int) Math.round(3*Math.sin(i/5));
					for (int j = Coord-4; j < Coord+4; j++) {
						worldObj.setBlock((int) i, y, (int) j+z, ModBlock.lyokoGrass);
						worldObj.setBlock((int) i, y+1, (int) j+z, ModBlock.lyokoGrass);
					}
				}
				coordArray.add(new int[]{(int) i,y,Coord+z,side,4});
			}
		}
	}
	
	private void generateWay(int x, int y, int z, int side,int rint){
		int mint = rint+100;
		double i;
		int Coord = 0;
		if (side == 1) {
			for (i = z; i > z-mint; i--) {
				Coord = (int) Math.round(3*Math.sin(i/5));
				for (int j = Coord-4; j < Coord+4; j++) {
					worldObj.setBlock(j, y, (int) i, ModBlock.lyokoGrass);
					worldObj.setBlock(j, y+1, (int) i, ModBlock.lyokoGrass);
				}
			}
			coordArray.add(new int[]{Coord,y,(int) i,side,2});
		}else if (side == 2) {
			for (i = x; i < x+mint; i++) {
				Coord = (int) Math.round(3*Math.sin(i/5));
				for (int j = Coord-4; j < Coord+4; j++) {
					worldObj.setBlock((int) i, y, (int) j, ModBlock.lyokoGrass);
					worldObj.setBlock((int) i, y+1, (int) j, ModBlock.lyokoGrass);
				}
			}
			coordArray.add(new int[]{(int) i,y,Coord,side,2});
		}else if (side == 3) {
			for (i = z; i < z+mint; i++) {
				Coord = (int) Math.round(3*Math.sin(i/5));
				for (int j = Coord-4; j < Coord+4; j++) {
					worldObj.setBlock(j, y, (int) i, ModBlock.lyokoGrass);
					worldObj.setBlock(j, y+1, (int) i, ModBlock.lyokoGrass);
				}
			}
			coordArray.add(new int[]{Coord,y,(int) i,side,2});
		}else if (side == 4) {
			for (i = x; i > x-mint; i--) {
				Coord = (int) Math.round(3*Math.sin(i/5));
				for (int j = Coord-4; j < Coord+4; j++) {
					worldObj.setBlock((int) i, y, (int) j, ModBlock.lyokoGrass);
					worldObj.setBlock((int) i, y+1, (int) j, ModBlock.lyokoGrass);
				}
			}
			coordArray.add(new int[]{(int) i,y,Coord,side,2});
		}
	}
	
	private boolean canspawnWay(int x1, int y1, int z1,int x2, int y2,int z2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				for (int j2 = z1; j2 < z2; j2++) {
					if (worldObj.getBlock(i, j, j2)instanceof BlockLE) {
						return false;
					}
				}
			}
		}
		//if (x1 < 0 && x2 > x1 && !(x2-x1 < 10)) {
		//	return false;
		//}
		if ( x2 > x1 && !(x2-x1 < 10) && moreXorZ(x1, x2, z1, z2).equals(CorrdEnum.x)) {
			return false;
		}
		//if (z1 < 0 && z2 > z1) {
		//	return false;
		//}
		if (z2 > z1 && !(z2-z1 < 10)&& moreXorZ(x1, x2, z1, z2).equals(CorrdEnum.z)) {
			return false;
		}
		return true;
	}
	private enum CorrdEnum{
		x,y,z
	}
	private CorrdEnum moreXorZ(int x1, int x2, int z1, int z2) {
		if (x1 < 0) {
			if (z1 < 0) {
				if (x1 < z1) {
					return CorrdEnum.x;
				}else {
					return CorrdEnum.z;
				}
			}else {
				if (Math.pow(x1, 2) < Math.pow(z1, 2)) {
					return CorrdEnum.z;
				}else {
					return CorrdEnum.x;
				}
			}
		}else {
			if (z1 < 0) {
				if (Math.pow(x1, 2) < Math.pow(z1, 2)) {
					return CorrdEnum.x;
				}else {
					return CorrdEnum.z;
				}
			}else {
				if (x1 < z1) {
					return CorrdEnum.z;
				}else {
					return CorrdEnum.x;
				}
			}
		}
	}
	
	private void drawDisk(int r,int x, int y, int z, Block block){
		for (int i = r; i >= 1; i--) {
			drawCrecle(i,x,y,z,block);
		}
		worldObj.setBlock(x, y, z, block);
	}
	
	private void drawCrecle(int r,int x, int y, int z,Block block) {
		for (int k = 0; k < 360; k = k+1) {
			int xC = (int) Math.round(r*Math.cos(k));
			int zC =  (int) Math.round(r*Math.sin(k));
			worldObj.setBlock(x+xC, y, z+zC, block);
		}
	}
	
	private void oneTree(int x,int y, int z){
		for (int b = y; b < 180; b++) {
			worldObj.setBlock(x, b, z, ModBlock.lyokoTree);
		}
		worldObj.setBlock(x+1, y, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y, z+1, ModBlock.lyokoTree);
		worldObj.setBlock(x-1, y, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y, z-1, ModBlock.lyokoTree);
		worldObj.setBlock(x+2, y-1, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y-1, z+2, ModBlock.lyokoTree);
		worldObj.setBlock(x-2, y-1, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y-1, z-2, ModBlock.lyokoTree);
		worldObj.setBlock(x+2, y-2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y-2, z+2, ModBlock.lyokoTree);
		worldObj.setBlock(x-2, y-2, z, ModBlock.lyokoTree);
		worldObj.setBlock(x, y-2, z-2, ModBlock.lyokoTree);
	}
	
	
	//worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air);
	
	private void generateChunk(int i, int j){
		int x = i*16;
		int z = j*16;
		for (int k2 = x; k2 < x+16; k2++) {
			for (int l = z; l < z+16; l++) {
				for (int k = 0; k < 4; k++) {
					worldObj.setBlock(k2, k, l, Blocks.air);
				}
				worldObj.setBlock(k2, 0, l, ModBlock.digitalSea);
			}
		}
	}
	
	private void generateDigitalSea(){
		generateChunk(i,j);
		if (i >= 32) {
			i = -32;
			if (j >= 32) {
				j = -32;
				progress = 1;
			}
			j++;
		}
		i++;
	}
}
