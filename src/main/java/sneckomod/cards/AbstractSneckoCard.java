 package sneckomod.cards;

 import basemod.abstracts.CustomCard;
 import chronomuncher.cards.AbstractSelfSwitchCard;
 import chronomuncher.cards.AbstractSwitchCard;
 import com.evacipated.cardcrawl.modthespire.Loader;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
 import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
 import com.megacrit.cardcrawl.cards.CardGroup;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.CardLibrary;
 import com.megacrit.cardcrawl.helpers.ModHelper;
 import com.megacrit.cardcrawl.random.Random;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.Map;
 import java.util.Map.Entry;
 import org.apache.logging.log4j.Logger;
 import sneckomod.SneckoMod;
 import yohanemod.YohaneMod;
 import yohanemod.patches.AbstractCardEnum;
 import yohanemod.patches.YohaneEnum;

 public abstract class AbstractSneckoCard extends CustomCard
 {

    public boolean used = false;
   public AbstractSneckoCard(String id, String name, String img, int cost, String rawDescription, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target)
   {
     super(id, name, img, cost, rawDescription, type, color, rarity, target);

   }

     @Override
     public void onMoveToDiscard() {
         super.onMoveToDiscard();
         if (this.hasTag(SneckoMod.UNKNOWN) && this.used){
             AbstractDungeon.player.discardPile.removeCard(this);

         }
     }



   public void replaceUnknown() {
     replaceUnknown(false);
   }

   public void replaceUnknown(boolean toHand) {
     AbstractPlayer p = AbstractDungeon.player;


     Boolean upgraded = Boolean.valueOf(this.upgraded);

     p.hand.removeCard(this);

     ArrayList<String> tmp = new ArrayList();
     Iterator var3 = CardLibrary.cards.entrySet().iterator();
     boolean validCard;

     while (var3.hasNext()) {
         validCard = true;
       Map.Entry<String, AbstractCard> c = (Map.Entry)var3.next();
       if ((((AbstractCard)c.getValue()).rarity == this.rarity) && (((AbstractCard)c.getValue()).type == this.type) && (!((AbstractCard)c.getValue()).hasTag(SneckoMod.UNKNOWN))) {

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
     SneckoMod.logger.info("Attempting to create new Unknown: " + this.name);

     AbstractCard cUnknown; if (tmp.size() > 0) {
       cUnknown = CardLibrary.cards.get(tmp.get(AbstractDungeon.cardRng.random(0, tmp.size() - 1))).makeStatEquivalentCopy();
     }
     else {
       cUnknown = new com.megacrit.cardcrawl.cards.colorless.Madness();
       SneckoMod.logger.info("Madness! Found no card for " + this.rarity + " " + this.type);
     }

     if (this.upgraded) cUnknown.upgrade();
     if (cUnknown != null) {




       if ((cUnknown.cost >= 0) && (!cUnknown.hasTag(SneckoMod.SNEKPROOF)) && cUnknown.rarity != CardRarity.BASIC && cUnknown.rarity != CardRarity.SPECIAL) {
           int newCost = AbstractDungeon.cardRandomRng.random(3);
         if (cUnknown.cost != newCost) {
           cUnknown.cost = newCost;
           cUnknown.costForTurn = cUnknown.cost;
           cUnknown.isCostModified = true;
         }
       }

       UnlockTracker.markCardAsSeen(cUnknown.cardID);

       if (!toHand) {
         p.drawPile.removeCard(this);
         AbstractDungeon.player.drawPile.addToRandomSpot(cUnknown);
       } else {

           AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(cUnknown,1, true));
       }
     }
   }
 }


