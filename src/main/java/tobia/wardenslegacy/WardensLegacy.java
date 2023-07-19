package tobia.wardenslegacy;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WardensLegacy implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("wardens-legacy");

	@Override
	public void onInitialize() {
		//todo: add warden armor attack

		LOGGER.info("Hello Fabric world!");

		ModItems.init();
		ModArmor.init();
	}
}