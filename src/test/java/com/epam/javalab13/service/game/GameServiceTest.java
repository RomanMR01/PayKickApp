package com.epam.javalab13.service.game;

import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Status;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Testing game services
 */
public class GameServiceTest {
    private static GameService gameService = null;
    private static Game game;

    @BeforeClass
    public static void init() {
        gameService = new GameService();
    }

    @Test
    public void createGame() throws Exception {

        //These fields must be also hardcoded
        //If you want change them, you need first change them at another test
        String bookmakerName = "JUnit Four";//From UserServiceTest
        String firstTeamName = "JUnit4 Team";//From TeamServiceTest
        String secondTeamName = "JUnit5 Team";//From TeamServiceTest

        //Game date must be in range from +1 day from now to +2 month from now
        Date gameDate = new Date(new Date().getTime() + (172_800_000));//We add + 2 days
        //Format for game date should be "yyyy-MM-dd'T'HH:mm"
        String gameDateInString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(gameDate);

        //And finally we add game to DB
        //Game will not be created if:
        //- Incorrect date
        //- No such bookmaker(user)
        //- No such teams (first or second)
        game = gameService.addNewGame("JUnit test game!", "Lviv", gameDateInString, firstTeamName, secondTeamName, bookmakerName);

        //TEST!
        //If we add new game successfully - game id will not be 0
        assertNotEquals(0,game.getId());

        System.out.println("createGame test passed!");
    }

    @Test
    public void cancelGame() throws Exception {
        //Get game from DB
        game = gameService.getGameById(game.getId());

        //TEST!
        //At this time game status must be active
        assertEquals(Status.NEW, game.getStatus());

        //Now we canceled game
        gameService.cancelGame(game.getId());
        //Refresh game
        game = gameService.getGameById(game.getId());

        //TEST!
        //And now game status must be canceled
        assertEquals(Status.CANCELED, game.getStatus());

        System.out.println("cancelGame test passed!");
    }
}