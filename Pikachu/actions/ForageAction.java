package Pikachu.actions;

import Pikachu.cards.SoftSoil;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;


    public class ForageAction extends AbstractGameAction {

        public ForageAction(AbstractCreature target, AbstractCreature source, int amount) {
            this.actionType = ActionType.DRAW;
            this.target = target;
            this.source = source;
            this.amount = amount;
        }


        public void update() {
            this.addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new SoftSoil(), this.amount)); }

    }

