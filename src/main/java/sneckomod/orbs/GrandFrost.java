//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GrandFrost extends Frost {
    public static final String ORB_ID = "SneckoMod:GrandFrost";
    private static final OrbStrings orbString;
    public static final String[] DESC;

    public GrandFrost() {
        this.ID = "SneckoMod:GrandFrost";
        this.showEvokeValue();
        this.updateDescription();
    }

    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0] + this.evokeAmount + DESC[1];
    }

    public void applyFocus() {
        if (AbstractDungeon.player != null) {
            AbstractPower power = AbstractDungeon.player.getPower("Focus");
            if (power != null && !this.ID.equals("Plasma")) {
                this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
                this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount);
            }

        }
    }

    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);

    }

    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.evokeAmount));

        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
    }

    public void onEndOfTurn() {
    }

    public AbstractOrb makeCopy() {
        return new GrandFrost();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("SneckoMod:GrandFrost");
        DESC = orbString.DESCRIPTION;
    }
}
