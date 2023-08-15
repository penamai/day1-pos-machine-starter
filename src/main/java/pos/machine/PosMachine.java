package pos.machine;

import java.util.ArrayList;
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
}
