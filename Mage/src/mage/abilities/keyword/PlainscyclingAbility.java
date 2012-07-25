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

package mage.abilities.keyword;

import mage.Constants.Zone;
import mage.abilities.ActivatedAbilityImpl;
import mage.abilities.costs.common.DiscardSourceCost;
import mage.abilities.costs.mana.ManaCosts;
import mage.abilities.effects.common.search.SearchLibraryPutInHandEffect;
import mage.filter.common.FilterLandCard;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author Loki
 */
public class PlainscyclingAbility extends ActivatedAbilityImpl<PlainscyclingAbility>{
    private static final FilterLandCard filter = new FilterLandCard("Plains card");

    static {
        filter.add(new SubtypePredicate("Plains"));
    }

    public PlainscyclingAbility(ManaCosts costs) {
        super(Zone.HAND, new SearchLibraryPutInHandEffect(new TargetCardInLibrary(filter)), costs);
        this.addCost(new DiscardSourceCost());
    }

    public PlainscyclingAbility(final PlainscyclingAbility ability) {
        super(ability);
    }

    @Override
    public PlainscyclingAbility copy() {
        return new PlainscyclingAbility(this);
    }

    @Override
    public String getRule() {
    return "Plainscycling " + super.getRule();
    }
}