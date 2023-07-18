package tobia.wardenslegacy;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class Advancements implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/root");

        Advancement hornsAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:sculk_horns")))
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/sculk_horns");

        Advancement helmetAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:warden_helmet")))
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/warden_helmet");
        Advancement chestplateAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:warden_chestplate")))
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/warden_chestplate");
        Advancement leggingsAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:warden_leggings")))
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/warden_leggings");
        Advancement bootsAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:warden_boots")))
                .criterion("has_creature_shard", InventoryChangedCriterion.Conditions.items(ModItems.CREATURE_SHARD))
                .build(consumer, "wardenslegacy" + "/warden_boots");

        Advancement templateAdvancement = Advancement.Builder.create()
                .rewards(AdvancementRewards.Builder.recipe(new Identifier("wardenslegacy:sculk_upgrade_smithing_template")))
                .criterion("has_template", InventoryChangedCriterion.Conditions.items(ModItems.SCULK_UPGRADE_SMITHING_TEMPLATE))
                .build(consumer, "wardenslegacy" + "/sculk_upgrade_smithing_template");
    }
}
