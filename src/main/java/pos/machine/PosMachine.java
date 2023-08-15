package pos.machine;

import java.util.*;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    private List<String> filterUniqueBarcodes(List<String> barcodes){
        return barcodes.stream().distinct().collect(Collectors.toList());
    }

    private HashMap<String, Integer> countEachItem(List<String> barcodes, List<String> uniqueBarcodes){
        HashMap<String, Integer> quantity = new HashMap<String, Integer>();
        uniqueBarcodes.forEach(barcode-> quantity.put(barcode, Collections.frequency(barcodes, barcode)));

        return quantity;
    }

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
