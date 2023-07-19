package tobia.wardenslegacy;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.List;

public class ModItems {
    public static final Block ANCIENT_FOSSIL = new Block(FabricBlockSettings.create().strength(40F).sounds(BlockSoundGroup.SCULK));
    public static final Item CREATURE_SHARD = new Item(new FabricItemSettings());
    public static final Item WARDENS_HEART = new Item(new FabricItemSettings());
    public static final Item SCULK_HORNS = new Item(new FabricItemSettings());

    //Text appliesToText, Text ingredientsText, Text titleText, Text baseSlotDescriptionText, Text additionsSlotDescriptionText, List<Identifier> emptyBaseSlotTextures, List<Identifier> emptyAdditionsSlotTextures
    private static final Text SCULK_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("wardenslegacy", "smithing_template.sculk_upgrade.applies_to"))).formatted(Formatting.BLUE);
    private static final Text SCULK_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("wardenslegacy", "smithing_template.sculk_upgrade.ingredients"))).formatted(Formatting.BLUE);
    private static final Text SCULK_TITLE_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("wardenslegacy", "smithing_template.sculk_upgrade.title"))).formatted(Formatting.GRAY);
    private static final Text SCULK_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("wardenslegacy", "smithing_template.sculk_upgrade.base_slot_description")));
    private static final Text SCULK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", new Identifier("wardenslegacy", "smithing_template.sculk_upgrade.additions_slot_description")));
    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");

    private static final List<Identifier> emptyBaseSlotTextures = List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE, EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE);
    public static final Item SCULK_UPGRADE_SMITHING_TEMPLATE = new SmithingTemplateItem(SCULK_UPGRADE_APPLIES_TO_TEXT, SCULK_UPGRADE_INGREDIENTS_TEXT, SCULK_TITLE_TEXT, SCULK_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, SCULK_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, emptyBaseSlotTextures, null);

    public static final RegistryKey<PlacedFeature> ANCIENT_FOSSIL_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("wardenslegacy", "ancient_fossil"));

    private static final Identifier WARDEN_LOOT_TABLE_ID = new Identifier("minecraft", "entities/warden");
    private static final Identifier ANCIENT_CITY_LOOT_TABLE_ID = new Identifier("minecraft", "chests/ancient_city");


    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier("wardenslegacy", "ancient_fossil"), ANCIENT_FOSSIL);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "ancient_fossil"), new BlockItem(ANCIENT_FOSSIL, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "creature_shard"), CREATURE_SHARD);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "wardens_heart"), WARDENS_HEART);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "sculk_horns"), SCULK_HORNS);
        Registry.register(Registries.ITEM, new Identifier("wardenslegacy", "sculk_upgrade_smithing_template"), SCULK_UPGRADE_SMITHING_TEMPLATE);

    }

    public static void addItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.SCULK_CATALYST, ANCIENT_FOSSIL);
        });


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.ECHO_SHARD, CREATURE_SHARD);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(CREATURE_SHARD, SCULK_HORNS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(SCULK_HORNS, WARDENS_HEART);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, SCULK_UPGRADE_SMITHING_TEMPLATE);
        });

    }

    public static void addToGeneration() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && WARDEN_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder().
                        with(ItemEntry.builder(WARDENS_HEART));

                tableBuilder.pool(poolBuilder);
            }

            if (source.isBuiltin() && ANCIENT_CITY_LOOT_TABLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder().
                        rolls(ConstantLootNumberProvider.create(1)).
                        conditionally(RandomChanceLootCondition.builder(0.1f)).
                        with(ItemEntry.builder(SCULK_UPGRADE_SMITHING_TEMPLATE));

                tableBuilder.pool(poolBuilder);
            }
        }));

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DEEP_DARK), GenerationStep.Feature.UNDERGROUND_ORES, ANCIENT_FOSSIL_KEY);
    }

    public static void init() {
        register();
        addItems();
        addToGeneration();
    }
}
