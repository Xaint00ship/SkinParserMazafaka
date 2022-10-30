package parser.module;

        import org.apache.poi.util.SystemOutLogger;

        import java.io.BufferedReader;
        import java.io.DataOutputStream;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

        public class SandBox {
        private static int countAttemp = 0;
        public static String getSkins(String targetURL) {
        HttpURLConnection connection = null;

        String urlB = targetURL;
//        RequestConfig globalConfig = RequestConfig.custom().setSocketTimeout(15000).build();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

        try {
        //Create connection
        URL url = new URL(targetURL);
//            HttpURLConnection.setFollowRedirects(false);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

//            connection.addRequestProperty("authority", " market.csgo.com");
//            connection.addRequestProperty(":method", " GET");
//            connection.addRequestProperty(":path", "/ajax/i_popularity/all/all/all/2/56/0;500000/all/all?sd=desc\n:scheme: https");
//            connection.addRequestProperty(":scheme","https");
//            connection.addRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n");
//            connection.addRequestProperty("accept-encoding", "gzip, deflate, br\n");
//            connection.addRequestProperty("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7\n");
//            connection.addRequestProperty("cache-control", "max-age=0\n");
//            connection.addRequestProperty("cookie", "_csrf=PGJchY6gBzR38cxtkr1qacW7Zm_lMvHe; goon=0; _ga=GA1.2.1930440296.1663001389; _ym_uid=1663001389283210058; _ym_d=1663001389; _fbp=fb.1.1663001388891.925590172; cf_clearance=9c5yzVuW6j4kn3B1X00f99Um3p8T.pz.5IRjAyqQI3I-1664818074-0-150; PHPSESSID=1e17475bc76ae5a390bb9c05178a5246; _gid=GA1.2.602196275.1664818076; _ym_visorc=b; _ym_isad=2; d2mid=WPSIzGnIanYKE7fUwpIdGVejLBrgPy; d2netAuthStatus=checked; _language=en\n");
//            connection.addRequestProperty("sec-ch-ua", "\"Google Chrome\";v=\"105\", \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"105\"\n");
//            connection.addRequestProperty("sec-ch-ua-mobile", "?0\n");
//            connection.addRequestProperty("sec-ch-ua-platform", "\"Windows\"\n");
//            connection.addRequestProperty("sec-fetch-dest", "document\n");
//            connection.addRequestProperty("sec-fetch-mode", "navigate\n");
//            connection.addRequestProperty("sec-fetch-site","none\n");
//            connection.addRequestProperty("sec-fetch-user", "?1\n");
//            connection.addRequestProperty("upgrade-insecure-requests"," 1\n");
//            connection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36\n");







        connection.setUseCaches(false);
        connection.setDoOutput(true);
        connection.setConnectTimeout(15000);
        //Get Response
        InputStream is = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
        }
        rd.close();
        countAttemp = 0;
        return response.toString();

        } catch (Exception e) {
//            System.out.println("Ебаное говно опять лежит нахуй");

        try {
        Thread.sleep(4000);
        } catch (InterruptedException er) {
        Thread.currentThread().interrupt();
        }

        e.printStackTrace();
        System.out.println("Market response code is 503. ");
        countAttemp++;
//            System.out.println(countAttemp);
        if(countAttemp >= 20){
        countAttemp = 0;
        return "null";
        }
        else{
        getSkins(urlB);
        }
        return null;
        } finally {
        if (connection != null) {
        connection.disconnect();
        }
        }
        }


        }
