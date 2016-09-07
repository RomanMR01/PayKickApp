import java.sql.SQLException;

import com.epam.javalab13.dao.game.PlayerCoefficientDAO;
import com.epam.javalab13.service.PaymentService;

/*
 * Test for checking sql query
 */
public class Test {
    public static void main(String[] args) throws SQLException {
//        Statement statement = ConnectionPool.getConnection().createStatement();
//        ResultSet set = statement.executeQuery("SELECT tb.user_id,tb.award FROM totalizator.total_bet tb JOIN totalizator.single_bet sb ON sb.total_bet_id=tb.id WHERE sb.game_id=1");
//
//        while (set.next()){
//            System.out.println(set.getInt("user_id") + "\t" + set.getString("award"));//1 1000 for 3:00 PM
//
//        }
    	
//    	GameDAO dao = new GameDAO();
////    	System.out.println(dao.findAll());
//    	
//    	Team team1=new Team(1,"","","",0,0,0,null);
//    	Team team2=new Team(2,"","","",0,0,0,null);
//		Game game = new Game("гра1", "Київ", new Date(), team1, team2, Status.NEW);
////		dao.create(game);
//		game=dao.findById(1);
//		game.setDate(new Date());
//		dao.update(game);
//		System.out.println(game);
		
//    	PlayerCoefficientDAO dao = new PlayerCoefficientDAO();
//		
//    	System.out.println(dao.findAll());
    	
    	PaymentService pc = new PaymentService();
//    	pc.refillAccount("1");
//    	pc.p2pCredit("1","USD","4731195301524633");
    	
    }
}
