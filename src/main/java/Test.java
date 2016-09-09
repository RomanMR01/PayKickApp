import com.epam.javalab13.dao.ConnectionPool;
import com.epam.javalab13.dao.game.GameDAO;
import com.epam.javalab13.model.game.Game;
import com.epam.javalab13.model.game.Team;
import com.epam.javalab13.util.DateConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/*
 * Test for checking sql query
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        Game game = new Game();
        game.setTitle("G1");
        game.setLocation("L1");
        game.setDate(new Date());
        Team team = new Team();
        team.setId(5);
        game.setFirstTeam(team);

        Team team2 = new Team();
        team2.setId(6);
        game.setSecondTeam(team2);

        System.out.println(game);

        new GameDAO().addGame(game);

        System.out.println(game);
    }
}