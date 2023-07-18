package tobia.wardenslegacy;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BrushableBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModItems {
    public static final Block ANCIENT_FOSSIL = new Block(FabricBlockSettings.create().strength(40F).sounds(BlockSoundGroup.SCULK));
    public static final Item CREATURE_SHARD = new Item(new FabricItemSettings());
    public static final Item WARDENS_HEART = new Item(new FabricItemSettings());
    public static final Item SCULK_HORNS = new Item(new FabricItemSettings());
    public static final Item SCULK_UPGRADE_SMITHING_TEMPLATE = new Item(new FabricItemSettings());

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
