package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.project.WaterConservationEntity;
import com.saltyfish.domain.entity.project.conservation.*;
import com.saltyfish.domain.entity.project.device.*;
import com.saltyfish.domain.entity.project.division.CanalEntity;
import com.saltyfish.domain.entity.project.division.PipeEntity;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by weck on 16/9/20.
 */
@Service
public class ExcelService {

    private static String aqueductModelFilePath = "file/渡槽基本情况表.xls";
    private static String bridgeModelFilePath = "file/桥梁基本情况表.xls";
    private static String channelModelFilePath = "file/渠道基本情况表.xls";
    private static String culvertModelFilePath = "file/涵洞基本情况表.xls";
    private static String damModelFilePath = "file/塘坝基本情况表.xls";
    private static String deepWellsModelFilePath = "file/深水井基本情况表.xls";
    private static String dripIrrigationPipeModelFilePath = "file/管滴灌基本情况表.xls";
    private static String greatWellsModelFilePath = "file/大口井基本情况表.xls";
    private static String hydropowerModelFilePath = "file/水电站基本情况表.xls";
    private static String pondModelFilePath = "file/水塘基本情况表.xls";
    private static String pumpStationModelFilePath = "file/泵站基本情况表.xls";
    private static String sluiceModelFilePath = "file/水闸基本情况表.xls";
    private static String watercourseModelFilePath = "file/河道基本情况表.xls";
    private static String waterWorksModelFilePath = "file/水厂基本情况表(饮水).xls";

    private static String aqueductSummaryModelFilePath = "file/渡槽汇总表.xls";
    private static String bridgeSummaryModelFilePath = "file/桥梁汇总表.xls";
    private static String channelSummaryModelFilePath = "file/渠道汇总表.xls";
    private static String culvertSummaryModelFilePath = "file/涵洞汇总表.xls";
    private static String damSummaryModelFilePath = "file/塘坝汇总表.xls";
    private static String deepWellsSummaryModelFilePath = "file/深水井汇总表.xls";
    private static String dripIrrigationPipeSummaryModelFilePath = "file/管滴灌汇总表.xls";
    private static String greatWellsSummaryModelFilePath = "file/大口井汇总表.xls";
    private static String hydropowerSummaryModelFilePath = "file/水电站汇总表.xls";
    private static String pondSummaryModelFilePath = "file/水塘汇总表.xls";
    private static String pumpStationSummaryModelFilePath = "file/泵站汇总表.xls";
    private static String sluiceSummaryModelFilePath = "file/水闸汇总表.xls";
    private static String watercourseSummaryModelFilePath = "file/河道汇总表.xls";
    private static String waterWorksSummaryModelFilePath = "file/水厂汇总表.xls";


    /**
     * 导出水厂
     */
    public void exportWaterWorks(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        WaterWorksEntity waterWorksEntity = waterConservationEntity.getProjectMark().getWaterWorks();
        InputStream inputStream = new FileInputStream(new File(waterWorksModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 8; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(waterWorksEntity.getProvideAmount());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(waterWorksEntity.getWaterModel());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(waterWorksEntity.getHaveCleaner());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(waterWorksEntity.getIsRegularCheck());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(waterWorksEntity.getDayProvideAmount());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(waterWorksEntity.getProvideVillageCount());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterWorksEntity.getProvidePopulation());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(waterWorksEntity.getHaveProtectArea());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue(waterConservationEntity.getManager());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(waterConservationEntity.getRemark());
                    break;
                default:
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "shuichang");
    }

