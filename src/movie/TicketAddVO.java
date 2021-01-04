package movie;

import java.sql.Date;

public class TicketAddVO {
	int ticketNo;
	Date bookDate;
	int schNo;
	int seatNo;
	String id;
	
	@Override
	public String toString() {
		return "TicketVO [ticketNo=" + ticketNo + ", bookDate=" + bookDate + ", schNo=" + schNo + ", seatNo=" + seatNo
				+ ", id=" + id + "]";
	}
	
	public TicketAddVO(int ticketNo, int schNo, int seatNo, String id) {
		this.ticketNo = ticketNo;
		this.schNo = schNo;
		this.seatNo = seatNo;
		this.id = id;
	}

	public TicketAddVO() {
		
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
