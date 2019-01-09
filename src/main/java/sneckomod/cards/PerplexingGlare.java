 package sneckomod.cards;

 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
 import com.megacrit.cardcrawl.monsters.MonsterGroup;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.random.Random;
 import java.util.ArrayList;
 import sneckomod.SneckoMod;
 import sneckomod.characters.SneckoCharacter;
 import sneckomod.patches.AbstractCardEnum;

 public class PerplexingGlare extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ALL_ENEMY;







   public PerplexingGlare()
   {
     super("Snecko:PerplexingGlare", NAME, SneckoMod.getResourcePath("cards/perplexingstare.png"), 0, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.magicNumber = (this.baseMagicNumber = 2);
     this.tags.add(SneckoMod.SNEKPROOF);
     this.exhaust = true;
   }


   public void triggerWhenDrawn()
   {
     if (this.cost != 0) {
       this.cost = 0;
       this.costForTurn = 0;
       this.isCostModified = false;
     }
   }

   public void use(AbstractPlayer p, AbstractMonster m)
   {
     if ((p instanceof SneckoCharacter)) {
       ((SneckoCharacter)p).eyeAnim();
     }

     if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
       for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
         if ((!monster.isDead) && (!monster.isDying))
         {
           int newStr = AbstractDungeon.cardRandomRng.random(-3, this.magicNumber);
           if (newStr == 0){
               if (AbstractDungeon.cardRandomRng.randomBoolean()){
                   newStr++;
               } else {
                   newStr--;
               }
           }

           AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new StrengthPower(monster, newStr), newStr, true, com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.NONE));
         }
       }
     }
   }







   public AbstractCard makeCopy()
   {
     return new PerplexingGlare();
   }



   public void upgrade()
   {
     if (!this.upgraded)
     {
       upgradeName();


       upgradeMagicNumber(-1);
     }
   }




   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:PerplexingGlare");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:PerplexingGlare";
   public static final String IMG_PATH = "cards/perplexingstare.png";
   private static final int COST = 0;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


