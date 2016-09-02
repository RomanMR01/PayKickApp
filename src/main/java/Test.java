import com.epam.javalab13.dao.ConnectionPool;
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
        Statement statement = ConnectionPool.getConnection().createStatement();
        ResultSet set = statement.executeQuery("SELECT tb.user_id,tb.award FROM totalizator.total_bet tb JOIN totalizator.single_bet sb ON sb.total_bet_id=tb.id WHERE sb.game_id=1");

        while (set.next()){
            System.out.println(set.getInt("user_id") + "\t" + set.getString("award"));//1 1000 for 3:00 PM

        }
    	
    }
}
