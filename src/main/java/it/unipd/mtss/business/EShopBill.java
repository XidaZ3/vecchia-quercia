////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import java.util.List;
import java.util.TreeMap;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.business.EShopBill;

public class EShopBill implements Bill{
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException{

        if(itemsOrdered.size() > 30){                                           //requisito 6
            throw new BillException(0, "troppi ordini");
        }

        double total = 0.0;
        TreeMap<itemType, Integer> counters = new TreeMap<itemType, Integer>();
        counters.put(itemType.Processor, 0);
        counters.put(itemType.Motherboard, 0);
        counters.put(itemType.Mouse, 0);
        counters.put(itemType.Keyboard, 0);

        TreeMap<itemType, Double> cheapest = new TreeMap<itemType, Double>();
        cheapest.put(itemType.Processor, Double.POSITIVE_INFINITY);
        cheapest.put(itemType.Motherboard, Double.POSITIVE_INFINITY);
        cheapest.put(itemType.Mouse, Double.POSITIVE_INFINITY);
        cheapest.put(itemType.Keyboard, Double.POSITIVE_INFINITY);

        itemType type = null;

        for(EItem item : itemsOrdered){

            type = item.getType();
            counters.put(type, counters.get(type) + 1);
            if(item.getPrice() < cheapest.get(type)){
                cheapest.put(type, item.getPrice());
            }

            total += item.getPrice();                                                                         //requisito 1
        }
        if(counters.get(itemType.Processor) >= 5){                                                            //requisito 2
            total -= cheapest.get(itemType.Processor) / 2;
        }
        if(counters.get(itemType.Mouse) >= 10){                                                               //requisito 3
            total -= cheapest.get(itemType.Mouse);
        }
        if(counters.get(itemType.Mouse)==counters.get(itemType.Keyboard)){                                    //requisito 4
            total -= Math.min(cheapest.get(itemType.Mouse), cheapest.get(itemType.Keyboard)) / 2;
        }
        if(total < 10){                           //requisito 7
            total += 2;
        }
        if(total > 1000){
            total -= total * 0.1;                  //requisito 5
        }
        return total;
    }
}
