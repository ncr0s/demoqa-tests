package tests.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
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
        // gradle clean one_prop_test
        // firefox

        // gradle clean one_prop_test -Dbrowser=safari
        // safari
    }

    @Test
    @Tag("hello")
    void simplePropertyHelloTest() {
        System.out.println("Hello, " + System.getProperty("user_name", "anonymous"));

        /*
         gradle clean hello
                Hello, anonymous

         gradle clean hello -Duser_name=World
                Hello, World
         gradle clean hello -Duser_name="World of Warcraft"
         gradle clean hello "-Duser_name=World of Warcraft"
                Hello, World of Warcraft
         */
    }
}
