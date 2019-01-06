 package sneckomod.cards;

 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.vfx.GreenBeamEffect;

 public class SnekBeam extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;







   public SnekBeam()
   {
     super("Snecko:SnekBeam", NAME, SneckoMod.getResourcePath("cards/snekbeam.png"), 2, DESCRIPTION, TYPE, AbstractCard.CardColor.COLORLESS, RARITY, TARGET);

     this.baseDamage = 0;

     this.isMultiDamage = true;

     this.tags.add(SneckoMod.SNEKPROOF);
   }


   public void triggerWhenDrawn()
   {
     if (this.upgraded) {
       if (this.cost != 1) this.cost = 1; this.costForTurn = 1;
       this.isCostModified = false;
     } else {
       if (this.cost != 2) this.cost = 2; this.costForTurn = 2;
       this.isCostModified = false;
     }
   }



   public static int countCards()
   {
     int count = 0;
     for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
       if (c.hasTag(SneckoMod.UNKNOWN)) {
         count++;
       }
     }

     return count;
   }

   public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster mo, float tmp) {
     int bonus = 0;
     int cards = countCards();
     bonus = cards;
     return tmp + bonus;
   }


   public void use(AbstractPlayer p, AbstractMonster m)
   {
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("ATTACK_HEAVY"));
     AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new GreenBeamEffect(p.hb.cX, p.hb.cY, false), 0.1F));
     AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));
     AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.NONE));
   }




   public AbstractCard makeCopy()
   {
     return new SnekBeam();
   }



   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();
       upgradeBaseCost(1);
     }
   }





   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:SnekBeam");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:SnekBeam";
   public static final String IMG_PATH = "cards/snekbeam.png";
   private static final int COST = 2;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


