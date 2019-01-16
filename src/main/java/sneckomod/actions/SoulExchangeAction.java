 package sneckomod.actions;

 import chronomuncher.cards.AbstractSelfSwitchCard;
 import chronomuncher.cards.AbstractSwitchCard;
 import com.badlogic.gdx.graphics.Color;
 import com.evacipated.cardcrawl.modthespire.Loader;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.cards.colorless.Madness;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.localization.LocalizedStrings;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.random.Random;
 import com.megacrit.cardcrawl.screens.select.HandCardSelectScreen;

 import java.util.*;
 import java.util.Map.Entry;

 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import sneckomod.SneckoMod;






 public class SoulExchangeAction
   extends AbstractGameAction
 {
   public SoulExchangeAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero)
   {
     this.canPickZero = false;
     this.anyNumber = anyNumber;
     this.canPickZero = canPickZero;
     this.p = ((AbstractPlayer)target);
     this.isRandom = isRandom;
     setValues(target, source, amount);
     this.duration = Settings.ACTION_DUR_FAST;
     this.actionType = AbstractGameAction.ActionType.EXHAUST;
   }

   public void update() {
     if (this.duration == Settings.ACTION_DUR_FAST) {
       if (this.p.hand.size() == 0) {
         this.isDone = true;
         return;
       }


       if ((!this.anyNumber) && (this.p.hand.size() <= this.amount)) {
         this.amount = this.p.hand.size();
         numExhausted = this.amount;
         int i2 = this.p.hand.size();

         for (int i = 0; i < i2; i++) {
           AbstractCard c = this.p.hand.getTopCard();
           this.p.hand.moveToExhaustPile(c);
           dissolveEffect(c);
         }


         CardCrawlGame.dungeon.checkForPactAchievement();
         return;
       }

       if (!this.isRandom) {
         numExhausted = this.amount;
         AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, this.anyNumber, this.canPickZero);
         tickDuration();
         return;
       }

       for (int i = 0; i < this.amount; i++) {
         AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
         this.p.hand.moveToExhaustPile(c);
         dissolveEffect(c);
       }


       CardCrawlGame.dungeon.checkForPactAchievement();
     }

     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
       Iterator var4 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator();

       while (var4.hasNext()) {
         AbstractCard c = (AbstractCard)var4.next();
         this.p.hand.moveToExhaustPile(c);
         dissolveEffect(c);
       }

       CardCrawlGame.dungeon.checkForPactAchievement();
       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
     }

     tickDuration();
   }

   public void dissolveEffect(AbstractCard c2)
   {
     int cardsToReplace = 0;

     for (AbstractCard c : this.p.hand.group) {
       AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(c, this.p.hand));
       cardsToReplace++;
     }

     AbstractCard.CardColor classColor = c2.color;



     ArrayList<String> tmp = new ArrayList();
     Iterator var3 = CardLibrary.cards.entrySet().iterator();


       boolean validCard = true;

       while (var3.hasNext()) {
           Map.Entry<String, AbstractCard> c = (Map.Entry)var3.next();
             if (c.getValue().color == classColor && c.getValue().rarity != AbstractCard.CardRarity.SPECIAL) {

               if (Loader.isModLoaded("Yohane")){
                   //SneckoMod.logger.info("Detected Yohane Mod when trying to randomize");
                   if (c.getValue().cardID.contains("Yohane")){
                       // SneckoMod.logger.info("Skipping Yohane Card");
                       validCard = false;
                   }
               }

               //Disciple Switch cards break.  Blacklisting.
                 /*
               if (Loader.isModLoaded("chronomuncher")){
                   if (c instanceof AbstractSwitchCard){
                       validCard = false;
                   }
                   if (c instanceof AbstractSelfSwitchCard){
                       validCard = false;
                   }
               }
               */



               if (validCard) tmp.add(c.getKey());



           }
       }


     for (int i = 0; i < cardsToReplace; i++) {
         AbstractCard cNew;

       if (tmp.size() > 0) {
         cNew = CardLibrary.cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1)));
       } else {
         cNew = new Madness();
       }

       if ((cNew.cost >= 0) && (!cNew.hasTag(SneckoMod.SNEKPROOF))) {
         int newCost = AbstractDungeon.cardRandomRng.random(3);
         if (cNew.cost != newCost) {
           cNew.cost = newCost;
           cNew.costForTurn = cNew.cost;
           cNew.isCostModified = true;
           cNew.superFlash(Color.PURPLE.cpy());
         }
       }
         UnlockTracker.markCardAsSeen(cNew.cardID);

       AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.getCopy(cNew.cardID)));
     }
   }











   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
   public static final String[] TEXT = uiStrings.TEXT;
   private AbstractPlayer p;
   private boolean isRandom;
   private boolean anyNumber;
   private boolean canPickZero;
   private int block;
   private int extraCards;
   public static int numExhausted;
 }


