package expressivo.expressions;

import java.util.Map;
import java.util.stream.Collectors;

class EnvironmentParser {
    private Map<String, Double> rawEnvironment;

    public EnvironmentParser(Map<String, Double> rawEnvironment) {
        this.rawEnvironment = rawEnvironment;
    }

    public Map<Variable, Numeric> asExpressionMap() {
        return rawEnvironment.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> new Variable(entry.getKey()),
                        entry -> new Numeric(entry.getValue().toString())
                ));
    }
}
