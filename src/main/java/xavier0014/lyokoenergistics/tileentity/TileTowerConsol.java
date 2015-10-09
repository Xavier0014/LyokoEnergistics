package xavier0014.lyokoenergistics.tileentity;

import java.util.ArrayList;
import java.util.Random;

import xavier0014.lyokoenergistics.recipes.RecipesMaterializationScanner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileTowerConsol extends TileEntityLE {
	public int towerid;
	public ArrayList<ItemStack> result;
	public ArrayList<Integer> InvalidNumber = new ArrayList<Integer>();
	private Random random = new Random();
	public boolean isProsesing;
	public long time1 =0;
	public int randomInt;
	public int progresse;
	public int menu;
	public boolean itemtogive;
	
	@Override
	public void updateEntity() {
		if (!isProsesing) {
			if (towerid != 0) {
				result = RecipesMaterializationScanner.smelting().result;
				randomInt = random.nextInt(result.size());
				if (isValidNumber(randomInt)) {
					isProsesing = true;
					time1 = System.nanoTime();
				}
			}
		}else {
			progresse = (int) (((System.nanoTime()-time1)*100)/25000000000L);
			//System.out.println(System.nanoTime()-time1);
			if ((System.nanoTime()-time1) >= 25000000000L) {
				if (worldObj.isRemote) {
					//System.out.println(result.get(randomInt));
					InvalidNumber.add(randomInt);
					if (TileMaterializationScanner.knowledge.containsKey(playerName)) {
						TileMaterializationScanner.knowledge.get(playerName).set(randomInt,true);
					}
				}
				isProsesing = false;
				towerid = 0;
				menu = 6;
				itemtogive =true;
			}
		}
	}
	
	public boolean isValidNumber(int number){
		InvalidNumber.add(0,result.size()-1);
		InvalidNumber.add(1,6);
		InvalidNumber.add(2,7);
		InvalidNumber.add(3,0);
		for (int i = 0; i < InvalidNumber.size(); i++) {
			if (number == InvalidNumber.get(i)) {
				return false;
			}
		}
		return true;
		
	}
}
