/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.mercadianmasques;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.ObjectColor;
import mage.abilities.Ability;
import mage.abilities.costs.AlternativeCostImpl;
import mage.abilities.costs.Cost;
import mage.abilities.costs.CostsImpl;
import mage.abilities.costs.common.ExileFromHandCost;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.constants.Outcome;
import mage.filter.FilterSpell;
import mage.filter.common.FilterOwnedCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.CardIdPredicate;
import mage.filter.predicate.mageobject.ColorPredicate;
import mage.filter.predicate.mageobject.NumberOfTargetsPredicate;
import mage.game.Game;
import mage.game.stack.Spell;
import mage.target.TargetSpell;
import mage.target.common.TargetCardInHand;

/**
 *
 * @author jonubuu
 */
public class Misdirection extends CardImpl<Misdirection> {

    private static final FilterSpell filter2 = new FilterSpell();

    static {
        filter2.add(new NumberOfTargetsPredicate(1));
    }

    public Misdirection(UUID ownerId) {
        super(ownerId, 87, "Misdirection", Rarity.RARE, new CardType[]{CardType.INSTANT}, "{3}{U}{U}");
        this.expansionSetCode = "MMQ";

        this.color.setBlue(true);

        // You may exile a blue card from your hand rather than pay Misdirection's mana cost.
        FilterOwnedCard filterCardInHand = new FilterOwnedCard("blue card from your hand");
        filterCardInHand.add(new ColorPredicate(ObjectColor.BLUE));
        // the exile cost can never be paid with the card itself
        filterCardInHand.add(Predicates.not(new CardIdPredicate(this.getId())));
        CostsImpl<Cost> costs = new CostsImpl<Cost>();
        costs.add(new ExileFromHandCost(new TargetCardInHand(filterCardInHand)));
        this.getSpellAbility().addAlternativeCost(new AlternativeCostImpl("You may exile a blue card from your hand rather than pay Misdirection's mana cost", costs));
        // Change the target of target spell with a single target.
        this.getSpellAbility().addEffect(new MisdirectionEffect());
        this.getSpellAbility().addTarget(new TargetSpell(filter2));
    }

    public Misdirection(final Misdirection card) {
        super(card);
    }

    @Override
    public Misdirection copy() {
        return new Misdirection(this);
    }
}

class MisdirectionEffect extends OneShotEffect<MisdirectionEffect> {

    public MisdirectionEffect() {
        super(Outcome.Neutral);
        staticText = "Change the target of target spell with a single target";
    }

    public MisdirectionEffect(final MisdirectionEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Spell spell = game.getStack().getSpell(source.getFirstTarget());
        if (spell != null && source.getControllerId() != null) {
            return spell.chooseNewTargets(game, source.getControllerId());
        }
        return false;
    }

    @Override
    public MisdirectionEffect copy() {
        return new MisdirectionEffect(this);
    }
}
