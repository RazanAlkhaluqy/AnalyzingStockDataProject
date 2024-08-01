import java.util.Date;
public class StockHistoryImp implements StockHistory{

TimeSeries<StockData> TimeSer;
String CompanyCode; 
        
public StockHistoryImp()
 {
TimeSer = new TimeSeriesImp<StockData> ();
 }

//Returns the number of elements in the history.
public int size(){
return TimeSer.size();}

//Returns true if the history is empty, false otherwise.
public boolean empty(){
return TimeSer.empty();}

//Clears all data from the storage.
public void clear(){
TimeSer = new TimeSeriesImp<StockData> ();}

//Returns company code.
public String getCompanyCode(){
return CompanyCode;}

//Sets company code
public void SetCompanyCode(String companyCode){
CompanyCode = companyCode;}

//Returns stock history as a time series.
public TimeSeries<StockData> getTimeSeries(){
return TimeSer;}

//Retrieves StockData for a specific date, or null if no data is found.
public StockData getStockData(Date date){
return TimeSer.getDataPoint(date);}

//Adds a new StockData and returns true if the operation is successful, false otherwise.
public boolean addStockData(Date date, StockData stockData)
  {
DataPoint<StockData> dataPoint = new DataPoint<StockData>(date, stockData);
return TimeSer.addDataPoint(dataPoint);
  }

//Remove the StockData of a given date, and returns true if the operation is successful
//false otherwise.
public boolean removeStockData(Date date){
return TimeSer.removeDataPoint(date);}

}
