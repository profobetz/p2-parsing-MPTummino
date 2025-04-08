import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RequestLoader
{
    File loaderFile;
    CSVFormat format;
    CSVParser parser;

    public RequestLoader(File inputFile)
    {
        loaderFile = inputFile;
        format = Builder.create()
            .setHeader()
            .setDelimiter(',')
            .setQuote('"')
            .build();
        try
        {
            parser = CSVParser.parse(loaderFile, StandardCharsets.UTF_8, format);
        }
        catch(IOException e)
        {
            System.out.println("There was an error loading the file.");
        }
    }

    public List<Neighborhood> load()
    {
        List<Neighborhood> neighborhoods = new ArrayList<Neighborhood>();
        for ( CSVRecord next_row : parser.getRecords() ) 
        {   
            String this_rows_open_date = next_row.get("open_dt");
            String this_rows_closed_date = next_row.get("closed_dt");
            boolean this_rows_overdue;
            if(next_row.get("on_time").equals("ONTIME"))
            {
                this_rows_overdue = false;
            }
            else
            {
                this_rows_overdue = true;
            }
            boolean this_rows_open;
            if(next_row.get("case_status").equals("Closed"))
            {
                this_rows_open = false;
            }
            else
            {
                this_rows_open = true;
            }
            String this_rows_reason = next_row.get("reason");
            String this_rows_neighborhood = next_row.get("neighborhood");

            boolean listExistsCheck = false;

            for (Neighborhood n : neighborhoods) 
            {
                if(this_rows_neighborhood.equals(n.getName()))
                {
                    n.addRequest(new ServiceRequest(this_rows_open_date, this_rows_closed_date, this_rows_overdue, this_rows_open, this_rows_reason, this_rows_neighborhood));
                    listExistsCheck = true;
                }
            }

            if(!listExistsCheck) //this whole statement feels SUPER janky
            {
                neighborhoods.add(new Neighborhood(this_rows_neighborhood, new ServiceRequest(this_rows_open_date, this_rows_closed_date, this_rows_overdue, this_rows_open, this_rows_reason, this_rows_neighborhood)));
            }
        }
        return neighborhoods;
    }
}
