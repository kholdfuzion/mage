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

package mage.sets.championsofkamigawa;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.SpellCastTriggeredAbility;
import mage.abilities.effects.common.continious.BecomesCreatureTargetEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterSpiritOrArcaneCard;
import mage.game.permanent.token.Token;
import mage.target.common.TargetLandPermanent;

/**
 * @author Loki
 */
public class Soilshaper extends CardImpl<Soilshaper> {

    private static final FilterSpiritOrArcaneCard filter = new FilterSpiritOrArcaneCard();

    public Soilshaper(UUID ownerId) {
        super(ownerId, 243, "Soilshaper", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{1}{G}");
        this.expansionSetCode = "CHK";
        this.subtype.add("Spirit");
        this.color.setGreen(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(1);
        Ability ability = new SpellCastTriggeredAbility(new BecomesCreatureTargetEffect(new SoilshaperToken(), "land", Duration.EndOfTurn), filter, false);
        ability.addTarget(new TargetLandPermanent());
        this.addAbility(ability);
    }

    public Soilshaper(final Soilshaper card) {
        super(card);
    }

    @Override
    public Soilshaper copy() {
        return new Soilshaper(this);
    }

}

class SoilshaperToken extends Token {

    public SoilshaperToken() {
        super("", "3/3 creature");
        this.cardType.add(CardType.CREATURE);

        this.power = new MageInt(3);
        this.toughness = new MageInt(3);
    }
}
