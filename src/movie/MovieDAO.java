package movie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

public class MovieDAO {
	private static MovieDAO instance = new MovieDAO();
	private MovieDAO() {}
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public ArrayList<MovieVO> movieList = new ArrayList<>(); //영화 정보 리스트
	public ArrayList<MemberVO> memberList = new ArrayList<>(); //회원 정보 리스트
	public ArrayList<ScheduleVO> scheduleList = new ArrayList<>(); //시간표 정보 리스트
	public ArrayList<TicketVO> ticketList = new ArrayList<>(); //티켓 정보 리스트
	public ArrayList<TicketVO> ticketList2 = new ArrayList<>(); //로그인 한사람이 예매한 티켓을 분리하는 리스트
	public int[] seatNoArr = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //티켓 좌석정보
	public boolean first = true;
	public boolean firstPay = false;
	public int loginCheck = 0;//로그인 상태
	public int scheduleNo = 0;//예매 스케줄 번호
	public int loginMember = 0;//현재 로그인 되어있는 사람 체크
	public int schMax = 28;//스케줄넘버 최대치
	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 접속 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Connection getMysqlConnection() {
		String url = "jdbc:mysql://localhost:3306/mysqldb";
		String user = "root";
		String pw = "root";
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//mysql 드라이버 로딩
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("mysql 접속성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) try {rs.close();} catch (Exception e) {e.printStackTrace();}
		if(pstmt != null) try {pstmt.close();} catch (Exception e) {e.printStackTrace();}
		if(conn != null) try {conn.close();} catch (Exception e) {e.printStackTrace();}
	}
	
//	public int insertMovie(MovieVO vo){ //영화를 추가시키는 함수
//		int cnt = 0;
//	try {
//		String insertSql = "INSERT INTO movie(movieNo, movieName, category, runtime, img, info)"
//				+ " VALUES(?, ?, ?, ?, ?, ?)";
//		Connection conn = getConnection();
//		PreparedStatement pstmt = conn.prepareStatement(insertSql);
//		
//		pstmt.setInt(1, vo.movieNo);
//		pstmt.setString(2, vo.movieName);
//		pstmt.setInt(3, vo.category);
//		pstmt.setInt(4, vo.runtime);
//		pstmt.setString(5, vo.img);
//		pstmt.setString(6, vo.info);
//		cnt = pstmt.executeUpdate();
//		
//		movieList.add(vo);
//		close(null, pstmt, conn);
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	
//		return cnt;
//}
	
	public void firstMember() { //첫 실행시 맴버 테이블의 정보를 리스트에 추가해줌
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM member";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			
			MemberVO vo = null;
			while(rs.next()){
				vo = new MemberVO(rs.getString("id"), rs.getString("pw"), rs.getString("email"),
					rs.getString("tel"), rs.getDate("birth"));
				memberList.add(vo);
			}
			close(rs, pstmt, conn);
			first = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void movieList() { //영화 정보를 리스트에 추가
		movieList.clear();
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM movie ORDER BY movieNo";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			
			MovieVO vo = null;
			while(rs.next()){
				vo = new MovieVO(rs.getInt("movieNo"), rs.getString("movieName"), rs.getInt("category"),
					rs.getInt("runtime"), rs.getString("img"), rs.getString("info"));
				movieList.add(vo);
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkLogin(String id, String pw) {// 아이디와 비밀번호를 확인함
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).id.equals(id)) {
				if(memberList.get(i).pw.equals(pw)) {
					loginMember = i;
					return true;
				}
			}
		}
		return false;
	}
	
	public void sortList(int category) { //사용자가 원하는 장르만 리스트에 추가한다
		movieList.clear();
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM movie WHERE category = ?";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, category);
			ResultSet rs = pstmt.executeQuery();
			
			MovieVO vo = null;
			while(rs.next()){
				vo = new MovieVO(rs.getInt("movieNo"), rs.getString("movieName"), rs.getInt("category"),
					rs.getInt("runtime"), rs.getString("img"), rs.getString("info"));
				movieList.add(vo);
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void scheduleAdd(int movieNo) {
		scheduleList.clear();
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM schedule where movieNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, movieNo);
			ResultSet rs = pstmt.executeQuery();
			
			ScheduleVO vo = null;
			while(rs.next()){
				vo = new ScheduleVO(rs.getInt("schNo"), rs.getInt("movieNo"), rs.getTime("runDay"),
					rs.getInt("runtime"), rs.getInt("roomNo"));
				scheduleList.add(vo);
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scheduleMovie(int movieNo) { //사용자가 원하는 장르만 리스트에 추가한다
		movieList.clear();
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM movie WHERE movieNo = ?";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, movieNo);
			ResultSet rs = pstmt.executeQuery();
			
			MovieVO vo = null;
			while(rs.next()){
				vo = new MovieVO(rs.getInt("movieNo"), rs.getString("movieName"), rs.getInt("category"),
					rs.getInt("runtime"), rs.getString("img"), rs.getString("info"));
				movieList.add(vo);
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void countRoom(int schNo, int roomNo) { //room테이블에 자릿수를 카운트 시키는 함수
		int seatCnt = maxRoom(schNo, roomNo) + 1;
		try {
			Connection conn = getConnection();
			int cnt;
			String updateSql = "UPDATE room SET seatCnt=? WHERE schNo=? and roomNo=?";
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, seatCnt);
			pstmt.setInt(2, schNo);
			pstmt.setInt(3, roomNo);
			cnt = pstmt.executeUpdate();
			close(null, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int maxRoom(int schNo, int roomNo) { //room테이블에 자릿수를 가져오는 함수
		int seatCnt = 0;
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM room WHERE schNo=? and roomNo=?";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, schNo);
			pstmt.setInt(2, roomNo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				seatCnt = rs.getInt("seatCnt");
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seatCnt;
	}
	
	public void ticketAdd(TicketVO vo) { //티켓을 추가하는 함수
		try {
			int cnt;
			String insertSql = "INSERT INTO ticket(ticketNo, bookDate, schNo, seatNo, id)"
					+ " VALUES(?, SYSDATE, ?, ?, ?)";
			Connection conn = getConnection();
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			
			pstmt.setInt(1, vo.ticketNo);
			pstmt.setInt(2, vo.schNo);
			pstmt.setInt(3, vo.seatNo);
			pstmt.setString(4, vo.id);
			cnt = pstmt.executeUpdate();
			close(null, pstmt, conn);
			ticketAddList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ticketAddList() { //티켓 정보를 리스트에 추가해줌
		ticketList.clear();
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM ticket";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			ResultSet rs = pstmt.executeQuery();
			
			TicketVO vo = null;
			while(rs.next()){
				vo = new TicketVO(rs.getInt("ticketNo"), rs.getDate("bookDate"), rs.getInt("schNo"), rs.getInt("seatNo"),rs.getString("id"));
				ticketList.add(vo); 
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ticketAddList2() {
		ticketList2.clear();
		for(int i = 0; i < ticketList.size(); i++) {
			if(ticketList.get(i).id.equals(memberList.get(loginMember).id)) {
				ticketList2.add(ticketList.get(i));
			}
		}
	}
	 
	public int getMaxNo() { //회원 번호를 증가시키는 함수
		int ticketNo = 0;
		try {
			
			Connection conn = getConnection();
			String getNoSql = "SELECT MAX(ticketNo) FROM ticket";
			PreparedStatement pstmt = conn.prepareStatement(getNoSql);
			ResultSet rs = pstmt.executeQuery();
				
			if(rs.next()){
				ticketNo = rs.getInt(1) + 1;
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ticketNo;
	}
	
	public String category(int category) { //선택한 영화 장르 판별
		String[] categoryS = {"","액션","로멘스","코미디","스릴러","에니메이션"};
		return categoryS[category];
	}
	
	public String movieSchNo(int schNo) { //스케줄 번호로 영화 판별
		String[] movieS = {"어벤져스","노팅힐","아이언맨","겨울왕국2","엑시트", "반도", "23아이덴티디"};
		String msg = "";
		if(schNo <= 4 ) {
			msg = movieS[0];
		}else if(schNo <= 8) {
			msg = movieS[1];
		}else if(schNo <= 12) {
			msg = movieS[2];
		}else if(schNo <= 16) {
			msg = movieS[3];
		}else if(schNo <= 20) {
			msg = movieS[4];
		}else if(schNo <= 24) {
			msg = movieS[5];
		}else if(schNo <= 28) {
			msg = movieS[6];
		}
		return msg;
	}
	
	public void seatNo() {
		resetSeatArr(); 
		try {
			Connection conn = getConnection();
			String selectSql = "SELECT * FROM ticket WHERE schNo = ? ORDER BY seatNo ASC";
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, scheduleNo);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				seatNoArr[rs.getInt("seatNo")-1] = rs.getInt("seatNo");
			}
			close(rs, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetSeatArr() { //seatNoArr를 초기화
		for(int i = 0; i < seatNoArr.length; i++) {
			seatNoArr[i] = 0;
		}
	}
}