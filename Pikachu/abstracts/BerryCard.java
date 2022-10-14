package Pikachu.abstracts;

import Pikachu.util.CustomTags;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import java.lang.reflect.Type;


    public abstract class BerryCard
            extends CustomCard
    {
        public static boolean ResetOnPlay = true;
        public int initialValue;
        public static Color growthFlash = Color.GREEN;
        public static Color resetFlash = Color.RED;

        public boolean upgradePreview = true;


        public BerryCard(String ID, String NAME, String IMG, int COST, String DESCRIPTION, AbstractCard.CardType TYPE, AbstractCard.CardColor COLOR, AbstractCard.CardRarity RARITY, AbstractCard.CardTarget TARGET) {
            super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

            AlwaysRetainField.alwaysRetain.set(this, Boolean.valueOf(true));
            this.initialValue = 0;
            this.tags.add(CustomTags.BERRY);
        }




        public AbstractCard makeStatEquivalentCopy() {
            AbstractCard AbstractCopy = super.makeStatEquivalentCopy();

            if (AbstractCopy instanceof BerryCard) {

                ((BerryCard)AbstractCopy).initialValue = this.initialValue;
                ((BerryCard)AbstractCopy).magicNumber = this.magicNumber;
            }

            return AbstractCopy;
        }



        public void applyPowers() {
            super.applyPowers();

            if (this.isEthereal && this.retain) {

                AlwaysRetainField.alwaysRetain.set(this, Boolean.valueOf(false));
                this.retain = false;
            } else {

                AlwaysRetainField.alwaysRetain.set(this, Boolean.valueOf(true));
                this.retain = true;
            }
        }




       public Type savedType() { return int.class; }


}
