import java.util.*;

public class Reccomendations {

    private ScoreMatchCalc scoreCalc = new ScoreMatchCalc();

    public List<Property> getRecommendations(Student student, List<Property> allProperties, int topN) {
        List<PropertyScore> scoredProperties = new ArrayList<>();

        for (Property property : allProperties) {
            double score = scoreCalc.calculate(student, property);
            scoredProperties.add(new PropertyScore(property, score));
        }

        // Sort by score descending
        scoredProperties.sort((a, b) -> Double.compare(b.score, a.score));

        // Return top N
        List<Property> recommendations = new ArrayList<>();
        for (int i = 0; i < Math.min(topN, scoredProperties.size()); i++) {
            recommendations.add(scoredProperties.get(i).property);
        }

        return recommendations;
    }

    private static class PropertyScore {
        Property property;
        double score;

        PropertyScore(Property property, double score) {
            this.property = property;
            this.score = score;
        }
    }
}
