 package sneckomod.characters;

 import basemod.abstracts.CustomPlayer;
 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.g2d.BitmapFont;
 import com.badlogic.gdx.math.MathUtils;
 import com.esotericsoftware.spine.AnimationState;
 import com.esotericsoftware.spine.AnimationState.TrackEntry;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
 import com.megacrit.cardcrawl.actions.GameActionManager;
 import com.megacrit.cardcrawl.audio.SoundMaster;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.EnergyManager;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.FontHelper;
 import com.megacrit.cardcrawl.helpers.Hitbox;
 import com.megacrit.cardcrawl.helpers.ScreenShake;
 import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
 import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
 import com.megacrit.cardcrawl.screens.CharSelectInfo;
 import com.megacrit.cardcrawl.unlock.UnlockTracker;
 import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
 import java.util.ArrayList;
 import sneckomod.patches.AbstractCardEnum;
 import sneckomod.patches.SneckoEnum;

 public class SneckoCharacter extends CustomPlayer
 {
   public static Color cardRenderColor = new Color(0.7F, 0.5F, 0.7F, 1.0F);







   public static final String[] orbTextures = { "SneckoImages/char/orb/layer1.png", "SneckoImages/char/orb/layer2.png", "SneckoImages/char/orb/layer3.png", "SneckoImages/char/orb/layer4.png", "SneckoImages/char/orb/layer5.png", "SneckoImages/char/orb/layer6.png", "SneckoImages/char/orb/layer1d.png", "SneckoImages/char/orb/layer2d.png", "SneckoImages/char/orb/layer3d.png", "SneckoImages/char/orb/layer4d.png", "SneckoImages/char/orb/layer5d.png" };


   public SneckoCharacter(String name, AbstractPlayer.PlayerClass setClass)
   {
     super(name, setClass, orbTextures, "SneckoImages/char/orb/vfx.png", (String)null, (String)null);


     initializeClass(null, "SneckoImages/char/shoulder2.png", "SneckoImages/char/shoulder.png", "SneckoImages/char/corpse.png", getLoadout(), 0.0F, 0.0F, 300.0F, 180.0F, new EnergyManager(3));
     reloadAnimation();
   }






















   public void damage(DamageInfo info)
   {
     super.damage(info);
     if ((info.owner != null) && (info.type != DamageInfo.DamageType.THORNS) && (info.output > 0)) {
       this.state.setAnimation(0, "Hit", false);
       this.state.addAnimation(0, "Idle", true, 0.0F);
     }
   }

   public void eyeAnim()
   {
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.utility.SFXAction("MONSTER_SNECKO_GLARE"));
     AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.VFXAction(this, new IntimidateEffect(this.hb.cX, this.hb.cY), 0.5F));
     this.state.setAnimation(0, "Attack", false);
     this.state.addAnimation(0, "Idle", true, 0.0F);
   }

   public void biteAnim() {
     this.state.setAnimation(0, "Attack_2", false);
     this.state.addAnimation(0, "Idle", true, 0.0F);
   }

   public void reloadAnimation() {
     loadAnimation("SneckoImages/char/skeleton.atlas", "SneckoImages/char/skeleton.json", 1.2F);
     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
     e.setTime(e.getEndTime() * MathUtils.random());
     this.stateData.setMix("Hit", "Idle", 0.1F);
     e.setTimeScale(0.8F);
   }

   public ArrayList<String> getStartingDeck()
   {
     ArrayList<String> retVal = new ArrayList();
     retVal.add("Snecko:Strike_Snecko");
     retVal.add("Snecko:Strike_Snecko");
     retVal.add("Snecko:Strike_Snecko");
     retVal.add("Snecko:Strike_Snecko");
     retVal.add("Snecko:Defend_Snecko");
     retVal.add("Snecko:Defend_Snecko");
     retVal.add("Snecko:Defend_Snecko");
     retVal.add("Snecko:Defend_Snecko");
     retVal.add("Snecko:TailWhip");
     retVal.add("Snecko:SnekBite");
     return retVal;
   }

   public ArrayList<String> getStartingRelics() {
     ArrayList<String> retVal = new ArrayList();
     retVal.add("Snecko:SneckoSoulRelic");
     retVal.add("Snecko Eye");

     UnlockTracker.markRelicAsSeen("Snecko Eye");
     UnlockTracker.markRelicAsSeen("Snecko:SneckoSoulRelic");

     return retVal;
   }


   public CharSelectInfo getLoadout()
   {
     return new CharSelectInfo(NAME, DESCRIPTION, 50, 50, 1, 99, 5, this,

       getStartingRelics(), getStartingDeck(), false);
   }

   public String getTitle(AbstractPlayer.PlayerClass playerClass) {
     return NAME;
   }

   public com.megacrit.cardcrawl.cards.AbstractCard.CardColor getCardColor() {
     return AbstractCardEnum.SNECKO;
   }

   public Color getCardRenderColor()
   {
     return cardRenderColor;
   }

   public AbstractCard getStartCardForEvent()
   {
     return new sneckomod.cards.Strike_Snecko();
   }

   public Color getCardTrailColor() {
     return cardRenderColor.cpy();
   }

   public int getAscensionMaxHPLoss() {
     return 10;
   }

   public BitmapFont getEnergyNumFont() {
     return FontHelper.energyNumFontBlue;
   }

   public void doCharSelectScreenSelectEffect() {
     CardCrawlGame.sound.playA("MONSTER_SNECKO_GLARE", MathUtils.random(-0.1F, 0.1F));
     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
   }

   public String getCustomModeCharacterButtonSoundKey() {
     return "MONSTER_SNECKO_GLARE";
   }

   public String getLocalizedCharacterName() {
     return NAME;
   }

   public com.megacrit.cardcrawl.characters.AbstractPlayer newInstance() {
     return new SneckoCharacter(NAME, SneckoEnum.SNECKO);
   }

   public String getSpireHeartText() {
     return "???????";
   }

   public Color getSlashAttackColor() {
     return Color.GREEN;
   }

   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SMASH, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
   }

   public String getVampireText()
   {
     return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[5];
   }



   private static final com.megacrit.cardcrawl.localization.CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("Snecko");
   public static final String NAME = charStrings.NAMES[0];
   public static final String DESCRIPTION = charStrings.TEXT[0];
 }


