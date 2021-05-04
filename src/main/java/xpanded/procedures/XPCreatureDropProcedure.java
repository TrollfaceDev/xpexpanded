package xpanded.procedures;

import xpanded.item.ConcentratedXPItem;

import xpanded.XpandedModElements;

import xpanded.XpandedMod;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;

import java.util.Map;

@XpandedModElements.ModElement.Tag
public class XPCreatureDropProcedure extends XpandedModElements.ModElement {
	public XPCreatureDropProcedure(XpandedModElements instance) {
		super(instance, 19);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				XpandedMod.LOGGER.warn("Failed to load dependency x for procedure XPCreatureDrop!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				XpandedMod.LOGGER.warn("Failed to load dependency y for procedure XPCreatureDrop!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				XpandedMod.LOGGER.warn("Failed to load dependency z for procedure XPCreatureDrop!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				XpandedMod.LOGGER.warn("Failed to load dependency world for procedure XPCreatureDrop!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof World && !world.isRemote()) {
			ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, new ItemStack(ConcentratedXPItem.block, (int) (1)));
			entityToSpawn.setPickupDelay((int) 10);
			world.addEntity(entityToSpawn);
		}
	}
}
