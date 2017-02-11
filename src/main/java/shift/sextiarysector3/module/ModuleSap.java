package shift.sextiarysector3.module;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shift.sextiarysector3.SSBlocks;
import shift.sextiarysector3.SSFluids;
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

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void preInitClient(FMLPreInitializationEvent event) {

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void loadClient(FMLInitializationEvent event) {

    }

    @Override
    public void load(FMLInitializationEvent event) {

        saps.add(new SapEntry(Blocks.LOG, SSBlocks.sapCauldron, new FluidStack(SSFluids.sap, 300)));
        saps.add(new SapEntry(Blocks.LOG2, SSBlocks.sapCauldron, new FluidStack(SSFluids.sap, 300)));

        saps.add(new SapEntry(SSBlocks.rubberLog, SSBlocks.rubberCauldron, new FluidStack(SSFluids.rubber, 300)));
        saps.add(new SapEntry(SSBlocks.mapleLog, SSBlocks.mapleCauldron, new FluidStack(SSFluids.maple, 300)));

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

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
        public FluidStack fluid;

        public SapEntry(Block wood, Block sap, FluidStack fluid) {
            this.wood = wood;
            this.sapCauldron = sap;
            this.fluid = fluid;
        }

        public SapEntry setMeta(int m) {
            this.logMeta = m;
            return this;
        }

    }

}
