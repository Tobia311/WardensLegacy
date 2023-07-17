package tobia.wardenslegacy;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static tobia.wardenslegacy.WardenItems.ANCIENT_FOSSIL;

public class WardenArmor {
    public static final ArmorMaterial WARDEN_MATERIAL = new WardenMaterial();
    public static final Item WARDEN_HELMET = new ArmorItem(WARDEN_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings());
    public static final Item WARDEN_CHESTPLATE = new ArmorItem(WARDEN_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    public static final Item WARDEN_LEGGINGS = new ArmorItem(WARDEN_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings());
    public static final Item WARDEN_BOOTS = new ArmorItem(WARDEN_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings());

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "warden_helmet"), WARDEN_HELMET);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "warden_chestplate"), WARDEN_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "warden_leggings"), WARDEN_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "warden_boots"), WARDEN_BOOTS);
    }

    public static void add() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(Items.NETHERITE_BOOTS, WARDEN_HELMET);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(WARDEN_HELMET, WARDEN_CHESTPLATE);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(WARDEN_CHESTPLATE, WARDEN_LEGGINGS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.addAfter(WARDEN_LEGGINGS, WARDEN_BOOTS);
        });
    }

    public static void init() {
        register();
        add();
    }
}


