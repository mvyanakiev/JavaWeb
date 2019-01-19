import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//import org.apache.commons.codec.binary.Base64;


public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

//        String name = "UGVzaG8=";
//        System.out.println(decode64(name));

        List<String> urlList = getValidUrls();
        List<String> request = getRequest();

        Map<String, String> headers = getHeaders(request);
        Map<String, String> params = getBodyParams(request);



        String name = headers.get("Authorization").split("\\s+")[1];
        System.out.println(decode64(name));

        if (!urlList.contains(getUrl(request.get(0)))) {
            System.out.println("The requested functionality was not found.");
        }


        if (params.size() == 0) {
            System.out.println("There was an error with the requested functionality due to malformed request");
        }






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
                    headers.put(headerKvp[0], headerKvp[1]);
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
}



