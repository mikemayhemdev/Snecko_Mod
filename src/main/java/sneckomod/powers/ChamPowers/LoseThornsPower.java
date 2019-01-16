//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.powers.ChamPowers;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sneckomod.SneckoMod;

public class LoseThornsPower extends AbstractPower {
    public static final String POWER_ID = "Snecko:LoseThornsPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private DamageInfo thornsInfo;
    private boolean naturalclear = false;
    public static final String IMG = "powers/ThornsDownS.png";


    public LoseThornsPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Snecko:LoseThornsPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath(IMG));
    }

    public void stackPower(int stackAmount) {
        if (this.amount == -1) {
        } else {
            this.fontScale = 8.0F;
            this.amount += stackAmount;
            this.updateDescription();
        }
    }

    public void atStartOfTurn() {

        flash();

        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, -this.amount), -this.amount));


        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, LoseThornsPower.POWER_ID));
        this.naturalclear = true;

    }


    public void onRemove() {
        if (naturalclear) {
            if (this.owner.getPower("Thorns").amount <= 0) {
                AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, "Thorns"));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Snecko:LoseThornsPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
