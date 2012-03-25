/*
 *  Copyright 2011 BetaSteward_at_googlemail.com. All rights reserved.
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
package mage.watchers.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mage.Constants.WatcherScope;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.events.GameEvent.EventType;
import mage.watchers.WatcherImpl;

/**
 * Watcher stores whitch sources did damage to a player
 * 
 * @author LevelX
 */
public class PlayerDamagedBySourceWatcher extends WatcherImpl<PlayerDamagedBySourceWatcher> {

    public List<UUID> damageSources = new ArrayList<UUID>();
    
    public PlayerDamagedBySourceWatcher(UUID playerId) {
		super("PlayerDamagedBySource", WatcherScope.PLAYER);
                setControllerId(playerId);
	}

	public PlayerDamagedBySourceWatcher(final PlayerDamagedBySourceWatcher watcher) {
		super(watcher);
        this.damageSources = watcher.damageSources;
	}

	@Override
	public PlayerDamagedBySourceWatcher copy() {
		return new PlayerDamagedBySourceWatcher(this);
	}

	@Override
	public void watch(GameEvent event, Game game) {
		if (event.getType() == EventType.DAMAGED_PLAYER) {
                        if (event.getTargetId().equals(controllerId) && !damageSources.contains(event.getSourceId())) {
                                damageSources.add(event.getSourceId());
                        }
                }
	}
    
	@Override
	public void reset() {
		super.reset();
		damageSources.clear();
	}
}