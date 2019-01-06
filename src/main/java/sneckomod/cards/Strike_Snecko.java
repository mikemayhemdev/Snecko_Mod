 package sneckomod.cards;

 import basemod.helpers.BaseModCardTags;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import java.util.ArrayList;
 import sneckomod.patches.AbstractCardEnum;

 public class Strike_Snecko extends AbstractSneckoCard
 {
   private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
   private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.BASIC;
   private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;






   public Strike_Snecko()
   {
     super("Snecko:Strike_Snecko", NAME, sneckomod.SneckoMod.getResourcePath("cards/strikesnecko.png"), 1, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);

     this.baseDamage = 6;
     this.tags.add(BaseModCardTags.BASIC_STRIKE);
     this.tags.add(AbstractCard.CardTags.STRIKE);
   }

   public void use(AbstractPlayer p, AbstractMonster m) {
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_LIGHT));
   }

   public AbstractCard makeCopy()
   {
     return new Strike_Snecko();
   }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     }
   }


   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Snecko:Strike_Snecko");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
   public static final String ID = "Snecko:Strike_Snecko";
   public static final String IMG_PATH = "cards/strikesnecko.png";
   private static final int COST = 1;
   private static final int POWER = 6;
   private static final int UPGRADE_BONUS = 3;
 }


