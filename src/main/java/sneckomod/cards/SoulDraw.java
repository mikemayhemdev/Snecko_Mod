 package sneckomod.cards;

 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.common.DiscardAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import sneckomod.SneckoMod;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;


 public class SoulDraw
   extends AbstractSneckoCard
 {
   private static final CardType TYPE = CardType.SKILL;
   private static final CardRarity RARITY = CardRarity.COMMON;
   private static final CardTarget TARGET = CardTarget.SELF;



   public SoulDraw()
   {
     super("Snecko:SoulDraw", NAME, SneckoMod.getResourcePath("cards/souldraw.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);


     this.tags.add(SneckoMod.SNEKPROOF);
     this.magicNumber = this.baseMagicNumber = 3;
   }


   public void use(AbstractPlayer p, AbstractMonster m)
   {
       int cardsToDraw = AbstractDungeon.cardRandomRng.random(1,3);
       int cardsToDiscard = AbstractDungeon.cardRandomRng.random(1,this.magicNumber);

       AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, cardsToDraw));
       AbstractDungeon.actionManager.addToBottom(new WaitAction(.2F));
       AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, cardsToDiscard, true));

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

   public AbstractCard makeCopy() { return new SoulDraw(); }

   public static final String NAME;

   public void upgrade() { if (!this.upgraded) {
       upgradeName();

        upgradeMagicNumber(-1);
     }
   }

   private static final CardStrings cardStrings;
   public static final String DESCRIPTION;
   public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/souldraw.png";
   private static final int COST = 0;


     static
     {
         cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:SoulDraw");
         NAME = cardStrings.NAME;
         DESCRIPTION = cardStrings.DESCRIPTION;
         UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION; }
 }


