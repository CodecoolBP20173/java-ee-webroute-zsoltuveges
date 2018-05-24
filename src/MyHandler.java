import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {

        String route = t.getRequestURI().getPath();
        String response = null;
        try {
            Class myClass = Class.forName("AnnoImplementation");
            Method classMethod = getMethod(myClass, route);
            try {
                response = (String) classMethod.invoke(myClass.newInstance());
            } catch (IllegalArgumentException |
                    IllegalAccessException |
                    InvocationTargetException |
                    InstantiationException |
                    SecurityException e) {
                System.out.println(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found...");
        }

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private Method getMethod(Class myClass, String route) {
        Method method = null;
        for (Method m : myClass.getMethods()) {
            if (m.isAnnotationPresent(WebRoute.class)) {
                if (m.getAnnotation(WebRoute.class).url().equals(route)) {
                    method = m;
                }
            }
        }
        return method;
    }
}
