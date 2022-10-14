package Pikachu.cards;

import Pikachu.actions.ExhaustAllBerryAction;
import Pikachu.actions.FlingAction;
import Pikachu.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;

import java.util.Iterator;

import static Pikachu.DefaultMod.makeCardPath;

public class Fling extends AbstractDynamicCard {



    public static final String ID = DefaultMod.makeID(Fling.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Attack.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;  // COST =
    private static final int UPGRADED_COST = 1; // UPGRADED_COST =

    private static final int DAMAGE = 6;

    // /STAT DECLARATION/


    public Fling() { // public FLING() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
   public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new FlingAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ExhaustAllBerryAction());
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new SoftSoil(), 1));
    }


    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        Iterator var2 = AbstractDungeon.player.hand.group.iterator();

        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.hasTag(CustomTags.BERRY)) {
                ++count;
            }
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
