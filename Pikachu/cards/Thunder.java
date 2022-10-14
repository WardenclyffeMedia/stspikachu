package Pikachu.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static Pikachu.DefaultMod.makeCardPath;

// public class Thunder extends AbstractDynamicCard
public class Thunder extends AbstractDynamicCard {

      // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Thunder.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("Thunder.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 3;  // COST = 3

    private static final int DAMAGE = 20;    // DAMAGE = 20
    private static final int UPGRADE_PLUS_DMG = 5;  // UPGRADE_PLUS_DMG = 5

    private static final int VULNERABLE = 1;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;

    // /STAT DECLARATION/


    public Thunder() { // public Thunder() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.baseDamage = 20;
        this.isMultiDamage = true;

        magicNumber = baseMagicNumber = VULNERABLE;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("THUNDERCLAP", 0.05F));

        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractGameEffect) new LightningEffect(mo.drawX, mo.drawY), 0.05F));
            }
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAllEnemiesAction((AbstractCreature) p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) p, (AbstractPower) new VulnerablePower((AbstractCreature) mo, this.magicNumber, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            this.upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
            initializeDescription();
        }
    }
}
