import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
public class ShippingService {
    public void ship(List<Shippable> items){
        System.out.println("** Shipment notice **");

        Map<String, Integer> itemCount = new LinkedHashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            itemCount.put(name, itemCount.getOrDefault(name, 0) + 1);
            totalWeight += item.getWeight();
        }

        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            System.out.println(entry.getValue() + "x " + entry.getKey());
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }
}
