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
package mage.sets.conflux;

import java.util.UUID;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.MageInt;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.costs.Costs;
import mage.abilities.costs.CostsImpl;
import mage.abilities.costs.common.SacrificeTargetCost;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.dynamicvalue.common.CardsInControllerGraveyardCount;
import mage.abilities.effects.common.continious.BoostSourceEffect;
import mage.abilities.effects.common.search.SearchLibraryPutInPlayEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterControlledLandPermanent;
import mage.filter.common.FilterControlledPermanent;
import mage.filter.common.FilterLandCard;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.target.common.TargetCardInLibrary;
import mage.target.common.TargetControlledPermanent;

/**
 *
 * @author BetaSteward_at_googlemail.com
 */
public class KnightOfTheReliquary extends CardImpl<KnightOfTheReliquary> {

    private static final FilterControlledPermanent filter = new FilterControlledLandPermanent("Forest or Plains");

    static {
        filter.add(Predicates.or(new SubtypePredicate("Forest"), new SubtypePredicate("Plains")));
    }

    public KnightOfTheReliquary(UUID ownerId) {
        super(ownerId, 113, "Knight of the Reliquary", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{1}{G}{W}");
        this.expansionSetCode = "CON";
        this.subtype.add("Human");
        this.subtype.add("Knight");

        this.color.setWhite(true);
        this.color.setGreen(true);
        this.power = new MageInt(2);
        this.toughness = new MageInt(2);

        // Knight of the Reliquary gets +1/+1 for each land card in your graveyard.
        CardsInControllerGraveyardCount value = new CardsInControllerGraveyardCount(new FilterLandCard());
        this.addAbility(new SimpleStaticAbility(Zone.BATTLEFIELD, new BoostSourceEffect(value, value, Duration.WhileOnBattlefield)));

        // {T}, Sacrifice a Forest or Plains: Search your library for a land card, put it onto the battlefield, then shuffle your library.
        TargetCardInLibrary target = new TargetCardInLibrary(new FilterLandCard());
        Costs costs = new CostsImpl();
        costs.add(new TapSourceCost());
        costs.add(new SacrificeTargetCost(new TargetControlledPermanent(1, 1, filter, false)));
        this.addAbility(new SimpleActivatedAbility(Zone.BATTLEFIELD, new SearchLibraryPutInPlayEffect(target, false, Outcome.PutLandInPlay), costs));
    }

    public KnightOfTheReliquary(final KnightOfTheReliquary card) {
        super(card);
    }

    @Override
    public KnightOfTheReliquary copy() {
        return new KnightOfTheReliquary(this);
    }
}
