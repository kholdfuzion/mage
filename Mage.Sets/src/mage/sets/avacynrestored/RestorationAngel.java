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
package mage.sets.avacynrestored;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.constants.Zone;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.FlashAbility;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.constants.Outcome;
import mage.filter.common.FilterControlledCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.SubtypePredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetControlledCreaturePermanent;

/**
 *
 * @author noxx

 */
public class RestorationAngel extends CardImpl<RestorationAngel> {

    private static final FilterControlledCreaturePermanent filter = new FilterControlledCreaturePermanent("non-Angel creature you control");

    static {
        filter.add(Predicates.not(new SubtypePredicate("Angel")));
    }

    public RestorationAngel(UUID ownerId) {
        super(ownerId, 32, "Restoration Angel", Rarity.RARE, new CardType[]{CardType.CREATURE}, "{3}{W}");
        this.expansionSetCode = "AVR";
        this.subtype.add("Angel");

        this.color.setWhite(true);
        this.power = new MageInt(3);
        this.toughness = new MageInt(4);

        this.addAbility(FlashAbility.getInstance());
        this.addAbility(FlyingAbility.getInstance());
        
        // When Restoration Angel enters the battlefield, you may exile target non-Angel creature you control, then return that card to the battlefield under your control
        Ability ability = new EntersBattlefieldTriggeredAbility(new RestorationAngelEffect(), true);
        ability.addTarget(new TargetControlledCreaturePermanent(1, 1, filter, false));
        this.addAbility(ability);
    }

    public RestorationAngel(final RestorationAngel card) {
        super(card);
    }

    @Override
    public RestorationAngel copy() {
        return new RestorationAngel(this);
    }
}

class RestorationAngelEffect extends OneShotEffect<RestorationAngelEffect> {

    public RestorationAngelEffect() {
        super(Outcome.Exile);
        staticText = "you may exile target non-Angel creature you control, then return that card to the battlefield under your control";
    }

    public RestorationAngelEffect(final RestorationAngelEffect effect) {
        super(effect);
    }

    @Override
    public RestorationAngelEffect copy() {
        return new RestorationAngelEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        boolean exiled = false;
        Permanent sourcePermanent = game.getPermanent(source.getSourceId());
        if (sourcePermanent == null) {
            sourcePermanent = (Permanent) game.getLastKnownInformation(source.getSourceId(), Zone.BATTLEFIELD);
        }
        Permanent permanent = game.getPermanent(targetPointer.getFirst(game, source));
        if (permanent != null) {
            permanent.moveToExile(source.getSourceId(), sourcePermanent.getName(), source.getSourceId(), game);
            Card card = game.getCard(targetPointer.getFirst(game, source));
            if (card != null) {
                Zone currentZone = game.getState().getZone(card.getId());
                return card.putOntoBattlefield(game, currentZone, source.getSourceId(), source.getControllerId());
            }
        }
        return exiled; 
    }
}
