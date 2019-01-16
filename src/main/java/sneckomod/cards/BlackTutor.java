package sneckomod.cards;



import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import infinitespire.abstracts.BlackCard;
import infinitespire.helpers.CardHelper;
import sneckomod.SneckoMod;
import sneckomod.patches.AbstractCardEnum;

import java.util.*;


public class BlackTutor extends AbstractSneckoCard {
    public static final String ID = "Snecko:BlackTutor";
    public static final String NAME;
    private static final CardStrings cardStrings;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/blackcard.png";

    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = 0;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BONUS = 3;

    public BlackTutor() {

        super(ID, NAME, SneckoMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.SNECKO, RARITY, TARGET);


        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 5;


    }

    public void triggerWhenDrawn()
    {
        if (this.cost != 0) {
            this.cost = 0;
            this.costForTurn = 0;
            this.isCostModified = false;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));

        ArrayList<String> tmpStatus = new ArrayList();
        ArrayList<String> tmpCurse = new ArrayList();
        Iterator var3 = CardLibrary.cards.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, AbstractCard> c = (Map.Entry) var3.next();
            if (c.getValue().type == CardType.STATUS) {
                tmpStatus.add(c.getKey());
                SneckoMod.logger.info("adding card to Black pool: " + c.getKey());
            }
            if (c.getValue().type == CardType.CURSE) {
                tmpCurse.add(c.getKey());
                SneckoMod.logger.info("adding card to Black pool: " + c.getKey());

            }

        }

        int randomizer = AbstractDungeon.cardRandomRng.random(2);

        switch (randomizer) {
            case 0:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.cards.get(tmpStatus.get(AbstractDungeon.cardRng.random(0, tmpStatus.size() - 1)))));
                if (upgraded){
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.cards.get(tmpStatus.get(AbstractDungeon.cardRng.random(0, tmpStatus.size() - 1)))));
                }
                break;
            case 1:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardHelper.getRandomBlackCard().makeStatEquivalentCopy()));
                if (upgraded){
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardHelper.getRandomBlackCard().makeStatEquivalentCopy()));
                }
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.cards.get(tmpCurse.get(AbstractDungeon.cardRng.random(0, tmpCurse.size() - 1)))));
                if (upgraded){
                    AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(CardLibrary.cards.get(tmpCurse.get(AbstractDungeon.cardRng.random(0, tmpCurse.size() - 1)))));
                }
                break;

        }



    }


    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }

    public AbstractCard makeCopy() {
        return new BlackTutor();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            //upgradeMagicNumber(-2);

            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();


        }
    }
}


