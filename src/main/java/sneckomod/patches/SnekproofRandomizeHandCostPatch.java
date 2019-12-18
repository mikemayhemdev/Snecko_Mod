package sneckomod.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.random.Random;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import sneckomod.SneckoMod;

import java.util.ArrayList;

import static sneckomod.SneckoMod.SNEKPROOF;

public class SnekproofRandomizeHandCostPatch {
  //Patches the RandomizeHandCostAction to not randomize costs on Snekproof cards
  @SpirePatch(
      clz= RandomizeHandCostAction.class,
      method = "update"
  )
  public static class DoNotRandomizeSnekproofCosts {

    @SpireInsertPatch(
        locator = Locator.class,
        localvars = {"card","newCost"}
    )
    //This method will be inserted after the RandomizeHandAction gets a newCost from the AbstractDungeon.cardRandomRng.random(3)
    public static void Insert (RandomizeHandCostAction __this, AbstractCard card, @ByRef int[] newCost) {//
      if (card.hasTag(SNEKPROOF)) {
        SneckoMod.logger.info("Not randomizing cost of " + card.name + "since it has snekproof.");
        newCost[0]=card.cost;
      }
    }

    // ModTheSpire searches for a nested class that extends SpireInsertLocator
    // This class will be the Locator for the @SpireInsertPatch
    // When a Locator is not specified, ModTheSpire uses the default behavior for the @SpireInsertPatch
    private static class Locator extends SpireInsertLocator {
      // This is the abstract method from SpireInsertLocator that will be used to find the line
      // numbers you want this patch inserted at
      public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
        // finalMatcher is the line that we want to insert our patch before -
        // in this example we are using a `MethodCallMatcher` which is a type
        // of matcher that matches a method call based on the type of the calling
        // object and the name of the method being called. Here you can see that
        // we're expecting the `end` method to be called on a `SpireBatch`
        Matcher finalMatcher = new Matcher.MethodCallMatcher(Random.class, "random");

        // the `new ArrayList<Matcher>()` specifies the prerequisites before the line can be matched -
        // the LineFinder will search for all the prerequisites in order before it will match the finalMatcher -
        // since we didn't specify any prerequisites here, the LineFinder will simply find the first expression
        // that matches the finalMatcher.
        int[] result = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        //we actually want the immediate next line, after the value has been assigned to newCost
        result[0]++;
        return result;
      }
    }

  }
}
