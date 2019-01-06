 package sneckomod.cards;

 import basemod.helpers.BaseModCardTags;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.patches.AbstractCardEnum;

 public class Defend_Snecko extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;




   public Defend_Snecko()
   {
     super("Snecko:Defend_Snecko", NAME, SneckoMod.getResourcePath("cards/defendsnecko.png"), 1, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);


     this.baseBlock = 5;
     this.tags.add(BaseModCardTags.BASIC_DEFEND);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
   }

   public AbstractCard makeCopy() {
     return new Defend_Snecko();
   }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     }
   }

     private static final CardStrings cardStrings= CardCrawlGame.languagePack.getCardStrings("Snecko:Defend_Snecko");


   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:Defend_Snecko";
   public static final String IMG_PATH = "cards/defendsnecko.png";
   private static final int COST = 1;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;


 }