    /**
     * 导出河道
     */
    public void exportWatercourse(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        WatercourseEntity watercourseEntity = waterConservationEntity.getProjectMark().getWatercourse();
        InputStream inputStream = new FileInputStream(new File(watercourseModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 8; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(watercourseEntity.getNature());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(watercourseEntity.getLength());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(watercourseEntity.getLastDredgingTime());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(watercourseEntity.getEstuaryWidth());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(watercourseEntity.getHediWidth());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(watercourseEntity.getLeftWidth());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(watercourseEntity.getEstuaryHeight());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(watercourseEntity.getHediHeight());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(watercourseEntity.getRightWidth());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(watercourseEntity.getFlowVillages());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue("经度:" + watercourseEntity.getEndpointLocation().getLongitude() + "\n"
                            + "纬度：" + watercourseEntity.getEndpointLocation().getLatitude());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue(waterConservationEntity.getManager());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(waterConservationEntity.getRemark());
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "hedao");
    }


    /**
     * 导出水闸
     */
    public void exportSluice(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        SluiceEntity sluiceEntity = waterConservationEntity.getProjectMark().getSluice();
        InputStream inputStream = new FileInputStream(new File(sluiceModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 9; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(sluiceEntity.getWatercourseLocation());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(sluiceEntity.getModel());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(sluiceEntity.getHoleCount());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(sluiceEntity.getDoor());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(sluiceEntity.getHoistTonnage());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue("宽：" + sluiceEntity.getHoleWidth() + "\n高：" + sluiceEntity.getHoleHeight());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue("宽：" + sluiceEntity.getDoorWidth() + "\n高：" + sluiceEntity.getDoorHeight());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(sluiceEntity.getHoistModel());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(sluiceEntity.getBuildingSituation());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(sluiceEntity.getDoorSituation());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue(sluiceEntity.getHoistSituation());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell8_4 = row.getCell(3);
                    cell8_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell8_6 = row.getCell(5);
                    cell8_6.setCellValue(waterConservationEntity.getManager());
                    break;
                case 8:
                    HSSFCell cell9_2 = row.getCell(1);
                    cell9_2.setCellValue(waterConservationEntity.getRemark());
                    break;
                default:
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "shuizha");
    }

    /**
     * 导出水塘
     */
    public void exportPond(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        PondEntity pondEntity = waterConservationEntity.getProjectMark().getPond();
        InputStream inputStream = new FileInputStream(new File(pondModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 6; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(pondEntity.getMainFunction());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(pondEntity.getLastDredgingTime());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(pondEntity.getWaterArea());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(pondEntity.getWaterCapacity());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(waterConservationEntity.getManager());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(waterConservationEntity.getRemark());
                    break;
                default:
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "shuitang");
    }

    /**
     * 导出泵站
     */
    public void exportPumpStation(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        PumpStationEntity pumpStationEntity = waterConservationEntity.getProjectMark().getPumpStation();
        InputStream inputStream = new FileInputStream(new File(pumpStationModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        for (int i = 2; i < 12; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 2:
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(pumpStationEntity.getRiverLocation());
                    HSSFCell cell3_9 = row.getCell(8);
                    cell3_9.setCellValue(pumpStationEntity.getIrrigateArea());
                    break;
                case 3:
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    HSSFCell cell4_9 = row.getCell(8);
                    cell4_9.setCellValue(pumpStationEntity.getPaddyFieldArea());
                    break;
                case 4:
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell5_9 = row.getCell(8);
                    cell5_9.setCellValue(pumpStationEntity.getDrainageArea());
                    break;
                case 5:
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell6_9 = row.getCell(8);
                    cell6_9.setCellValue(pumpStationEntity.getIrrigateFee());
                    break;
                case 6:
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(pumpStationEntity.getNature());
                    HSSFCell cell7_9 = row.getCell(8);
                    cell7_9.setCellValue(pumpStationEntity.getDrainageFee());
                    break;
                case 7:
                    HSSFCell cell8_4 = row.getCell(3);
                    cell8_4.setCellValue(pumpStationEntity.getMachineArea());
                    HSSFCell cell8_9 = row.getCell(8);
                    cell8_9.setCellValue(pumpStationEntity.getAnnualFee());
                    break;
                case 8:
                    HSSFCell cell9_4 = row.getCell(3);
                    cell9_4.setCellValue(pumpStationEntity.getTotalInstalledCapacity());
                    HSSFCell cell9_9 = row.getCell(8);
                    cell9_9.setCellValue(waterConservationEntity.getPropertyOwner());
                    break;
                case 9:
                    HSSFCell cell10_4 = row.getCell(3);
                    cell10_4.setCellValue(pumpStationEntity.getRiverElevation());
                    HSSFCell cell10_9 = row.getCell(8);
                    cell10_9.setCellValue(waterConservationEntity.getManageModel());
                    break;
                case 10:
                    HSSFCell cell11_4 = row.getCell(3);
                    cell11_4.setCellValue(pumpStationEntity.getPoolHeight());
                    HSSFCell cell11_9 = row.getCell(8);
                    cell11_9.setCellValue(waterConservationEntity.getManager());
                    break;
                case 11:
                    HSSFCell cell12_8 = row.getCell(7);
                    cell12_8.setCellValue("运行情况:" + waterConservationEntity.getSituation() +
                            "\n存在的问题：" + pumpStationEntity.getProblem());
                default:
                    break;
            }
        }
        List<TransformerEntity> transformers = pumpStationEntity.getTransformers();
        for (TransformerEntity transformer : transformers) {
            int rowNum = 11;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(transformer.getCapacity());
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(transformer.getModel());
            }
        }
        List<PumpEntity> pumps = pumpStationEntity.getPumps();
        for (PumpEntity pump : pumps) {
            int rowNum = 13;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(pump.getModel());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(pump.getCount());
                HSSFCell cell3 = sheet1.getRow(rowNum + 2).getCell(column);
                cell3.setCellValue(pump.getLiftOrFlow());
                HSSFCell cell4 = sheet1.getRow(rowNum + 3).getCell(column);
                cell4.setCellValue(pump.getFactoryDate());
            }
        }
        List<ElectricMotorEntity> electricMotors = pumpStationEntity.getElectricMotors();
        for (ElectricMotorEntity electricMotor : electricMotors) {
            int rowNum = 17;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(electricMotor.getModel());
                HSSFCell cell3 = sheet1.getRow(rowNum + 2).getCell(column);
                cell3.setCellValue(electricMotor.getFactoryDate());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(electricMotor.getPower());
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "bengzhan");
    }

    /**
     * 导出水电站
     */
    public void exportHydropower(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        HydropowerEntity hydropowerEntity = waterConservationEntity.getProjectMark().getHydropower();
        InputStream inputStream = new FileInputStream(new File(hydropowerModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        for (int i = 2; i < 12; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 2:
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_9 = row.getCell(8);
                    cell3_9.setCellValue(hydropowerEntity.getIrrigateArea());
                    break;
                case 3:
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(hydropowerEntity.getRiverLocation());
                    HSSFCell cell4_9 = row.getCell(8);
                    cell4_9.setCellValue(hydropowerEntity.getPaddyFieldArea());
                    break;
                case 4:
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    HSSFCell cell5_9 = row.getCell(8);
                    cell5_9.setCellValue(hydropowerEntity.getDrainageArea());
                    break;
                case 5:
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell6_9 = row.getCell(8);
                    cell6_9.setCellValue(hydropowerEntity.getIrrigateFee());
                    break;
                case 6:
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell7_9 = row.getCell(8);
                    cell7_9.setCellValue(hydropowerEntity.getDrainageFee());
                    break;
                case 7:
                    HSSFCell cell8_4 = row.getCell(3);
                    cell8_4.setCellValue(hydropowerEntity.getAffiliation());
                    HSSFCell cell8_9 = row.getCell(8);
                    cell8_9.setCellValue(hydropowerEntity.getAnnualFee());
                    break;
                case 8:
                    HSSFCell cell9_4 = row.getCell(3);
                    cell9_4.setCellValue(hydropowerEntity.getMachineArea());
                    HSSFCell cell9_9 = row.getCell(8);
                    cell9_9.setCellValue(waterConservationEntity.getPropertyOwner());
                    break;
                case 9:
                    HSSFCell cell10_4 = row.getCell(3);
                    cell10_4.setCellValue(hydropowerEntity.getSumElectricCapacity());
                    HSSFCell cell10_9 = row.getCell(8);
                    cell10_9.setCellValue(waterConservationEntity.getManageModel());
                    break;
                case 10:
                    HSSFCell cell11_4 = row.getCell(3);
                    cell11_4.setCellValue(hydropowerEntity.getAverageCapacity());
                    HSSFCell cell11_9 = row.getCell(8);
                    cell11_9.setCellValue(waterConservationEntity.getManager());
                    break;
                case 11:
                    HSSFCell cell12_8 = row.getCell(7);
                    cell12_8.setCellValue("运行情况:" + waterConservationEntity.getSituation() +
                            "\n存在的问题：" + hydropowerEntity.getProblem());
                default:
                    break;
            }
        }
        List<TransformerEntity> transformers = hydropowerEntity.getTransformers();
        for (TransformerEntity transformer : transformers) {
            int rowNum = 11;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(transformer.getModel());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(transformer.getCapacity());
            }
        }
        List<TurbineEntity> turbines = hydropowerEntity.getTurbines();
        for (TurbineEntity turbineEntity : turbines) {
            int rowNum = 13;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(turbineEntity.getModel());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(turbineEntity.getCount());
                HSSFCell cell3 = sheet1.getRow(rowNum + 2).getCell(column);
                cell3.setCellValue(turbineEntity.getTurnsOrFlow());
                HSSFCell cell4 = sheet1.getRow(rowNum + 3).getCell(column);
                cell4.setCellValue(turbineEntity.getFactoryDate());
            }
        }
        List<GeneratorEntity> generators = hydropowerEntity.getGenerators();
        for (GeneratorEntity generator : generators) {
            int rowNum = 17;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(generator.getModel());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(generator.getPower());
                HSSFCell cell3 = sheet1.getRow(rowNum + 2).getCell(column);
                cell3.setCellValue(generator.getFactoryDate());
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "shuidianzhan");
    }

    /**
     * 导出大口井
     */
    public void exportGreateWells(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        GreatWellsEntity greatWellsEntity = waterConservationEntity.getProjectMark().getGreatWells();
        InputStream inputStream = new FileInputStream(new File(greatWellsModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        for (int i = 2; i < 6; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(greatWellsEntity.getIrrigateArea());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(greatWellsEntity.getWaterCapacity());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(greatWellsEntity.getSize());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(greatWellsEntity.getDepth());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(greatWellsEntity.getModelAndMaterial());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue(waterConservationEntity.getManager());
                    break;
                default:
                    break;
            }
        }
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "dakoujin");
    }

    /**
     * 导出管滴灌
     */
    public void exportDripIrrigationPipe(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        DripIrrigationPipeEntity dripIrrigationPipeEntity = waterConservationEntity.getProjectMark().getDripIrrigationPipe();
        InputStream inputStream = new FileInputStream(new File(dripIrrigationPipeModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 5; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(dripIrrigationPipeEntity.getIntakeWay());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(dripIrrigationPipeEntity.getWaterResource());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(dripIrrigationPipeEntity.getLength());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(dripIrrigationPipeEntity.getIrrigateArea());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(waterConservationEntity.getConstructTime());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(waterConservationEntity.getManager());
                    break;
                default:
                    break;
            }
        }
        List<PipeEntity> pipes = dripIrrigationPipeEntity.getPipes();
        int rowNum = 6;
        for (PipeEntity pipe : pipes) {
            int i = 1;
            HSSFRow row = sheet1.getRow(rowNum);
            for (int j = 0; j < 4; j++) {
                HSSFCell cell = row.getCell(j);
                switch (j) {
                    case 0:
                        cell.setCellValue(pipe.getModel());
                        break;
                    case 1:
                        cell.setCellValue(pipe.getLength());
                        break;
                    case 2:
                        cell.setCellValue(pipe.getDiameter());
                        break;
                    case 3:
                        cell.setCellValue(pipe.getMaterial());
                        break;
                    default:
                        break;
                }
            }
            i++;
            if (i % 2 == 0) {
                rowNum++;
                HSSFRow hr = sheet1.getRow(rowNum);
                HSSFCell hc = hr.getCell(0);
                hc.setCellValue(".");
            }
            rowNum++;
        }
        HSSFRow row15 = sheet1.getRow(14);
        for (int k = 0; k < 4; k++) {
            HSSFCell cell = row15.getCell(k);
            switch (k) {
                case 1:
                    cell.setCellValue(dripIrrigationPipeEntity.getSumLength());
                    break;
                case 2:
                    cell.setCellValue(dripIrrigationPipeEntity.getSumDiameter());
                    break;
                default:
                    break;
            }
        }
        HSSFRow row16 = sheet1.getRow(15);
        HSSFCell cell16_2 = row16.getCell(1);
        cell16_2.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "guandiguan");
    }

