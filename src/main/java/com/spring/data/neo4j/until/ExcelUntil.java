package com.spring.data.neo4j.until;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.Borders;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2018/7/10.
 */
public class ExcelUntil {

    /*
    * poi写入
    * */
    public static void writeExcel(String filePath) throws IOException {
        Random random=new Random();
        //String filePath="E:\\NewTouch\\上海学习\\test.xlsx";//文件路径
        Workbook workbook=null;
        String[] ss=filePath.split("\\.");
        if ("xls".equals(ss[1])){
            workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        }else if("xlsx".equals(ss[1])){
            workbook=new XSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet("Test");//创建工作表(Sheet)
        FileOutputStream out = new FileOutputStream(filePath);
        Row r0=sheet.createRow(0);//PS:不怎么写的话，下面9句代码执行完之后，导致前面8句全部没有写进Excel里面
        r0.createCell(0).setCellValue("no");
        r0.createCell(1).setCellValue("name");
        r0.createCell(2).setCellValue("hp");
        r0.createCell(3).setCellValue("mp");
        r0.createCell(4).setCellValue("gj");
        r0.createCell(5).setCellValue("fy");
        r0.createCell(6).setCellValue("bj");
        r0.createCell(7).setCellValue("sb");
        r0.createCell(8).setCellValue("sex");
        /*设置数据验证*/
        DataValidationHelper helper=sheet.getDataValidationHelper();
        CellRangeAddressList cellRangeAddressList=new CellRangeAddressList(1,1000,8,8);
        String[] yz={"0","1","2"};
        DataValidation dataValidation=helper.createValidation(helper.createExplicitListConstraint(yz),cellRangeAddressList);
        dataValidation.createPromptBox("提示头","提示输入的内容");
        dataValidation.setShowErrorBox(true);
        dataValidation.setShowPromptBox(true);
        sheet.addValidationData(dataValidation);
        for (int r=1;r<=25;r++){
            Row row = sheet.createRow(r);// 创建行,从1开始
            Cell c0=row.createCell(0);// 设置单元格内容
            Cell c1=row.createCell(1);
            Cell c2=row.createCell(2);
            Cell c3=row.createCell(3);
            Cell c4=row.createCell(4);
            Cell c5=row.createCell(5);
            Cell c6=row.createCell(6);
            Cell c7=row.createCell(7);
            Cell c8=row.createCell(8);

                c1.setCellValue("骡子"+r);
                c2.setCellValue(random.nextInt(8)+2);
                c3.setCellValue(random.nextInt(4)+2);
                c4.setCellValue(random.nextInt(3)+1);
                c5.setCellValue(random.nextInt(3)+1);
                c6.setCellValue(random.nextInt(5)+1);
                c7.setCellValue(random.nextInt(5)+1);
                CellStyle cellStyle=workbook.createCellStyle();
                cellStyle.setLeftBorderColor(HSSFColor.GREEN.index);
                cellStyle.setFillBackgroundColor(HSSFColor.LEMON_CHIFFON.index );
                c8.setCellStyle(cellStyle);
                c8.setCellValue(random.nextInt(2));
        }
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }

    public static void writeExcel(String filePath,List<String> list) throws IOException {
        Random random=new Random();
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=null;
        FileOutputStream fileOutputStream= new FileOutputStream(filePath);
        for (String s:list){
            System.out.println(s);
            if (s.indexOf("#")>=0){
                System.out.println("--->#<---");
                System.out.println("--->去掉#，取出名字设置为sheet名字并创建<---");
                System.out.println(s.substring(1,s.length()));
                sheet=workbook.createSheet(s.replace("#",""));
            }else if (s.indexOf("title")>=0){
                System.out.println("--->title<---");
                String[] st=s.split(":");
                String stt=st[1].substring(1,st[1].length()-1);
                String[] tname=stt.split(",");
                int i=0;
                Row row=sheet.createRow(0);
                for (String s1:tname){
                    Cell cell=row.createCell(i);
                    if (s1.indexOf("+")>=0){//技能

                    }else {
                        cell.setCellValue(s1);
                    }
                    i++;
                }
            }else if (s.indexOf("value")>=0){
                System.out.println("--->value<---");
                String[] st=s.split(":");
                String[] vst=st[1].split("-");
                for (int j=0;j<vst.length;j++){
                    String sttv=vst[j].substring(1,vst[j].length()-1);
                    String[] values=sttv.split(",");
                    Row row=sheet.createRow(j+1);
                    for (int v=0;v<values.length;v++){
                        Cell cell=row.createCell(v);
                        cell.setCellValue(values[v]);
                    }
                }
            }
            System.out.println("-------------------------------->");
        }
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        System.out.println("OK!");
    }

    /*
    * IO读(按行读取)
    * */
    public List<String> readTxtAtLine(String file){
        System.out.println(file);
        List<String> list=new ArrayList<>();
        String line = " ";
        try {
            FileInputStream fileInputStream = new FileInputStream(file);//文件路径
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);//输入流
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);//读取
            while ((line = bufferedReader.readLine()) != null) {//按行读取
                list.add(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }




    public Map<String, Object> getExcelValues(String filePath )
    {
        String values = null;
        Map<String,Object> tlist=new HashMap<>();
        try{
            InputStream is = new FileInputStream(filePath);
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            XSSFWorkbook xwb = new XSSFWorkbook(is);
            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的内容
            for (int i = sheet.getFirstRowNum()+1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，
                    //cell = row.getCell(j).toString();
                    if (row.getCell(0).toString().indexOf(" 汇总")>1){
                        String cell=row.getCell(0).toString();
                        String cell2=String.valueOf(row.getCell(1).getNumericCellValue());
                        tlist.put(cell,cell2);
                    }
                    //System.out.print(cell + "\t");
                }
                //System.out.println("");
            }
            is.close();
        }catch(Exception e) {
            System.out.println("已运行xlRead() : " + e );
        }
        return tlist;
    }

    public List<String> getExcelValuesSC(String filePath ) throws IOException {
        String values = null;
        List<String> tlist=new ArrayList<>();
            InputStream is = new FileInputStream(filePath);
            XSSFWorkbook xwb = new XSSFWorkbook(is);
            XSSFSheet sheet = xwb.getSheetAt(0);
            XSSFRow row;
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                        String cell1=row.getCell(1).toString();
                        StringBuffer cells=new StringBuffer();
                        //System.out.print(row.getCell(4).toString());
                        cells.append((row.getCell(4).toString().isEmpty()?"null":row.getCell(4).toString())+",");
                        cells.append((row.getCell(5).toString().isEmpty()?"null":row.getCell(5).toString())+",");
                        cells.append((row.getCell(6).toString().isEmpty()?"null":row.getCell(6).toString())+",");
                        cells.append((row.getCell(7).toString().isEmpty()?"null":row.getCell(7).toString())+",");
                        cells.append((row.getCell(8).toString().isEmpty()?"null":row.getCell(8).toString())+",");
                        cells.append((row.getCell(9).toString().isEmpty()?"null":row.getCell(9).toString()));
                        tlist.add(cell1+":"+cells.toString());
                        System.out.println(cell1);
                        //tlist.put(cell1,cells.toString());
            }
            is.close();

        return tlist;
    }


