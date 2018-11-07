package com.daecher.withered;

import init.ModBlocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Reference.MODID, name=Reference.MODNAME, version = Reference.Version, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSIONS)

public class WitheredEarthMod {
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		System.out.println(Reference.MODID + ":preInit");
		ModBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		System.out.println(Reference.MODID + ":init");
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println(Reference.MODID + ":postInit");
	}
	
	@Instance
	public static WitheredEarthMod instance;
	
	@SidedProxy(serverSide = "com.daecher.withered.CommonProxy", clientSide = "com.daecher.withered.ClientProxy")
	public static CommonProxy proxy;
}
