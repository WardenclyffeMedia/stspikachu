package Pikachu.cards;
import Pikachu.DefaultMod;
import Pikachu.characters.TheDefault;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import java.util.ArrayList;

import static Pikachu.DefaultMod.makeCardPath;

public class SoftSoil extends CustomCard {
        public static final String ID = DefaultMod.makeID(SoftSoil.class.getSimpleName());
        public static final String IMG = makeCardPath("Berry.png");
        private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
    private static final CardType TYPE = CardType.SKILL;
        private static final int COST = -2;


        public SoftSoil() {
            super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

            this.isEthereal = true;
        }



        public void use(AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new UseCardAction((AbstractCard)this)); }





        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            this.cantUseMessage = "Pika-pii... (It's not done growing yet.)";
            return false;
        }


        public AbstractCard makeStatEquivalentCopy() {
            AbstractCard c = super.makeStatEquivalentCopy();
            c.retain = this.retain;
            return c;
        }


        public void triggerOnExhaust() {
            applyPowers();

                         ArrayList<String> BerryCardKeys = new ArrayList<>();
                ArrayList<AbstractCard> Cards = CardLibrary.getAllCards();

                for (AbstractCard c : Cards) {

                    if (c instanceof Pikachu.abstracts.BerryCard)
                    {
                        switch (c.rarity) {

                            case SPECIAL:
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                                BerryCardKeys.add(c.cardID);
                        }

                    }
                }
                AbstractCard randomBerry = CardLibrary.getCard(BerryCardKeys.get(AbstractDungeon.cardRandomRng.random(BerryCardKeys.size() - 1))).makeCopy();

                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(randomBerry, 1));

        }



        public AbstractCard makeCopy() { return (AbstractCard)new SoftSoil(); }



        public void upgrade() {
            if (!this.upgraded) {
                upgradeName();
                initializeDescription();
            }
        }
    }
