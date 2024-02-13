package in.testonics.omni.frameworks;

public class Omni {

    Selenium selenium = new Selenium();
    PW pw = new PW();
    Android android = new Android();
    WinApp winApp = new WinApp();

    private static String framework = "SELENIUM";
    private static final String SELENIUM = "SELENIUM";
    private static final String PLAYWRIGHT = "PLAYWRIGHT";
    private static final String ANDROID = "ANDROID";
    private static final String WINDOWS = "WINDOWS";
    private static String app = "";

    private static String Url = "";
    private static String browser = "CHROME";
    private static boolean headlessMode = true;

    public void setFramework(String framework){
        this.framework = framework.toUpperCase();
    }

    public void setNavigationUrl(String url){
        this.Url = url;
    }

    public void setHeadlessMode(boolean headlessMode){
        this.headlessMode = headlessMode;
    }

    public void setApp(String app){
        this.app = app;
    }

    public void setBrowser(String browser){
        this.browser = browser.toUpperCase();
    }

    public void setDriver(){
        switch (framework){
            case SELENIUM:
                selenium.setBrowser(browser);
                selenium.setHeadlessMode(headlessMode);
                selenium.setDriver();
                break;
            case PLAYWRIGHT:
                pw.setBrowser(browser);
                pw.setHeadlessMode(headlessMode);
                pw.setDriver();
                break;
            case ANDROID:
                android.setApp(app);
                android.setDriver();
                break;
            case WINDOWS:
                winApp.setApp(app);
                winApp.setDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public Object getDriver(){
        framework = framework.toUpperCase();
        switch (framework){
            case SELENIUM:
                return selenium.getDriver();
            case PLAYWRIGHT:
                return pw.getDriver();
            case ANDROID:
                return android.getDriver();
            case WINDOWS:
                return winApp.getDriver();
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void navigate(){
        switch (framework){
            case SELENIUM:
                selenium.setNavigationUrl(Url);
                selenium.navigate();
                break;
            case PLAYWRIGHT:
                pw.setNavigationUrl(Url);
                pw.navigate();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void close(){
        switch (framework){
            case SELENIUM:
                selenium.closeDriver();
                break;
            case PLAYWRIGHT:
                pw.closeDriver();
                break;
            case ANDROID:
                android.closeDriver();
                break;
            case WINDOWS:
                winApp.closeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void click(Object element) {
        switch (framework){
            case SELENIUM:
                selenium.click(element);
                break;
            case PLAYWRIGHT:
                pw.click(element);
                break;
            case ANDROID:
                android.click(element);
                break;
            case WINDOWS:
                winApp.click(element);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void clickWithAction(Object element){
        switch (framework){
            case SELENIUM:
                selenium.click(element);
                break;
            case PLAYWRIGHT:
                pw.click(element);
                break;
            case ANDROID:
                android.click(element);
                break;
            case WINDOWS:
                winApp.clickWithAction(element);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void enter(Object element, String value) {
        switch (framework){
            case SELENIUM:
                selenium.enter(element,value);
                break;
            case PLAYWRIGHT:
                pw.enter(element,value);
                break;
            case ANDROID:
                android.click(element);
                break;
            case WINDOWS:
                winApp.enter(element,value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public void select(Object element, Object value) {
        switch (framework){
            case SELENIUM:
                selenium.select(element,value);
                break;
            case PLAYWRIGHT:
                pw.select(element,value);
                break;
            case ANDROID:
                android.click(element);
                break;
            case WINDOWS:
                winApp.select(element,value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }
    }

    public String getText(Object element){
        switch (framework){
            case SELENIUM:
                return "a";
            case PLAYWRIGHT:
                return "b";
            case ANDROID:
                return "c";
            case WINDOWS:
                return winApp.getText(element);
            default:
                throw new IllegalStateException("Unexpected value: " + framework);
        }

    }
}

