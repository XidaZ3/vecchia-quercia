////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;
import java.util.List;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.Item;


/**
 * Hello world!
 *
 */
public class App 
{

    public interface Bill {
      double getOrderPrice(List<EItem> itemsOrdered, User user);
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public int sum(int a, int b)
    {
         return a+b;
    }
}
