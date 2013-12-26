package elliotcm.player_tracking;

import java.util.EnumSet;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class TrailCreator implements IScheduledTickHandler {

	private Logger logger;
	
	public TrailCreator(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		EntityPlayer player = (EntityPlayer) tickData[0];
		World world = player.worldObj;
		
		int playerX = MathHelper.floor_double(player.posX);
		int playerZ = MathHelper.floor_double(player.posZ);
		int worldHeight = world.getActualHeight();
		int trackingBlockId = 710;
		
		world.setBlock(playerX, worldHeight - 1, playerZ, trackingBlockId);
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return String.format("%s - Trail creation, player ticking", ModInfo.NAME);
	}

	@Override
	public int nextTickSpacing() {
		return 10;
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) { /* noop */ }

}
