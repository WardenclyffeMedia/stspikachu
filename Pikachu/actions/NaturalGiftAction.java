package Pikachu.actions;

import Pikachu.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NaturalGiftAction extends AbstractGameAction {
        private DamageInfo info;

        public NaturalGiftAction(AbstractCreature target, DamageInfo info) {
            this.duration = Settings.ACTION_DUR_XFAST;
            this.info = info;
            this.actionType = AbstractGameAction.ActionType.BLOCK;
            this.target = target;
        }


        public void update() {
            int count = 0;
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.BERRY)) {
                    count++;
                }
                if (count > 0) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction(this.target, this.info, true));
                }
            }
                this.isDone = true;
        }



    }
