public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(double weight){
        this.weight=weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
