package Pikachu.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class MonsterLoseHP extends AbstractGameAction {


        private static final float DURATION = 0.33F;


        public MonsterLoseHP(AbstractMonster target, AbstractCreature source, int amount, AbstractGameAction.AttackEffect effect) {
            setValues(target, source, amount);
            this.actionType = AbstractGameAction.ActionType.DAMAGE;
            this.attackEffect = effect;
            this.duration = 0.33F;
        }


        public void update() {
            if (this.duration == 0.33F && this.target.currentHealth > 0) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            }


            tickDuration();

            if (this.isDone) {
                this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));

                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
                if (!Settings.FAST_MODE)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
            }
        }
    }
