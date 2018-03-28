package cc.jorgen.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose of this class ...
 * <p>
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-03-28.
 */
public enum ConfigVariable {
    PORT("PORT"),
    ENVIRONMENT("ENVIRONMENT");

    static Map<String, String> mEnvironmentVariables = System.getenv();
    static Map<String, String> mPropertiesFileValues = readProperties();
    private static final String mFileName = ".env";

    private String mKey;

    ConfigVariable(String variableName) {
        mKey = variableName;
    }

    public static String getValue(ConfigVariable variable) {
        return mEnvironmentVariables.containsValue(variable.mKey) ?
                mEnvironmentVariables.get(variable.mKey) :
                mPropertiesFileValues.get(variable.mKey);
    }


    public boolean valueEquals(String compareValue) {
        return getValue(this).equals(compareValue);
    }


    /**
     * Read all values from properties file
     */
    private static Map<String, String> readProperties() {
        Map<String, String> map = new HashMap<>();
        try {
            if (FileUtil.fileExists(mFileName)) {
                FileUtil
                        .fileToStream(mFileName)
                        .filter(s -> !s.startsWith("#") && !s.isEmpty())
                        .forEach(s -> {
                            String[] split = s.split("=");
                            if (split.length == 2 && !map.containsValue(split[0])) {
                                map.put(split[0], split[1].trim());
                            }
                        });
            } else {
                System.out.println("File does not exists");
            }
        } catch (IOException e) {

        }
        return map;
    }
}
