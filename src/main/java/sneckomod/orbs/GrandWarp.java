//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package sneckomod.orbs;

import basemod.BaseMod;
import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
import sneckomod.SneckoMod;
import sneckomod.actions.QueueCardFrontAction;
import sneckomod.actions.WarpPlayAction;

import java.util.ArrayList;

public class GrandWarp extends AbstractOrb {
    public static final String ORB_ID = "SneckoMod:GrandWarp";
    private static final OrbStrings orbStrings;
    public static final String[] DESC;
    private float vfxTimer;

    public static ArrayList<AbstractCard> cards;
    public static AbstractCard nextCard;
    public boolean isFinishedThisTurn = false;
    public int maxAmount;

    private float previous_x = -1;
    private float previous_y;

    public GrandWarp() {
        this.vfxTimer = 0.5F;
        this.ID = "SneckoMod:GrandWarp";
        this.img = ImageMaster.loadImage(SneckoMod.getResourcePath("orbs/warp.png"));
        this.name = orbStrings.NAME;
        this.baseEvokeAmount = 1;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 0;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.channelAnimTimer = this.vfxTimer;
    }

    public void updateDescription() {
        this.applyFocus();
        if (this.evokeAmount == 1){
            this.description = DESC[0];
        } else {
            this.description = DESC[1] + this.evokeAmount + DESC[2];
        }
    }

    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower("Focus");
        if (power != null && !this.ID.equals("Plasma")) {
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
            this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount);
        }

    }
    public void onEvoke() {
        if (cards == null){
            cards = CardLibrary.getAllCards();
        }
        AbstractCreature m = AbstractDungeon.getRandomMonster();
        if (m != null) {
            playCardEffect(0);
        }
    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
    }


    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 120.0F;
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
            this.vfxTimer = 0.25F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        this.renderText(sb);
        this.hb.render(sb);
    }



    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
    }


    public void playCardEffect(int queueIndex){

        if (this.evokeAmount-- <= 0){
            SneckoMod.logger.info("Grand Warp chain finished");
            this.evokeAmount = this.maxAmount;
            isFinishedThisTurn = true;
            return;
        }

        do{
            nextCard = cards.get(AbstractDungeon.cardRandomRng.random(0, cards.size() - 1)).makeCopy();
        } while ((nextCard.rarity == AbstractCard.CardRarity.SPECIAL && nextCard.color.toString() != "INFINITE_BLACK") ||
                nextCard.rarity == AbstractCard.CardRarity.CURSE ||
                nextCard.type != AbstractCard.CardType.ATTACK ||
                nextCard.cost == -2 ||
                // Yohane's summons require a special FriendlyMinions-enabled character, which Beaked is not.
                nextCard.cardID.startsWith("Yohane:Little_Demon_") ||
                // Mad Scientist's Mechanize apparently doesn't work
                nextCard.cardID == "MadScienceMod:Mechanize" ||
                // Pickle why is this ID not public, I'm far too lazy to use reflection
                nextCard.cardID == "ReplayTheSpireMod:??????????????????????" ||
                // blakkmod
                nextCard.cardID == "BlakkBlade" ||
                // blakkmod
                nextCard.cardID == "LegSlice");

        SneckoMod.logger.info("Grand Warp next card starting");

        nextCard.purgeOnUse = true;
        nextCard.freeToPlayOnce = true;
        AbstractDungeon.player.limbo.addToTop(nextCard);
        //nextCard.targetDrawScale = nextCard.targetDrawScale*1.4f;
        if (AbstractDungeon.player.hasRelic("Quantum Egg")) nextCard.upgrade();
        AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
        SneckoMod.logger.info("Grand Warp playing " + nextCard.name);
        // Typically, cards are added to the front of the queue so they can't be interrupted by player actions.
        //if (m != null) {
        nextCard.calculateCardDamage(targetMonster);
        if (this.previous_x == -1){
            nextCard.target_x = (Settings.WIDTH / 2.0F);
        } else{
            nextCard.target_x = this.previous_x - 200.0F * Settings.scale;

        }
        nextCard.current_x = (Settings.WIDTH / 2.0F);

        nextCard.current_y = (Settings.HEIGHT / 2.0F);
        nextCard.target_y = (Settings.HEIGHT / 2.0F);
        this.previous_x = nextCard.target_x;

        AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(nextCard, targetMonster));
        // Wait action can't wait for more than 0.1s on fast mode, so just add a bunch of them
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1f));
        // If a card can't be used, it doesn't trigger onAfterUseCard, which would break the chain.
        // Instead, after the typical wait time, we add the NEXT card to the queue early, placing it after the current unusable card.
            AbstractDungeon.actionManager.addToBottom(new WarpPlayAction(this));

    }


    public AbstractOrb makeCopy() {
        return new GrandWarp();
    }

    public void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);

    }
    static {
        orbStrings = CardCrawlGame.languagePack.getOrbString("SneckoMod:GrandWarp");
        DESC = orbStrings.DESCRIPTION;
    }
}
