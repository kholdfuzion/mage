/*
* Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
*    1. Redistributions of source code must retain the above copyright notice, this list of
*       conditions and the following disclaimer.
*
*    2. Redistributions in binary form must reproduce the above copyright notice, this list
*       of conditions and the following disclaimer in the documentation and/or other materials
*       provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and should not be interpreted as representing official policies, either expressed
* or implied, of BetaSteward_at_googlemail.com.
*/

package mage.abilities.effects.common;

import mage.constants.Outcome;
import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.effects.OneShotEffect;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.Target;

import java.util.UUID;

/**
 * @author BetaSteward_at_googlemail.com
 */
public class TapTargetEffect extends OneShotEffect<TapTargetEffect> {

    public TapTargetEffect() {
        super(Outcome.Tap);
    }

    public TapTargetEffect(String text) {
        this();
        this.staticText = text;
    }

    public TapTargetEffect(final TapTargetEffect effect) {
        super(effect);
    }

    @Override
    public TapTargetEffect copy() {
        return new TapTargetEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (UUID target : targetPointer.getTargets(game, source)) {
            Permanent permanent = game.getPermanent(target);
            if (permanent != null) {
                permanent.tap(game);
            }
        }
        return true;
    }

    @Override
    public String getText(Mode mode) {
        if (staticText.length() > 0) {
            return "tap " + staticText;
        }

        Target target = mode.getTargets().get(0);
        if (target.getMaxNumberOfTargets() > 1) {
            if (target.getMaxNumberOfTargets() == target.getNumberOfTargets()) {
                return "tap " + target.getNumberOfTargets() + " target " + target.getTargetName() + "s";
            } else {
                return "tap up to " + target.getMaxNumberOfTargets() + " target " + target.getTargetName() + "s";
            }
        } else if (target.getMaxNumberOfTargets() == 0){
            return "tap X target " + mode.getTargets().get(0).getTargetName();
        } else {
            return "tap target " + mode.getTargets().get(0).getTargetName();
        }
    }

}
