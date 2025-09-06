package utlis;

import org.openqa.selenium.WebElement;

public class Browser {
    /**
     * Check if a checkbox is selected or not.
     * If not selected, perform click action to check it.
     * @param element
     */
    public static void check(WebElement element){
        if(!element.isSelected()){
            element.click();
        }
    }

    /**
     * Check if a checkbox is selected or not.
     * If selected, perform click action to uncheck it.
     * @param element
     */
    public static void uncheck(WebElement element){
        if(element.isSelected()){
            element.click();
        }
    }
}
