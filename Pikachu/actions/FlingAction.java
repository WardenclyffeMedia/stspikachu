package Pikachu.actions;

import Pikachu.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import java.util.Iterator;


public class FlingAction extends AbstractGameAction {

        private DamageInfo info;

        public FlingAction(AbstractCreature target, DamageInfo info) {
            this.duration = Settings.ACTION_DUR_XFAST;
            this.info = info;
            this.actionType = ActionType.DAMAGE;
            this.target = target;
        }

        public void update() {
            Iterator var1 = AbstractDungeon.player.hand.group.iterator();

            while(var1.hasNext()) {
                AbstractCard c = (AbstractCard)var1.next();
                if (c.hasTag(CustomTags.BERRY)) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target, this.info, true));
                    if (this.target != null && this.target.hb != null) {
                        AbstractDungeon.actionManager.addToBottom(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
                    }
                }
            }

            this.isDone = true;
        }
    }
