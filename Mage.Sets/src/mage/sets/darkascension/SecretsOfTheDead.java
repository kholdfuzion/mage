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
package mage.sets.darkascension;

import java.util.UUID;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.abilities.common.SpellCastTriggeredAbility;
import mage.abilities.effects.common.DrawCardControllerEffect;
import mage.cards.CardImpl;
import mage.filter.FilterSpell;
import mage.filter.predicate.Predicate;
import mage.game.Game;
import mage.game.stack.Spell;

/**
 *
 * @author BetaSteward
 */
public class SecretsOfTheDead extends CardImpl<SecretsOfTheDead> {

    private static final FilterSpell filter = new FilterSpell("a spell from your graveyard");

    static {
        filter.add(new SpellZonePredicate(Zone.GRAVEYARD));
    }

    public SecretsOfTheDead(UUID ownerId) {
        super(ownerId, 48, "Secrets of the Dead", Rarity.UNCOMMON, new CardType[]{CardType.ENCHANTMENT}, "{2}{U}");
        this.expansionSetCode = "DKA";

        this.color.setBlue(true);

        // Whenever you cast a spell from your graveyard, draw a card.
        this.addAbility(new SpellCastTriggeredAbility(new DrawCardControllerEffect(1), filter, false));
    }

    public SecretsOfTheDead(final SecretsOfTheDead card) {
        super(card);
    }

    @Override
    public SecretsOfTheDead copy() {
        return new SecretsOfTheDead(this);
    }
}

class SpellZonePredicate implements Predicate<Spell> {

    private final Zone zone;

    public SpellZonePredicate(Zone zone) {
        this.zone = zone;
    }

    @Override
    public boolean apply(Spell input, Game game) {
        return input.getFromZone().match(zone);
    }

    @Override
    public String toString() {
        return "SpellZone(" + zone + ')';
    }
}
