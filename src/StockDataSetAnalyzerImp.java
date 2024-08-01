
import java.util.Date;

public class StockDataSetAnalyzerImp implements StockDataSetAnalyzer {

        StockHistoryDataSet stockDataSet ;
        
        public StockDataSetAnalyzerImp()
        {   this.stockDataSet = new StockHistoryDataSetImp();
        }
        
        // Returns dataset.
        public StockHistoryDataSet getStockHistoryDataSet()
        {
            return stockDataSet;
        }

        // Sets dataset.
        public void setStockHistoryDataSet(StockHistoryDataSet stockHistoryDataSet)
        {
                DLLComp <String> companyNames = stockHistoryDataSet.getAllCompanyCodes();
            if (! companyNames.empty())
            {
            	companyNames.findFirst();
                for ( int i=0; i< companyNames.size() ; i++)
                {
                    
                    StockHistory SH = new StockHistoryImp();
                    SH.SetCompanyCode(companyNames.retrieve());
                    StockHistory company = stockHistoryDataSet.getStockHistory(companyNames.retrieve());
                    DLL <DataPoint<StockData>> data = company.getTimeSeries().getAllDataPoints();
                    if (! data.empty())
                    {
                        data.findFirst();
                        for ( int j=0; j< data.size() ; j++)
                        {
                            DataPoint dataPointNew = data.retrieve();
                            StockData stockDataNew = (StockData) dataPointNew.value;
                            
                            SH.addStockData(dataPointNew.date, 
                                    new StockData(stockDataNew.open, stockDataNew.close, stockDataNew.high, stockDataNew.low, stockDataNew.volume));
                            
                            data.findNext();
                        }
                        
                    }
                    else
                        System.out.println("No Company Available");

                   
                    stockDataSet.addStockHistory(SH);
                    companyNames.findNext();
                }
           }
            else
                System.out.println("No Company Available");
        }

        // Returns the list of company codes sorted according to their stock performance
        // between startDate and endDate. It returns an empty list if either dates is
        // null.
        public DLLComp<CompPair<String, Double>> getSortedByPerformance(Date startDate, Date endDate)
        {
            DLLComp<CompPair<String, Double>> dllcomp = new DLLCompImp <CompPair<String, Double>>();
            DLLComp <String> companyNames = stockDataSet.getAllCompanyCodes();
            
            if (! (startDate == null && endDate == null) && (! companyNames.empty()))
            {
            	companyNames.findFirst();
                for ( int i=0; i< companyNames.size() ; i++)
                {
                    
                   String CompanyName = companyNames.retrieve();
                    StockData data1 =  stockDataSet.getStockHistory(CompanyName).getStockData(startDate);
                    StockData data2 =  stockDataSet.getStockHistory(CompanyName).getStockData(endDate);
                    
                    Double performance = new Double(0);
                    
                    if (data1 != null && data2 != null)
                    {
                        double day1 = data1.close;
                        double day2 = data2.close;
                        
                        performance = (day2 - day1) / day1;
                    }

                    CompPair<String, Double> val = new CompPair<String, Double>(CompanyName, performance);
                    dllcomp.insert(val);
                    companyNames.findNext();
                }
                dllcomp.sort(false);
           }
            return dllcomp;
        }

        // Returns the list of company codes sorted according to their total volume
        // between startDate and endDate inclusive. If startDate is null, fetches from
        // the earliest date. If endDate is null, fetches to the latest
        // date.
        public DLLComp<CompPair<String, Long>> getSortedByVolume(Date startDate, Date endDate)
        {
            DLLComp<CompPair<String, Long>> dllcomp = new DLLCompImp <CompPair<String, Long>>();
            DLLComp <String> companyNames = stockDataSet.getAllCompanyCodes();
            
            if (! companyNames.empty())
            {
            	companyNames.findFirst();
                for ( int i=0; i< companyNames.size() ; i++)
                {
                    
                   String CompanyName = companyNames.retrieve();
                    DLL <DataPoint<StockData>> data = stockDataSet.getStockHistory(CompanyName).getTimeSeries().getDataPointsInRange(startDate, endDate);

                    Long vol = new Long(0);
                    
                    if (! data.empty())
                    {
                        data.findFirst();
                        for (int j = 0 ; j < data.size() ; j++)
                        {
                            vol += data.retrieve().value.volume;
                            data.findNext();
                        }
                    }
                    
                    CompPair<String, Long> val = new CompPair<String, Long>(CompanyName, vol);
                    dllcomp.insert(val);
                    companyNames.findNext();
                }
                dllcomp.sort(false);
           }
            return dllcomp;
        }

        // Returns the list of company codes sorted by the maximum single day price
        // increase between startDate and endDate inclusive. If startDate is null,
        // fetches from the earliest date. If endDate is null, fetches to the latest
        // date.
        public DLLComp<CompPair<Pair<String, Date>, Double>> getSortedByMSDPI(Date startDate, Date endDate)
        {
            DLLComp<CompPair<Pair<String, Date>, Double>> dllcomp = new DLLCompImp<CompPair<Pair<String, Date>, Double>>();
            DLLComp <String> companyNames = stockDataSet.getAllCompanyCodes();
            
            if (! companyNames.empty())
            {
            	companyNames.findFirst();
                for ( int i=0; i< companyNames.size() ; i++)
                {
                    
                   String CompanyName = companyNames.retrieve();
                   Pair<String, Date> company_data = null;
                   Double maxSPDI = new Double (0);
                   Date maxDay = new Date ("1/1/1950");
                   
                   DLL <DataPoint<StockData>> data = stockDataSet.getStockHistory(CompanyName).getTimeSeries().getDataPointsInRange(startDate, endDate);

                    if (! data.empty()) 
                    {
                        DLLComp <CompPair<Date,Double>> allDays = new DLLCompImp <CompPair<Date,Double>> ();
                        
                        data.findFirst();
                        while (!data.last())    
                        {
                            double SDPI = (data.retrieve().value.close - data.retrieve().value.open)/ data.retrieve().value.open;
                            CompPair<Date,Double> val = new CompPair<Date,Double>(data.retrieve().date, SDPI);
                            allDays.insert(val);
                            data.findNext();
                        }
                        allDays.sort(true);
                        maxDay = allDays.getMax().first;
                        maxSPDI = new Double (allDays.getMax().second);
                    
                        company_data = new Pair<String, Date>(CompanyName, maxDay);
                        CompPair<Pair<String, Date>, Double> val = new CompPair<Pair<String, Date>, Double>(company_data, maxSPDI);
                        dllcomp.insert(val);
                    
                    }
                    companyNames.findNext();
                }
                dllcomp.sort(false);
           }
            return dllcomp;
        }
}