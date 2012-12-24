package com.zzvc.mmps.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.zzvc.mmps.model.Player;

public interface PlayerManager extends GenericManager<Player, Long> {
	List<Player> getNormal();
	List<Player> getFault();
	List<Player> search(String searchTerm);
}