    public List<String> getExcelOne(String filePath )
    {
        String values = null;
        List<String> tlist=new ArrayList<>();
        try{
            InputStream is = new FileInputStream(filePath);
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            XSSFWorkbook xwb = new XSSFWorkbook(is);
            // 读取第一章表格内容
            XSSFSheet sheet = xwb.getSheetAt(0);
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的内容
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                    String cell2=row.getCell(1).toString();
                    tlist.add(cell2);
                    System.out.println(cell2);
            }
            is.close();
        }catch(Exception e) {
            System.out.println("已运行xlRead() : " + e );
        }
        return tlist;
    }



    public static void writeExcel(String filePath,Map<String,Object> tmap,List<String> scmap,List<String> listInfos) throws IOException {
        Random random=new Random();
        Workbook workbook=null;
        String[] ss=filePath.split("\\.");
        if ("xls".equals(ss[1])){
            workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        }else if("xlsx".equals(ss[1])){
            workbook=new XSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet("TestNew");//创建工作表(Sheet)
        FileOutputStream out = new FileOutputStream(filePath);
        for (int r=0;r<listInfos.size();r++){
            //String tts=sc.replaceAll(" 汇总","");
                Row row = sheet.createRow(r+1);
                for (int i=0;i<scmap.size();i++){
                    String[] scs=scmap.get(i).split(":");
                    String[] scm=scs[1].split(",");
                    if (listInfos.get(r).equals(scs[0])){
                        Cell c3=row.createCell(13);
                        Cell c4=row.createCell(14);
                        Cell c5=row.createCell(15);
                        Cell c6=row.createCell(16);
                        Cell c7=row.createCell(17);
                        Cell c8=row.createCell(18);
                        Cell c9=row.createCell(19);
                        c3.setCellValue(tmap.get(scs[0]+" 汇总").toString());//+" 汇总"
                        c4.setCellValue(scm[0]);
                        c5.setCellValue(scm[1]);
                        c6.setCellValue(scm[2]);
                        c7.setCellValue(scm[3]);
                        c8.setCellValue(scm[4]);
                        c9.setCellValue(scm[5]);
                        break;
                    }
                }
        }
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }

    @Test
    public void testRead(){
        String tttt="E:\\NewTouch\\上海学习\\0709_数据处理\\抄表记录\\1512.xlsx";
        List<String> listInfos=getExcelOne(tttt);
        //String tttt="E:\\NewTouch\\上海学习\\0709_数据处理\\0730\\TEST\\tttt.xlsx";//0,1
        /*String allI="E:\\NewTouch\\上海学习\\0709_数据处理\\0730\\TEST\\6AllInfos.xlsx";
        String scTest6="E:\\NewTouch\\上海学习\\0709_数据处理\\0730\\TEST\\6scTest.xlsx";//1-4,5,6,7,8,9
        String ly6="E:\\NewTouch\\上海学习\\0709_数据处理\\0730\\TEST\\6TestInfo.xlsx";//1-3,4,5,6,7,8,9
        List<String> listInfos=getExcelOne(allI);
        System.out.println(listInfos.size());
        Map<String,Object> tlist=getExcelValues(tttt);

        Map<String,Object> lyMap=new HashMap<>();
        System.out.println(tlist.size());

        try {
            List<String> scMap=getExcelValuesSC(scTest6);
            System.out.println(scMap.size());
            writeExcel(ly6,tlist,scMap,listInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*for (String s:tlist.keySet()) {
            System.out.println(s+":"+tlist.get(s));
        }
        System.out.println("--------------------------------------------------------------------");
        for (String sc:scMap.keySet()) {
            System.out.println(sc+":"+scMap.get(sc));
        }*/
        //List<String> sclist=new ArrayList<>();
        //List<String> lylist6=new ArrayList<>();
    }


    /*@Test
    public void test(){
        String filePath="E:\\NewTouch\\上海学习\\mystandy\\jn.xlsx";
        String path="E:\\NewTouch\\上海学习\\mystandy\\allTileAndValue.txt";
        try {
            List<String> list=readTxtAtLine(path);
            writeExcel(filePath,list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

































    }
