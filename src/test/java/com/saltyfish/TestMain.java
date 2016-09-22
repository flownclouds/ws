package com.saltyfish;

import com.saltyfish.common.utils.PasswordEncode;
import org.apache.poi.hssf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by weck on 16/9/2.
 * <p>
 * 测试主类
 */
public class TestMain {
    public static void main(String args[]) {
        String salt = PasswordEncode.generateSalt();
        String password = "40bd001563085fc35165329ea1ff5c5ecbdbbeef";
        String encodedPassword = PasswordEncode.getHashedPassword(password, salt);
        boolean v = PasswordEncode.verify(encodedPassword, password, salt);
        System.out.println("salt:" + salt + "\n" + "password:" + password + "\n"
                + "encodedPassword:" + encodedPassword + "\n" + "verify:" + v + "\n");
        System.out.println(System.currentTimeMillis());

//        Double longitudeDegree, longitudeMinute, longitudeSecond, latitudeDegree, latitudeMinute, latitudeSecond;
//        Scanner scanner = new Scanner(System.in);
//        longitudeDegree = scanner.nextDouble();
//        longitudeMinute = scanner.nextDouble();
//        longitudeSecond = scanner.nextDouble();
//        latitudeDegree = scanner.nextDouble();
//        latitudeMinute = scanner.nextDouble();
//        latitudeSecond = scanner.nextDouble();
//        Double longitude = longitudeDegree + longitudeMinute / 600 + longitudeSecond / 6000;
//        Double latitude = latitudeDegree + latitudeMinute / 600 + latitudeSecond / 6000;
//        DecimalFormat df = new DecimalFormat("0.000000");
//        System.out.println("经度：" + df.format(longitude) + "," + "纬度:" + df.format(latitude));

        try {
            String begin = "";
            String end = "";
            String path = "file/水电站基本情况表.xls";
            InputStream in = new FileInputStream(new File(path));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //为了转时间
            HSSFWorkbook work = new HSSFWorkbook(in);
            // 得到excel的第0张表
            HSSFSheet sheet = work.getSheetAt(0);
            // 得到第1行的第一个单元格的样式
            HSSFRow rowCellStyle = sheet.getRow(2);
            HSSFCellStyle columnOne = rowCellStyle.getCell(0).getCellStyle();
            HSSFCellStyle columnOne1 = rowCellStyle.getCell(1).getCellStyle();
            HSSFCellStyle columnOne2 = rowCellStyle.getCell(4).getCellStyle();
            // 这里面的行和列的数法与计算机里的一样，从0开始是第一
            // 填充title数据
            HSSFRow row1 = sheet.getRow(0);
            HSSFCell cell1 = row1.getCell(0);
            HSSFRow row3 = sheet.getRow(2);
            HSSFCell cell = row3.getCell(0);
            HSSFRow row12 = sheet.getRow(11);
//            System.out.println(cell.getRichStringCellValue());
//            System.out.println(cell1.getRichStringCellValue());
            System.out.println(row3.getCell(7).getRichStringCellValue());
            System.out.println(row12.getCell(6).getRichStringCellValue());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}