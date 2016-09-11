package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class PlayerService {
    PlayerDAO playerDAO = new PlayerDAO();

    private static Logger logger = Logger.getLogger(PlayerService.class);

    public void addPlayer(Player player)  {
        try {
            playerDAO.addPlayer(player);
        } catch (SQLException e) {
            logger.error("Can't add new player:" + player,e);
        }
    }

    public void removePlayerFromTeam(int playerId){
        Player player = new Player();
        player.setId(playerId);
        try {
            playerDAO.updatePlayer(player,"remove");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlayerName(Player player) throws SQLException {
        playerDAO.updatePlayer(player, "name");
    }

    public void updatePlayerTotalGames(Player player) throws SQLException {
        playerDAO.updatePlayer(player, "games");
    }

    public void updatePlayerTeam(String playerName,int teamId){
        Player p = new Player();
        p.setFulName(playerName);
        Player player = null;
        try {
            player = playerDAO.getPlayer(p,"name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Team team = new Team();
        team.setId(teamId);
        player.setTeam(team);
        try {
            playerDAO.updatePlayer(player, "team");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Player getPlayerById(Player player) throws SQLException {
        return playerDAO.getPlayer(player, "id");
    }

    public Player getPlayerByName(String playerName) {
        Player p = new Player();
        p.setFulName(playerName);

        Player player = null;
        try {
            player = playerDAO.getPlayer(p, "name");
        } catch (SQLException e) {
           logger.error("Cant get player by name: " + playerName,e);
        }
        return player;
    }

    public List<Player> getPlayersByTeam(Team team){
        try {
            return playerDAO.getPlayersByTeam(team);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Player> getAllPlayers() {
        try {
            return playerDAO.getAllPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}