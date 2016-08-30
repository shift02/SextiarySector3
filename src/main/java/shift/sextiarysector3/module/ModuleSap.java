package shift.sextiarysector3.module;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.block.BlockSapCauldron;

public class ModuleSap implements IModule {

	private static ModuleSap instance;

	private ModuleSap() {
	}

	public static ModuleSap getInstance() {
		if (instance == null) {
			instance = new ModuleSap();
		}
		return instance;
	}

	public final ArrayList<SapEntry> saps = new ArrayList<SapEntry>();

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void load(FMLInitializationEvent event) {

		saps.add(new SapEntry(Blocks.LOG, SSBlocks.sapCauldron));
		saps.add(new SapEntry(Blocks.LOG2, SSBlocks.sapCauldron));

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public boolean isSapBlock(Block block) {

		for (SapEntry sap : this.saps) {
			if (sap.wood.equals(block)) return true;
		}

		return false;

	}

	@Nullable
	public BlockSapCauldron getSapCauldron(Block block) {

		for (SapEntry sap : this.saps) {
			if (sap.wood.equals(block)) return (BlockSapCauldron) sap.sapCauldron;
		}

		return null;

	}

	public static class SapEntry {

		public Block wood;
		public int logMeta;
		public Block sapCauldron;

		public SapEntry(Block wood, Block sap) {
			this.wood = wood;
			this.sapCauldron = sap;
		}

		public SapEntry setMeta(int m) {
			this.logMeta = m;
			return this;
		}

	}

}
