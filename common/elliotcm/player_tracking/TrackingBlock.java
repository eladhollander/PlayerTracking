package elliotcm.player_tracking;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;

public class TrackingBlock extends Block {

	public TrackingBlock(int blockId, Logger logger) {
		super(blockId, buildTrackingBlockMaterial(logger));
	}
	
	@SuppressWarnings("unchecked")
	private static Material buildTrackingBlockMaterial(Logger logger) {
		MapColor mapColor = null;
		try {
			Constructor<MapColor> constructor = (Constructor<MapColor>) MapColor.class.getDeclaredConstructors()[0];
			constructor.setAccessible(true); 
			mapColor = constructor.newInstance(14, 16711935);
		} catch (Exception e) {
			logger.warning("Failed to create a custom map color for player tracking, falling back to TNT color");
			mapColor = MapColor.tntColor;
		} 

		return new MaterialTransparent(mapColor);
	}

}
