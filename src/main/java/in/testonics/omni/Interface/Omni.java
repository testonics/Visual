package in.testonics.omni.Interface;

public interface Omni {

    void setNavigationUrl(String url);

    void setHeadlessMode(boolean headlessMode);

    void setBrowser(String browser);

    void click(Object element);

    void enter(Object element, String value);

    void select(Object element, Object value);

    void setDriver();

    void closeDriver();

}
