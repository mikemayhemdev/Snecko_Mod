//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.orbs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.hubris.actions.monsterOrbs.MonsterLightningOrbEvokeAction;

import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GrandLightning extends Lightning {
    public static final String ORB_ID = "SneckoMod:GrandLightning";
    private static final OrbStrings orbString;
    public static final String[] DESC;

    public GrandLightning() {
        this.ID = "SneckoMod:GrandLightning";
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

    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new LightningOrbEvokeAction( new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageType.THORNS),false));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
    }

    public void onEndOfTurn() {
    }
    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);

    }
    public AbstractOrb makeCopy() {
        return new GrandLightning();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("SneckoMod:GrandLightning");
        DESC = orbString.DESCRIPTION;
    }
}
