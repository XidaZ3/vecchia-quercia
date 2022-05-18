package it.unipd.mtss.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 166.5, 0.1);
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
            add(new EItem(itemType.Processor, "i4", 9.50));
            add(new EItem(itemType.Motherboard, "nuova", 50.00));
            add(new EItem(itemType.Keyboard, "con le lucine", 100.50));
        }};
        try{
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 193.25, 0.1);
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21) ), 282.5, 0.1);
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 287.75, 0.1);
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 9.00, 0.1);
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 2071.35, 0.1);
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
            assertEquals(new EShopBill(lista, new UserImpl(0, "xida",21), new Date()).getOrderPrice(lista, new UserImpl(0, "xida",21)), 110.50, 0.1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void makeGiftsWhenBoughtBetween18and19hour()
    {
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

        List<EShopBill> ordineDanilo = new ArrayList<EShopBill>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 11, 1, 18, 30, 59);
        ordineDanilo.add(new EShopBill(lista, new UserImpl(0, "Danilo", 21), calendar.getTime()));
        List<EShopBill> ordiniPEGI12 = new LinkedList<>();
        ordiniPEGI12.add(ordineDanilo.get(0));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(1, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(2, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(3, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(4, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(5, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(6, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(7, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(8, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(9, "Danilo", 10), calendar.getTime()));
        ordiniPEGI12.add(new EShopBill(lista, new UserImpl(10, "Danilo", 10), calendar.getTime()));
        EShopBill.makeFreeOrder(ordiniPEGI12);
        assertEquals(ordineDanilo.get(0), ordiniPEGI12.get(0));
    }
}