    /**
     * 导出深水井
     */
    public void exportDeepWells(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        DeepWellsEntity deepWellsEntity = waterConservationEntity.getProjectMark().getDeepWells();
        InputStream inputStream = new FileInputStream(new File(deepWellsModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 7; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterConservationEntity.getName());
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(deepWellsEntity.getIrrigateArea());
                    HSSFCell cell3_6 = row.getCell(5);
                    cell3_6.setCellValue(deepWellsEntity.getDeepPump());
                    break;
                case 3:
                    HSSFCell cell4_2 = row.getCell(1);
                    cell4_2.setCellValue(deepWellsEntity.getDiameter());
                    HSSFCell cell4_4 = row.getCell(3);
                    cell4_4.setCellValue(deepWellsEntity.getDepth());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(deepWellsEntity.getMaterial());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(waterConservationEntity.getConstructUnit());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(waterConservationEntity.getConstructTime());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(waterConservationEntity.getPropertyOwner());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterConservationEntity.getManageModel());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue(waterConservationEntity.getManager());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(waterConservationEntity.getRemark());
                    break;
                default:
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "shenshuijin");
    }


    /**
     * 导出塘坝
     */
    public void exportDam(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        DamEntity damEntity = waterConservationEntity.getProjectMark().getDam();
        InputStream inputStream = new FileInputStream(new File(damModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 21; i++) {
            HSSFRow row = sheet1.getRow(i);
            HSSFCell cell = row.getCell(1);
            switch (i) {
                case 0:
                    cell = row.getCell(0);
                    cell.setCellValue("编码：" + waterConservationEntity.getCode()
                            + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                            + "  村: " + waterConservationEntity.getVillage().getName()
                            + "  组: " + waterConservationEntity.getGroup().getName());
                    break;
                case 2:
                    cell.setCellValue(waterConservationEntity.getName());
                    break;
                case 3:
                    cell.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                            + "纬度：" + waterConservationEntity.getLocation().getLatitude());
                    break;
                case 4:
                    cell.setCellValue(damEntity.getIsRegistered());
                    break;
                case 5:
                    cell.setCellValue(damEntity.getFeatures());
                    break;
                case 6:
                    cell.setCellValue(damEntity.getMainFunctions());
                    break;
                case 7:
                    cell.setCellValue(damEntity.getIsAccountability());
                    break;
                case 8:
                    cell.setCellValue(damEntity.getFeeResources());
                    break;
                case 9:
                    cell.setCellValue(damEntity.getMaintainPersonFee());
                    break;
                case 10:
                    cell.setCellValue(damEntity.getIsCertificated());
                    break;
                case 11:
                    cell.setCellValue(waterConservationEntity.getSituation());
                    break;
                case 12:
                    cell.setCellValue(waterConservationEntity.getPropertyOwner());
                    break;
                case 13:
                    cell.setCellValue(waterConservationEntity.getManager());
                    break;
                case 14:
                    cell.setCellValue(damEntity.getDevelopment());
                    break;
                case 15:
                    cell.setCellValue(damEntity.getManageRageLine());
                    break;
                case 16:
                    cell.setCellValue(damEntity.getPondDamManageRageLine());
                    break;
                case 17:
                    cell.setCellValue(damEntity.getProtectRageManagement());
                    break;
                case 18:
                    cell.setCellValue(damEntity.getProtectGround());
                    break;
                case 19:
                    cell.setCellValue(damEntity.getTwoLinesBuilding());
                    break;
                case 20:
                    cell.setCellValue(damEntity.getSpecifiedMange());
                    break;
                default:
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "tangba");
    }


    /**
     * 导出涵洞
     */
    public void exportCulvert(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        CulvertEntity culvertEntity = waterConservationEntity.getProjectMark().getCulvert();
        InputStream inputStream = new FileInputStream(new File(culvertModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(waterConservationEntity.getName());
        HSSFCell cell3_4 = row3.getCell(3);
        cell3_4.setCellValue(culvertEntity.getWatercourseLocation());
        HSSFCell cell3_6 = row3.getCell(5);
        cell3_6.setCellValue(culvertEntity.getCulvertModel());
        HSSFRow row4 = sheet1.getRow(3);
        HSSFCell cell4_2 = row4.getCell(1);
        cell4_2.setCellValue(culvertEntity.getSectionSize());
        HSSFCell cell4_4 = row4.getCell(3);
        cell4_4.setCellValue(culvertEntity.getLength());
        HSSFCell cell4_6 = row4.getCell(5);
        cell4_6.setCellValue(culvertEntity.getHoleModel());
        HSSFRow row5 = sheet1.getRow(4);
        HSSFCell cell5_2 = row5.getCell(1);
        cell5_2.setCellValue(culvertEntity.getDoorMaterial());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(culvertEntity.getHoistModel());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue(culvertEntity.getHoleMaterial());
        HSSFRow row6 = sheet1.getRow(5);
        HSSFCell cell6_2 = row6.getCell(1);
        cell6_2.setCellValue(waterConservationEntity.getConstructUnit());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(waterConservationEntity.getConstructTime());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                + "纬度：" + waterConservationEntity.getLocation().getLatitude());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(waterConservationEntity.getPropertyOwner());
        HSSFCell cell7_4 = row7.getCell(3);
        cell7_4.setCellValue(waterConservationEntity.getManageModel());
        HSSFCell cell7_6 = row7.getCell(5);
        cell7_6.setCellValue(waterConservationEntity.getManager());
        HSSFRow row8 = sheet1.getRow(7);
        HSSFCell cell8_2 = row8.getCell(1);
        cell8_2.setCellValue(waterConservationEntity.getSituation());
        HSSFCell cell8_4 = row8.getCell(3);
        cell8_4.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "handong");
    }

    /**
     * 导出渠道
     */
    public void exportChannel(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        ChannelEntity channelEntity = waterConservationEntity.getProjectMark().getChannel();
        InputStream inputStream = new FileInputStream(new File(channelModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(channelEntity.getHeadOrPumpStation());
        HSSFCell cell3_4 = row3.getCell(3);
        cell3_4.setCellValue(channelEntity.getBuildingMatchRate());
        HSSFCell cell3_6 = row3.getCell(5);
        cell3_6.setCellValue(waterConservationEntity.getConstructUnit());
        HSSFRow row4 = sheet1.getRow(3);
        HSSFCell cell4_2 = row4.getCell(1);
        cell4_2.setCellValue(channelEntity.getLength());
        HSSFCell cell4_4 = row4.getCell(3);
        cell4_4.setCellValue(channelEntity.getGoodConditionRate());
        HSSFCell cell4_6 = row4.getCell(5);
        cell4_6.setCellValue(waterConservationEntity.getConstructTime());
        HSSFRow row5 = sheet1.getRow(4);
        HSSFCell cell5_2 = row5.getCell(1);
        cell5_2.setCellValue(waterConservationEntity.getPropertyOwner());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(waterConservationEntity.getManageModel());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue(waterConservationEntity.getManager());
        List<CanalEntity> canals = channelEntity.getCanals();
        int rowNum = 6;
        for (CanalEntity canal : canals) {
            int i = 1;
            HSSFRow row = sheet1.getRow(rowNum);
            for (int j = 0; j < 6; j++) {
                HSSFCell cell = row.getCell(j);
                switch (j) {
                    case 0:
                        cell.setCellValue(canal.getModel());
                        break;
                    case 1:
                        cell.setCellValue(canal.getLength());
                        break;
                    case 2:
                        cell.setCellValue(canal.getSectionSize());
                        break;
                    case 3:
                        cell.setCellValue(canal.getSeepageCanalLength());
                        break;
                    case 4:
                        cell.setCellValue(canal.getLiningSectionSize());
                        break;
                    case 5:
                        cell.setCellValue(canal.getLiningMaterial());
                        break;
                    default:
                        break;
                }
                i++;
                if (0 == i % 2) {
                    rowNum++;
                    HSSFRow hssfRow = sheet1.getRow(rowNum);
                    HSSFCell hssfCell = hssfRow.getCell(0);
                    hssfCell.setCellValue(".");
                }
                rowNum++;
            }
        }
        HSSFRow row19 = sheet1.getRow(18);
        HSSFCell cell19_2 = row19.getCell(1);
        cell19_2.setCellValue(channelEntity.getSumLength());
        HSSFCell cell19_3 = row19.getCell(2);
        cell19_3.setCellValue(channelEntity.getSumSectionSize());
        HSSFCell cell19_4 = row19.getCell(3);
        cell19_4.setCellValue(channelEntity.getSumSeepageCanalLength());
        HSSFCell cell19_5 = row19.getCell(4);
        cell19_5.setCellValue(channelEntity.getSumLiningSectionSize());
        HSSFRow row20 = sheet1.getRow(19);
        HSSFCell cell20_2 = row20.getCell(1);
        cell20_2.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "qudao");
    }

    /**
     * 导出桥梁
     */
    public void exportBridge(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        BridgeEntity bridgeEntity = waterConservationEntity.getProjectMark().getBridge();
        InputStream inputStream = new FileInputStream(new File(bridgeModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(waterConservationEntity.getName());
        HSSFCell cell3_4 = row3.getCell(3);
        cell3_4.setCellValue(bridgeEntity.getWatercourseLocation());
        HSSFCell cell3_6 = row3.getCell(5);
        cell3_6.setCellValue(bridgeEntity.getCrossCount());
        HSSFRow row4 = sheet1.getRow(3);
        HSSFCell cell4_2 = row4.getCell(1);
        cell4_2.setCellValue(bridgeEntity.getStructureAndMaterial());
        HSSFCell cell4_4 = row4.getCell(3);
        cell4_4.setCellValue(bridgeEntity.getLoadStandard());
        HSSFCell cell4_6 = row4.getCell(5);
        cell4_6.setCellValue(bridgeEntity.getCrossLength());
        HSSFRow row5 = sheet1.getRow(4);
        HSSFCell cell5_2 = row5.getCell(1);
        cell5_2.setCellValue(bridgeEntity.getWidth());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(bridgeEntity.getLength());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue(waterConservationEntity.getSituation());
        HSSFRow row6 = sheet1.getRow(5);
        HSSFCell cell6_2 = row6.getCell(1);
        cell6_2.setCellValue(waterConservationEntity.getConstructUnit());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(waterConservationEntity.getConstructTime());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                + "纬度：" + waterConservationEntity.getLocation().getLatitude());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(waterConservationEntity.getPropertyOwner());
        HSSFCell cell7_4 = row7.getCell(3);
        cell7_4.setCellValue(waterConservationEntity.getManageModel());
        HSSFCell cell7_6 = row7.getCell(5);
        cell7_6.setCellValue(waterConservationEntity.getManager());
        HSSFRow row8 = sheet1.getRow(7);
        HSSFCell cell8_2 = row8.getCell(1);
        cell8_2.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "qiaoliang");
    }

    /**
     * 导出渡槽
     */
    public void exportAqueduct(WaterConservationEntity waterConservationEntity, HttpServletResponse httpServletResponse) throws IOException {
        AqueductEntity aqueductEntity = waterConservationEntity.getProjectMark().getAqueduct();
        InputStream inputStream = new FileInputStream(new File(aqueductModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + waterConservationEntity.getCode()
                + "                街道/乡/镇: " + waterConservationEntity.getTown().getName()
                + "  村: " + waterConservationEntity.getVillage().getName()
                + "  组: " + waterConservationEntity.getGroup().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(waterConservationEntity.getName());
        HSSFCell cell3_4 = row3.getCell(3);
        cell3_4.setCellValue(aqueductEntity.getCrossWatercourseLocation());
        HSSFCell cell3_6 = row3.getCell(5);
        cell3_6.setCellValue(aqueductEntity.getCrossCount());
        HSSFRow row4 = sheet1.getRow(3);
        HSSFCell cell4_2 = row4.getCell(1);
        cell4_2.setCellValue(aqueductEntity.getSectionSize());
        HSSFCell cell4_4 = row4.getCell(3);
        cell4_4.setCellValue(aqueductEntity.getStructureAndMaterial());
        HSSFCell cell4_6 = row4.getCell(5);
        cell4_6.setCellValue(aqueductEntity.getCrossLength());
        HSSFRow row5 = sheet1.getRow(4);
        HSSFCell cell5_2 = row5.getCell(1);
        cell5_2.setCellValue(waterConservationEntity.getConstructUnit());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(waterConservationEntity.getConstructTime());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue("经度:" + waterConservationEntity.getLocation().getLongitude() + "\n"
                + "纬度：" + waterConservationEntity.getLocation().getLatitude());
        HSSFRow row6 = sheet1.getRow(5);
        HSSFCell cell6_2 = row6.getCell(1);
        cell6_2.setCellValue(waterConservationEntity.getPropertyOwner());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(waterConservationEntity.getManageModel());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue(waterConservationEntity.getManager());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(waterConservationEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "project");
    }

    private void writeOut(HttpServletResponse httpServletResponse, HSSFWorkbook hssfWorkbook, String category) {
        try {
            OutputStream outputStream = httpServletResponse.getOutputStream();
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-disposition",
                    "attachment;filename=" + category + "_" + System.currentTimeMillis() + ".xls");
            httpServletResponse.setHeader("Cache-Control", "public");//HTTP 1.1
            httpServletResponse.setDateHeader("Expires", System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000000);   //在代理服务器端防止缓冲, nanoseconds
            httpServletResponse.setDateHeader("max-age", System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000000);
            hssfWorkbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出渡槽汇总
     */
    public void exportAqueductSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(aqueductSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            AqueductEntity aqueduct = project.getProjectMark().getAqueduct();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(aqueduct.getCrossWatercourseLocation());
                            break;
                        case 5:
                            cell.setCellValue(aqueduct.getCrossCount());
                            break;
                        case 6:
                            cell.setCellValue(aqueduct.getCrossLength());
                            break;
                        case 7:
                            cell.setCellValue(aqueduct.getSectionSize());
                            break;
                        case 8:
                            cell.setCellValue(aqueduct.getStructureAndMaterial());
                            break;
                        case 9:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 13:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[0]);
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 14:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[1]);
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 15:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[2]);
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 17:
//                        cell.setCellValue(project.getManager().split("#")[0]);
                            cell.setCellValue(project.getManager());
                            break;
                        case 18:
//                        cell.setCellValue(project.getManager().split("#")[1]);
                            cell.setCellValue(project.getManager());
                            break;
                        case 19:
//                        cell.setCellValue(project.getManager().split("#")[2]);
                            cell.setCellValue(project.getManager());
                            break;
                        case 20:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }

        }
        writeOut(response, hssfWorkbook, "ducaohuizong");
    }

    /**
     * 导出桥梁汇总
     */
    public void exportBridgeSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(bridgeSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            BridgeEntity bridge = project.getProjectMark().getBridge();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 25; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(bridge.getWatercourseLocation());
                            break;
                        case 5:
                            cell.setCellValue(bridge.getCrossCount());
                            break;
                        case 6:
                            cell.setCellValue(bridge.getCrossLength());
                            break;
                        case 7:
                            cell.setCellValue(bridge.getStructureAndMaterial());
                            break;
                        case 8:
                            cell.setCellValue(bridge.getStructureAndMaterial());
                            break;
                        case 9:
                            cell.setCellValue(bridge.getLoadStandard());
                            break;
                        case 10:
                            cell.setCellValue(bridge.getWidth());
                            break;
                        case 11:
                            cell.setCellValue(bridge.getLength());
                            break;
                        case 12:
                            cell.setCellValue(project.getSituation());
                            break;
                        case 13:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 14:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 15:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 16:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 17:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 18:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 21:
                            cell.setCellValue(project.getManager());
                            break;
                        case 22:
                            cell.setCellValue(project.getManager());
                            break;
                        case 23:
                            cell.setCellValue(project.getManager());
                            break;
                        case 24:
                            cell.setCellValue(project.getRemark());
                            break;
                    }
                }
            }
            writeOut(response, hssfWorkbook, "qiaolianghuizong");
        }
    }

    /**
     * 导出渠道汇总
     */
    public void exportChannelSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(channelSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            ChannelEntity channel = project.getProjectMark().getChannel();
            List<CanalEntity> canals = channel.getCanals();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 37; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(channel.getHeadOrPumpStation());
                            break;
                        case 4:
                            cell.setCellValue(channel.getBuildingMatchRate());
                            break;
                        case 5:
                            cell.setCellValue(channel.getLength());
                            break;
                        case 6:
                            cell.setCellValue(channel.getGoodConditionRate());
                            break;
                        case 7:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 8:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 9:
                            cell.setCellValue(canals.get(0).getLength());
                            HSSFCell cell3_10 = sheet1.getRow(2).getCell(9);
                            cell3_10.setCellValue(canals.get(0).getModel());
                            break;
                        case 10:
                            cell.setCellValue(canals.get(0).getSectionSize());
                            break;
                        case 11:
                            cell.setCellValue(canals.get(0).getSeepageCanalLength());
                            break;
                        case 12:
                            cell.setCellValue(canals.get(0).getLiningSectionSize());
                            break;
                        case 13:
                            cell.setCellValue(canals.get(0).getLiningMaterial());
                            break;
                        case 14:
                            cell.setCellValue(canals.get(1).getLength());
                            HSSFCell cell3_15 = sheet1.getRow(2).getCell(14);
                            cell3_15.setCellValue(canals.get(1).getModel());
                            break;
                        case 15:
                            cell.setCellValue(canals.get(1).getSectionSize());
                            break;
                        case 16:
                            cell.setCellValue(canals.get(1).getSeepageCanalLength());
                            break;
                        case 17:
                            cell.setCellValue(canals.get(1).getLiningSectionSize());
                            break;
                        case 18:
                            cell.setCellValue(canals.get(1).getLiningMaterial());
                            break;
                        case 19:
                            cell.setCellValue(canals.get(2).getLength());
                            HSSFCell cell3_20 = sheet1.getRow(2).getCell(19);
                            cell3_20.setCellValue(canals.get(2).getModel());
                            break;
                        case 20:
                            cell.setCellValue(canals.get(2).getSectionSize());
                            break;
                        case 21:
                            cell.setCellValue(canals.get(2).getSeepageCanalLength());
                            break;
                        case 22:
                            cell.setCellValue(canals.get(2).getLiningSectionSize());
                            break;
                        case 23:
                            cell.setCellValue(canals.get(2).getLiningMaterial());
                            break;
                        case 24:
                            cell.setCellValue(canals.get(3).getLength());
                            HSSFCell cell3_25 = sheet1.getRow(2).getCell(24);
                            cell3_25.setCellValue(canals.get(3).getModel());
                            break;
                        case 25:
                            cell.setCellValue(canals.get(3).getSectionSize());
                            break;
                        case 26:
                            cell.setCellValue(canals.get(3).getSeepageCanalLength());
                            break;
                        case 27:
                            cell.setCellValue(canals.get(3).getLiningSectionSize());
                            break;
                        case 28:
                            cell.setCellValue(canals.get(2).getLiningMaterial());
                            break;
                        case 29:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 32:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 33:
                            cell.setCellValue(project.getManager());
                            break;
                        case 34:
                            cell.setCellValue(project.getManager());
                            break;
                        case 35:
                            cell.setCellValue(project.getManager());
                            break;
                        case 36:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "qudaohuizong");
    }

    /**
     * 导出涵洞汇总
     */
    public void exportCulvertSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(culvertSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            CulvertEntity culvert = project.getProjectMark().getCulvert();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 26; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(culvert.getWatercourseLocation());
                            break;
                        case 5:
                            cell.setCellValue(culvert.getCulvertModel());
                            break;
                        case 6:
                            cell.setCellValue(culvert.getSectionSize());
                            break;
                        case 7:
                            cell.setCellValue(culvert.getSectionSize());
                            break;
                        case 8:
                            cell.setCellValue(culvert.getLength());
                            break;
                        case 9:
                            cell.setCellValue(culvert.getHoleModel());
                            break;
                        case 10:
                            cell.setCellValue(culvert.getDoorMaterial());
                            break;
                        case 11:
                            cell.setCellValue(culvert.getHoistModel());
                            break;
                        case 12:
                            cell.setCellValue(culvert.getHoleMaterial());
                            break;
                        case 13:
                            cell.setCellValue(project.getSituation());
                            break;
                        case 14:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 15:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 16:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 17:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 18:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 21:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 22:
                            cell.setCellValue(project.getManager());
                            break;
                        case 23:
                            cell.setCellValue(project.getManager());
                            break;
                        case 24:
                            cell.setCellValue(project.getManager());
                            break;
                        case 25:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "handonghuizong");
    }

    /**
     * 导出塘坝汇总
     */
    public void exportDamSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(damSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            DamEntity dam = project.getProjectMark().getDam();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 29; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(dam.getIsRegistered());
                            break;
                        case 5:
                            cell.setCellValue(dam.getFeatures());
                            break;
                        case 6:
                            cell.setCellValue(dam.getMainFunctions());
                            break;
                        case 7:
                            cell.setCellValue(dam.getIsAccountability());
                            break;
                        case 8:
                            cell.setCellValue(dam.getFeeResources());
                            break;
                        case 9:
                            cell.setCellValue(dam.getMaintainPersonFee());
                            break;
                        case 10:
                            cell.setCellValue(dam.getIsCertificated());
                            break;
                        case 11:
                            cell.setCellValue(project.getSituation());
                            break;
                        case 12:
                            cell.setCellValue(dam.getDevelopment());
                            break;
                        case 13:
                            cell.setCellValue(dam.getManageRageLine());
                            break;
                        case 14:
                            cell.setCellValue(dam.getPondDamManageRageLine());
                            break;
                        case 15:
                            cell.setCellValue(dam.getProtectRageManagement());
                            break;
                        case 16:
                            cell.setCellValue(dam.getProtectGround());
                            break;
                        case 17:
                            cell.setCellValue(dam.getTwoLinesBuilding());
                            break;
                        case 18:
                            cell.setCellValue(dam.getSpecifiedMange());
                            break;
                        case 19:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 20:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 21:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 22:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 25:
                            cell.setCellValue(project.getManager());
                            break;
                        case 26:
                            cell.setCellValue(project.getManager());
                            break;
                        case 27:
                            cell.setCellValue(project.getManager());
                            break;
                        case 28:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "tangbahuizong");
    }

    /**
     * 导出深水井汇总
     */
    public void exportDeepWellsSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(deepWellsSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            DeepWellsEntity deepWells = project.getProjectMark().getDeepWells();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(deepWells.getIrrigateArea());
                            break;
                        case 5:
                            cell.setCellValue(deepWells.getDeepPump());
                            break;
                        case 6:
                            cell.setCellValue(deepWells.getDiameter());
                            break;
                        case 7:
                            cell.setCellValue(deepWells.getDepth());
                            break;
                        case 8:
                            cell.setCellValue(deepWells.getMaterial());
                            break;
                        case 9:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 13:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 14:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 15:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 17:
                            cell.setCellValue(project.getManager());
                            break;
                        case 18:
                            cell.setCellValue(project.getManager());
                            break;
                        case 19:
                            cell.setCellValue(project.getManager());
                            break;
                        case 20:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "shenshuijinhuizong");
    }

    /**
     * 导出管滴灌汇总
     */
    public void exportDripIrrigationPipeSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(dripIrrigationPipeSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            DripIrrigationPipeEntity dripIrrigationPipe = project.getProjectMark().getDripIrrigationPipe();
            List<PipeEntity> pipes = dripIrrigationPipe.getPipes();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 29; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(dripIrrigationPipe.getIntakeWay());
                            break;
                        case 4:
                            cell.setCellValue(dripIrrigationPipe.getWaterResource());
                            break;
                        case 5:
                            cell.setCellValue(dripIrrigationPipe.getLength());
                            break;
                        case 6:
                            cell.setCellValue(dripIrrigationPipe.getIrrigateArea());
                            break;
                        case 7:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 8:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 9:
                            HSSFCell cell3_10 = sheet1.getRow(2).getCell(9);
                            cell3_10.setCellValue(pipes.get(0).getModel());
                            cell.setCellValue(pipes.get(0).getLength());
                            break;
                        case 10:
                            cell.setCellValue(pipes.get(0).getDiameter());
                            break;
                        case 11:
                            cell.setCellValue(pipes.get(0).getMaterial());
                            break;
                        case 12:
                            HSSFCell cell3_13 = sheet1.getRow(2).getCell(12);
                            cell3_13.setCellValue(pipes.get(1).getModel());
                            cell.setCellValue(pipes.get(1).getLength());
                            break;
                        case 13:
                            cell.setCellValue(pipes.get(1).getDiameter());
                            break;
                        case 14:
                            cell.setCellValue(pipes.get(1).getMaterial());
                            break;
                        case 15:
                            HSSFCell cell3_16 = sheet1.getRow(2).getCell(15);
                            cell3_16.setCellValue(pipes.get(2).getModel());
                            cell.setCellValue(pipes.get(2).getLength());
                            break;
                        case 16:
                            cell.setCellValue(pipes.get(2).getDiameter());
                            break;
                        case 17:
                            cell.setCellValue(pipes.get(2).getMaterial());
                            break;
                        case 18:
                            HSSFCell cell3_19 = sheet1.getRow(2).getCell(18);
                            cell3_19.setCellValue(pipes.get(3).getModel());
                            cell.setCellValue(pipes.get(3).getLength());
                            break;
                        case 19:
                            cell.setCellValue(pipes.get(3).getDiameter());
                            break;
                        case 20:
                            cell.setCellValue(pipes.get(3).getMaterial());
                            break;
                        case 21:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 22:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 25:
                            cell.setCellValue(project.getManager());
                            break;
                        case 26:
                            cell.setCellValue(project.getManager());
                            break;
                        case 27:
                            cell.setCellValue(project.getManager());
                            break;
                        case 28:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "guandiguanhuizong");
    }

    /**
     * 导出大口井汇总
     */
    public void exportGreatWellsSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(greatWellsSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            GreatWellsEntity greatWells = project.getProjectMark().getGreatWells();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(greatWells.getIrrigateArea());
                            break;
                        case 5:
                            cell.setCellValue(greatWells.getWaterCapacity());
                            break;
                        case 6:
                            cell.setCellValue(greatWells.getSize());
                            break;
                        case 7:
                            cell.setCellValue(greatWells.getDepth());
                            break;
                        case 8:
                            cell.setCellValue(greatWells.getModelAndMaterial());
                            break;
                        case 9:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 13:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 14:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 15:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 17:
                            cell.setCellValue(project.getManager());
                            break;
                        case 18:
                            cell.setCellValue(project.getManager());
                            break;
                        case 19:
                            cell.setCellValue(project.getManager());
                            break;
                        case 20:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "dakoujinhuizong");
    }

    /**
     * 导出水电站
     */
    public void exportHydropowerSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(hydropowerSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            HydropowerEntity hydropower = project.getProjectMark().getHydropower();
            List<TransformerEntity> transformers = hydropower.getTransformers();
            List<TurbineEntity> turbines = hydropower.getTurbines();
            List<GeneratorEntity> generators = hydropower.getGenerators();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 36; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(hydropower.getRiverLocation());
                            break;
                        case 5:
                            cell.setCellValue(hydropower.getAffiliation());
                            break;
                        case 6:
                            cell.setCellValue(hydropower.getMachineArea());
                            break;
                        case 7:
                            cell.setCellValue(hydropower.getSumElectricCapacity());
                            break;
                        case 8:
                            cell.setCellValue(hydropower.getAverageCapacity());
                            break;
                        case 9:
                            cell.setCellValue(transformers.get(0).getModel());
                            break;
                        case 10:
                            cell.setCellValue(transformers.get(0).getCapacity());
                            break;
                        case 11:
                            cell.setCellValue(turbines.get(0).getModel());
                            break;
                        case 12:
                            cell.setCellValue(turbines.get(0).getCount());
                            break;
                        case 13:
                            cell.setCellValue(turbines.get(0).getTurnsOrFlow());
                            break;
                        case 14:
                            cell.setCellValue(turbines.get(0).getFactoryDate());
                            break;
                        case 15:
                            cell.setCellValue(generators.get(0).getModel());
                            break;
                        case 16:
                            cell.setCellValue(generators.get(0).getPower());
                            break;
                        case 17:
                            cell.setCellValue(generators.get(0).getFactoryDate());
                            break;
                        case 18:
                            cell.setCellValue(hydropower.getIrrigateArea());
                            break;
                        case 19:
                            cell.setCellValue(hydropower.getPaddyFieldArea());
                            break;
                        case 20:
                            cell.setCellValue(hydropower.getDrainageArea());
                            break;
                        case 21:
                            cell.setCellValue(hydropower.getIrrigateFee());
                            break;
                        case 22:
                            cell.setCellValue(hydropower.getDrainageFee());
                            break;
                        case 23:
                            cell.setCellValue(hydropower.getAnnualFee());
                            break;
                        case 24:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 25:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 26:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 27:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 28:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 29:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 32:
                            cell.setCellValue(project.getManager());
                            break;
                        case 33:
                            cell.setCellValue(project.getManager());
                            break;
                        case 34:
                            cell.setCellValue(project.getManager());
                            break;
                        case 35:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "shuidianzhanhuizong");
    }

    /**
     * 导出水塘汇总
     */
    public void exportPondSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pondSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            PondEntity pond = project.getProjectMark().getPond();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 18; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(pond.getMainFunction());
                            break;
                        case 5:
                            cell.setCellValue(pond.getLastDredgingTime());
                            break;
                        case 6:
                            cell.setCellValue(pond.getWaterArea());
                            break;
                        case 7:
                            cell.setCellValue(pond.getWaterCapacity());
                            break;
                        case 8:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 9:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 10:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 11:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 12:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 13:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 14:
                            cell.setCellValue(project.getManager());
                            break;
                        case 15:
                            cell.setCellValue(project.getManager());
                            break;
                        case 16:
                            cell.setCellValue(project.getManager());
                            break;
                        case 17:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "shuitanghuizong");
    }

    /**
     * 导出泵站汇总
     */
    public void exportPumpStationSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pumpStationSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            PumpStationEntity pumpStation = project.getProjectMark().getPumpStation();
            List<TransformerEntity> transformers = pumpStation.getTransformers();
            List<PumpEntity> pumps = pumpStation.getPumps();
            List<ElectricMotorEntity> electricMotors = pumpStation.getElectricMotors();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 37; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(pumpStation.getRiverLocation());
                            break;
                        case 5:
                            cell.setCellValue(pumpStation.getNature());
                            break;
                        case 6:
                            cell.setCellValue(pumpStation.getMachineArea());
                            break;
                        case 7:
                            cell.setCellValue(pumpStation.getTotalInstalledCapacity());
                            break;
                        case 8:
                            cell.setCellValue(pumpStation.getRiverElevation());
                            break;
                        case 9:
                            cell.setCellValue(pumpStation.getPoolHeight());
                            break;
                        case 10:
                            cell.setCellValue(transformers.get(0).getModel());
                            break;
                        case 11:
                            cell.setCellValue(transformers.get(0).getCapacity());
                            break;
                        case 12:
                            cell.setCellValue(pumps.get(0).getModel());
                            break;
                        case 13:
                            cell.setCellValue(pumps.get(0).getCount());
                            break;
                        case 14:
                            cell.setCellValue(pumps.get(0).getLiftOrFlow());
                            break;
                        case 15:
                            cell.setCellValue(pumps.get(0).getFactoryDate());
                            break;
                        case 16:
                            cell.setCellValue(electricMotors.get(0).getModel());
                            break;
                        case 17:
                            cell.setCellValue(electricMotors.get(0).getPower());
                            break;
                        case 18:
                            cell.setCellValue(electricMotors.get(0).getFactoryDate());
                            break;
                        case 19:
                            cell.setCellValue(pumpStation.getIrrigateArea());
                            break;
                        case 20:
                            cell.setCellValue(pumpStation.getPaddyFieldArea());
                            break;
                        case 21:
                            cell.setCellValue(pumpStation.getDrainageArea());
                            break;
                        case 22:
                            cell.setCellValue(pumpStation.getIrrigateFee());
                            break;
                        case 23:
                            cell.setCellValue(pumpStation.getDrainageFee());
                            break;
                        case 24:
                            cell.setCellValue(pumpStation.getAnnualFee());
                            break;
                        case 25:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 26:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 27:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 28:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 29:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 32:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 33:
                            cell.setCellValue(project.getManager());
                            break;
                        case 34:
                            cell.setCellValue(project.getManager());
                            break;
                        case 35:
                            cell.setCellValue(project.getManager());
                            break;
                        case 36:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "bengzhanhuizong");
    }

    /**
     * 导出水闸汇总
     */
    public void exportSluiceSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(sluiceSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            SluiceEntity sluice = project.getProjectMark().getSluice();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 30; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(sluice.getWatercourseLocation());
                            break;
                        case 5:
                            cell.setCellValue(sluice.getModel());
                            break;
                        case 6:
                            cell.setCellValue(sluice.getHoleCount());
                            break;
                        case 7:
                            cell.setCellValue(sluice.getHoleWidth());
                            break;
                        case 8:
                            cell.setCellValue(sluice.getHoleHeight());
                            break;
                        case 9:
                            cell.setCellValue(sluice.getDoor());
                            break;
                        case 10:
                            cell.setCellValue(sluice.getDoor());
                            break;
                        case 11:
                            cell.setCellValue(sluice.getDoorWidth());
                            break;
                        case 12:
                            cell.setCellValue(sluice.getDoorHeight());
                            break;
                        case 13:
                            cell.setCellValue(sluice.getHoistTonnage());
                            break;
                        case 14:
                            cell.setCellValue(sluice.getHoistModel());
                            break;
                        case 15:
                            cell.setCellValue(sluice.getBuildingSituation());
                            break;
                        case 16:
                            cell.setCellValue(sluice.getDoorSituation());
                            break;
                        case 17:
                            cell.setCellValue(sluice.getHoistSituation());
                            break;
                        case 18:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 19:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 20:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 21:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 22:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 25:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 26:
                            cell.setCellValue(project.getManager());
                            break;
                        case 27:
                            cell.setCellValue(project.getManager());
                            break;
                        case 28:
                            cell.setCellValue(project.getManager());
                            break;
                        case 29:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "shuizhahuizong");
    }

    /**
     * 导出河道汇总
     */
    public void exportWatercourseSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(watercourseSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            WatercourseEntity watercourse = project.getProjectMark().getWatercourse();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 26; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(watercourse.getNature());
                            break;
                        case 5:
                            cell.setCellValue(watercourse.getLength());
                            break;
                        case 6:
                            cell.setCellValue(watercourse.getLastDredgingTime());
                            break;
                        case 7:
                            cell.setCellValue(watercourse.getEstuaryWidth());
                            break;
                        case 8:
                            cell.setCellValue(watercourse.getHediWidth());
                            break;
                        case 9:
                            cell.setCellValue(watercourse.getLeftWidth());
                            break;
                        case 10:
                            cell.setCellValue(watercourse.getRightWidth());
                            break;
                        case 11:
                            cell.setCellValue(watercourse.getEstuaryHeight());
                            break;
                        case 12:
                            cell.setCellValue(watercourse.getHediHeight());
                            break;
                        case 13:
                            cell.setCellValue(watercourse.getFlowVillages());
                            break;
                        case 14:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 15:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 16:
                            cell.setCellValue(watercourse.getEndpointLocation().getLongitude());
                            break;
                        case 17:
                            cell.setCellValue(watercourse.getEndpointLocation().getLatitude());
                            break;
                        case 18:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 21:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 22:
                            cell.setCellValue(project.getManager());
                            break;
                        case 23:
                            cell.setCellValue(project.getManager());
                            break;
                        case 24:
                            cell.setCellValue(project.getManager());
                            break;
                        case 25:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "hedaohuizong");
    }

    /**
     * 导出水厂汇总
     */
    public void exportWaterWorksSummary(HttpServletResponse response, List<WaterConservationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(waterWorksSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterConservationEntity project : projects) {
            WaterWorksEntity waterWorks = project.getProjectMark().getWaterWorks();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 24; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(project.getTown().getName());
                            break;
                        case 2:
                            cell.setCellValue(project.getVillage().getName() + " " + project.getGroup().getName());
                            break;
                        case 3:
                            cell.setCellValue(project.getName());
                            break;
                        case 4:
                            cell.setCellValue(waterWorks.getProvideAmount());
                            break;
                        case 5:
                            cell.setCellValue(waterWorks.getWaterModel());
                            break;
                        case 6:
                            cell.setCellValue(waterWorks.getHaveCleaner());
                            break;
                        case 7:
                            cell.setCellValue(waterWorks.getIsRegularCheck());
                            break;
                        case 8:
                            cell.setCellValue(waterWorks.getDayProvideAmount());
                            break;
                        case 9:
                            cell.setCellValue(waterWorks.getProvideVillageCount());
                            break;
                        case 10:
                            cell.setCellValue(waterWorks.getProvidePopulation());
                            break;
                        case 11:
                            cell.setCellValue(waterWorks.getHaveProtectArea());
                            break;
                        case 12:
                            cell.setCellValue(project.getConstructUnit());
                            break;
                        case 13:
                            cell.setCellValue(project.getConstructTime());
                            break;
                        case 14:
                            cell.setCellValue(project.getLocation().getLongitude());
                            break;
                        case 15:
                            cell.setCellValue(project.getLocation().getLatitude());
                            break;
                        case 16:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 17:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 18:
                            cell.setCellValue(project.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(project.getManageModel());
                            break;
                        case 20:
                            cell.setCellValue(project.getManager());
                            break;
                        case 21:
                            cell.setCellValue(project.getManager());
                            break;
                        case 22:
                            cell.setCellValue(project.getManager());
                            break;
                        case 23:
                            cell.setCellValue(project.getRemark());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        writeOut(response, hssfWorkbook, "shuichanghuizong");
    }
}
