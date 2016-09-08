package com.epam.javalab13.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.UserDAO.GetType;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.dao.game.GameDAO.GetGamesType;
import com.epam.javalab13.model.User;
import com.epam.javalab13.model.game.Game;

public class PaginationService {
	
	private UserDAO userDao;
	private GameDAO gameDao;
	private static final Logger loger = Logger.getLogger(PaginationService.class);
	
	public List<User> getUsers(String type,String page){
		List<User> users=null;
		
		return users;
	}

	public int getPagesForUsers(String type, String page,String itemsOnPage, List<User> users) {
		List<User> allUsers =null;
		int pages=1;
		try {
			int pageNumber=page==null?1:Integer.valueOf(page);
			int items=itemsOnPage==null?10:Integer.valueOf(itemsOnPage);
			GetType enumType=type==null?GetType.ALL:GetType.valueOf(type);
			userDao= new UserDAO();
			allUsers = userDao.getAllUsersByType(enumType);
			pages=1+allUsers.size()/items;
			int start =allUsers.size()<items*(pageNumber-1)?(allUsers.size()-allUsers.size()/items):items*(pageNumber-1);
			int end = allUsers.size()<items*pageNumber?allUsers.size():items*pageNumber;
			users.addAll(allUsers.subList(start, end));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			loger.error("failed to find users by type = " + type, e);
		}
		return pages;
	}
	public int getPagesForGames(String type, String page,String itemsOnPage, List<Game> games) {
		List<Game> allGames =null;
		int pages=1;
		try {
			int pageNumber=page==null||page.equals("")?1:Integer.valueOf(page);
			int items=itemsOnPage==null||itemsOnPage.equals("")?10:Integer.valueOf(itemsOnPage);
			GetGamesType enumType=type==null||type.equals("")?GetGamesType.ALL:GetGamesType.valueOf(type);
			gameDao=new GameDAO();
			allGames = gameDao.getGamesByStatus(enumType);
			
			pages=1+allGames.size()/items;
			int start =allGames.size()<items*(pageNumber-1)?(allGames.size()-allGames.size()/items):items*(pageNumber-1);
			int end = allGames.size()<items*pageNumber?allGames.size():items*pageNumber;
			games.addAll(allGames.subList(start, end));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			loger.error("failed to find users by type = " + type, e);
		}
		return pages;
	}
	
}
