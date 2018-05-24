public class AnnoImplementation {
    @WebRoute(url = "/test")
    public String testRoute() {
        return "Test";
    }

    @WebRoute(url = "/index")
    public String index() {
        return "Index";
    }

    @WebRoute(url = "/littleduck")
    public String littleDuck() {
        return "HAP-HAP-HAP";
    }
}