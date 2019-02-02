import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        List<String> request = getRequest();

        Map<String, String> headers = getHeaders(request);
    }

    private static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();

        String line = null;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }

        request.add(System.lineSeparator());

        if ((line = reader.readLine()) != null && line.length() > 0) {
            request.add(line);
        }

        return request;
    }

    private static Map<String, String> getHeaders(List<String> requests) {
        Map<String, String> headers = new LinkedHashMap<>();

        requests.stream()
                .skip(1)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(headerKvp -> {

                    if (!"Cookie".equals(headerKvp[0])) {
                        headers.put(headerKvp[0], headerKvp[1]);
                    } else {
                        getCookies(headerKvp[1]);
                    }

                });

        return headers;
    }

    private static void getCookies(String requests) {
        Map<String, String> cookies = new LinkedHashMap<>();

        String[] values = requests.split(";");


        for (String value : values) {

            String[] cookie = value.trim().split("=");

            System.out.println(cookie[0] + " <-> " + cookie[1]);
        }
    }
}



