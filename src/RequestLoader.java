import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVParser;

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

        return neighborhoods;
    }
}
