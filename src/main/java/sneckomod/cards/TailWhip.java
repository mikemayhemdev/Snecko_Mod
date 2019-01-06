 package sneckomod.cards;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import sneckomod.SneckoMod;
 import sneckomod.patches.AbstractCardEnum;


 public class TailWhip
   extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;





   public TailWhip()
   {
     super("Snecko:TailWhip", NAME, SneckoMod.getResourcePath("cards/tailwhip.png"), 2, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.baseDamage = 10;
     this.magicNumber = (this.baseMagicNumber = 2);
   }



   public void use(AbstractPlayer p, AbstractMonster m)
   {
     AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
     AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
   }



   public AbstractCard makeCopy()
   {
     return new TailWhip();
   }



   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();


       upgradeDamage(3);
     }
   }




   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:TailWhip");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String ID = "Snecko:TailWhip";
   public static String UPGRADED_DESCRIPTION;
   public static final String IMG_PATH = "cards/tailwhip.png";
   private static final int COST = 2;
 }


