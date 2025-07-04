public class NonExpirableProduct extends Product {

    @Override
    public boolean isExpired() {
        return false;
    }
}
