package com.saltyfish.framework.service;

import com.saltyfish.common.bean.device.*;
import com.saltyfish.common.bean.division.Canal;
import com.saltyfish.common.bean.division.Pipe;
import com.saltyfish.domain.entity.conservation.*;
import com.saltyfish.domain.entity.unit.GroupEntity;
import com.saltyfish.domain.entity.unit.TownEntity;
import com.saltyfish.domain.entity.unit.VillageEntity;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ProjectService projectService;

    public void importData(Integer userId, File f, String category, Long timeStamp) throws IOException {
        InputStream inputStream = new FileInputStream(f);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 6; i < sheet1.getLastRowNum() + 1; i++) {
            HSSFRow row = sheet1.getRow(i);
            String townName = row.getCell(1).getStringCellValue();
            String villageName = row.getCell(2).getStringCellValue();
            TownEntity town = townRepository.findByName(townName);
            VillageEntity village = villageRepository.findByName(villageName);
            GroupEntity group = groupRepository.findByName("");
            if (town == null || !authService.checkUserTownAccess(userId, town.getId())) {
                return;
            }
            switch (category) {
                case "渡槽":
                    AqueductEntity aqueduct = new AqueductEntity();
                    projectService.setCommonProperty(aqueduct, userId, category, row.getCell(20).getStringCellValue(),
                            row.getCell(3).getStringCellValue(), "", row.getCell(16).getStringCellValue(), town.getId(),
                            village.getId(), group.getId(), "", row.getCell(10).getStringCellValue(), row.getCell(9).getStringCellValue(),
                            row.getCell(13).getStringCellValue() + "#" + row.getCell(14).getStringCellValue() + "#" + row.getCell(15).getStringCellValue(),
                            row.getCell(17).getStringCellValue() + "#" + row.getCell(18).getStringCellValue() + "#" + row.getCell(19).getStringCellValue(),
                            row.getCell(11).getStringCellValue(), row.getCell(12).getStringCellValue(), timeStamp);
                    projectService.saveAqueduct(aqueduct, row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue(),
                            row.getCell(7).getStringCellValue(), row.getCell(8).getStringCellValue(), row.getCell(6).getStringCellValue(), "");
                    break;
                case "渠道":
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 导出水厂
     */
    public void exportWaterWorks(WaterWorksEntity waterWorksEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(waterWorksModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 8; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + waterWorksEntity.getCode()
                            + "                街道/乡/镇: " + waterWorksEntity.getTownEntity().getName()
                            + "  村: " + waterWorksEntity.getVillageEntity().getName()
                            + "  组: " + waterWorksEntity.getGroupEntity().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(waterWorksEntity.getName());
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
                    cell6_2.setCellValue(waterWorksEntity.getConstructUnit());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(waterWorksEntity.getConstructTime());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue("经度:" + waterWorksEntity.getLongitude() + "\n"
                            + "纬度：" + waterWorksEntity.getLatitude());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(waterWorksEntity.getPropertyOwner());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(waterWorksEntity.getManageModel());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue(waterWorksEntity.getManager());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(waterWorksEntity.getRemark());
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
    public void exportWatercourse(WatercourseEntity watercourseEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(watercourseModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 8; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + watercourseEntity.getCode()
                            + "                街道/乡/镇: " + watercourseEntity.getTownEntity().getName()
                            + "  村: " + watercourseEntity.getVillageEntity().getName()
                            + "  组: " + watercourseEntity.getGroupEntity().getName());
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
                    cell6_4.setCellValue("经度:" + watercourseEntity.getLongitude() + "\n"
                            + "纬度：" + watercourseEntity.getLatitude());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue("经度:" + watercourseEntity.getEndpointLongitude() + "\n"
                            + "纬度：" + watercourseEntity.getEndpointLatitude());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(watercourseEntity.getPropertyOwner());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(watercourseEntity.getManageModel());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue(watercourseEntity.getManager());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(watercourseEntity.getRemark());
                    break;
            }
        }
        writeOut(httpServletResponse, hssfWorkbook, "hedao");
    }


    /**
     * 导出水闸
     */
    public void exportSluice(SluiceEntity sluiceEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(sluiceModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 9; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + sluiceEntity.getCode()
                            + "                街道/乡/镇: " + sluiceEntity.getTownEntity().getName()
                            + "  村: " + sluiceEntity.getVillageEntity().getName()
                            + "  组: " + sluiceEntity.getGroupEntity().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(sluiceEntity.getName());
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
                    cell7_2.setCellValue(sluiceEntity.getConstructUnit());
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(sluiceEntity.getConstructTime());
                    HSSFCell cell7_6 = row.getCell(5);
                    cell7_6.setCellValue("经度:" + sluiceEntity.getLongitude() + "\n"
                            + "纬度：" + sluiceEntity.getLatitude());
                    break;
                case 7:
                    HSSFCell cell8_2 = row.getCell(1);
                    cell8_2.setCellValue(sluiceEntity.getPropertyOwner());
                    HSSFCell cell8_4 = row.getCell(3);
                    cell8_4.setCellValue(sluiceEntity.getManageModel());
                    HSSFCell cell8_6 = row.getCell(5);
                    cell8_6.setCellValue(sluiceEntity.getManager());
                    break;
                case 8:
                    HSSFCell cell9_2 = row.getCell(1);
                    cell9_2.setCellValue(sluiceEntity.getRemark());
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
    public void exportPond(PondEntity pondEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pondModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int rowNum = 0; rowNum < 6; rowNum++) {
            HSSFRow row = sheet1.getRow(rowNum);
            switch (rowNum) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + pondEntity.getCode()
                            + "                街道/乡/镇: " + pondEntity.getTownEntity().getName()
                            + "  村: " + pondEntity.getVillageEntity().getName()
                            + "  组: " + pondEntity.getGroupEntity().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(pondEntity.getName());
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
                    cell4_6.setCellValue("经度:" + pondEntity.getLongitude() + "\n"
                            + "纬度：" + pondEntity.getLatitude());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(pondEntity.getPropertyOwner());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(pondEntity.getManageModel());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(pondEntity.getManager());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(pondEntity.getRemark());
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
    public void exportPumpStation(PumpStationEntity pumpStationEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pumpStationModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + pumpStationEntity.getCode()
                + "                街道/乡/镇: " + pumpStationEntity.getTownEntity().getName()
                + "  村: " + pumpStationEntity.getVillageEntity().getName()
                + "  组: " + pumpStationEntity.getGroupEntity().getName());
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
                    cell4_4.setCellValue("经度:" + pumpStationEntity.getLongitude() + "\n"
                            + "纬度：" + pumpStationEntity.getLatitude());
                    HSSFCell cell4_9 = row.getCell(8);
                    cell4_9.setCellValue(pumpStationEntity.getPaddyFieldArea());
                    break;
                case 4:
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(pumpStationEntity.getConstructTime());
                    HSSFCell cell5_9 = row.getCell(8);
                    cell5_9.setCellValue(pumpStationEntity.getDrainageArea());
                    break;
                case 5:
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(pumpStationEntity.getConstructUnit());
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
                    cell9_9.setCellValue(pumpStationEntity.getPropertyOwner());
                    break;
                case 9:
                    HSSFCell cell10_4 = row.getCell(3);
                    cell10_4.setCellValue(pumpStationEntity.getRiverElevation());
                    HSSFCell cell10_9 = row.getCell(8);
                    cell10_9.setCellValue(pumpStationEntity.getManageModel());
                    break;
                case 10:
                    HSSFCell cell11_4 = row.getCell(3);
                    cell11_4.setCellValue(pumpStationEntity.getPoolHeight());
                    HSSFCell cell11_9 = row.getCell(8);
                    cell11_9.setCellValue(pumpStationEntity.getManager());
                    break;
                case 11:
                    HSSFCell cell12_8 = row.getCell(7);
                    cell12_8.setCellValue("运行情况:" + pumpStationEntity.getSituation() +
                            "\n存在的问题：" + pumpStationEntity.getProblem());
                default:
                    break;
            }
        }
        List<Transformer> transformers = pumpStationEntity.getTransformers();
        for (Transformer transformer : transformers) {
            int rowNum = 11;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(transformer.getCapacity());
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(transformer.getModel());
            }
        }
        List<Pump> pumps = pumpStationEntity.getPumps();
        for (Pump pump : pumps) {
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
        List<ElectricMotor> electricMotors = pumpStationEntity.getElectricMotors();
        for (ElectricMotor electricMotor : electricMotors) {
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
    public void exportHydropower(HydropowerEntity hydropowerEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(hydropowerModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + hydropowerEntity.getCode()
                + "                街道/乡/镇: " + hydropowerEntity.getTownEntity().getName()
                + "  村: " + hydropowerEntity.getVillageEntity().getName()
                + "  组: " + hydropowerEntity.getGroupEntity().getName());
        for (int i = 2; i < 12; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 2:
                    HSSFCell cell3_4 = row.getCell(3);
                    cell3_4.setCellValue(hydropowerEntity.getName());
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
                    cell5_4.setCellValue("经度:" + hydropowerEntity.getLongitude() + "\n"
                            + "纬度：" + hydropowerEntity.getLatitude());
                    HSSFCell cell5_9 = row.getCell(8);
                    cell5_9.setCellValue(hydropowerEntity.getDrainageArea());
                    break;
                case 5:
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(hydropowerEntity.getConstructTime());
                    HSSFCell cell6_9 = row.getCell(8);
                    cell6_9.setCellValue(hydropowerEntity.getIrrigateFee());
                    break;
                case 6:
                    HSSFCell cell7_4 = row.getCell(3);
                    cell7_4.setCellValue(hydropowerEntity.getConstructUnit());
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
                    cell9_9.setCellValue(hydropowerEntity.getPropertyOwner());
                    break;
                case 9:
                    HSSFCell cell10_4 = row.getCell(3);
                    cell10_4.setCellValue(hydropowerEntity.getSumElectricCapacity());
                    HSSFCell cell10_9 = row.getCell(8);
                    cell10_9.setCellValue(hydropowerEntity.getManageModel());
                    break;
                case 10:
                    HSSFCell cell11_4 = row.getCell(3);
                    cell11_4.setCellValue(hydropowerEntity.getAverageCapacity());
                    HSSFCell cell11_9 = row.getCell(8);
                    cell11_9.setCellValue(hydropowerEntity.getManager());
                    break;
                case 11:
                    HSSFCell cell12_8 = row.getCell(7);
                    cell12_8.setCellValue("运行情况:" + hydropowerEntity.getSituation() +
                            "\n存在的问题：" + hydropowerEntity.getProblem());
                default:
                    break;
            }
        }
        List<Transformer> transformers = hydropowerEntity.getTransformers();
        for (Transformer transformer : transformers) {
            int rowNum = 11;
            for (int column = 3; column < 6; column++) {
                HSSFCell cell1 = sheet1.getRow(rowNum).getCell(column);
                cell1.setCellValue(transformer.getModel());
                HSSFCell cell2 = sheet1.getRow(rowNum + 1).getCell(column);
                cell2.setCellValue(transformer.getCapacity());
            }
        }
        List<Turbine> turbines = hydropowerEntity.getTurbines();
        for (Turbine turbineEntity : turbines) {
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
        List<Generator> generators = hydropowerEntity.getGenerators();
        for (Generator generator : generators) {
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
    public void exportGreateWells(GreatWellsEntity greatWellsEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(greatWellsModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + greatWellsEntity.getCode()
                + "                街道/乡/镇: " + greatWellsEntity.getTownEntity().getName()
                + "  村: " + greatWellsEntity.getVillageEntity().getName()
                + "  组: " + greatWellsEntity.getGroupEntity().getName());
        for (int i = 2; i < 6; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(greatWellsEntity.getName());
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
                    cell5_2.setCellValue(greatWellsEntity.getConstructUnit());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(greatWellsEntity.getConstructTime());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue("经度:" + greatWellsEntity.getLongitude() + "\n"
                            + "纬度：" + greatWellsEntity.getLatitude());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(greatWellsEntity.getPropertyOwner());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(greatWellsEntity.getManageModel());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue(greatWellsEntity.getManager());
                    break;
                default:
                    break;
            }
        }
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(greatWellsEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "dakoujin");
    }

    /**
     * 导出管滴灌
     */
    public void exportDripIrrigationPipe(DripIrrigationPipeEntity dripIrrigationPipeEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(dripIrrigationPipeModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 5; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + dripIrrigationPipeEntity.getCode()
                            + "                街道/乡/镇: " + dripIrrigationPipeEntity.getTownEntity().getName()
                            + "  村: " + dripIrrigationPipeEntity.getVillageEntity().getName()
                            + "  组: " + dripIrrigationPipeEntity.getGroupEntity().getName());
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
                    cell4_4.setCellValue(dripIrrigationPipeEntity.getConstructUnit());
                    HSSFCell cell4_6 = row.getCell(5);
                    cell4_6.setCellValue(dripIrrigationPipeEntity.getConstructTime());
                    break;
                case 4:
                    HSSFCell cell5_2 = row.getCell(1);
                    cell5_2.setCellValue(dripIrrigationPipeEntity.getPropertyOwner());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(dripIrrigationPipeEntity.getManageModel());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue(dripIrrigationPipeEntity.getManager());
                    break;
                default:
                    break;
            }
        }
        List<Pipe> pipes = dripIrrigationPipeEntity.getPipes();
        int rowNum = 6;
        for (Pipe pipe : pipes) {
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
        cell16_2.setCellValue(dripIrrigationPipeEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "guandiguan");
    }

    /**
     * 导出深水井
     */
    public void exportDeepWells(DeepWellsEntity deepWellsEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(deepWellsModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 7; i++) {
            HSSFRow row = sheet1.getRow(i);
            switch (i) {
                case 0:
                    HSSFCell cell1_1 = row.getCell(0);
                    cell1_1.setCellValue("编码：" + deepWellsEntity.getCode()
                            + "                街道/乡/镇: " + deepWellsEntity.getTownEntity().getName()
                            + "  村: " + deepWellsEntity.getVillageEntity().getName()
                            + "  组: " + deepWellsEntity.getGroupEntity().getName());
                    break;
                case 2:
                    HSSFCell cell3_2 = row.getCell(1);
                    cell3_2.setCellValue(deepWellsEntity.getName());
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
                    cell5_2.setCellValue(deepWellsEntity.getConstructUnit());
                    HSSFCell cell5_4 = row.getCell(3);
                    cell5_4.setCellValue(deepWellsEntity.getConstructTime());
                    HSSFCell cell5_6 = row.getCell(5);
                    cell5_6.setCellValue("经度:" + deepWellsEntity.getLongitude() + "\n"
                            + "纬度：" + deepWellsEntity.getLatitude());
                    break;
                case 5:
                    HSSFCell cell6_2 = row.getCell(1);
                    cell6_2.setCellValue(deepWellsEntity.getPropertyOwner());
                    HSSFCell cell6_4 = row.getCell(3);
                    cell6_4.setCellValue(deepWellsEntity.getManageModel());
                    HSSFCell cell6_6 = row.getCell(5);
                    cell6_6.setCellValue(deepWellsEntity.getManager());
                    break;
                case 6:
                    HSSFCell cell7_2 = row.getCell(1);
                    cell7_2.setCellValue(deepWellsEntity.getRemark());
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
    public void exportDam(DamEntity damEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(damModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (int i = 0; i < 21; i++) {
            HSSFRow row = sheet1.getRow(i);
            HSSFCell cell = row.getCell(1);
            switch (i) {
                case 0:
                    cell = row.getCell(0);
                    cell.setCellValue("编码：" + damEntity.getCode()
                            + "                街道/乡/镇: " + damEntity.getTownEntity().getName()
                            + "  村: " + damEntity.getVillageEntity().getName()
                            + "  组: " + damEntity.getGroupEntity().getName());
                    break;
                case 2:
                    cell.setCellValue(damEntity.getName());
                    break;
                case 3:
                    cell.setCellValue("经度:" + damEntity.getLongitude() + "\n"
                            + "纬度：" + damEntity.getLatitude());
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
                    cell.setCellValue(damEntity.getSituation());
                    break;
                case 12:
                    cell.setCellValue(damEntity.getPropertyOwner());
                    break;
                case 13:
                    cell.setCellValue(damEntity.getManager());
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
    public void exportCulvert(CulvertEntity culvertEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(culvertModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + culvertEntity.getCode()
                + "                街道/乡/镇: " + culvertEntity.getTownEntity().getName()
                + "  村: " + culvertEntity.getVillageEntity().getName()
                + "  组: " + culvertEntity.getGroupEntity().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(culvertEntity.getName());
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
        cell6_2.setCellValue(culvertEntity.getConstructUnit());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(culvertEntity.getConstructTime());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue("经度:" + culvertEntity.getLongitude() + "\n"
                + "纬度：" + culvertEntity.getLatitude());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(culvertEntity.getPropertyOwner());
        HSSFCell cell7_4 = row7.getCell(3);
        cell7_4.setCellValue(culvertEntity.getManageModel());
        HSSFCell cell7_6 = row7.getCell(5);
        cell7_6.setCellValue(culvertEntity.getManager());
        HSSFRow row8 = sheet1.getRow(7);
        HSSFCell cell8_2 = row8.getCell(1);
        cell8_2.setCellValue(culvertEntity.getSituation());
        HSSFCell cell8_4 = row8.getCell(3);
        cell8_4.setCellValue(culvertEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "handong");
    }

    /**
     * 导出渠道
     */
    public void exportChannel(ChannelEntity channelEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(channelModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + channelEntity.getCode()
                + "                街道/乡/镇: " + channelEntity.getTownEntity().getName()
                + "  村: " + channelEntity.getVillageEntity().getName()
                + "  组: " + channelEntity.getGroupEntity().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(channelEntity.getHeadOrPumpStation());
        HSSFCell cell3_4 = row3.getCell(3);
        cell3_4.setCellValue(channelEntity.getBuildingMatchRate());
        HSSFCell cell3_6 = row3.getCell(5);
        cell3_6.setCellValue(channelEntity.getConstructUnit());
        HSSFRow row4 = sheet1.getRow(3);
        HSSFCell cell4_2 = row4.getCell(1);
        cell4_2.setCellValue(channelEntity.getLength());
        HSSFCell cell4_4 = row4.getCell(3);
        cell4_4.setCellValue(channelEntity.getGoodConditionRate());
        HSSFCell cell4_6 = row4.getCell(5);
        cell4_6.setCellValue(channelEntity.getConstructTime());
        HSSFRow row5 = sheet1.getRow(4);
        HSSFCell cell5_2 = row5.getCell(1);
        cell5_2.setCellValue(channelEntity.getPropertyOwner());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(channelEntity.getManageModel());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue(channelEntity.getManager());
        List<Canal> canals = channelEntity.getCanals();
        int rowNum = 6;
        for (Canal canal : canals) {
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
        cell20_2.setCellValue(channelEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "qudao");
    }

    /**
     * 导出桥梁
     */
    public void exportBridge(BridgeEntity bridgeEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(bridgeModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + bridgeEntity.getCode()
                + "                街道/乡/镇: " + bridgeEntity.getTownEntity().getName()
                + "  村: " + bridgeEntity.getVillageEntity().getName()
                + "  组: " + bridgeEntity.getGroupEntity().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(bridgeEntity.getName());
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
        cell5_6.setCellValue(bridgeEntity.getSituation());
        HSSFRow row6 = sheet1.getRow(5);
        HSSFCell cell6_2 = row6.getCell(1);
        cell6_2.setCellValue(bridgeEntity.getConstructUnit());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(bridgeEntity.getConstructTime());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue("经度:" + bridgeEntity.getLongitude() + "\n"
                + "纬度：" + bridgeEntity.getLatitude());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(bridgeEntity.getPropertyOwner());
        HSSFCell cell7_4 = row7.getCell(3);
        cell7_4.setCellValue(bridgeEntity.getManageModel());
        HSSFCell cell7_6 = row7.getCell(5);
        cell7_6.setCellValue(bridgeEntity.getManager());
        HSSFRow row8 = sheet1.getRow(7);
        HSSFCell cell8_2 = row8.getCell(1);
        cell8_2.setCellValue(bridgeEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "qiaoliang");
    }

    /**
     * 导出渡槽
     */
    public void exportAqueduct(AqueductEntity aqueductEntity, HttpServletResponse httpServletResponse) throws IOException {
        InputStream inputStream = new FileInputStream(new File(aqueductModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        HSSFRow row1 = sheet1.getRow(0);
        HSSFCell cell1_1 = row1.getCell(0);
        cell1_1.setCellValue("编码：" + aqueductEntity.getCode()
                + "                街道/乡/镇: " + aqueductEntity.getTownEntity().getName()
                + "  村: " + aqueductEntity.getVillageEntity().getName()
                + "  组: " + aqueductEntity.getGroupEntity().getName());
        HSSFRow row3 = sheet1.getRow(2);
        HSSFCell cell3_2 = row3.getCell(1);
        cell3_2.setCellValue(aqueductEntity.getName());
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
        cell5_2.setCellValue(aqueductEntity.getConstructUnit());
        HSSFCell cell5_4 = row5.getCell(3);
        cell5_4.setCellValue(aqueductEntity.getConstructTime());
        HSSFCell cell5_6 = row5.getCell(5);
        cell5_6.setCellValue("经度:" + aqueductEntity.getLongitude() + "\n"
                + "纬度：" + aqueductEntity.getLatitude());
        HSSFRow row6 = sheet1.getRow(5);
        HSSFCell cell6_2 = row6.getCell(1);
        cell6_2.setCellValue(aqueductEntity.getPropertyOwner());
        HSSFCell cell6_4 = row6.getCell(3);
        cell6_4.setCellValue(aqueductEntity.getManageModel());
        HSSFCell cell6_6 = row6.getCell(5);
        cell6_6.setCellValue(aqueductEntity.getManager());
        HSSFRow row7 = sheet1.getRow(6);
        HSSFCell cell7_2 = row7.getCell(1);
        cell7_2.setCellValue(aqueductEntity.getRemark());
        writeOut(httpServletResponse, hssfWorkbook, "ducao");
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
    public void exportAqueductSummary(HttpServletResponse response, List<AqueductEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(aqueductSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (AqueductEntity aqueduct : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(aqueduct.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(aqueduct.getVillageEntity().getName() + " " + aqueduct.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(aqueduct.getName());
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
                            cell.setCellValue(aqueduct.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(aqueduct.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(aqueduct.getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(aqueduct.getLatitude());
                            break;
                        case 13:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[0]);
                            cell.setCellValue(aqueduct.getPropertyOwner());
                            break;
                        case 14:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[1]);
                            cell.setCellValue(aqueduct.getPropertyOwner());
                            break;
                        case 15:
//                        cell.setCellValue(project.getPropertyOwner().split("#")[2]);
                            cell.setCellValue(aqueduct.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(aqueduct.getManageModel());
                            break;
                        case 17:
//                        cell.setCellValue(project.getManager().split("#")[0]);
                            cell.setCellValue(aqueduct.getManager());
                            break;
                        case 18:
//                        cell.setCellValue(project.getManager().split("#")[1]);
                            cell.setCellValue(aqueduct.getManager());
                            break;
                        case 19:
//                        cell.setCellValue(project.getManager().split("#")[2]);
                            cell.setCellValue(aqueduct.getManager());
                            break;
                        case 20:
                            cell.setCellValue(aqueduct.getRemark());
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
    public void exportBridgeSummary(HttpServletResponse response, List<BridgeEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(bridgeSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (BridgeEntity bridge : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 25; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(bridge.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(bridge.getVillageEntity().getName() + " " + bridge.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(bridge.getName());
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
                            cell.setCellValue(bridge.getSituation());
                            break;
                        case 13:
                            cell.setCellValue(bridge.getConstructUnit());
                            break;
                        case 14:
                            cell.setCellValue(bridge.getConstructTime());
                            break;
                        case 15:
                            cell.setCellValue(bridge.getLongitude());
                            break;
                        case 16:
                            cell.setCellValue(bridge.getLatitude());
                            break;
                        case 17:
                            cell.setCellValue(bridge.getPropertyOwner());
                            break;
                        case 18:
                            cell.setCellValue(bridge.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(bridge.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(bridge.getManageModel());
                            break;
                        case 21:
                            cell.setCellValue(bridge.getManager());
                            break;
                        case 22:
                            cell.setCellValue(bridge.getManager());
                            break;
                        case 23:
                            cell.setCellValue(bridge.getManager());
                            break;
                        case 24:
                            cell.setCellValue(bridge.getRemark());
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
    public void exportChannelSummary(HttpServletResponse response, List<ChannelEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(channelSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (ChannelEntity channel : projects) {
            List<Canal> canals = channel.getCanals();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 37; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(channel.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(channel.getVillageEntity().getName() + " " + channel.getGroupEntity().getName());
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
                            cell.setCellValue(channel.getConstructUnit());
                            break;
                        case 8:
                            cell.setCellValue(channel.getConstructTime());
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
                            cell.setCellValue(channel.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(channel.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(channel.getPropertyOwner());
                            break;
                        case 32:
                            cell.setCellValue(channel.getManageModel());
                            break;
                        case 33:
                            cell.setCellValue(channel.getManager());
                            break;
                        case 34:
                            cell.setCellValue(channel.getManager());
                            break;
                        case 35:
                            cell.setCellValue(channel.getManager());
                            break;
                        case 36:
                            cell.setCellValue(channel.getRemark());
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
    public void exportCulvertSummary(HttpServletResponse response, List<CulvertEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(culvertSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (CulvertEntity culvert : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 26; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(culvert.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(culvert.getVillageEntity().getName() + " " + culvert.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(culvert.getName());
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
                            cell.setCellValue(culvert.getSituation());
                            break;
                        case 14:
                            cell.setCellValue(culvert.getConstructUnit());
                            break;
                        case 15:
                            cell.setCellValue(culvert.getConstructTime());
                            break;
                        case 16:
                            cell.setCellValue(culvert.getLongitude());
                            break;
                        case 17:
                            cell.setCellValue(culvert.getLatitude());
                            break;
                        case 18:
                            cell.setCellValue(culvert.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(culvert.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(culvert.getPropertyOwner());
                            break;
                        case 21:
                            cell.setCellValue(culvert.getManageModel());
                            break;
                        case 22:
                            cell.setCellValue(culvert.getManager());
                            break;
                        case 23:
                            cell.setCellValue(culvert.getManager());
                            break;
                        case 24:
                            cell.setCellValue(culvert.getManager());
                            break;
                        case 25:
                            cell.setCellValue(culvert.getRemark());
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
    public void exportDamSummary(HttpServletResponse response, List<DamEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(damSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (DamEntity dam : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 29; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(dam.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(dam.getVillageEntity().getName() + " " + dam.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(dam.getName());
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
                            cell.setCellValue(dam.getSituation());
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
                            cell.setCellValue(dam.getLongitude());
                            break;
                        case 20:
                            cell.setCellValue(dam.getLatitude());
                            break;
                        case 21:
                            cell.setCellValue(dam.getPropertyOwner());
                            break;
                        case 22:
                            cell.setCellValue(dam.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(dam.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(dam.getManageModel());
                            break;
                        case 25:
                            cell.setCellValue(dam.getManager());
                            break;
                        case 26:
                            cell.setCellValue(dam.getManager());
                            break;
                        case 27:
                            cell.setCellValue(dam.getManager());
                            break;
                        case 28:
                            cell.setCellValue(dam.getRemark());
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
    public void exportDeepWellsSummary(HttpServletResponse response, List<DeepWellsEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(deepWellsSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (DeepWellsEntity deepWells : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(deepWells.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(deepWells.getVillageEntity().getName() + " " + deepWells.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(deepWells.getName());
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
                            cell.setCellValue(deepWells.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(deepWells.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(deepWells.getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(deepWells.getLatitude());
                            break;
                        case 13:
                            cell.setCellValue(deepWells.getPropertyOwner());
                            break;
                        case 14:
                            cell.setCellValue(deepWells.getPropertyOwner());
                            break;
                        case 15:
                            cell.setCellValue(deepWells.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(deepWells.getManageModel());
                            break;
                        case 17:
                            cell.setCellValue(deepWells.getManager());
                            break;
                        case 18:
                            cell.setCellValue(deepWells.getManager());
                            break;
                        case 19:
                            cell.setCellValue(deepWells.getManager());
                            break;
                        case 20:
                            cell.setCellValue(deepWells.getRemark());
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
    public void exportDripIrrigationPipeSummary(HttpServletResponse response, List<DripIrrigationPipeEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(dripIrrigationPipeSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (DripIrrigationPipeEntity dripIrrigationPipe : projects) {
            List<Pipe> pipes = dripIrrigationPipe.getPipes();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 29; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(dripIrrigationPipe.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(dripIrrigationPipe.getVillageEntity().getName() + " " + dripIrrigationPipe.getGroupEntity().getName());
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
                            cell.setCellValue(dripIrrigationPipe.getConstructUnit());
                            break;
                        case 8:
                            cell.setCellValue(dripIrrigationPipe.getConstructTime());
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
                            cell.setCellValue(dripIrrigationPipe.getPropertyOwner());
                            break;
                        case 22:
                            cell.setCellValue(dripIrrigationPipe.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(dripIrrigationPipe.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(dripIrrigationPipe.getManageModel());
                            break;
                        case 25:
                            cell.setCellValue(dripIrrigationPipe.getManager());
                            break;
                        case 26:
                            cell.setCellValue(dripIrrigationPipe.getManager());
                            break;
                        case 27:
                            cell.setCellValue(dripIrrigationPipe.getManager());
                            break;
                        case 28:
                            cell.setCellValue(dripIrrigationPipe.getRemark());
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
    public void exportGreatWellsSummary(HttpServletResponse response, List<GreatWellsEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(greatWellsSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (GreatWellsEntity greatWells : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 21; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(greatWells.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(greatWells.getVillageEntity().getName() + " " + greatWells.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(greatWells.getName());
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
                            cell.setCellValue(greatWells.getConstructUnit());
                            break;
                        case 10:
                            cell.setCellValue(greatWells.getConstructTime());
                            break;
                        case 11:
                            cell.setCellValue(greatWells.getLongitude());
                            break;
                        case 12:
                            cell.setCellValue(greatWells.getLatitude());
                            break;
                        case 13:
                            cell.setCellValue(greatWells.getPropertyOwner());
                            break;
                        case 14:
                            cell.setCellValue(greatWells.getPropertyOwner());
                            break;
                        case 15:
                            cell.setCellValue(greatWells.getPropertyOwner());
                            break;
                        case 16:
                            cell.setCellValue(greatWells.getManageModel());
                            break;
                        case 17:
                            cell.setCellValue(greatWells.getManager());
                            break;
                        case 18:
                            cell.setCellValue(greatWells.getManager());
                            break;
                        case 19:
                            cell.setCellValue(greatWells.getManager());
                            break;
                        case 20:
                            cell.setCellValue(greatWells.getRemark());
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
    public void exportHydropowerSummary(HttpServletResponse response, List<HydropowerEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(hydropowerSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (HydropowerEntity hydropower : projects) {
            List<Transformer> transformers = hydropower.getTransformers();
            List<Turbine> turbines = hydropower.getTurbines();
            List<Generator> generators = hydropower.getGenerators();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 36; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(hydropower.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(hydropower.getVillageEntity().getName() + " " + hydropower.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(hydropower.getName());
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
                            cell.setCellValue(hydropower.getConstructTime());
                            break;
                        case 25:
                            cell.setCellValue(hydropower.getConstructUnit());
                            break;
                        case 26:
                            cell.setCellValue(hydropower.getLongitude());
                            break;
                        case 27:
                            cell.setCellValue(hydropower.getLatitude());
                            break;
                        case 28:
                            cell.setCellValue(hydropower.getPropertyOwner());
                            break;
                        case 29:
                            cell.setCellValue(hydropower.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(hydropower.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(hydropower.getManageModel());
                            break;
                        case 32:
                            cell.setCellValue(hydropower.getManager());
                            break;
                        case 33:
                            cell.setCellValue(hydropower.getManager());
                            break;
                        case 34:
                            cell.setCellValue(hydropower.getManager());
                            break;
                        case 35:
                            cell.setCellValue(hydropower.getRemark());
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
    public void exportPondSummary(HttpServletResponse response, List<PondEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pondSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (PondEntity pond : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 18; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(pond.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(pond.getVillageEntity().getName() + " " + pond.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(pond.getName());
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
                            cell.setCellValue(pond.getLongitude());
                            break;
                        case 9:
                            cell.setCellValue(pond.getLatitude());
                            break;
                        case 10:
                            cell.setCellValue(pond.getPropertyOwner());
                            break;
                        case 11:
                            cell.setCellValue(pond.getPropertyOwner());
                            break;
                        case 12:
                            cell.setCellValue(pond.getPropertyOwner());
                            break;
                        case 13:
                            cell.setCellValue(pond.getManageModel());
                            break;
                        case 14:
                            cell.setCellValue(pond.getManager());
                            break;
                        case 15:
                            cell.setCellValue(pond.getManager());
                            break;
                        case 16:
                            cell.setCellValue(pond.getManager());
                            break;
                        case 17:
                            cell.setCellValue(pond.getRemark());
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
    public void exportPumpStationSummary(HttpServletResponse response, List<PumpStationEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(pumpStationSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (PumpStationEntity pumpStation : projects) {
            List<Transformer> transformers = pumpStation.getTransformers();
            List<Pump> pumps = pumpStation.getPumps();
            List<ElectricMotor> electricMotors = pumpStation.getElectricMotors();
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 37; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(pumpStation.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(pumpStation.getVillageEntity().getName() + " " + pumpStation.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(pumpStation.getName());
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
                            cell.setCellValue(pumpStation.getConstructTime());
                            break;
                        case 26:
                            cell.setCellValue(pumpStation.getConstructUnit());
                            break;
                        case 27:
                            cell.setCellValue(pumpStation.getLongitude());
                            break;
                        case 28:
                            cell.setCellValue(pumpStation.getLatitude());
                            break;
                        case 29:
                            cell.setCellValue(pumpStation.getPropertyOwner());
                            break;
                        case 30:
                            cell.setCellValue(pumpStation.getPropertyOwner());
                            break;
                        case 31:
                            cell.setCellValue(pumpStation.getPropertyOwner());
                            break;
                        case 32:
                            cell.setCellValue(pumpStation.getManageModel());
                            break;
                        case 33:
                            cell.setCellValue(pumpStation.getManager());
                            break;
                        case 34:
                            cell.setCellValue(pumpStation.getManager());
                            break;
                        case 35:
                            cell.setCellValue(pumpStation.getManager());
                            break;
                        case 36:
                            cell.setCellValue(pumpStation.getRemark());
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
    public void exportSluiceSummary(HttpServletResponse response, List<SluiceEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(sluiceSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (SluiceEntity sluice : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 30; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(sluice.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(sluice.getVillageEntity().getName() + " " + sluice.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(sluice.getName());
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
                            cell.setCellValue(sluice.getConstructUnit());
                            break;
                        case 19:
                            cell.setCellValue(sluice.getConstructTime());
                            break;
                        case 20:
                            cell.setCellValue(sluice.getLongitude());
                            break;
                        case 21:
                            cell.setCellValue(sluice.getLatitude());
                            break;
                        case 22:
                            cell.setCellValue(sluice.getPropertyOwner());
                            break;
                        case 23:
                            cell.setCellValue(sluice.getPropertyOwner());
                            break;
                        case 24:
                            cell.setCellValue(sluice.getPropertyOwner());
                            break;
                        case 25:
                            cell.setCellValue(sluice.getManageModel());
                            break;
                        case 26:
                            cell.setCellValue(sluice.getManager());
                            break;
                        case 27:
                            cell.setCellValue(sluice.getManager());
                            break;
                        case 28:
                            cell.setCellValue(sluice.getManager());
                            break;
                        case 29:
                            cell.setCellValue(sluice.getRemark());
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
    public void exportWatercourseSummary(HttpServletResponse response, List<WatercourseEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(watercourseSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WatercourseEntity watercourse : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 26; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(watercourse.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(watercourse.getVillageEntity().getName() + " " + watercourse.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(watercourse.getName());
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
                            cell.setCellValue(watercourse.getLongitude());
                            break;
                        case 15:
                            cell.setCellValue(watercourse.getLatitude());
                            break;
                        case 16:
                            cell.setCellValue(watercourse.getEndpointLongitude());
                            break;
                        case 17:
                            cell.setCellValue(watercourse.getEndpointLatitude());
                            break;
                        case 18:
                            cell.setCellValue(watercourse.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(watercourse.getPropertyOwner());
                            break;
                        case 20:
                            cell.setCellValue(watercourse.getPropertyOwner());
                            break;
                        case 21:
                            cell.setCellValue(watercourse.getManageModel());
                            break;
                        case 22:
                            cell.setCellValue(watercourse.getManager());
                            break;
                        case 23:
                            cell.setCellValue(watercourse.getManager());
                            break;
                        case 24:
                            cell.setCellValue(watercourse.getManager());
                            break;
                        case 25:
                            cell.setCellValue(watercourse.getRemark());
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
    public void exportWaterWorksSummary(HttpServletResponse response, List<WaterWorksEntity> projects) throws IOException {
        InputStream inputStream = new FileInputStream(new File(waterWorksSummaryModelFilePath));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet sheet1 = hssfWorkbook.getSheetAt(0);
        for (WaterWorksEntity waterWorks : projects) {
            for (int rowNum = 6; rowNum < projects.size() + 6; rowNum++) {
                for (int column = 0; column < 24; column++) {
                    HSSFCell cell = sheet1.getRow(rowNum).getCell(column);
                    switch (column) {
                        case 0:
                            cell.setCellValue(rowNum - 5);
                            break;
                        case 1:
                            cell.setCellValue(waterWorks.getTownEntity().getName());
                            break;
                        case 2:
                            cell.setCellValue(waterWorks.getVillageEntity().getName() + " " + waterWorks.getGroupEntity().getName());
                            break;
                        case 3:
                            cell.setCellValue(waterWorks.getName());
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
                            cell.setCellValue(waterWorks.getConstructUnit());
                            break;
                        case 13:
                            cell.setCellValue(waterWorks.getConstructTime());
                            break;
                        case 14:
                            cell.setCellValue(waterWorks.getLongitude());
                            break;
                        case 15:
                            cell.setCellValue(waterWorks.getLatitude());
                            break;
                        case 16:
                            cell.setCellValue(waterWorks.getPropertyOwner());
                            break;
                        case 17:
                            cell.setCellValue(waterWorks.getPropertyOwner());
                            break;
                        case 18:
                            cell.setCellValue(waterWorks.getPropertyOwner());
                            break;
                        case 19:
                            cell.setCellValue(waterWorks.getManageModel());
                            break;
                        case 20:
                            cell.setCellValue(waterWorks.getManager());
                            break;
                        case 21:
                            cell.setCellValue(waterWorks.getManager());
                            break;
                        case 22:
                            cell.setCellValue(waterWorks.getManager());
                            break;
                        case 23:
                            cell.setCellValue(waterWorks.getRemark());
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
