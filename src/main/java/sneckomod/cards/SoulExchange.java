 package sneckomod.cards;

 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
 import sneckomod.actions.SoulExchangeAction;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;



 public class SoulExchange
   extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.RARE;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;





   public SoulExchange()
   {
     super("Snecko:SoulExchange", NAME, SneckoMod.getResourcePath("cards/soulexchange.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.tags.add(SneckoMod.SNEKPROOF);
   }



   public void use(AbstractPlayer p, AbstractMonster m)
   {
     if ((p instanceof SneckoCharacter)) {
       ((SneckoCharacter)p).eyeAnim();
     }

     AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
     if (this.upgraded) { AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
     }
     AbstractDungeon.actionManager.addToBottom(new SoulExchangeAction(p, p, 1, false, false, false));
   }

   public void triggerWhenDrawn()
   {
     if (this.cost != 0) {
       this.cost = 0;
       this.costForTurn = 0;
       this.isCostModified = false;
     }
   }


   public static final String ID = "Snecko:SoulExchange";
   public static final String NAME;

   public AbstractCard makeCopy() { return new SoulExchange(); }

   private static final CardStrings cardStrings;

   public void upgrade() { if (!this.upgraded) {
       upgradeName();


       this.rawDescription = UPGRADED_DESCRIPTION;
       initializeDescription();
     }
   }

   public static final String DESCRIPTION;
   public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/soulexchange.png";
   private static final int COST = 0;
   private static final int BLOCK = 5;
   private static final int UPGRADE_BONUS = 3;

     static
     {
         cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:SoulExchange");
         NAME = cardStrings.NAME;
         DESCRIPTION = cardStrings.DESCRIPTION;
         UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION; }
 }


