 package sneckomod.cards;

 import com.badlogic.gdx.graphics.Color;
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
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;



 public class SoulCleanse
   extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;



   public SoulCleanse()
   {
     super("Snecko:SoulCleanse", NAME, SneckoMod.getResourcePath("cards/soulcleanse.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);


     this.tags.add(SneckoMod.SNEKPROOF);
   }


   public void use(AbstractPlayer p, AbstractMonster m)
   {
     if ((p instanceof SneckoCharacter)) {
       ((SneckoCharacter)p).eyeAnim();
     }


     for (AbstractCard c : p.hand.group) {
       if (c.cost >= 3) {
         c.cost = 0;
         c.costForTurn = c.cost;
         c.isCostModified = true;
         c.superFlash(Color.PURPLE.cpy());
         break;
       }
     }
       if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
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




   public static final String ID = "Snecko:SoulCleanse";

   public AbstractCard makeCopy() { return new SoulCleanse(); }

   public static final String NAME;

   public void upgrade() { if (!this.upgraded) {
       upgradeName();


       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }

   private static final CardStrings cardStrings;
   public static final String DESCRIPTION;
   public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/soulcleanse.png";
   private static final int COST = 0;


     static
     {
         cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:SoulCleanse");
         NAME = cardStrings.NAME;
         DESCRIPTION = cardStrings.DESCRIPTION;
         UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION; }
 }


