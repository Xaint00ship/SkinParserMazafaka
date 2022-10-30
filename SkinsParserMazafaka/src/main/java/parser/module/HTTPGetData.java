package parser.module;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPGetData {
    private static int countAttemp = 0;
    public static String getSkins(String targetURL) {
        HttpURLConnection connection = null;
        String urlRepeat = targetURL; //

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();


            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Cookie", "S3CheckTimeout=true; d2netAuthStatus=checked; PHPSESSID=d8fd444ecff25647f659fb19d4c453c3; _csrf=iCZaSryZFEfS67RATf8rYwofidNK0PVG; goon=0; _ga=GA1.2.1140091824.1664903083; _gid=GA1.2.619430186.1664903083; _ym_uid=1664903084510250493; _ym_d=1664903084; _ym_isad=2; cf_chl_2=bfbed56a8e01b46; cf_chl_prog=x15; cf_clearance=_i.0skBswEIhKCaL_kpVfc3EiMjH2Go1Es1Bnoi7PDI-1664906773-0-150; _gat=1; _ym_visorc=b; _fbp=fb.1.1664906777611.1993423751; d2mid=3mTkO7TC7cRKx4Q7cH3U9Ej67sGgly");

            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            System.out.println(connection.getResponseMessage());


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

            try {
                Thread.sleep(4000);
            } catch (InterruptedException er) {
                Thread.currentThread().interrupt();
            }

            e.printStackTrace();
            countAttemp++; // итерация числа попыток обращения к серверу

            if(countAttemp >= 20){ // Выход если за указанное число попыток не получилось получить ответ от сервера.
                countAttemp = 0;
                return null;
            }
            else{
                getSkins(urlRepeat);// Рекурсия функции запроса к серверу.
            }
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

    }


}
