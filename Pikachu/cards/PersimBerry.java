package Pikachu.cards;

import Pikachu.DefaultMod;
import Pikachu.abstracts.BerryCard;
import Pikachu.characters.TheDefault;
import Pikachu.util.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static Pikachu.DefaultMod.makeCardPath;

public class PersimBerry extends BerryCard {


        // TEXT DECLARATION

        public static final String ID = DefaultMod.makeID(Pikachu.cards.PersimBerry.class.getSimpleName());
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

        // /STAT DECLARATION/


        public PersimBerry() {
            super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

            this.exhaust = true;
            this.retain = true;
            this.tags.add(CustomTags.BERRY);
        }

        // Actions the card should do.
        @Override
        public void use(AbstractPlayer p, AbstractMonster m) {

            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, "Confusion"));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new EnergizedPower((AbstractCreature)p, 1), 1));
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