package core;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;




public class Student {
	
	private List<Event> events;
	private HashMap<String, Exercise> exos;
	boolean isSort = false;
	private Collection<Session> sessions;
	private String branchName;
	private ArrayList<String> triedExoName;
 	private Boolean flagExo; //set at true to collect event in Exo object
	
	public Student(String branchName){
		this.branchName = branchName;
		events = new ArrayList<Event>();
		exos = new HashMap<String, Exercise>();
	}
	
	
	public void addEvent(Event e){
		if(!flagExo)				
			events.add(e);
		else
			this.addEventExos(e);
	}
	
	private String previousExoName;
	private void addEventExos(Event e){
		if(exos.containsKey(e.getExoName())){
			if(!previousExoName.isEmpty() && previousExoName != e.getExoName()){
				Exercise exo = exos.get(e.getExoName());
				Instant inst = exo.getEvent(exo.getSize()-1).getCommitTime();
				exos.get(e.getExoName()).addIdleEvent(inst, e.getCommitTime());
			}
			exos.get(e.getExoName()).addCommit(e);}			
		else{
			Exercise exo = new Exercise();
			exo.addCommit(e);
			exos.put(e.getExoName(), exo);			
		}
			
		previousExoName = e.getExoName();		
	}
	
	
	
	public void sortEvent(){
		if(!isSort){
			Collections.sort(events);		
			isSort = true;
		}	
	}

	public void computeSessions(){		
		//if(events.size() < 10)
		//	return;

		this.sortEvent();
		sessions = new ArrayList<Session>();
		boolean sessionActive = false;
		Session temp = null;

		for(Event e : events){
			if(!sessionActive){
				if(temp!=null)
					sessions.add(temp);
				temp = new Session(e);
				sessionActive = true;
			}else
				sessionActive = temp.add(e);
			
		}
	}

	public double getEQ(String exoName){
		this.sortEvent();
		String prevResult = null, prevError = null;
		int nbreEQ = 0, currentEQ;
		double sumEQ = 0;

		for(Event e : events){
			if(e.getCommitType().equals(Event.Executed) && e.getExoName().equals(exoName)){
				if(prevResult == null){
					prevResult = e.getResultCompil();
					prevError = e.getError();
					continue;
				}

				currentEQ = 0;
				if(prevResult.equals(Event.CompilError) && e.getResultCompil().equals(Event.CompilError)){
					currentEQ += 8;
					if(prevError.equals(e.getError()))
						currentEQ += 3;
				}
				sumEQ += currentEQ/11;
				nbreEQ ++;
				prevResult = e.getResultCompil();
				prevError = e.getError();
			}
		}

		if(nbreEQ > 0)
			return sumEQ/nbreEQ;
		else
			return -1;
	}		

	public HashMap<String, Integer> getHashMapExoTriedAndFailed(){
		if(events.size()<1)
			return null;
		
		HashMap<String, Integer> hash= new HashMap<>();
		for(Event e : events){
			if(!hash.containsKey(e.getExoName()))
				hash.put(e.getExoName(), 1);
			if(e.getResultCompil() != null && !e.getResultCompil().equals(Event.Success))
				hash.replace(e.getExoName(), 0);
		}
		return hash;
	}
	public Collection<Event> getEventsByExo(String exo){
		Collection<Event> eventList = new ArrayList<Event>();
		for(Event e : events)
			if(e.getExoName() != null && e.getExoName().equals(exo))
				eventList.add(e);
		return eventList;		
	}
	
	public Collection<String> getTriedExoName(){
		if(triedExoName == null){
			triedExoName = new ArrayList<String>();

			for(Event e : events)
				if(e.getCommitType().equals(Event.Executed) && e.getExoName() != null && !triedExoName.contains(e.getExoName()))
					triedExoName.add(e.getExoName());
		}
		return triedExoName;
	}
	
	public void printSession(){
		System.out.println("\n\n Elève : " + branchName +"\n\n");
		if(sessions != null)
			for(Session s : sessions)
				s.printSession();
	}

	public List<Event> getEvents() {
		return events;
	}

	public String getBranchName() {
		return branchName;
	}


	public void setFlagExo(Boolean flagExo) {
		this.flagExo = flagExo;
	}


	public HashMap<String, Exercise> getExos() {
		return exos;
	}
	
	
	static public String getHeader() {
		return "# branch,  exoPassed,  exoAttempted, passedLines, passed,  failed,  compile,  help,  tip,  start,  switch, revert";
	}
	
	public String toString(){
		
		int nbExoPassed = 0;
		int nbExo = 0;
		ArrayList<String> listExoPassed = new ArrayList<String>();
		ArrayList<String> listExo = new ArrayList<String>();
		for(Event e : this.events){

			if(e.getCommitType().equals(Event.Executed) 
					&& e.getResultCompil().equals(Event.Success) 
					&& !listExoPassed.contains(e.getExoName())){
				nbExoPassed ++;
				listExoPassed.add(e.getExoName());
			}

			if(!listExo.contains(e.getExoName())){
				listExo.add(e.getExoName());
				nbExo ++;
			}

		}
		
		
		return this.getBranchName() + nbExoPassed + nbExo + "passedKine" ; 
	}

}
