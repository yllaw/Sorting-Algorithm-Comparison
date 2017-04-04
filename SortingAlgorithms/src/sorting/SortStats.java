package sorting;

public class SortStats{
	public String sortName;
	public long numberOfSwap;
	public long numberOfComparison;
	public long executionTimeMillis;
	public String runtime;
	private long startTime;
	
	public SortStats(){
		this.startTime = System.currentTimeMillis();
	}
	
	protected void stopExecutionTime(){
		this.executionTimeMillis = System.currentTimeMillis() - this.startTime;
	}
	
	
	public String toString(){
		return ( sortName  + System.lineSeparator() + "Comparisons: " + numberOfComparison + ", Swaps: " + numberOfSwap + ", Time in Millis: " + executionTimeMillis);
	}
	
	public String getSortName(){//TableView needs getters
		return sortName;
	}
	
	public Long getNumberOfComparison(){
		return numberOfComparison;
	}
	
	public Long getNumberOfSwap(){
		return numberOfSwap;
	}
	
	public Long getExecutionTimeMillis(){
		return executionTimeMillis;
	}
	
	public String getRuntime(){
		return runtime;
	}
	
	
}