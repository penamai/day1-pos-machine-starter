package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        //TODO: this can be remove since it is not used anymore
        ItemsLoader itemsLoader = new ItemsLoader();

        List<Item> items = ItemsLoader.loadAllItems();
        List<String> uniqueBarcodes = filterUniqueBarcodes(barcodes);
        HashMap<String, Integer> quantity = countEachItem(barcodes, uniqueBarcodes);

        return generateReceipt(uniqueBarcodes, quantity, items);
    }

    private List<String> filterUniqueBarcodes(List<String> barcodes){
        return barcodes.stream().distinct().collect(Collectors.toList());
    }

    private HashMap<String, Integer> countEachItem(List<String> barcodes, List<String> uniqueBarcodes){
        HashMap<String, Integer> quantity = new HashMap<String, Integer>();
        uniqueBarcodes.forEach(barcode-> quantity.put(barcode, Collections.frequency(barcodes, barcode)));

        return quantity;
    }

    private String generateReceipt(List<String> uniqueBarcodes, HashMap<String, Integer> quantity, List<Item> items) {
        StringBuilder output = new StringBuilder("***<store earning no money>Receipt***\n");
        //TODO: should convert this into Stream to shorten and make it simpler
        for (String barcode : uniqueBarcodes) {
            String itemName = getItemName(barcode, items);
            int itemQuantity = quantity.get(barcode);
            int itemPrice = getItemPrice(barcode, items);
            int itemSubtotal = getSubtotal(itemQuantity, itemPrice);

            output.append(generateOneItem(itemName, itemQuantity, itemPrice, itemSubtotal));
        }
        output.append("----------------------\nTotal: 24 (yuan)\n**********************");
        return output.toString();
    }

    //TODO: can place these functions into a new Object class to achieve single responsibility
    private String getItemName(String barcode, List<Item>items){
        return Objects.requireNonNull(items.stream().filter(item -> barcode.equals(item.getBarcode())).findAny().orElse(null)).getName();
    }

    private int getItemPrice(String barcode, List<Item> items){
        return Objects.requireNonNull(items.stream().filter(item -> barcode.equals(item.getBarcode())).findAny().orElse(null)).getPrice();
    }
    private int getSubtotal(int quantity, int price){
        return quantity*price;
    }

    private String generateOneItem(String name, int quantity, int price, int subtotal){
        return "Name: " + name + ", Quantity: " + quantity + ", Unit price: " + price + " (yuan), Subtotal: " + subtotal + " (yuan)\n";
    }
}
