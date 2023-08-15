package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
}
