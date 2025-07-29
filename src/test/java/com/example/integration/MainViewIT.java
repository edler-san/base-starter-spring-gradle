package com.example.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.vaadin.flow.component.html.testbench.ParagraphElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import org.openqa.selenium.Keys;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.BrowserTestBase;

public class MainViewIT extends BrowserTestBase {

    /**
     * If running on CI, get the host name from environment variable HOSTNAME
     *
     * @return the host name
     */
    private static String getDeploymentHostname() {
        String hostname = System.getenv("HOSTNAME");
        if (hostname != null && !hostname.isEmpty()) {
            return hostname;
        }
        return "localhost";
    }

    @BeforeEach
    public void open() {
        getDriver().get("http://"+getDeploymentHostname()+":8080/");
    }

    @BrowserTest
    public void testEnterShowsHelloUserNotificationWhenUserIsNotEmpty() {
        Assertions.assertFalse($(ParagraphElement.class).exists());
        TextFieldElement textField = $(TextFieldElement.class).first();
        textField.setValue("User");
        textField.sendKeys(Keys.ENTER);
        Assertions.assertTrue($(ParagraphElement.class).exists());
        ParagraphElement msg = $(ParagraphElement.class).first();
        Assertions.assertEquals("Hello User", msg.getText());
    }
}
