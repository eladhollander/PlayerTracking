package elliotcm.player_tracking;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(channels = {ModInfo.ID}, clientSideRequired = false, serverSideRequired = false)
public class PlayerTracking {

	@Instance(ModInfo.ID)
	public static PlayerTracking mod;

	private Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = Logger.getLogger(ModInfo.ID);
		logger.setParent(FMLLog.getLogger());
		
		int blockId = 710;
		TrackingBlock trackingBlock = new TrackingBlock(blockId, logger);
		trackingBlock.setUnlocalizedName("trackingBlock").setTextureName("elliotcmplayertracking:footprints").setBlockUnbreakable();
        GameRegistry.registerBlock(trackingBlock, "TrackingBlock");
        LanguageRegistry.addName(trackingBlock, "Tracking block");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		logger.info("Registering tick handler");
		TickRegistry.registerTickHandler(new TrailCreator(logger), Side.SERVER);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
