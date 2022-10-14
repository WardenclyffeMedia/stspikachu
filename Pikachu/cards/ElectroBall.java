package Pikachu.cards;

import Pikachu.actions.UncommonPowerAction;
import Pikachu.orbs.DefaultOrb;
import basemod.helpers.BaseModCardTags;
import basemod.helpers.dynamicvariables.MagicNumberVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static Pikachu.DefaultMod.makeCardPath;

public class ElectroBall extends AbstractDynamicCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ElectroBall.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int Dexterity = 1;
    private static final int CHANNEL = 1;
    private static final int UPGRADEDCHANNEL = 1;


    // /STAT DECLARATION/


    public ElectroBall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = baseMagicNumber = CHANNEL;
        this.showEvokeValue = true;


    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChannelAction((AbstractOrb)new Lightning()));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ChannelAction((AbstractOrb)new Lightning()));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DexterityPower((AbstractCreature)p, Dexterity), Dexterity));

        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EvokeOrbAction(1));
        }


    public AbstractCard makeCopy() { return new ElectroBall(); }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.upgradeMagicNumber(UPGRADEDCHANNEL);
            initializeDescription();
        }
    }
}
