package parser.module;

//import com.example.skinsparsermazafaka.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;



public class Parser {
    String dir;
    String game;



    public void goParsing(String dir, String game){
        this.dir = dir;
        this.game = game;

        if(this.game.equals("DOTA")){
            CreateExcelFile file = new CreateExcelFile();
            file.loadInExcelInLootFarm(dir+"/fileDota.xls", "LootFarm", parseDataInLootFarm("DOTA"));
            file.loadInExcelInMarket(dir+"/fileDota.xls", "Market", parseDataInMarket("DOTA"));
            file.loadInExcelInTM(dir+"/fileDota.xls", "TM", parseDataInTM("DOTA"));

        }
        else if(this.game.equals("CS")){
            CreateExcelFile file = new CreateExcelFile();
            file.loadInExcelInLootFarm(dir+"/fileCS.xls", "LootFarm", parseDataInLootFarm("CS"));
            file.loadInExcelInMarket(dir+"/fileCS.xls", "Market", parseDataInMarket("CS"));
            file.loadInExcelInTM(dir+"/fileCS.xls", "TM", parseDataInTM("CS"));

        }

    }

    private static float getExchangeRates(){
        HTTPGetData requestInLootFarm = new HTTPGetData();
        String skinsInLootFarm = requestInLootFarm.getSkins("https://www.cbr-xml-daily.ru/daily_utf8.xml");
        Float RatesUSD = Float.parseFloat(skinsInLootFarm.split("<Valute ID=\"R01235\">")[1].split("Value")[1].replace(">", "").replace("</", "").replace(",", "."));
        return RatesUSD;
    }

    private static String [] parseDataInLootFarm(String game){
        String gameName = "";
        if(game.equals("DOTA")){
            gameName = "DOTA";
        } else if (game.equals("CS")) {
            gameName = "";
        }

//        Controllers.progressBarLootFarm.setProgress(1.0);
        HTTPGetData requestInLootFarm = new HTTPGetData();
        String skinsInLootFarm = requestInLootFarm.getSkins("https://loot.farm/fullprice" +
                gameName +
                ".json");
        String [] skinsInLootFarmArray = skinsInLootFarm.split("},");

//        System.out.println(skinsInLootFarmArray[0].replace("[{", ""));
        return skinsInLootFarmArray;
    }

    private static ArrayList<String []> parseDataInMarket(String game){
        String gameName = "";
        if(game.equals("DOTA")){
            gameName = ".dota2.net";
        } else if (game.equals("CS")) {
            gameName = ".csgo.com";
        }

        ArrayList<String []> skinsInMarketArray = new ArrayList<> ();
        HTTPGetData requestInMarket = new HTTPGetData();

        String skinsInMarket = requestInMarket.getSkins(
                "https://market" +
                        gameName +
                        "/ajax/i_popularity/all/all/all/6/56/0;500000/all/all?sd=desc");

        if (null == skinsInMarket){
            skinsInMarket = "\"[[[\\\"638242570\\\",\\\"188530139\\\",\\\"К сожалению, не удалось пропарсить сайт маркета, так как на нем ведутся технические работы\\\",0,\\\"D2D2D2\\\",true,\\\"<small></small>267.7\\\",[],\\\"M4A1-S | Basilisk (Field-Tested)\\\"]]],179]\"";
        }

        int getQuantityPagesCsInMarket = Integer.parseInt (
                skinsInMarket.substring(skinsInMarket.length()-9, skinsInMarket.length()-1)
                        .replaceAll("[^0-9]", "")
        );

        for(int numPage = 1; numPage <= getQuantityPagesCsInMarket-1; numPage++) {

            if(numPage%1 == 0){

//                break;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }

            skinsInMarket = requestInMarket.getSkins(
                    "https://market" +
                            gameName +
                            "/ajax/i_popularity/all/all/all/" +
                            numPage +
                            "/56/0;500000/all/all?sd=desc");



            if (skinsInMarket.equals("null")){
                skinsInMarket = "\"[[[\\\"638242570\\\",\\\"188530139\\\",\\\"К сожалению, не удалось пропарсить сайт маркета, так как на нем ведутся технические работы\\\",0,\\\"D2D2D2\\\",true,\\\"<small></small>267.7\\\",[],\\\"M4A1-S | Basilisk (Field-Tested)\\\"]]],179]\"";
                skinsInMarket = skinsInMarket.replace(",[],", ",");
//            String[] skinsInMarketArray = skinsInMarket.split("],");
                skinsInMarketArray.add(skinsInMarket.split("],"));
                break;

            }

            skinsInMarket = skinsInMarket.replace(",[],", ",");
//            String[] skinsInMarketArray = skinsInMarket.split("],");
            skinsInMarketArray.add(skinsInMarket.split("],"));

//            System.out.println(skinsInMarketArray.size());
        }
        return skinsInMarketArray;

    }

    private static ArrayList<String []> parseDataInTM(String game){
        ArrayList<String []> skinsInTMList = new ArrayList<> ();

        String gameName = "";
        if(game.equals("DOTA")){
            gameName = "appId=570";
        } else if (game.equals("CS")) {
            gameName = "appId=730";
        }

        HTTPGetData requestInTM = new HTTPGetData();
//        dota appId=570
//          cs appId=730
        for(int numPage =1; numPage<=150; numPage++) {
            String skinsInTM = requestInTM.getSkins("https://www.skinwallet.com/en/market/get-offers?" +
                    gameName +
                    "&page=" +
                    numPage +
                    "&sortBy=HotDeals");
            skinsInTM = skinsInTM.replace("{\"offerThumbnails\":{\"thumbnails\":[", "");
            String[] skinsInTMArray = skinsInTM.split("e},");
//            System.out.println(skinsInTMArray[0]);

            skinsInTMList.add(skinsInTMArray);
//            System.out.println(numPage);
            if(numPage%10 == 0){
//                break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }
        }


        return skinsInTMList;


    }
}
