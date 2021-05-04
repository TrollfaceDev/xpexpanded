package xpanded.procedures;

import xpanded.item.ConcentratedXPItem;

import xpanded.XpandedModElements;

import xpanded.XpandedMod;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@XpandedModElements.ModElement.Tag
public class ConcentratedXPtoXPProcedure extends XpandedModElements.ModElement {
	public ConcentratedXPtoXPProcedure(XpandedModElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				XpandedMod.LOGGER.warn("Failed to load dependency entity for procedure ConcentratedXPtoXP!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(ConcentratedXPItem.block, (int) (1)))
				: false)) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(ConcentratedXPItem.block, (int) (1));
				((PlayerEntity) entity).inventory.func_234564_a_(p -> _stktoremove.getItem() == p.getItem(), (int) 1,
						((PlayerEntity) entity).container.func_234641_j_());
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).giveExperiencePoints((int) 1);
		}
	}
}
