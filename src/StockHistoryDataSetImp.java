
import java.lang.String;
import java.util.Date;

public class StockHistoryDataSetImp  implements StockHistoryDataSet {
        
        Map <String , StockHistory> Company;
        
    
        public StockHistoryDataSetImp()
        {
        	Company = new BST<String , StockHistory>();
        }
        
        // Returns the number of companies for which data is stored.
        public int size()
        {
            return Company.size();
        }

        // Returns true if there are no records, false otherwise.
        public boolean empty()
        {
            return Company.empty();
        }

        // Clears all data from the storage.
        public void clear()
        { 
        	Company.clear();
        }

        // Returns the map of stock histories, where the key is the company code.
        public Map<String, StockHistory> getStockHistoryMap()
        {
            return Company;
        }

        // Returns the list of all company codes stored in the dataset sorted in
        // increasing order.
        public DLLComp<String> getAllCompanyCodes()
        {
            return Company.getKeys();
        }

        // Retrieves the stock history for a specific company code. This method returns
        // null if no data is found.
        public StockHistory getStockHistory(String companyCode)
        {
            if (Company.find(companyCode))
                return Company.retrieve();
            return null;
        }

        // Adds the stock history of a specific company. This method returns true if the
        // operation is successful, false otherwise (company code already exists).
        public boolean addStockHistory(StockHistory stockHistory)
        {
            if ( ! Company.find(stockHistory.getCompanyCode()))
            {
            	Company.insert(stockHistory.getCompanyCode(), stockHistory);
                return true;
            }
            return false;
        }

        // Removes the stock history of a specific company from the storage. This method
        // returns true if the operation is successful and false if the company code
        // does not exist.
        public boolean removeStockHistory(String companyCode)
        {
            if ( Company.find(companyCode))
            {
            	Company.remove(companyCode);
                return true;
            }
            return false;
        }
        
}
