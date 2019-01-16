//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.powers.ChamPowers;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.*;
import sneckomod.characters.SneckoCharacter;
import sneckomod.orbs.*;

public class ChameleonPower extends AbstractPower {
    public static final String POWER_ID = "Snecko:ChameleonPower";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    private DamageInfo thornsInfo;
    private int currentColor;
    private int nextColor;
    private int currentAmountMod;
    private int nextAmountMod;
    private int[] amounts = {3,3,1,6,10,2,2};
    private Color[] colors = {Color.RED,Color.GREEN,Color.YELLOW,new Color(0.3137255F, 0.19607843F, 0.078431375F, 1.0F),new Color(1.5F, 1.5F, 1.5F, 1.0F),Color.CYAN,Color.DARK_GRAY};
    private Color powerColor;
    private boolean initialApplication = true;

    public ChameleonPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Snecko:ChameleonPower";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("blur");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();

        this.nextColor = AbstractDungeon.cardRandomRng.random(6);
        this.nextAmountMod = amounts[nextColor];

        this.updateDescription();

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
        this.flash();
        this.currentColor = this.nextColor;
        this.currentAmountMod = this.nextAmountMod;
        powerColor = colors[currentColor];
        ReflectionHacks.setPrivate(this, AbstractPower.class, "color", powerColor);
        this.owner.tint.changeColor(powerColor.cpy());

        switch (currentColor) {
            case 0:
                //RED
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseStrengthPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));

                break;
            case 1:
                //GREEN
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseDexterityPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                break;
            case 2:
                //YELLOW
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseEnvenomPower(this.owner, this.owner,this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                break;
            case 3:
                //BRONZE
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseThornsPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                break;
            case 4:
                //PALE
                AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(this.owner,this.owner,this.amount * this.currentAmountMod));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new LoseTempHPPower(this.owner, this.amount * this.currentAmountMod), this.amount * this.currentAmountMod));
                break;
            case 5:
                //BLUE
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new VulnWeakOnHitPower(this.owner,this.owner,this.amount * this.currentAmountMod)));
                break;
            case 6:
                //DARK
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DarkPower(this.owner,this.amount * this.currentAmountMod)));
                break;

        }

        this.nextColor = AbstractDungeon.cardRandomRng.random(6);
        while (this.nextColor == this.currentColor){
            this.nextColor = AbstractDungeon.cardRandomRng.random(6);
        }
        this.nextAmountMod = amounts[nextColor];
        this.initialApplication = false;
        updateDescription();
    }

    public void updateDescription() {

        if (initialApplication) {
            this.description = DESCRIPTIONS[17] + DESCRIPTIONS[16]
                    + DESCRIPTIONS[1 + nextColor] +
                    DESCRIPTIONS[8] + this.amount * this.nextAmountMod + DESCRIPTIONS[9 + nextColor];
        } else {
            this.description = DESCRIPTIONS[17] + DESCRIPTIONS[0]
                    + DESCRIPTIONS[1 + currentColor] +
                    DESCRIPTIONS[8] + this.amount * this.currentAmountMod + DESCRIPTIONS[9 + currentColor] +
                    " NL " +
                    DESCRIPTIONS[16]
                    + DESCRIPTIONS[1 + nextColor] +
                    DESCRIPTIONS[8] + this.amount * this.nextAmountMod + DESCRIPTIONS[9 + nextColor];
        }
    }

    @Override
    public void onVictory() {
        super.onVictory();
        this.owner.tint.changeColor(Color.WHITE.cpy());

    }

    @Override
    public void onRemove() {
        super.onRemove();
        this.owner.tint.changeColor(Color.WHITE.cpy());
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Snecko:ChameleonPower");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
