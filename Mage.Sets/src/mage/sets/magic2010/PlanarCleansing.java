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
package mage.sets.magic2010;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.constants.Outcome;
import mage.filter.FilterPermanent;
import mage.filter.common.FilterNonlandPermanent;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author Loki
 */
public class PlanarCleansing extends CardImpl<PlanarCleansing> {

    public PlanarCleansing(UUID ownerId) {
        super(ownerId, 24, "Planar Cleansing", Rarity.RARE, new CardType[]{CardType.SORCERY}, "{3}{W}{W}{W}");
        this.expansionSetCode = "M10";
        this.color.setWhite(true);

        // Destroy all nonland permanents.
        this.getSpellAbility().addEffect(new PlanarCleansingEffect());
    }

    public PlanarCleansing(final PlanarCleansing card) {
        super(card);
    }

    @Override
    public PlanarCleansing copy() {
        return new PlanarCleansing(this);
    }
}

class PlanarCleansingEffect extends OneShotEffect<PlanarCleansingEffect> {

    private static final FilterPermanent filter = new FilterNonlandPermanent();

    public PlanarCleansingEffect() {
        super(Outcome.DestroyPermanent);
        staticText = "Destroy all nonland permanents";
    }

    public PlanarCleansingEffect(final PlanarCleansingEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent : game.getBattlefield().getActivePermanents(filter, source.getControllerId(), source.getSourceId(), game)) {
            permanent.destroy(source.getId(), game, false);
        }
        return true;
    }

    @Override
    public PlanarCleansingEffect copy() {
        return new PlanarCleansingEffect(this);
    }

}