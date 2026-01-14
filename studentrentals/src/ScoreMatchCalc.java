public class ScoreMatchCalc {

    public double calculate(Student student, Property property) {
        double score = 0.0;
        Preferences studentPrefs = student.getPreferences();
        Preferences propertyPrefs = property.getHouseData();

        if (studentPrefs == null || propertyPrefs == null) {
            return 0.0;
        }

        // Price score: higher if property price <= student max price, scaled
        int studentPrice = studentPrefs.getPricePreference();
        int propertyPrice = propertyPrefs.getPricePreference();
        if (propertyPrice <= studentPrice) {
            score += 1.0 - ((double)(studentPrice - propertyPrice) / studentPrice); // Closer to 1 if cheaper
        }

        // Location score: exact match = 1, else 0
        if (studentPrefs.getLocationPreference().equalsIgnoreCase(propertyPrefs.getLocationPreference())) {
            score += 1.0;
        }

        // Utility score: match = 1
        if (studentPrefs.getUtilityPreference() == propertyPrefs.getUtilityPreference()) {
            score += 1.0;
        }

        // Bedrooms score: exact match = 1, close = 0.5
        int bedDiff = Math.abs(studentPrefs.getBedroomPreference() - propertyPrefs.getBedroomPreference());
        if (bedDiff == 0) {
            score += 1.0;
        } else if (bedDiff == 1) {
            score += 0.5;
        }

        // Bathrooms score: similar
        int bathDiff = Math.abs(studentPrefs.getBathroomPreference() - propertyPrefs.getBathroomPreference());
        if (bathDiff == 0) {
            score += 1.0;
        } else if (bathDiff == 1) {
            score += 0.5;
        }

        return score;
    }
}
