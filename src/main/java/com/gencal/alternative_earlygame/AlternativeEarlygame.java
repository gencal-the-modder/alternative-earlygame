package com.gencal.alternative_earlygame;

import com.gencal.alternative_earlygame.items.custom.CrushingHammerItem;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AlternativeEarlygame.MODID)
public class AlternativeEarlygame
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "alternative_earlygame";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "alternative_earlygame" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Create a Deferred Register to hold Blocks which will all be registered under the "alternative_earlygame" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);


    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "alternative_earlygame" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Register the almighty HAMMER
    public static final RegistryObject<Item> CRUSHING_HAMMER = ITEMS.register("crushing_hammer", () -> new CrushingHammerItem(new Item.Properties()));

    // Register vanilla ore shards
    public static final RegistryObject<Item> RAW_COPPER_SHARD = ITEMS.register("raw_copper_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_IRON_SHARD = ITEMS.register("raw_iron_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_GOLD_SHARD = ITEMS.register("raw_gold_shard", () -> new Item(new Item.Properties()));

    // The missing piece of the puzzle... THE ONE PIECE! (one piece is real)
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", () -> new Item(new Item.Properties()));

    // Reinforced things
    public static final RegistryObject<Item> REINFORCED_CLAY_BALL = ITEMS.register("reinforced_clay_ball", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> REINFORCED_BRICK = ITEMS.register("reinforced_brick", () -> new Item(new Item.Properties()));

    // Reinforced bricks
    public static final RegistryObject<Item> REINFORCED_BRICKS_ITEM = ITEMS.register("reinforced_bricks", () ->
            new BlockItem((Block) AlternativeEarlygame.REINFORCED_BRICKS_BLOCK.get(), new Item.Properties())
    );

    public static final RegistryObject<Block> REINFORCED_BRICKS_BLOCK = BLOCKS.register("reinforced_bricks", () -> new Block(BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .strength(2.0F, 6.0F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_BRICKS)
    ));




    public static final RegistryObject<CreativeModeTab> ALTERNATIVE_EARLYGAME_TAB =
            CREATIVE_MODE_TABS.register("alternative_earlygame_tab", () ->
                    CreativeModeTab.builder()
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .title(Component.translatable("creativetab.alternative_earlygame_tab"))
                            .icon(() -> CRUSHING_HAMMER.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                output.accept(CRUSHING_HAMMER.get());
                                output.accept(RAW_COPPER_SHARD.get());
                                output.accept(RAW_IRON_SHARD.get());
                                output.accept(RAW_GOLD_SHARD.get());
                                output.accept(COPPER_NUGGET.get());
                                output.accept(REINFORCED_CLAY_BALL.get());
                                output.accept(REINFORCED_BRICK.get());
                                output.accept(REINFORCED_BRICKS_ITEM.get());
                            })
                            .build()
            );


    public static void log(String string) {
        LOGGER.info(string);
    }

    public AlternativeEarlygame()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        }
}
