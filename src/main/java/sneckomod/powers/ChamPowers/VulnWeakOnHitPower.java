package sneckomod.powers.ChamPowers;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sneckomod.SneckoMod;


public class VulnWeakOnHitPower extends AbstractPower {
    public static final String POWER_ID = "Snecko:VulnWeakOnHitPower";

    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/LivingWallS.png";
    public boolean doubleUp = false;
    public static String[] DESCRIPTIONS;
    private AbstractCreature source;
    public boolean triggered = false;


    public VulnWeakOnHitPower(AbstractCreature owner, AbstractCreature source, int amount) {



        this.ID = POWER_ID;
        this.amount = amount;

        this.owner = owner;

        this.source = source;


        this.img = new com.badlogic.gdx.graphics.Texture(SneckoMod.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();


    }


    public void updateDescription() {

        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];


    }



    public void atStartOfTurn() {


        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, this.POWER_ID));


    }


    public int onAttacked(DamageInfo info, int damageAmount) {

        if (info.type == DamageInfo.DamageType.NORMAL && info.owner != AbstractDungeon.player) {
            flash();

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, this.owner, new VulnerablePower(this.owner,this.amount,false)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, this.owner, new WeakPower(this.owner,this.amount,false)));

        }



        return super.onAttacked(info, damageAmount);
    }


}





