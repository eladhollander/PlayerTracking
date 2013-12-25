package elliotcm.player_tracking;

import java.util.EnumSet;
import java.util.logging.Logger;

import net.minecraft.entity.player.EntityPlayer;
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
		logger.info(String.format("Ticking player %s at X %s, Y %s, Z %s", player.username, player.posX, player.posY, player.posZ));
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
