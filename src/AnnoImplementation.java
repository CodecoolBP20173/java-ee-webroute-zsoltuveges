public class AnnoImplementation {
    @WebRoute(path = "/test")
    public String testRoute() {
        return "Test";
    }

    @WebRoute(path = "/index")
    public String index() {
        return "Index";
    }

    @WebRoute(path = "/littleduck")
    public String littleDuck() {
        return "HAP-HAP-HAP";
    }
}