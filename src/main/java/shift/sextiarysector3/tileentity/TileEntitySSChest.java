package shift.sextiarysector3.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shift.sextiarysector3.block.BlockSSChest;

public class TileEntitySSChest extends TileEntityChest {

    private BlockSSChest.Type cachedChestType;

    public TileEntitySSChest(BlockSSChest.Type typeIn) {
        this.cachedChestType = typeIn;
    }

    public TileEntitySSChest() {

    }

    @Override
    public String getName() {
        return this.hasCustomName() ? super.getName() : getChestName();
    }

    protected String getChestName() {
        return "container.ss.chest";
    }

    @SuppressWarnings("incomplete-switch")
    protected void setNeighbor(TileEntityChest chestTe, EnumFacing side) {
        if (chestTe.isInvalid()) {
            this.adjacentChestChecked = false;
        } else if (this.adjacentChestChecked) {
            switch (side) {
            case NORTH:

                if (this.adjacentChestZNeg != chestTe) {
                    this.adjacentChestChecked = false;
                }

                break;
            case SOUTH:

                if (this.adjacentChestZPos != chestTe) {
                    this.adjacentChestChecked = false;
                }

                break;
            case EAST:

                if (this.adjacentChestXPos != chestTe) {
                    this.adjacentChestChecked = false;
                }

                break;
            case WEST:

                if (this.adjacentChestXNeg != chestTe) {
                    this.adjacentChestChecked = false;
                }
            }
        }
    }

    @Override
    @Nullable
    protected TileEntityChest getAdjacentChest(EnumFacing side) {
        BlockPos blockpos = this.pos.offset(side);

        if (this.isChestAt(blockpos)) {
            TileEntity tileentity = this.worldObj.getTileEntity(blockpos);

            if (tileentity instanceof TileEntitySSChest) {
                TileEntitySSChest tileentitychest = (TileEntitySSChest) tileentity;
                tileentitychest.setNeighbor(this, side.getOpposite());
                return tileentitychest;
            }
        }

        return null;
    }

    protected boolean isChestAt(BlockPos posIn) {
        if (this.worldObj == null) {
            return false;
        } else {
            Block block = this.worldObj.getBlockState(posIn).getBlock();
            return block instanceof BlockSSChest && ((BlockSSChest) block).chestType == this.getChestType2();
        }
    }

    public BlockSSChest.Type getChestType2() {

        if (this.cachedChestType == null) {
            if (this.worldObj == null || !(this.getBlockType() instanceof BlockSSChest)) {
                return BlockSSChest.Type.BASIC;
            }

            this.cachedChestType = ((BlockSSChest) this.getBlockType()).chestType;
        }

        return this.cachedChestType;
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(EntityPlayer player) {

        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
            this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
        }
    }

    @Override
    public void closeInventory(EntityPlayer player) {

        if (!player.isSpectator() && this.getBlockType() instanceof BlockSSChest) {
            --this.numPlayersUsing;
            this.worldObj.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
            this.worldObj.notifyNeighborsOfStateChange(this.pos, this.getBlockType());
            this.worldObj.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType());
        }
    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }
}
