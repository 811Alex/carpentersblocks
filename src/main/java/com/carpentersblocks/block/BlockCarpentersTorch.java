package com.carpentersblocks.block;
 
import java.util.Random;

import com.carpentersblocks.Reference;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCarpentersTorch extends BlockTorch
{
	private static final PropertyBool LIT = PropertyBool.create("lit");
	
	public BlockCarpentersTorch() 
	{
		super(); 
	}
	
	@Override
	public int getLightValue(IBlockState state) 
	{
		if(state.getValue(LIT).booleanValue())
			return 15;
		return 0;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if(hand == EnumHand.MAIN_HAND 
				&& (heldItem.equals(Items.FLINT_AND_STEEL) || heldItem.equals(Blocks.TORCH)) 
				&& !state.getValue(LIT).booleanValue())
		{
			worldIn.setBlockState(pos, state.withProperty(LIT, true));
		}
		return true;
	}
	
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if(stateIn.getValue(LIT).booleanValue())
		{
			super.randomDisplayTick(stateIn, worldIn, pos, rand);
			if(worldIn.isRaining() && Reference.enableTorchWeatherEffects)
			{
				worldIn.setBlockState(pos, stateIn.withProperty(LIT, false));
			}
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		return this.getDefaultState().withProperty(
				FACING, (meta == 0 || meta == 5) ? EnumFacing.UP :
					(meta == 1 || meta == 6) ? EnumFacing.NORTH :
					(meta == 2 || meta == 7) ? EnumFacing.EAST :
					(meta == 3 || meta == 8) ? EnumFacing.SOUTH : EnumFacing.WEST)
				.withProperty(LIT, meta >= 5 ? true:false);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		boolean lit = state.getValue(LIT);
		switch(state.getValue(FACING))
		{
			case UP:
			case DOWN:
				default:
					return lit == true ? 5:0;
					
			case NORTH:
				return lit == true ? 6:1;
			case EAST:
				return lit == true ? 7:2;
			case SOUTH:
				return lit == true ? 8:3;
			case WEST:
				return lit == true ? 9:4;
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING, LIT});
    }
}
