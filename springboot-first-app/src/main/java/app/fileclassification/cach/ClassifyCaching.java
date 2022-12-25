package app.fileclassification.cach;

import app.model.DBFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassifyCaching {
    private static final Map<String, List<DBFile>> classification = new HashMap<>();
    public static Map<String, List<DBFile>> getClassification() {
        return classification;
    }
}