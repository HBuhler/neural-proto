package core;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise {

	private List<Event> events;


	public Exercise(Student student, String exo){
		events = new ArrayList<Event>();
		boolean inIdle = false;
		Instant idleStart = null;
		for(Event e : student.getEvents()){
			if(e.getExoName().equals(exo)){
				if(inIdle){
					this.addIdleEvent(idleStart, e.getCommitTime());
					inIdle=false;
				}
				events.add(e);
			}
			else
				if(!inIdle){
					idleStart = e.getCommitTime();
					inIdle = true;
				}

		}

	}

	private void addIdleEvent(Instant start, Instant end){
		this.events.add(Event.getIdleEVent(start, end));
	}

	public int getSize(){
		return events.size();
	}

	public Event getEvent(int i){
		return events.get(i);
	}

	public void sort(){
		Collections.sort(events);
	}

	public int firstCommitSuccessful(){
		for(int i = 0; i < events.size(); i ++)
			if(events.get(i).getCommitType().equals(Event.Executed) && events.get(i).getResultCompil().equals(Event.Success))
				return i;
		return -1;		
	}

}
