package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.UserImpl;
import it.unipd.mtss.model.itemType;


public class EShopBillTest {
    @Test
    public void getOrderPriceFromListOfItems(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Processor, "i7", 10.50));
            add(new EItem(itemType.Processor, "i5", 5.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 166.5, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPriceWithDiscountWhenFiveProcessorsBought(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Processor, "i7", 10.50));
            add(new EItem(itemType.Processor, "i5", 7.50));
            add(new EItem(itemType.Processor, "i2", 2.50));
            add(new EItem(itemType.Processor, "i3", 4.50));
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 183.75, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPriceWithDiscontWhenSameAmountKeybordsAndMouses(){
        //interpretazione di meno caro: tra mouse e tastiere

        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "medio", 17.50));
            add(new EItem(itemType.Mouse, "gigante", 30.00));
            add(new EItem(itemType.Processor, "i3", 4.50));
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
            add(new EItem(itemType.Keyboard, "senza le lucine", 10.50));
            add(new EItem(itemType.Keyboard, "meccanica", 60.00));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 287.75, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPriceErrorWithMoreThan30Orders(){
        //interpretazione di meno caro: tra mouse e tastiere

        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
            add(new EItem(itemType.Mouse, "piccolo", 10.50));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 287.75, 0.1);
        }
        catch(BillException e){
            assertEquals(e.id, 0);
        }
    }

    @Test
    public void getOrderPriceWithAdditionalCostsSinceTotalIsLessThan10(){
        
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "conveniente", 3.50));
            add(new EItem(itemType.Mouse, "conveniente", 3.50));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 9.00, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPriceWithDiscontWhenTotalIsBiggerThan1000(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "di diamanti", 300.50));
            add(new EItem(itemType.Keyboard, "di diamanti", 500.50));
            add(new EItem(itemType.Keyboard, "di diamanti", 500.50));
            add(new EItem(itemType.Motherboard, "indistruttibile", 300.00));
            add(new EItem(itemType.Processor, "alieno", 700.00));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 2071.35, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPriceWithFreeMouseWhenAtLeast10AreBought(){
        List<EItem> lista = new ArrayList<EItem>(){{
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "rotto", 0.50));
            add(new EItem(itemType.Mouse, "economico", 1.50));
            add(new EItem(itemType.Mouse, "base", 5.50));
            add(new EItem(itemType.Mouse, "okay", 10.50));
            add(new EItem(itemType.Mouse, "buono", 15.50));
            add(new EItem(itemType.Mouse, "buonetto", 20.50));
            add(new EItem(itemType.Mouse, "molto buono", 25.50));
            add(new EItem(itemType.Mouse, "buonissimo", 30.50));
        }};
        try{
            assertEquals(new EShopBill().getOrderPrice(lista, new UserImpl(0, "xida")), 110.50, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
