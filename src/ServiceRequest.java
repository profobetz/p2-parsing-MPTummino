import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ServiceRequest
{
    private LocalDate openDate;
    private LocalDate closedDate;
    private boolean overdue;
    private boolean open;
    private String reason;
    private String neighborhood;

    public ServiceRequest(String openDate, String closedDate, boolean overdue, boolean open, String reason, String neighborhood)
    {
        try
        {
            this.openDate = LocalDate.parse(openDate);
        }
        catch(DateTimeParseException e)
        {
            this.openDate = null;
        }
        try
        {
            this.closedDate = LocalDate.parse(closedDate);
        }
        catch(DateTimeParseException e)
        {
            this.closedDate = null;
        }
        this.overdue = overdue;
        this.open = open;
        this.reason = reason;
        this.neighborhood = neighborhood;
    }

    public int daysOpen()
    {
        if( openDate == null)
        {
            return 0;
        }
        else if( open )
        {
            return (int) openDate.until(LocalDate.now(), ChronoUnit.DAYS);
        }
        else
        {
            return (int) openDate.until(closedDate, ChronoUnit.DAYS);
        }
    }

    public LocalDate getOpenDate() 
    {
        return openDate;
    }

    public LocalDate getClosedDate() 
    {
        return closedDate;
    }

    public boolean isOverdue() 
    {
        return overdue;
    }

    public boolean isOpen()
    {
        return open;
    }

    public String getReason() 
    {
        return reason;
    }

    public String getNeighborhood() 
    {
        return neighborhood;
    }
 
}
