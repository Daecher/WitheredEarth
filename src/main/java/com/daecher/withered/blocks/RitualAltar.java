package com.daecher.withered.blocks;

import javax.annotation.Nullable;

import com.daecher.withered.WitheredEarthMod;
import com.daecher.withered.tileentity.TileEntityAltar;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class RitualAltar extends BlockTileEntity<TileEntityAltar> {
	public RitualAltar(String name, Material material)
	{
		super(material, name);
		//setUnlocalizedName(name);
		//setRegistryName(name);
	}
	
	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override public Class<TileEntityAltar> getTileEntityClass()
	{
		return TileEntityAltar.class;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntityAltar createTileEntity(World world, IBlockState state)
	{
		return new TileEntityAltar();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
			EntityPlayer player, EnumHand hand, EnumFacing side, float hitx, float hity, float hitz)
	{
		if (!world.isRemote) {
			TileEntityAltar tile = getTileEntity(world, pos);
			IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			if (!player.isSneaking()) {
				if (player.getHeldItem(hand).isEmpty()) {
					player.setHeldItem(hand, itemHandler.extractItem(0, 64, false));
				} else {
					player.setHeldItem(hand, itemHandler.insertItem(0, player.getHeldItem(hand), false));
				}
				tile.markDirty();
			} else {
				ItemStack stack = itemHandler.getStackInSlot(0);
				if (!stack.isEmpty()) {
					String localized = WitheredEarthMod.proxy.localize(stack.getUnlocalizedName() + ".name");
					player.sendMessage(new TextComponentString(stack.getCount() + "x " + localized));
				} else {
					//player.sendMessage(new TextComponentString("Empty"));
				}
			}
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityAltar tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}
}
