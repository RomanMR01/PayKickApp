package com.epam.javalab13.service.game;

import com.epam.javalab13.dao.game.PlayerDAO;
import com.epam.javalab13.model.game.Player;
import com.epam.javalab13.model.game.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Olga on 04.09.2016.
 */
public class PlayerService {
    PlayerDAO playerDAO=new PlayerDAO();

    //todo ask if need to add synchronization on dao???
    public void addPlayer(Player player) throws SQLException{
        playerDAO.addPlayer(player);
    }

    public void updatePlayerName(Player player)throws SQLException {
        playerDAO.updatePlayer(player,"name");
    }

    public void updatePlayerTotalGames(Player player)throws SQLException {
        playerDAO.updatePlayer(player,"games");
    }

    public void updatePlayerTeam(Player player)throws SQLException{
        playerDAO.updatePlayer(player,"team");
    }

    public Player getPlayerById(Player player) throws SQLException {
        return playerDAO.getPlayer(player,"id");
    }

    public Player getPlayerByName(Player player) throws SQLException {
        return playerDAO.getPlayer(player,"name");
    }

    public List<Player> getPlayersByTeam(Team team) throws SQLException {
        return playerDAO.getPlayersByTeam(team);
    }

    public List<Player> getAllPlayers() throws SQLException{
        return playerDAO.getAllPlayers();
    }
}