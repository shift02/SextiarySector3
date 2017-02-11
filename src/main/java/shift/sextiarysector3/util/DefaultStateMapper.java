package shift.sextiarysector3.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;

public class DefaultStateMapper extends StateMapperBase {

    ResourceLocation location;

    public DefaultStateMapper(ResourceLocation location) {
        this.location = location;
    }

    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return new ModelResourceLocation(
                new ResourceLocation(this.location.getResourceDomain(), this.location.getResourcePath()),
                this.getPropertyString(state.getProperties()));
    }
}
