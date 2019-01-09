package sneckomod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sneckomod.cards.GrandSneckoForm;
import sneckomod.orbs.GrandWarp;
import sneckomod.powers.GrandSneckoPower;

import java.util.Iterator;

public class WarpPlayAction extends AbstractGameAction {

    public GrandWarp power;

    public WarpPlayAction(GrandWarp power) {
        this.power = power;
    }

    public void update() {

        power.playCardEffect(1);

        this.isDone = true;
    }
}