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
package mage.sets.gatecrash;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.abilities.Ability;
import mage.abilities.effects.ReplacementEffectImpl;
import mage.abilities.effects.common.DamageTargetEffect;
import mage.cards.CardImpl;
import mage.constants.Duration;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.target.TargetPlayer;

/**
 *
 * @author LevelX2
 */
public class Skullcrack extends CardImpl<Skullcrack> {

    public Skullcrack (UUID ownerId) {
        super(ownerId, 106, "Skullcrack", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{1}{R}");
        this.expansionSetCode = "GTC";

        this.color.setRed(true);

        // Players can't gain life this turn. Damage can't be prevented this turn. Skullcrack deals 3 damage to target player.
        this.getSpellAbility().addEffect(new PlayersCantGetLiveEffect());
        this.getSpellAbility().addEffect(new DamageCantBePreventedEffect());
        this.getSpellAbility().addEffect(new DamageTargetEffect(3));
        this.getSpellAbility().addTarget(new TargetPlayer());

    }

    public Skullcrack(final Skullcrack card) {
        super(card);
    }

    @Override
    public Skullcrack  copy() {
        return new Skullcrack(this);
    }
}

class DamageCantBePreventedEffect extends ReplacementEffectImpl<DamageCantBePreventedEffect> {

    public DamageCantBePreventedEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit);
        staticText = "Damage can't be prevented this turn";
    }

    public DamageCantBePreventedEffect(final DamageCantBePreventedEffect effect) {
        super(effect);
    }

    @Override
    public DamageCantBePreventedEffect copy() {
        return new DamageCantBePreventedEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return true;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getType() == GameEvent.EventType.PREVENT_DAMAGE) {
            return true;
        }
        return false;
    }
}

class PlayersCantGetLiveEffect extends ReplacementEffectImpl<PlayersCantGetLiveEffect> {

    public PlayersCantGetLiveEffect() {
        super(Duration.EndOfTurn, Outcome.Benefit);
        staticText = "Players can't gain life this turn";
    }

    public PlayersCantGetLiveEffect(final PlayersCantGetLiveEffect effect) {
        super(effect);
    }

    @Override
    public PlayersCantGetLiveEffect copy() {
        return new PlayersCantGetLiveEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return true;
    }

    @Override
    public boolean replaceEvent(GameEvent event, Ability source, Game game) {
        return true;
    }

    @Override
    public boolean applies(GameEvent event, Ability source, Game game) {
        if (event.getType() == GameEvent.EventType.GAIN_LIFE) {
            return true;
        }
        return false;
    }
}
