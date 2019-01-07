 package sneckomod;

 import basemod.BaseMod;
 import basemod.ModPanel;
 import basemod.helpers.RelicType;
 import com.badlogic.gdx.Files;
 import com.badlogic.gdx.Gdx;
 import com.badlogic.gdx.files.FileHandle;
 import com.badlogic.gdx.graphics.Texture;
 import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.random.Random;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import java.nio.charset.StandardCharsets;
 import org.apache.logging.log4j.Logger;
 import sneckomod.cards.*;
 import sneckomod.patches.AbstractCardEnum;
 import sneckomod.patches.SneckoEnum;

 @com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
 public class SneckoMod implements basemod.interfaces.OnStartBattleSubscriber, basemod.interfaces.PostInitializeSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.EditKeywordsSubscriber, basemod.interfaces.EditStringsSubscriber
 {
   private static final com.badlogic.gdx.graphics.Color SNECKO_COLOR = com.megacrit.cardcrawl.helpers.CardHelper.getColor(220.0F, 55.0F, 220.0F);

   private static final String SneckoMOD_ASSETS_FOLDER = "SneckoImages";

   private static final String ATTACK_CARD = "512/bg_attack_snecko.png";

   private static final String SKILL_CARD = "512/bg_skill_snecko.png";

   private static final String POWER_CARD = "512/bg_power_snecko.png";

   private static final String ENERGY_ORB = "512/card_snecko_orb.png";

   private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

   private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_snecko.png";
   private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_snecko.png";
   private static final String POWER_CARD_PORTRAIT = "1024/bg_power_snecko.png";
   private static final String ENERGY_ORB_PORTRAIT = "1024/card_snecko_orb.png";
   public static sneckomod.characters.SneckoCharacter SneckoCharacter;
   private ModPanel settingsPanel;
   @SpireEnum
   public static com.megacrit.cardcrawl.cards.AbstractCard.CardTags UNKNOWN;
   @SpireEnum
   public static com.megacrit.cardcrawl.cards.AbstractCard.CardTags SNEKPROOF;

   public static final String getResourcePath(String resource)
   {
     return "SneckoImages/" + resource;
   }

   public static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(SneckoMod.class.getName());

   public SneckoMod()
   {
     BaseMod.subscribe(this);

     BaseMod.addColor(AbstractCardEnum.SNECKO, SNECKO_COLOR, SNECKO_COLOR, SNECKO_COLOR, SNECKO_COLOR, SNECKO_COLOR, SNECKO_COLOR, SNECKO_COLOR,

       getResourcePath("512/bg_attack_snecko.png"), getResourcePath("512/bg_skill_snecko.png"),
       getResourcePath("512/bg_power_snecko.png"), getResourcePath("512/card_snecko_orb.png"),
       getResourcePath("1024/bg_attack_snecko.png"), getResourcePath("1024/bg_skill_snecko.png"),
       getResourcePath("1024/bg_power_snecko.png"), getResourcePath("1024/card_snecko_orb.png"), getResourcePath("512/card_small_orb.png"));
   }

   public static void initialize() {
     new SneckoMod();
   }

   public void receiveEditCharacters()
   {
     SneckoCharacter = new sneckomod.characters.SneckoCharacter("ThePerplexing", SneckoEnum.SNECKO);
     BaseMod.addCharacter(SneckoCharacter, getResourcePath("charSelect/button.png"), getResourcePath("charSelect/portrait.png"), SneckoEnum.SNECKO);
   }





   public static String printString(String s)
   {
     logger.info(s);
     return s;
   }

   public void receiveEditRelics() {
     BaseMod.addRelicToCustomPool(new sneckomod.relics.SneckoSoulRelic(), AbstractCardEnum.SNECKO);
     BaseMod.addRelicToCustomPool(new sneckomod.relics.SneckoSoulRelicUpgraded(), AbstractCardEnum.SNECKO);
     BaseMod.addRelic(new sneckomod.relics.Free3CostRelic(), RelicType.SHARED);
     BaseMod.addRelic(new sneckomod.relics.ReduceCostRelic(), RelicType.SHARED);
   }




   public void receiveEditCards()
   {
     BaseMod.addCard(new sneckomod.cards.Defend_Snecko());
     BaseMod.addCard(new sneckomod.cards.Strike_Snecko());
     BaseMod.addCard(new sneckomod.cards.SnekBite());
     BaseMod.addCard(new sneckomod.cards.TailWhip());

     BaseMod.addCard(new UnknownCommonAttack());
     BaseMod.addCard(new UnknownCommonSkill());


     BaseMod.addCard(new UnknownUncommonAttack());
     BaseMod.addCard(new sneckomod.cards.UnknownUncommonSkill());
     BaseMod.addCard(new UnknownUncommonPower());

     BaseMod.addCard(new UnknownRareAttack());
     BaseMod.addCard(new UnknownRareSkill());
     BaseMod.addCard(new UnknownRarePower());

     BaseMod.addCard(new sneckomod.cards.SoulCleanse());
     BaseMod.addCard(new sneckomod.cards.SoulExchange());
     BaseMod.addCard(new sneckomod.cards.SoulRoll());
     BaseMod.addCard(new sneckomod.cards.SnekBeam());
     BaseMod.addCard(new sneckomod.cards.MasterEye());
     BaseMod.addCard(new sneckomod.cards.PerplexingGlare());
     BaseMod.addCard(new sneckomod.cards.SingleExchange());
   }








   public void receiveEditKeywords()
   {
     BaseMod.addKeyword(new String[] { "unknown" }, "Soulbound.  Each combat, transforms into a random card from any class, with a randomized cost.");

     BaseMod.addKeyword(new String[] { "snekproof" }, "Cost cannot be randomized.");
   }


   public static AbstractSneckoCard getRandomUnknown(boolean raresOnly)
   {
     AbstractSneckoCard chosenCard;

     if (!raresOnly) {
       int newStr = AbstractDungeon.cardRandomRng.random(7);

     switch (newStr) {
       case 0:
         chosenCard = new UnknownCommonAttack();
         break;
       case 1:
         chosenCard = new UnknownCommonSkill();
         break;
       case 2:
         chosenCard = new UnknownUncommonAttack();
         break;
       case 3:
         chosenCard = new UnknownUncommonSkill();
         break;
       case 4:
         chosenCard = new UnknownUncommonPower();
         break;
       case 5:
         chosenCard = new UnknownRareAttack();
         break;
       case 6:
         chosenCard = new UnknownRareSkill();
         break;
       case 7:
         chosenCard = new UnknownRarePower();
         break;
       default:
         chosenCard = new UnknownCommonAttack();
       }

     }
     else
     {
       int newStr = AbstractDungeon.cardRandomRng.random(2);
       switch (newStr)
       {
       case 0:
         chosenCard = new UnknownRareAttack();
         break;
       case 1:
         chosenCard = new UnknownRareSkill();
         break;
       case 2:
         chosenCard = new UnknownRarePower();
         break;
       default:
         chosenCard = new UnknownRareAttack();
       }

     }


     return chosenCard;
   }

   public void receiveEditStrings()
   {
     String language = "eng";



     logger.info("begin editing strings");
     String relicStrings = Gdx.files.internal("localization/" + language + "/Snecko-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(com.megacrit.cardcrawl.localization.RelicStrings.class, relicStrings);
     String cardStrings = Gdx.files.internal("localization/" + language + "/Snecko-CardStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(com.megacrit.cardcrawl.localization.CardStrings.class, cardStrings);
     String powerStrings = Gdx.files.internal("localization/" + language + "/Snecko-PowerStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(com.megacrit.cardcrawl.localization.PowerStrings.class, powerStrings);
     String charStrings = Gdx.files.internal("localization/" + language + "/Snecko-CharacterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
     BaseMod.loadCustomStrings(com.megacrit.cardcrawl.localization.CharacterStrings.class, charStrings);
     logger.info("done editing strings");
   }


   public void receiveOnBattleStart(AbstractRoom room)
   {
     AbstractDungeon.actionManager.addToBottom(new sneckomod.actions.RandomizeUnknownCards());
   }


   public void receivePostInitialize()
   {
     logger.info("Load Badge Image and mod options");

     Texture badgeTexture = new Texture(getResourcePath("badge.png"));



     this.settingsPanel = new ModPanel();


     BaseMod.registerModBadge(badgeTexture, "Perplexed", "Michael Mayhem", "Adds everyone's favorite Snek as a playable character to the game.", this.settingsPanel);

     logger.info("Done loading badge Image and mod options");
   }
 }


