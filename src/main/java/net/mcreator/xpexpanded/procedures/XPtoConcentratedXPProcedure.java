package net.mcreator.xpexpanded.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import net.mcreator.xpexpanded.item.ConcentratedXPItem;
import net.mcreator.xpexpanded.XpExpandedModElements;
import net.mcreator.xpexpanded.XpExpandedMod;

import java.util.Map;
import java.util.Iterator;

@XpExpandedModElements.ModElement.Tag
public class XPtoConcentratedXPProcedure extends XpExpandedModElements.ModElement {
	public XPtoConcentratedXPProcedure(XpExpandedModElements instance) {
		super(instance, 10);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				XpExpandedMod.LOGGER.warn("Failed to load dependency entity for procedure XPtoConcentratedXP!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) > 0)) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("xp_expanded:concentration"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).giveExperiencePoints((int) -1);
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(ConcentratedXPItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		}
	}
}
