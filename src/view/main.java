package view;

import java.io.IOException;
import java.time.Instant;

import core.RepoIterator;
import core.Student;

public class main {

	public static void main(String[] args) throws IOException {
		
		RepoIterator iterator = new RepoIterator();
		
		Instant dateMin = Instant.parse("13/05/2014");
		Instant dateMax = Instant.parse("14/05/2014");
		iterator.setDateMin(dateMin);
		iterator.setDateMax(dateMax);
		
		
		while(iterator.hasNext()){
			Student temp = iterator.next();
			
			
			
		}
		
		

	}

}
