package shift.sextiarysector3.world.village;

import java.util.List;
import java.util.Random;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageCreationHandlerEnderStone implements IVillageCreationHandler {

    @Override
    public PieceWeight getVillagePieceWeight(Random random, int i) {

        return new StructureVillagePieces.PieceWeight(getComponentClass(), 20, MathHelper.getRandomIntegerInRange(random, i, i + 1));

    }

    @Override
    public Class<? extends Village> getComponentClass() {
        return ComponentEnderStone.class;
    }

    @Override
    public Village buildComponent(PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3,
            EnumFacing facing, int p5) {

        Object cm = ComponentEnderStone.buildComponent(startPiece, pieces, random, p1, p2, p3, facing, p5);

        if (cm == null) return null;

        return (Village) cm;

    }

}
