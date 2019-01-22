import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        List<String> urlList = getValidUrls();
        List<String> request = getRequest();

        Map<String, String> headers = getHeaders(request);
        Map<String, String> params = null;


//        String forParams = request.get(request.size() - 1);

//        if (!headers.containsKey("Authorization")) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("HTTP/1.1 401 Unauthorized").append(System.lineSeparator());
//            sb.append(returnHeadersDateHost(headers));
//            sb.append("You are not authorized to access the requested functionality.").append(System.lineSeparator());
//            System.out.println(sb.toString());
//        }
//
//        if (!urlList.contains(getUrl(request.get(0)))) {
//            StringBuilder sb = new StringBuilder();
//            sb.append("HTTP/1.1 404 Not Found").append(System.lineSeparator());
//            sb.append(returnHeadersDateHost(headers));
//            sb.append("The requested functionality was not found.").append(System.lineSeparator());
//            System.out.println(sb.toString());
//        }
//
//        if (forParams.contains("&") && "POST".equals(getMethod(request.get(0)))) {
//            params = getBodyParams(request);
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append("HTTP/1.1 400 Bad request").append(System.lineSeparator());
//            sb.append(returnHeadersDateHost(headers));
//            sb.append("There was an error with the requested functionality due to malformed request").append(System.lineSeparator());
//            System.out.println(sb.toString());
//        }


//        StringBuilder responseResult = new StringBuilder();
//
//        responseResult.append("HTTP/1.1 200 OK").append(System.lineSeparator());
//        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
//
//            if (!headerEntry.getKey().equals("Authorization")) {
//                responseResult.append(String.format("%s: %s", headerEntry.getKey(), headerEntry.getValue())).append(System.lineSeparator());
//            }
//        }
//        responseResult.append(System.lineSeparator());
//        String name = decode64(headers.get("Authorization").split("\\s+")[1]);
//
//        responseResult.append(String.format("Greetings %s! You have successfully created " +
//                "%s with quantity – %s, price – %s.", name, params.get("name"), params.get("quantity"), params.get("price")))
//                .append(System.lineSeparator());
//
//        System.out.println(responseResult.toString());
    }

    private static String returnHeadersDateHost(Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();

        if (headers.containsKey("Date")) {
            sb.append(String.format("Date: %s", headers.get("Date"))).append(System.lineSeparator());
        } else {
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            sb.append(String.format("Date: %s", timeStamp)).append(System.lineSeparator());
        }

        if (headers.containsKey("Host")) {
            sb.append(String.format("Host: %s", headers.get("Host"))).append(System.lineSeparator());
        }

        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private static String decode64(String toDecode) {
        byte[] byteArray = Base64.getDecoder().decode(toDecode.getBytes());
        return new String(byteArray);
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
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

    private static String getMethod(String line) {
        return line.split("\\s+")[0];
    }

    private static String getUrl(String line) {
        return line.split("\\s+")[1];
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

    private static Map<String, String> getBodyParams(List<String> requests) {
        Map<String, String> bodyParameters = new LinkedHashMap<>();

        if (requests.get(requests.size() - 1).equals("")) {
            return bodyParameters;
        }

        Arrays.stream(requests.get(requests.size() - 1)
                .split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpKvp -> {
                    bodyParameters.put(bpKvp[0], bpKvp[1]);
                });

        return bodyParameters;
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



