package com.carpentersblocks.block;

import java.util.Random;

import com.carpentersblocks.block.types.BlockFlowerpot;
import com.carpentersblocks.util.registry.BlockRegistry;

import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockCarpentersFlowerPot extends BlockFlowerpot
{ 
	public BlockCarpentersFlowerPot(Material material)
	{
		super(material);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CONTENTS, BlockFlowerPot.EnumFlowerType.EMPTY) );
	} 
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
	{
		return Item.getItemFromBlock(this);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) 
	{ 
		return new ItemStack(BlockRegistry.blockCarpentersFlowerPot);
	}
}
