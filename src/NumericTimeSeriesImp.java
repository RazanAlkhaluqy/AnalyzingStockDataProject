import java.util.Date;
public class NumericTimeSeriesImp implements NumericTimeSeries  {
    
TimeSeries<Double> TimeSeriesD;
    
public NumericTimeSeriesImp()
{
TimeSeriesD = new TimeSeriesImp<Double> ();
}
    
//Calculates and returns the moving average of the data points over a specified period.
//The value period must be strictly positive. If the time series is empty,
//the output must be empty.

public NumericTimeSeries calculateMovingAverage(int period){

NumericTimeSeries newData = new NumericTimeSeriesImp();
        
if ( period > 0 &&  !TimeSeriesD.empty())
 {
  DLL<DataPoint<Double>> data = TimeSeriesD.getAllDataPoints();
  boolean stop = false;

  for ( int iter = 0 ; iter <= data.size() - period; iter++)
      {
             
   data.findFirst();
                
  for ( int i = 0 ; i < iter && !data.last() ; i++)
   data.findNext();
                
   double total = 0;
   Date date = data.retrieve().date;
   for (int i = 1 ; i <= period ; i++)
   {
     total += data.retrieve().value;
     data.findNext();
   }
     newData.addDataPoint(new DataPoint<Double>(date, total/period));
      }
   }
        
      return newData;
    }

//Returns the maximum value in the time series. Time series must not be empty.
  public DataPoint<Double> getMax()
  {
  DataPoint<Double> MaxData = null;
  if (!TimeSeriesD.empty())
        {
  DLL<DataPoint<Double>> dll = TimeSeriesD.getAllDataPoints();
  dll.findFirst();
  MaxData = dll.retrieve();
  for (int i = 0 ; i < dll.size() ; i++) {
     if (MaxData.value.compareTo(dll.retrieve().value) < 0)
     MaxData = dll.retrieve();
     dll.findNext();
   }
       }
        
     return MaxData;
   }
    
//Returns the minimum value in the time series. Time series must not be empty.
 public DataPoint<Double> getMin() {
 DataPoint<Double> MinData = null;
 
 if (!TimeSeriesD.empty())
  {
   DLL<DataPoint<Double>> dll = TimeSeriesD.getAllDataPoints();
   dll.findFirst();
   MinData = dll.retrieve();
   for (int i = 0 ; i < dll.size() ; i++)
            {
    if (MinData.value.compareTo(dll.retrieve().value) > 0)
    MinData = dll.retrieve();
    dll.findNext();
            }
   }
        
      return MinData;
    
    }

    @Override
    public int size() {
    return TimeSeriesD.size();
    }

    @Override
    public boolean empty() {
    return TimeSeriesD.empty();
    }

    @Override
    public DLL<Date> getAllDates() {
    return TimeSeriesD.getAllDates();
    }

    @Override
    public Date getMinDate() {
    return TimeSeriesD.getMinDate();
    }

    @Override
    public Date getMaxDate() {
    return TimeSeriesD.getMaxDate();
    }

    @Override
    public boolean addDataPoint(DataPoint<Double> dataPoint) {
    return TimeSeriesD.addDataPoint(dataPoint);
    }

    @Override
    public boolean updateDataPoint(DataPoint<Double> dataPoint) {
    return TimeSeriesD.updateDataPoint(dataPoint);
    }

    @Override
    public boolean removeDataPoint(Date date) {
    return TimeSeriesD.removeDataPoint(date);
    }

    @Override
    public DLL<DataPoint<Double>> getAllDataPoints() {
    return TimeSeriesD.getAllDataPoints();
    }

    @Override
    public DLL<DataPoint<Double>> getDataPointsInRange(Date startDate, Date endDate) {
    return TimeSeriesD.getDataPointsInRange(startDate, endDate);
    }

    @Override
    public Double getDataPoint(Date date) {
    return TimeSeriesD.getDataPoint(date);
    }

}
