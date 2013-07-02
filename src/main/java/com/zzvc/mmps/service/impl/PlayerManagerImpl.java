package com.zzvc.mmps.service.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzvc.mmps.dao.PlayerDao;
import com.zzvc.mmps.model.Player;
import com.zzvc.mmps.service.PlayerManager;

@Service("playerManager")
public class PlayerManagerImpl extends GenericManagerImpl<Player, Long> implements PlayerManager {
	private static final int MINUTES_BEFORE_PLAYER_HEARTBEAT_FAIL = 30;
	
	private PlayerDao playerDao;
	
	@Autowired
	public PlayerManagerImpl(PlayerDao playerDao) {
		super(playerDao);
		this.playerDao = playerDao;
	}
	
	@Override
	public List<Player> getFault() {
		return playerDao.findByHeartbeatBefore(new Date(System.currentTimeMillis() - MINUTES_BEFORE_PLAYER_HEARTBEAT_FAIL * 60 * 1000));
	}
	
	@Override
	public List<Player> getNormal() {
		return playerDao.findByHeartbeatAfter(new Date(System.currentTimeMillis() - MINUTES_BEFORE_PLAYER_HEARTBEAT_FAIL * 60 * 1000));
	}

	@Override
	public List<Player> search(String searchTerm) {
		return super.search(searchTerm, Player.class);
	}
}
