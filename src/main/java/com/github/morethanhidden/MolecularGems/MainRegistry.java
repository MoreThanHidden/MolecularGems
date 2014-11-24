package com.github.morethanhidden.MolecularGems;

	import com.github.morethanhidden.MolecularGems.Client.ClientProxy;
import com.github.morethanhidden.MolecularGems.blocks.BlockLiquidElectricOoze;
import com.github.morethanhidden.MolecularGems.blocks.GemOre;
import com.github.morethanhidden.MolecularGems.blocks.Gemerator;
import com.github.morethanhidden.MolecularGems.blocks.GemeratorEmpty;
import com.github.morethanhidden.MolecularGems.handler.BucketHandler;
import com.github.morethanhidden.MolecularGems.handler.GemOnMineEvent;
import com.github.morethanhidden.MolecularGems.items.AscendedGem;
import com.github.morethanhidden.MolecularGems.items.CleanGem;
import com.github.morethanhidden.MolecularGems.items.BucketLiquidElectricOoze;
import com.github.morethanhidden.MolecularGems.items.GemCompoundItem;
import com.github.morethanhidden.MolecularGems.mob.MolecularMobs;
import com.github.morethanhidden.MolecularGems.world.MolecularWorld;
import com.github.morethanhidden.MolecularGems.world.WorldGenMoleculer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
	//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
	//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
	//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
	//import cpw.mods.fml.common.network.NetworkMod; // not used in 1.7
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

	@Mod(modid="moleculargems", name="Molecular Gems", version="0.0.1")
	//@NetworkMod(clientSideRequired=true) // not used in 1.7
	public class MainRegistry {
			//Items I have Added

			public static Item cleanGem;
			public static Item ascendedGem;
			public static Item gemcompoundItem;
			public static Block gemOre;
			public static Block Gemerator;
			public static Block GemeratorEmpty;
			public static Block blockElectricOooze;
			public static Fluid liquidElectricOoze = new Fluid("liquideuze");
			public static Item bucketliquidElectricOoze;
			public static Achievement electrifying;
			public static Achievement minegem;
			
			public static int gemeratorEnergyAmt = 1000000;
			public static boolean ShockingLiquid = true;
			
	        // The instance of your mod that Forge uses.
	        @Instance(value = "moleculargems")
	        public static MainRegistry instance;
	        
	        // Says where the client and server 'proxy' code is loaded.
	        @SidedProxy(clientSide="com.github.morethanhidden.MolecularGems.Client.ClientProxy", serverSide="com.github.morethanhidden.MolecularGems.common")
	        public static common proxy;
	        
	        @EventHandler // used in 1.6.2
	        //@PreInit    // used in 1.5.2
	        public void preLoad(FMLPreInitializationEvent PreEvent) {
	        MolecularWorld.mainRegistry();
	        MolecularMobs.mainRegistry();
	        }
	        
	        //Creative tab for my mod
	        public static CreativeTabs tabmoleculargems = new CreativeTabs("Molecular Gems") {
	            @Override
	            @SideOnly(Side.CLIENT)
	            public Item getTabIconItem() {
	                return Item.getItemFromBlock(Gemerator);
	            }
	        };
	        
	        @EventHandler // used in 1.6.2
	        //@PreInit    // used in 1.5.2
	        public void preInit(FMLPreInitializationEvent event) {  
	        	
	        	
	        	FluidRegistry.registerFluid(liquidElectricOoze);
	        	blockElectricOooze = new BlockLiquidElectricOoze(liquidElectricOoze, Material.water).setBlockName("liquidEUze");
	        	GameRegistry.registerBlock(blockElectricOooze, "moleculargems" + "_" + blockElectricOooze.getUnlocalizedName().substring(5));
	        	liquidElectricOoze.setUnlocalizedName(blockElectricOooze.getUnlocalizedName());
	        	
	        	bucketliquidElectricOoze = new BucketLiquidElectricOoze(blockElectricOooze);
	        	FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("liquideuze", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(bucketliquidElectricOoze), new ItemStack(Items.bucket));
	        	
	        	cleanGem = new CleanGem().setUnlocalizedName("CleanGem");
	        	gemcompoundItem = new GemCompoundItem();
	        	gemOre = new GemOre();
	        	Gemerator = new Gemerator();
	        	GemeratorEmpty = new GemeratorEmpty();
	        	ascendedGem = new AscendedGem();
	        	
	            GameRegistry.registerItem(cleanGem, cleanGem.getUnlocalizedName());
	            GameRegistry.registerItem(gemcompoundItem, gemcompoundItem.getUnlocalizedName());
	            GameRegistry.registerItem(ascendedGem, ascendedGem.getUnlocalizedName());
	            GameRegistry.registerBlock(gemOre, "gemOre");
	            GameRegistry.registerBlock(Gemerator, "Gemerator");
	            GameRegistry.registerBlock(GemeratorEmpty, "GemeratorEmpty");
	            GameRegistry.registerWorldGenerator(new WorldGenMoleculer(), 1);
	            
	            GameRegistry.registerItem(bucketliquidElectricOoze, bucketliquidElectricOoze.getUnlocalizedName());
	            
	            GameRegistry.registerTileEntity(TileGemerator.class, "TileGemerator");
	            
	            MinecraftForge.EVENT_BUS.register(new BucketHandler());
	            FMLCommonHandler.instance().bus().register(new GemOnMineEvent());
	            		
	        	Configuration config = new Configuration(event.getSuggestedConfigurationFile());

	        	minegem = new Achievement("achievement.minegem", "minegem", 0, 0, gemOre, (Achievement)null).initIndependentStat().registerStat();
	        	
	        	if (ShockingLiquid = true){
	        	electrifying = new Achievement("achievement.electrifying", "electrifying", 2, 1, bucketliquidElectricOoze, minegem).registerStat();
	        	}
	        	
	        	AchievementPage.registerAchievementPage(new AchievementPage("Molecular Gems", new Achievement[]{minegem, electrifying}));
	        	
	        	config.load();

	        	// Configuration goes here.
	        	gemeratorEnergyAmt = config.get(config.CATEGORY_GENERAL, "Gemerator Energy Amount", 1000000).getInt();
	        	ShockingLiquid = config.get(config.CATEGORY_GENERAL, "Lightning in ElectricOoze", true).getBoolean();
	        	
	        	
	        	config.save();
	            
	        }
	        
	        @EventHandler // used in 1.6.2
	        //@Init       // used in 1.5.2
	        public void load(FMLInitializationEvent event) {
	        	proxy.registerRenderers();
	        	addNames();
	            oreRegistration();
	            addOreRecipes();
	                 
	        }
	        public static void addNames()
	        {
	                LanguageRegistry.addName(gemOre, "Gem Ore");
	                LanguageRegistry.addName(ascendedGem, "Ascended Gem");
	                LanguageRegistry.addName(GemeratorEmpty, "Empty Gemerator");
	                LanguageRegistry.addName(Gemerator, "Gemerator");
	                LanguageRegistry.addName(bucketliquidElectricOoze, "Electric Ooze Bucket");
	                LanguageRegistry.addName(blockElectricOooze, "Electric Ooze");
	                LanguageRegistry.addName(gemcompoundItem, "Ascended Gem Compound");
	                LanguageRegistry.addName(new ItemStack(cleanGem, 1, 0), "Flameoid Gem");
	                LanguageRegistry.addName(new ItemStack(cleanGem, 1, 1), "Electroid Gem");
	                LanguageRegistry.addName(new ItemStack(cleanGem, 1, 2), "Naturoid Gem");
	                
	                LanguageRegistry.instance().addStringLocalization("achievement.minegem", "en_US", "Oooh Shiny!");
	                LanguageRegistry.instance().addStringLocalization("achievement.electrifying", "en_US", "A Electrifying Experience!");
	                LanguageRegistry.instance().addStringLocalization("achievement.minegem.desc", "en_US", "Mine a Gem Ore");
	                LanguageRegistry.instance().addStringLocalization("achievement.electrifying.desc", "en_US", "Step into some Electric Ooze");
	                LanguageRegistry.instance().addStringLocalization("itemGroup.Molecular Gems", "en_US", "Molecular Gems");
	                LanguageRegistry.instance().addStringLocalization("entity.Ghost of Ancients.name", "Ghost of the Ancients");
	        
	        }
	        
	        public static void oreRegistration()
	        {
	                //1.3.2 OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
	                //1.6.4
	                OreDictionary.registerOre("oreGem", gemOre);
	                OreDictionary.registerOre("gemFlameoid", new ItemStack(cleanGem, 1, 0));
	                OreDictionary.registerOre("gemElectroid", new ItemStack(cleanGem, 1, 1));
	                OreDictionary.registerOre("gemNaturoid", new ItemStack(cleanGem, 1, 2));
	                OreDictionary.registerOre("gemAscended", ascendedGem);
	                OreDictionary.registerOre("bucketElectricOoze", bucketliquidElectricOoze);
	        }
	        public static void addOreRecipes()
	        {
	        	
	                GameRegistry.addRecipe(new ShapedOreRecipe(GemeratorEmpty, true, new Object[]{
	                		"DRD","BIG","DPD",
	                		Character.valueOf('D'), "gemDiamond",
	                		Character.valueOf('R'), "gemFlameoid",
	                		Character.valueOf('B'), "gemElectroid",
	                		Character.valueOf('G'), "gemNaturoid",
	                		Character.valueOf('P'), Blocks.piston,
	                		Character.valueOf('I'), "blockIron"
	                		}));
	                
	                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(gemcompoundItem, 1), new Object[] { 
	                	"gemFlameoid", 
	                	"gemElectroid", 
	                	"gemNaturoid"
	                	}));
	                
	                GameRegistry.addRecipe(new ShapedOreRecipe(bucketliquidElectricOoze, true, new Object[]{
	                		"EEE","EBE","EEE",
	                		Character.valueOf('B'), Items.water_bucket.setContainerItem(null),
	                		Character.valueOf('E'), "gemElectroid",
	                		}));
	                
	               GameRegistry.addSmelting(gemcompoundItem, new ItemStack(ascendedGem), 3.0f);
	        }
	        
	        @EventHandler // used in 1.6.2
	        //@PostInit   // used in 1.5.2
	        public void postInit(FMLPostInitializationEvent event) {
	                // Stub Method
	        }
	        
	}
