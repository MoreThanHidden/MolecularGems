package morethanhidden.MolecularGems.world;

import java.util.Random;

import morethanhidden.MolecularGems.MolecularGems;
import morethanhidden.MolecularGems.registry.BlockRegistry;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenMoleculer implements IWorldGenerator {

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chuckGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.getDimensionId()){
		case -1:
			generateNether(world, rand, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, rand, chunkX * 16, chunkZ * 16);
		}
		
	}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		for(int i = 0; i < MolecularGems.config_gemrate; i++){
			int randPosX = chunkX + rand.nextInt(16);
			int randPosY = rand.nextInt(10);
			int randPosZ = chunkZ + rand.nextInt(16);
			
			(new WorldGenMinable(BlockRegistry.gemOre.getDefaultState(), 1)).generate(world, rand, new BlockPos(randPosX, randPosY, randPosZ));
		}
		}
		

	private void generateNether(World world, Random rand, int i, int j) {
		
		
	}

}
