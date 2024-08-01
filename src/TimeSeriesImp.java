import java.util.Date;

public class TimeSeriesImp<T> implements  TimeSeries<T> {
	DLLComp<CompPair<DataPoint<T>, Date>> dates;
	
	 public TimeSeriesImp()
	    {
	        dates = new DLLCompImp<CompPair<DataPoint<T>, Date>>();
	    } 
	 
	 // Returns the number of elements in the time series.
	public int size() {
	return dates.size();
	}

	// Returns true if the time series is empty, false otherwise.
	public boolean empty() {
		return dates.empty();
	}

	// Retrieves the data corresponding to a specific date. This method returns the
	// data point for the specified date, or null if no such data point exists.
	public T getDataPoint(Date date) {
		T data;
		if(this.dates.empty())
			return null;
	  dates.findFirst();
      for( int i=0; i <this.dates.size(); i++)
      {  if(dates.retrieve().second.compareTo(date) == 0 )
    		  return dates.retrieve().first.value;
         dates.findNext();
      }
      return null;
	}	  
	// Return all dates in increasing order.
	public DLL<Date> getAllDates(){
		if(dates.empty())
			return null;
		DLL<Date> allDates= new DLLImp<Date>();
	    
		 dates.sort(true);
		 dates.findFirst();
		 for(int i=0; i<dates.size();i++)
		 {
			 allDates.insert(dates.retrieve().second);
			  dates.findNext();
		 } return allDates;
	}

	// Returns min date. Time series must not be empty.
	public Date getMinDate() {
		dates.sort(true);
		return dates.getMin().second;
	}

	// Returns max date. Time series must not be empty.
	public Date getMaxDate() {
		dates.sort(true);
		return dates.getMax().second;
	}

	// Adds a new data point to the time series. If successful, the method returns
	// true. If date already exists, the method returns false.
	public boolean addDataPoint(DataPoint<T> dataPoint) {
	 CompPair<DataPoint<T>, Date> datapoint = new CompPair<DataPoint<T>, Date>(dataPoint, dataPoint.date);
		
	   if (!dates.empty()) {
		dates.findFirst();
		for(int i=0; i<dates.size();i++) 
		{ if ( dates.retrieve().compareTo(datapoint) == 0)
			return false;
	   	  else 
	   	    dates.findNext();
		}
	   }
		dates.insert(datapoint);
		    return true;
	}

	// Updates a data point. This method returns true if the date exists and the
	// update was successful, false otherwise.
	public boolean updateDataPoint(DataPoint<T> dataPoint) {
		if(dates.empty())
			return false;
		dates.findFirst();
		for(int i=0; i<dates.size();i++) 
			if (dates.retrieve().second.compareTo(dataPoint.date)==0) 
			{  CompPair<DataPoint<T>, Date> datapoint = new CompPair<DataPoint<T>, Date>(dataPoint, dataPoint.date);
		    	datapoint.first = dates.retrieve().first;
		    	datapoint.second = dates.retrieve().second;
               dates.update(datapoint);
				return true;
			} dates.findNext();
		return false;
	}
	// Removes a data point with given date from the time series. This method
	// returns true if the data point was successfully removed, false otherwise.
	public boolean removeDataPoint(Date date) {
		if(dates.empty())
			return false;
		for(int i=0; i<dates.size();i++) {
			if (dates.retrieve().second.compareTo(date) ==0)
			{   dates.remove();
			     return true;
			}
		  dates.findNext();
		} return false;
	}
       
	// Retrieves all data points in the time series as a DLL that is sorted in
	// increasing order of date.
	public DLL<DataPoint<T>> getAllDataPoints(){
		DLL<DataPoint<T>> datapoints=new DLLImp<DataPoint<T>>();
	   if (! dates.empty()) {
			 dates.sort(true);
			 dates.findFirst();
		for(int i=0; i< dates.size();i++) 
		 {	datapoints.insert(dates.retrieve().first);
			dates.findNext();
		 }	
	   } return datapoints;
    }
        

	// Gets data points from startDate to endDate inclusive. If startDate is null,
	// fetches from the earliest date. If endDate is null, fetches to the latest
	// date. Returns sorted list in increasing date order.
	public DLL<DataPoint<T>> getDataPointsInRange(Date startDate, Date endDate){
		if(dates.empty())
			return null;
		DLL<DataPoint<T>> startToEnd= getAllDataPoints();
   if(!startToEnd.empty()) {
		 startToEnd.findFirst();
	  for(int i=0; i< startToEnd.size();i++) 
	  {  if(startDate !=null && endDate != null) 
		  {   while(startToEnd.retrieve().date.compareTo(startDate) < 0)
                    startToEnd.remove();   
		  }  if(startDate !=null && endDate != null) 
		  {  while(startToEnd.retrieve().date.compareTo(endDate) <= 0)
                    startToEnd.findNext();
		     while(startToEnd.retrieve().date.compareTo(endDate) > 0)
		        	 startToEnd.remove();
		  }  
	  } 
	} return startToEnd;
  } 
} 