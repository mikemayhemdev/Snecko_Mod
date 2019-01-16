//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.powers.ChamPowers;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import sneckomod.SneckoMod;

public class LoseTempHPPower extends AbstractPower {
    public static final String POWER_ID = "Snecko:LoseTempHPPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private DamageInfo thornsInfo;
    private boolean naturalclear = false;
    public static final String IMG = "powers/tempTempHP.png";


    public LoseTempHPPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Snecko:LoseTempHPPower";
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

        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(this.owner,this.owner,this.amount * -1));


        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, LoseTempHPPower.POWER_ID));
        this.naturalclear = true;

    }




    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Snecko:LoseTempHPPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
