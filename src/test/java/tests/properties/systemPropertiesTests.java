package tests.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class systemPropertiesTests {
    @Test
    void simplePropertyTest() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // null
    }

    @Test
    void simplePropertyOperaTest() {
        System.setProperty("browser", "opera");
        String browser = System.getProperty("browser");
        System.out.println(browser); // opera
    }

    @Test
    void simplePropertyFfTest() {
        System.setProperty("browser", "opera");
        String browser = System.getProperty("browser", "firefox");
        System.out.println(browser); // opera
    }

    @Test
    @Tag("one_property")
    void simplePropertyNewTest() {
        String browser = System.getProperty("browser", "firefox");
        System.out.println(browser);
        //gradle clean one_prop_test
        //gradle clean one_prop_test -Dbrowser=safari
    }
}
