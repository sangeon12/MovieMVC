package movie;

import java.sql.Time;

public class ScheduleVO {
	int schNo;
	int movieNo;
	Time runDay;
	int runtime;
	int roomNo;
	
	public ScheduleVO(int schNo, int movieNo, Time runDay, int runtime, int roomNo) {
		this.schNo = schNo;
		this.movieNo = movieNo;
		this.runDay = runDay;
		this.runtime = runtime;
		this.roomNo = roomNo;
	}
	
	public ScheduleVO() {
		
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public Time getRunDay() {
		return runDay;
	}

	public void setRunDay(Time runDay) {
		this.runDay = runDay;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@Override
	public String toString() {
		return "ScheduleVO [schNo=" + schNo + ", movieNo=" + movieNo + ", runDay=" + runDay + ", runtime=" + runtime
				+ ", roomNo=" + roomNo + "]";
	}
}
