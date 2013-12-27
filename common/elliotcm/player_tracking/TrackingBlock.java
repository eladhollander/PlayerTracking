package elliotcm.player_tracking;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class TrackingBlock extends Block {

	public TrackingBlock(int blockId, Logger logger) {
		super(blockId, buildTrackingBlockMaterial(logger));
		
		float f = 0.2F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
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

		Material trackingMaterial = new TrackingMaterial(mapColor);
		
		return trackingMaterial;
	}

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube() {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock() {
        return false;
    }

    // Render like lillypads
    public int getRenderType() {
        return 23;
    }
}
