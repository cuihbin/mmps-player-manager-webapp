package com.zzvc.mmps.webapp.action;

import java.util.List;

import org.appfuse.dao.SearchException;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.Preparable;
import com.zzvc.mmps.model.Player;
import com.zzvc.mmps.service.PlayerManager;

public class PlayerAction extends BaseAction implements Preparable {
    private PlayerManager playerManager;
    private List players;
    private Player player;
    private Long id;
    private String query;

    @Override
	public void prepare() throws Exception {
        // prevent failures on new
        if (getRequest().getMethod().equalsIgnoreCase("post") && (StringUtils.hasText(getRequest().getParameter("player.id")))) {
        	player = playerManager.get(new Long(getRequest().getParameter("player.id")));
        }
	}

	public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public List getPlayers() {
        return players;
    }

    public String list() {
        try {
            players = playerManager.search(query);
        } catch (SearchException se) {
            addActionError(se.getMessage());
            players = playerManager.getAll();
        }
        return SUCCESS;
    }
    
    public String listNormal() {
    	players = playerManager.getNormal();
        return SUCCESS;
    }
    
    public String listFault() {
    	players = playerManager.getFault();
        return SUCCESS;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setQ(String q) {
		this.query = q;
    }
    
    public String getQ() {
    	return query;
    }

    public String delete() {
        playerManager.remove(player.getId());
        saveMessage(getText("player.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            player = playerManager.get(id);
        } else {
            player = new Player();
        }

        return SUCCESS;
    }

    public String save() throws Exception {
        if (cancel != null) {
            return CANCEL;
        }

        if (delete != null) {
            return delete();
        }

        boolean isNew = (player.getId() == null);

        player = playerManager.save(player);

        String key = (isNew) ? "player.added" : "player.updated";
        saveMessage(getText(key));

        if (!isNew) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }
}
