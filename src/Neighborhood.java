import java.util.List;
import java.util.ArrayList;

public class Neighborhood 
{
    private String name;
    private List<ServiceRequest> requests;

    public Neighborhood(String name, ServiceRequest firstRequest)
    {
        this.name = name;
        requests = new ArrayList<ServiceRequest>();
        requests.add(firstRequest);
    }

    public void addRequest(ServiceRequest request)
    {
        requests.add(request);
    }

    public int getOverdueCases()
    {
        int overdue = 0;

        for (ServiceRequest r : requests)
        {
            if(r.isOverdue())
            {
                overdue++;
            }
        }

        return overdue;
    }

    public int getOpenCases()
    {
        int open = 0;

        for (ServiceRequest r : requests)
        {
            if(r.isOpen())
            {
                open++;
            }
        }

        return open;
    }

    public double averageDaysOpen()
    {
        double totalDays = 0;

        for (ServiceRequest r : requests) 
        {
            totalDays += r.daysOpen();
        }

        return totalDays/requests.size();
    }

    public String getName()
    {
        return name;
    }
}
