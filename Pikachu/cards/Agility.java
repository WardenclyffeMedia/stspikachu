package Pikachu.cards;

import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import jdk.nashorn.internal.ir.Block;

import static Pikachu.DefaultMod.makeCardPath;

public class Agility extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Agility.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;

    private static final int DEXTERITYAMOUNT = 2;
    private static final int UPGRADED_DEXTERITYAMOUNT = 1;
    private static final int MetallicizeAmount = 2;
    private static final int UPGRADED_METALLICIZE = 1;

    // /STAT DECLARATION/


    public Agility() {

        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DEXTERITYAMOUNT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = MetallicizeAmount;

    }
    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DexterityPower((AbstractCreature)p, magicNumber), magicNumber));

        AbstractDungeon.actionManager.addToBottom(
        (AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new MetallicizePower((AbstractCreature)p, this.defaultSecondMagicNumber), this.defaultSecondMagicNumber));

    }

    public AbstractCard makeCopy() { return new Agility(); }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_DEXTERITYAMOUNT);
            upgradeDefaultSecondMagicNumber(UPGRADED_METALLICIZE);
            initializeDescription();
        }
    }
}
