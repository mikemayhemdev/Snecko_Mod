 package sneckomod.cards;

 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.actions.RandomizeCost;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;









 public class SoulRoll
   extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;





   public SoulRoll()
   {
     super("Snecko:SoulRoll", NAME, SneckoMod.getResourcePath("cards/soulroll.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.tags.add(SneckoMod.SNEKPROOF);
   }


   public void use(AbstractPlayer p, AbstractMonster m)
   {
     if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
     }
     if ((p instanceof SneckoCharacter)) {
       ((SneckoCharacter)p).eyeAnim();
     }


     for (AbstractCard c : p.hand.group) {
       AbstractDungeon.actionManager.addToBottom(new RandomizeCost(c));
     }
   }


   public void triggerWhenDrawn()
   {
     if (this.cost != 0) {
       this.cost = 0;
       this.costForTurn = 0;
       this.isCostModified = false;
     }
   }


   public static final String ID = "Snecko:SoulRoll";
   public static final String NAME;

   public AbstractCard makeCopy() { return new SoulRoll(); }

   private static final CardStrings cardStrings;

   public void upgrade() { if (!this.upgraded) {
       upgradeName();


       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }

   public static final String DESCRIPTION;
   public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/soulroll.png";
   private static final int COST = 0;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

     static
     {
         cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:SoulRoll");
         NAME = cardStrings.NAME;
         DESCRIPTION = cardStrings.DESCRIPTION;
         UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION; }
 }


