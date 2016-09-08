package com.epam.javalab13.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.javalab13.dao.UserDAO;
import com.epam.javalab13.dao.UserDAO.GetType;
import com.epam.javalab13.dao.UserDAO.GetUsersType;
import com.epam.javalab13.model.User;

public class PaginationService {
	
	private UserDAO userDao= new UserDAO();

	public List<User> getUsers(String type,String page){
		List<User> users=null;
		
		return users;
	}

	public int getPages(String type, String page,String itemsOnPage, List<User> users) {
		List<User> allUsers =null;
		int pages=0;
		try {
			int pageNumber=page==null?1:Integer.valueOf(page);
			int items=itemsOnPage==null?10:Integer.valueOf(itemsOnPage);
			GetType enumType=type==null?GetType.ALL:GetType.valueOf(type);
			allUsers = userDao.getAllUsersByType(enumType);
			System.out.println(allUsers);
			System.out.println();
			pages=1+allUsers.size()/items;
			int start =allUsers.size()<items*(pageNumber-1)?(allUsers.size()-allUsers.size()/items):items*(pageNumber-1);
			int end = allUsers.size()<items*pageNumber?allUsers.size():items*pageNumber;
			users.addAll(allUsers.subList(start, end));
			System.out.println(users);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pages;
	}
	
}
