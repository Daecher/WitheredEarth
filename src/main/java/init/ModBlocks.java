package init;

import com.daecher.withered.Reference;
import com.daecher.withered.blocks.RitualAltar;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid=Reference.MODID)
public class ModBlocks {
	public static Block ritualAltar;
	
	public static void init()
	{
		ritualAltar = new RitualAltar("ritual_altar", Material.ROCK).setHardness(5f).setCreativeTab(CreativeTabs.DECORATIONS).setLightLevel(0.0f);
		ritualAltar.setHarvestLevel("pickaxe", 0);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ritualAltar);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(new ItemBlock(ritualAltar).setRegistryName(ritualAltar.getRegistryName()));
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event)
	{
		registerRender(Item.getItemFromBlock(ritualAltar));
	}
	
	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void register(IForgeRegistry<Block> registry)
	{
		GameRegistry.registerTileEntity(((RitualAltar) ritualAltar).getTileEntityClass(), ritualAltar.getRegistryName().toString());
	}

}
