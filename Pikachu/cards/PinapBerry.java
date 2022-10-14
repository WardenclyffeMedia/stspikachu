package Pikachu.cards;

import Pikachu.DefaultMod;
import Pikachu.abstracts.BerryCard;
import Pikachu.characters.TheDefault;
import Pikachu.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Pikachu.DefaultMod.makeCardPath;

public class PinapBerry extends BerryCard {


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Pikachu.cards.PinapBerry.class.getSimpleName());
    public static final String IMG = makeCardPath("Berry.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int GOLD = 3;
    // /STAT DECLARATION/


    public PinapBerry() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);


        this.exhaust = true;
        this.retain = true;
        this.tags.add(CustomTags.BERRY);
        this.tags.add(CustomTags.PINAP);
        this.tags.add(AbstractCard.CardTags.HEALING);
        this.magicNumber = GOLD;


    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.gainGold(this.magicNumber);
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
