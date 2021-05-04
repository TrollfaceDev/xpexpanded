package net.mcreator.xpexpanded.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.xpexpanded.item.ConcentratedXPItem;
import net.mcreator.xpexpanded.XpExpandedModElements;
import net.mcreator.xpexpanded.XpExpandedMod;

import java.util.Map;

@XpExpandedModElements.ModElement.Tag
public class ConcentratedXPtoXPProcedure extends XpExpandedModElements.ModElement {
	public ConcentratedXPtoXPProcedure(XpExpandedModElements instance) {
		super(instance, 11);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				XpExpandedMod.LOGGER.warn("Failed to load dependency entity for procedure ConcentratedXPtoXP!");
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
