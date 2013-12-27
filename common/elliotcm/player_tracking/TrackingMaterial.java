package elliotcm.player_tracking;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLogic;

public class TrackingMaterial extends MaterialLogic {

	public TrackingMaterial(MapColor mapColor) {
		super(mapColor);
		this.setNoPushMobility().setReplaceable();
	}

}
