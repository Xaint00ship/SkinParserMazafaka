package parser.module;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class CreateExcelFile {
    public static void loadInExcelInLootFarm(String path, String pageName, String [] data){
        try
        {
//declare file name to be create
            String filename = path;
            System.out.println(filename);
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet(pageName);
//creating the 0th row using the createRow() method

            for (int i = 0; i < data.length; i++) {
//
                String skin = data[i];
                skin = skin.replace("{", "");

//                    skin = skin.replace("\"price\":", "");
//                        System.out.println(skin);
                HSSFRow rowhead = sheet.createRow((short)i);

                String [] param  = skin.split(",\"");

//                        rowhead.createCell(1).setCellValue(param[0]);
//                        rowhead.createCell(5).setCellValue(Integer.parseInt(param[1]) * 0.01 );

                String price = param[1].replace("price\":", "");
                String name = param[0].replace("\"name\":", "");
                name = name.replace("\"", "");
//                        System.out.println(price);
                rowhead.createCell(1).setCellValue(name);
                rowhead.createCell(5).setCellValue(Integer.parseInt(price) * 0.01);



            }

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void loadInExcelInMarket(String path, String pageName, ArrayList<String []> data){
        try
        {

//declare file name to be create
            String filename = path;
            FileInputStream fileOut = new FileInputStream(filename);
//creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook(fileOut);
//invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet(pageName);
//creating the 0th row using the createRow() method


            int numRow = 0;
            for(int listNum = 0; listNum < data.size(); listNum++){
                for (int arrayNum = 0; arrayNum < data.get(listNum).length; arrayNum++) {
//                    for(int i = 0; i < data.get(listNum)[arrayNum].length(); i++){
                    String skin = data.get(listNum)[arrayNum];
                    skin = skin.replace("[", "");
                    skin = skin.replace("<small></small>", "");
//                        System.out.println(skin);
                    HSSFRow rowhead = sheet.createRow((short)numRow);

                    String [] param  = skin.split(",");
                    if(param.length >=8){
                        rowhead.createCell(2).setCellValue(param[2]);
                        rowhead.createCell(6).setCellValue(param[3]);
                        numRow++;
                    }
//                        System.out.println(price.length + " и " + name.length);

//                    }
                }
            }

//creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method


            FileOutputStream output_file =new FileOutputStream(new File(path));

            workbook.write(output_file);
//closing the Stream
            fileOut.close();
//closing the workbook
            workbook.close();
//prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void loadInExcelInTM(String path, String pageName, ArrayList<String []> data){

        try
        {

            String filename = path;
            FileInputStream fileOut = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fileOut);
            HSSFSheet sheet = workbook.createSheet(pageName);

            int numRow = 0;
            for(int listNum = 0; listNum < data.size(); listNum++){
                for (int arrayNum = 0; arrayNum < data.get(listNum).length; arrayNum++) {
//                    for(int i = 0; i < data.get(listNum)[arrayNum].length(); i++){

                    String skin = data.get(listNum)[arrayNum];

                    HSSFRow rowhead = sheet.createRow((short)numRow);

                    String [] name  = skin.split("\"marketHashName\":")[1].split(",");
                    String [] price  = skin.split("\"price\":")[1].split(",");
//                    System.out.println(name[0]+"   ---   "+price[0]);
//
                    rowhead.createCell(3).setCellValue(name[0]);
                    rowhead.createCell(7).setCellValue((Integer.parseInt(price[0].replace("{\"amount\":", "")) * 0.01));
                    numRow++;
//
                }

            }

            FileOutputStream output_file =new FileOutputStream(new File(path));

            workbook.write(output_file);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}