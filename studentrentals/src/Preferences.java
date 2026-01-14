
public class Preferences {

    private int pricePreference;
    private String locationPreference;
    private boolean utilityPreference;
    private int bedroomPreference;
    private int bathroomPreference;

    public Preferences(int pricePreference, String locationPreference,
                       boolean utilityPreference, int bedroomPreference, int bathroomPreference) {
        this.pricePreference = pricePreference;
        this.locationPreference = locationPreference;
        this.utilityPreference = utilityPreference;
        this.bedroomPreference = bedroomPreference;
        this.bathroomPreference = bathroomPreference;
    }

    public void printPreferences(){

    }

    public int getPricePreference() {
        return pricePreference;
    }

    public String getLocationPreference() {
        return locationPreference;
    }

    public boolean getUtilityPreference() {
        return utilityPreference;
    }

    public int getBedroomPreference() {
        return bedroomPreference;
    }

    public int getBathroomPreference() {
        return bathroomPreference;
    }

}
