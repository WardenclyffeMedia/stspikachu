package Pikachu.cards;

import Pikachu.actions.MonsterLoseHP;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Pikachu.DefaultMod.makeCardPath;

// public class PlayNice extends AbstractDynamicCard
public class PlayNice extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(PlayNice.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;

    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("PlayNice.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;  // COST =

    private static final int DAMAGE = 7;
    private static final int BLOCK = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int WEAKNESS = 1;
    // /STAT DECLARATION/


    public PlayNice() { // public PlayNice() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        this.magicNumber = WEAKNESS;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(((AbstractGameAction) new MonsterLoseHP((AbstractMonster) m, (AbstractCreature) p, DAMAGE, AbstractGameAction.AttackEffect.BLUNT_HEAVY)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));

        }
        else
            AbstractDungeon.actionManager.addToBottom(((AbstractGameAction) new MonsterLoseHP((AbstractMonster) m, (AbstractCreature) p, DAMAGE, AbstractGameAction.AttackEffect.BLUNT_HEAVY)));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
