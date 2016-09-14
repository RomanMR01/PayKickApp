package com.epam.javalab13.service.game;

import com.epam.javalab13.model.game.Team;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testing adding new teams and getting them by id
 */
public class TeamServiceTest {

    private static TeamService teamService = null;

    //Team names must be hardcoded for another tests
    private static String firstTeamName = "JUnit4 Team";
    private static String secondTeamName = "JUnit5 Team";

    private static Team firstTeam = new Team();
    private static Team secondTeam = new Team();

    @BeforeClass
    public static void init() {
        teamService = new TeamService();
    }

    @Test
    public void addTeam() throws Exception {
        //Location and emblemURL not important
        teamService.addTeam(firstTeamName,"Lviv","NO");
        teamService.addTeam(secondTeamName,"Lviv","NO");

        //Getting added teams by names
        firstTeam = teamService.getTeamByName(firstTeamName);
        secondTeam = teamService.getTeamByName(secondTeamName);

        //If team are successfully obtained, then id will not be 0
        assertNotEquals(0,firstTeam.getId());
        assertNotEquals(0,secondTeam.getId());

        System.out.println("addTeam test passed!");
    }

    @Test
    public void getTeamById() throws Exception {
        //For junit4 test ordering not unknown, so we need check
        //if first or second teams are initialized (addTeam() test must be before getTeamById())
        if(firstTeam.getId()!=0){
            assertNotNull(teamService.getTeamById(firstTeam.getId()));
        }else{
            //null will be if team not in db (or method addTeam() not called yet
            assertNull(teamService.getTeamById(firstTeam.getId()));
        }

        if(secondTeam.getId()!=0){
            assertNotNull(teamService.getTeamById(secondTeam.getId()));
        }else{
            //null will be if team not in db (or method addTeam() not called yet
            assertNull(teamService.getTeamById(secondTeam.getId()));
        }
        System.out.println("getTeamById test passed!");
    }



}