import java.time.LocalDate;
public class ExpirableProduct extends Product {
    private String expiryDate;
    public ExpirableProduct (String expiryDate){
        this.expiryDate=expiryDate;
    }

    @Override
    public boolean isExpired() {
        LocalDate expiry = LocalDate.parse(expiryDate);
        return LocalDate.now().isAfter(expiry);
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
