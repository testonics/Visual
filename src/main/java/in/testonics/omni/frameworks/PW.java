package in.testonics.omni.frameworks;

import com.microsoft.playwright.*;
import in.testonics.omni.Interface.Omni;

public class PW implements Omni {

    protected static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    protected static ThreadLocal<Page> page = new ThreadLocal<>();
    private static String Url = "";
    private static String browser = "CHROME";
    private static boolean headlessMode = true;

    @Override
    public void setNavigationUrl(String url){
        this.Url = url;
    }

    @Override
    public void setHeadlessMode(boolean headlessMode){
        this.headlessMode = headlessMode;
    }

    @Override
    public void setBrowser(String browser){
        this.browser = browser.toLowerCase();
    }

    @Override
    public void setDriver(){
        Playwright playwrightDriver = Playwright.create();
        BrowserType.LaunchOptions browserLaunchOptions = new BrowserType.LaunchOptions().setHeadless(headlessMode).setChannel(browser.toLowerCase()).setSlowMo(50);
        Browser browser = playwrightDriver.chromium().launch(browserLaunchOptions);
        Page pagePlaywright = browser.newContext().newPage();
        playwright.set(playwrightDriver);
        page.set(pagePlaywright);
    }

    public Playwright getDriver(){
        return playwright.get();
    }

    @Override
    public void closeDriver(){
        playwright.get().close();
    }

    public void navigate(){
        page.get().navigate(Url);
    }

    @Override
    public void click(Object element) {
        Locator locator = getWebElement(element);
        locator.click();
    }

    @Override
    public void enter(Object element, String value) {
        Locator locator = getWebElement(element);
        locator.fill(value);
        locator.press("Enter");
    }

    @Override
    public void select(Object element, Object value) {
        Locator locator = getWebElement(element);
        locator.selectOption((String) value);
    }

    public Locator getWebElement(Object element){
        return page.get().locator((String) element);
    }
}
