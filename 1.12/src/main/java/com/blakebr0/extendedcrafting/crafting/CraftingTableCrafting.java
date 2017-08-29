package com.blakebr0.extendedcrafting.crafting;

import com.blakebr0.extendedcrafting.tile.TileCraftingTable;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;

public class CraftingTableCrafting extends InventoryCrafting implements ISidedInventory {

	public TileCraftingTable tile;
	private IItemHandler handler;
	public Container container;

	public CraftingTableCrafting(Container container, TileCraftingTable tile) {
		super(container, 3, 3);
		this.tile = tile;
		this.handler = tile.matrix;
		this.container = container;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slot >= this.getSizeInventory() ? ItemStack.EMPTY : this.handler.getStackInSlot(slot);
	}

	@Override
	public ItemStack getStackInRowAndColumn(int row, int column) {
		if (row >= 0 && row < 3) {
			int x = row + column * 3;
			return this.getStackInSlot(x);
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.tile.setInventorySlotContents(slot, stack);
		this.container.onCraftMatrixChanged(this);
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}
}