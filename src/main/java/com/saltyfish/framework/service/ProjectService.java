package com.saltyfish.framework.service;

import com.saltyfish.domain.entity.location.LocationEntity;
import com.saltyfish.domain.entity.project.WaterConservationEntity;
import com.saltyfish.domain.entity.project.conservation.*;
import com.saltyfish.domain.entity.project.device.*;
import com.saltyfish.domain.entity.project.division.CanalEntity;
import com.saltyfish.domain.entity.project.division.PipeEntity;
import com.saltyfish.domain.repository.LocationRepository;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.project.WaterConservationRepository;
import com.saltyfish.domain.repository.project.conservation.*;
import com.saltyfish.domain.repository.project.device.*;
import com.saltyfish.domain.repository.project.division.CanalRepository;
import com.saltyfish.domain.repository.project.division.PipeRepository;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weck on 16/9/3.
 * <p>
 * 工程操作
 */
@Service
public class ProjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private VillageRepository villageRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AqueductRepository aqueductRepository;

    @Autowired
    private BridgeRepository bridgeRepository;

    @Autowired
    private CanalRepository canalRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CulvertRepository culvertRepository;

    @Autowired
    private DamRepository damRepository;

    @Autowired
    private DeepWellsRepository deepWellsRepository;

    @Autowired
    private PipeRepository pipeRepository;

    @Autowired
    private DripIrrigationPipeRepository dripIrrigationPipeRepository;

    @Autowired
    private GreatWellsRepository greatWellsRepository;

    @Autowired
    private TransformerRepository transformerRepository;

    @Autowired
    private TurbineRepository turbineRepository;

    @Autowired
    private GeneratorRepository generatorRepository;

    @Autowired
    private HydropowerRepository hydropowerRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private PumpRepository pumpRepository;

    @Autowired
    private ElectricMotorRepository electricMotorRepository;

    @Autowired
    private PumpStationRepository pumpStationRepository;

    @Autowired
    private SluiceRepository sluiceRepository;

    @Autowired
    private WatercourseRepository watercourseRepository;

    @Autowired
    private WaterWorksRepository waterWorksRepository;

    @Autowired
    private WaterConservationRepository waterConservationRepository;

    /**
     * 保存共有属性
     */
    public void setCommonProperty(WaterConservationEntity wc, Integer userId, String category, String remark,
                                  String name, String code, String manageModel, Integer townId, Integer villageId,
                                  Integer groupId, String situation, String constructTime, String constructUnit,
                                  String propertyOwner, String manager, String longitude, String latitude,
                                  Long timeStamp) {
        Timestamp time = new Timestamp(timeStamp);
        wc.setCounty(userRepository.findById(userId).getCounty());
        wc.setCategory(category);
        wc.setRemark(remark);
        wc.setName(name);
        wc.setCode(code);
        wc.setManageModel(manageModel);
        wc.setTown(townRepository.findById(townId));
        wc.setVillage(villageRepository.findById(villageId));
        wc.setGroup(groupRepository.findById(groupId));
        wc.setSituation(situation);
        wc.setConstructTime(constructTime);
        wc.setConstructUnit(constructUnit);
        wc.setPropertyOwner(propertyOwner);
        wc.setManager(manager);
        wc.setIsDelete(0);
        LocationEntity location = new LocationEntity();
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        location.setIsDelete(0);
        location.setCreateTime(time);
        location.setUpdateTime(time);
        locationRepository.save(location);
        wc.setLocation(location);
    }

    /**
     * 保存渡槽属性
     */
    public void saveAqueduct(AqueductEntity aqueduct, String crossWatercourseLocation, String crossCount,
                             String sectionSize, String structureAndMaterial, String crossLength, String imagePath) {
        aqueduct.setCrossCount(crossCount);
        aqueduct.setCrossLength(crossLength);
        aqueduct.setCrossWatercourseLocation(crossWatercourseLocation);
        aqueduct.setImage(imagePath);
        aqueduct.setSectionSize(sectionSize);
        aqueduct.setStructureAndMaterial(structureAndMaterial);
        aqueductRepository.save(aqueduct);
    }

    /**
     * 保存桥梁属性
     */
    public void saveBridge(BridgeEntity bridgeEntity, String watercourseLocation, String crossCount,
                           String structureAndMaterial, String loadStandard, String crossLength, String width,
                           String length, String imagePath) {
        bridgeEntity.setStructureAndMaterial(structureAndMaterial);
        bridgeEntity.setCrossLength(crossLength);
        bridgeEntity.setImage(imagePath);
        bridgeEntity.setCrossCount(crossCount);
        bridgeEntity.setWatercourseLocation(watercourseLocation);
        bridgeEntity.setLoadStandard(loadStandard);
        bridgeEntity.setLength(length);
        bridgeEntity.setWidth(width);
        bridgeRepository.save(bridgeEntity);
    }

    /**
     * 保存渠道及其分渠
     */
    public void saveChannel(ChannelEntity channel, String headOrPumpStation, String buildingMatchRate, String length, String goodConditionRate, String imagePath, String sectionSize, String seepageCanalLength, String liningSectionSize, String sumLength, String planeSketchPath, String canalLength1, String canalLength2, String canalLength3, String canalLength4, String canalLength5, String canalLength6, String canalLength7, String canalLength8, String canalLiningMaterial1, String canalLiningMaterial2, String canalLiningMaterial3, String canalLiningMaterial4, String canalLiningMaterial5, String canalLiningMaterial6, String canalLiningMaterial7, String canalLiningMaterial8, String canalLiningSectionSize1, String canalLiningSectionSize2, String canalLiningSectionSize3, String canalLiningSectionSize4, String canalLiningSectionSize5, String canalLiningSectionSize6, String canalLiningSectionSize7, String canalLiningSectionSize8, String canalModel1, String canalModel2, String canalModel3, String canalModel4, String canalModel5, String canalModel6, String canalModel7, String canalModel8, String canalSectionSize1, String canalSectionSize2, String canalSectionSize3, String canalSectionSize4, String canalSectionSize5, String canalSectionSize6, String canalSectionSize7, String canalSectionSize8, String canalSeepageLength1, String canalSeepageLength2, String canalSeepageLength3, String canalSeepageLength4, String canalSeepageLength5, String canalSeepageLength6, String canalSeepageLength7, String canalSeepageLength8, String sumSectionSize, String sumLiningSectionSize, String sumSeepageCanalLength) {
        channel.setHeadOrPumpStation(headOrPumpStation);
        channel.setBuildingMatchRate(buildingMatchRate);
        channel.setLength(length);
        channel.setGoodConditionRate(goodConditionRate);
        channel.setSumLength(sumLength);
        channel.setImage(imagePath);
        channel.setSectionSize(sectionSize);
        channel.setSeepageCanalLength(seepageCanalLength);
        channel.setLiningSectionSize(liningSectionSize);
        channel.setPlaneSketch(planeSketchPath);
        channel.setSumSeepageCanalLength(sumSeepageCanalLength);
        channel.setSumSectionSize(sumSectionSize);
        channel.setSumLiningSectionSize(sumLiningSectionSize);
        CanalEntity canal1 = new CanalEntity();
        CanalEntity canal2 = new CanalEntity();
        CanalEntity canal3 = new CanalEntity();
        CanalEntity canal4 = new CanalEntity();
        CanalEntity canal5 = new CanalEntity();
        CanalEntity canal6 = new CanalEntity();
        CanalEntity canal7 = new CanalEntity();
        CanalEntity canal8 = new CanalEntity();
        canal1.setModel(canalModel1);
        canal1.setLiningSectionSize(canalLiningSectionSize1);
        canal1.setSeepageCanalLength(canalSeepageLength1);
        canal1.setLiningMaterial(canalLiningMaterial1);
        canal1.setLength(canalLength1);
        canal1.setSectionSize(canalSectionSize1);
        canal2.setLength(canalLength2);
        canal2.setLiningMaterial(canalLiningMaterial2);
        canal2.setSeepageCanalLength(canalSeepageLength2);
        canal2.setModel(canalModel2);
        canal2.setSectionSize(canalSectionSize2);
        canal2.setLiningSectionSize(canalLiningSectionSize2);
        canal3.setLength(canalLength3);
        canal3.setLiningMaterial(canalLiningMaterial3);
        canal3.setSeepageCanalLength(canalSeepageLength3);
        canal3.setModel(canalModel3);
        canal3.setSectionSize(canalSectionSize3);
        canal3.setLiningSectionSize(canalLiningSectionSize3);
        canal4.setLength(canalLength4);
        canal4.setLiningMaterial(canalLiningMaterial4);
        canal4.setSeepageCanalLength(canalSeepageLength4);
        canal4.setModel(canalModel4);
        canal4.setSectionSize(canalSectionSize4);
        canal4.setLiningSectionSize(canalLiningSectionSize4);
        canal5.setLength(canalLength5);
        canal5.setLiningMaterial(canalLiningMaterial5);
        canal5.setSeepageCanalLength(canalSeepageLength5);
        canal5.setModel(canalModel5);
        canal5.setSectionSize(canalSectionSize5);
        canal5.setLiningSectionSize(canalLiningSectionSize5);
        canal6.setLength(canalLength6);
        canal6.setLiningMaterial(canalLiningMaterial6);
        canal6.setSeepageCanalLength(canalSeepageLength6);
        canal6.setModel(canalModel6);
        canal6.setSectionSize(canalSectionSize6);
        canal6.setLiningSectionSize(canalLiningSectionSize6);
        canal7.setLength(canalLength7);
        canal7.setLiningMaterial(canalLiningMaterial7);
        canal7.setSeepageCanalLength(canalSeepageLength7);
        canal7.setModel(canalModel7);
        canal7.setSectionSize(canalSectionSize7);
        canal7.setLiningSectionSize(canalLiningSectionSize7);
        canal8.setLength(canalLength8);
        canal8.setLiningMaterial(canalLiningMaterial8);
        canal8.setSeepageCanalLength(canalSeepageLength8);
        canal8.setModel(canalModel8);
        canal8.setSectionSize(canalSectionSize8);
        canal8.setLiningSectionSize(canalLiningSectionSize8);
        List<CanalEntity> canals = new ArrayList<>();
        canals.add(canal1);
        canals.add(canal2);
        canals.add(canal3);
        canals.add(canal4);
        canals.add(canal5);
        canals.add(canal6);
        canals.add(canal7);
        canals.add(canal8);
        canalRepository.save(canals);
        channel.setCanals(canals);
        channelRepository.save(channel);
    }

    /**
     * 保存涵洞基本属性
     */
    public void saveCulvert(CulvertEntity culvertEntity, String watercourseLocation, String culvertModel, String sectionSize,
                            String length, String holeModel, String doorMaterial, String hoistModel, String holeMaterial,
                            String imagePath) {
        culvertEntity.setLength(length);
        culvertEntity.setSectionSize(sectionSize);
        culvertEntity.setCulvertModel(culvertModel);
        culvertEntity.setWatercourseLocation(watercourseLocation);
        culvertEntity.setHoistModel(hoistModel);
        culvertEntity.setHoleMaterial(holeMaterial);
        culvertEntity.setHoleModel(holeModel);
        culvertEntity.setDoorMaterial(doorMaterial);
        culvertEntity.setImage(imagePath);
        culvertRepository.save(culvertEntity);
    }

    /**
     * 保存塘坝基本属性
     */
    public void saveDam(DamEntity damEntity, String isRegistered, String features, String mainFunction, String isAccountability,
                        String feeResources, String maintainPersonFee, String isCertificated, String development,
                        String manageRageLine, String pondDamManageLine, String protectGround, String twoLinesBuilding,
                        String specifiedManage, String imagePath, String protectRageManagement) {
        damEntity.setDevelopment(development);
        damEntity.setIsAccountability(isAccountability);
        damEntity.setIsRegistered(isRegistered);
        damEntity.setFeatures(features);
        damEntity.setMainFunctions(mainFunction);
        damEntity.setFeeResources(feeResources);
        damEntity.setMaintainPersonFee(maintainPersonFee);
        damEntity.setIsCertificated(isCertificated);
        damEntity.setManageRageLine(manageRageLine);
        damEntity.setPondDamManageRageLine(pondDamManageLine);
        damEntity.setProtectGround(protectGround);
        damEntity.setTwoLinesBuilding(twoLinesBuilding);
        damEntity.setSpecifiedMange(specifiedManage);
        damEntity.setImage(imagePath);
        damEntity.setProtectRageManagement(protectRageManagement);
        damRepository.save(damEntity);
    }

    /**
     * 保存深水井基本属性
     */
    public void saveDeepWells(DeepWellsEntity deepWellsEntity, String irrigateArea, String deepPump, String diameter,
                              String depth, String material, String imagePath) {
        deepWellsEntity.setDeepPump(deepPump);
        deepWellsEntity.setIrrigateArea(irrigateArea);
        deepWellsEntity.setDiameter(diameter);
        deepWellsEntity.setDepth(depth);
        deepWellsEntity.setMaterial(material);
        deepWellsEntity.setImage(imagePath);
        deepWellsRepository.save(deepWellsEntity);
    }


    /**
     * 保存管滴灌及其支管
     */
    public void saveDripIrrigationPipe(DripIrrigationPipeEntity dripIrrigationPipeEntity, String irrigateArea, String intakeWay,
                                       String waterResource, String sumLength, String sumDiameter, String imagePath,
                                       String planeSketchPath, String pipeDiameter1, String pipeDiameter2, String pipeDiameter3,
                                       String pipeDiameter4, String pipeDiameter5, String pipeDiameter6, String pipeLength1,
                                       String pipeLength2, String pipeLength3, String pipeLength4, String pipeLength5,
                                       String pipeLength6, String pipeMaterial1, String pipeMaterial2, String pipeMaterial3,
                                       String pipeMaterial4, String pipeMaterial5, String pipeMaterial6, String pipeModel1,
                                       String pipeModel2, String pipeModel3, String pipeModel4, String pipeModel5, String pipeModel6, String length) {
        dripIrrigationPipeEntity.setImage(imagePath);
        dripIrrigationPipeEntity.setIrrigateArea(irrigateArea);
        dripIrrigationPipeEntity.setIntakeWay(intakeWay);
        dripIrrigationPipeEntity.setWaterResource(waterResource);
        dripIrrigationPipeEntity.setSumLength(sumLength);
        dripIrrigationPipeEntity.setSumDiameter(sumDiameter);
        dripIrrigationPipeEntity.setSketch(planeSketchPath);
        dripIrrigationPipeEntity.setLength(length);
        PipeEntity pipe1 = new PipeEntity();
        PipeEntity pipe2 = new PipeEntity();
        PipeEntity pipe3 = new PipeEntity();
        PipeEntity pipe4 = new PipeEntity();
        PipeEntity pipe5 = new PipeEntity();
        PipeEntity pipe6 = new PipeEntity();
        pipe1.setLength(pipeLength1);
        pipe1.setModel(pipeModel1);
        pipe1.setDiameter(pipeDiameter1);
        pipe1.setMaterial(pipeMaterial1);
        pipe2.setLength(pipeLength2);
        pipe2.setModel(pipeModel2);
        pipe2.setDiameter(pipeDiameter2);
        pipe2.setMaterial(pipeMaterial2);
        pipe3.setLength(pipeLength3);
        pipe3.setModel(pipeModel3);
        pipe3.setDiameter(pipeDiameter3);
        pipe3.setMaterial(pipeMaterial3);
        pipe4.setLength(pipeLength4);
        pipe4.setModel(pipeModel4);
        pipe4.setDiameter(pipeDiameter4);
        pipe4.setMaterial(pipeMaterial4);
        pipe5.setLength(pipeLength5);
        pipe5.setModel(pipeModel5);
        pipe5.setDiameter(pipeDiameter5);
        pipe5.setMaterial(pipeMaterial5);
        pipe6.setLength(pipeLength6);
        pipe6.setModel(pipeModel6);
        pipe6.setDiameter(pipeDiameter6);
        pipe6.setMaterial(pipeMaterial6);
        List<PipeEntity> pipes = new ArrayList<>();
        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);
        pipes.add(pipe4);
        pipes.add(pipe5);
        pipes.add(pipe6);
        pipeRepository.save(pipes);
        dripIrrigationPipeEntity.setPipes(pipes);
        dripIrrigationPipeRepository.save(dripIrrigationPipeEntity);
    }

    /**
     * 保存大口井基本属性
     */
    public void saveGreatWells(GreatWellsEntity greatWellsEntity, String irrigateArea, String waterCapacity, String size,
                               String depth, String modelAndMaterial, String imagePath) {
        greatWellsEntity.setDepth(depth);
        greatWellsEntity.setImage(imagePath);
        greatWellsEntity.setIrrigateArea(irrigateArea);
        greatWellsEntity.setWaterCapacity(waterCapacity);
        greatWellsEntity.setModelAndMaterial(modelAndMaterial);
        greatWellsEntity.setSize(size);
        greatWellsRepository.save(greatWellsEntity);
    }

    /**
     * 保存水电站及其水轮机,发电机,变压器信息
     */
    public void saveHydropower(HydropowerEntity hydropowerEntity, String irrigateArea, String paddyFieldArea,
                               String drainageArea, String irrigateFee, String drainageFee, String annualFee,
                               String watercourseLocation, String machineArea, String affiliation,
                               String sumElectricCapacity, String averageCapacity, String internalImagePath,
                               String externalImagePath, String problem, String transformerCapacity1,
                               String transformerCapacity2, String transformerCapacity3, String transformerModel1,
                               String transformerModel2, String transformerModel3, String turbineCount1,
                               String turbineCount2, String turbineCount3, String turbineFactoryDate1,
                               String turbineFactoryDate2, String turbineFactoryDate3, String turbineModel1,
                               String turbineModel2, String turbineModel3, String turbineTurnsOrFlow1,
                               String turbineTurnsOrFlow2, String turbineTurnsOrFlow3, String generatorFactoryDate1,
                               String generatorFactoryDate2, String generatorFactoryDate3, String generatorModel1,
                               String generatorModel2, String generatorModel3, String generatorPower1,
                               String generatorPower2, String generatorPower3) {
        hydropowerEntity.setIrrigateArea(irrigateArea);
        hydropowerEntity.setPaddyFieldArea(paddyFieldArea);
        hydropowerEntity.setDrainageArea(drainageArea);
        hydropowerEntity.setIrrigateFee(irrigateFee);
        hydropowerEntity.setDrainageFee(drainageFee);
        hydropowerEntity.setAnnualFee(annualFee);
        hydropowerEntity.setRiverLocation(watercourseLocation);
        hydropowerEntity.setMachineArea(machineArea);
        hydropowerEntity.setAffiliation(affiliation);
        hydropowerEntity.setSumElectricCapacity(sumElectricCapacity);
        hydropowerEntity.setAverageCapacity(averageCapacity);
        hydropowerEntity.setInternalImage(internalImagePath);
        hydropowerEntity.setExternalImage(externalImagePath);
        hydropowerEntity.setProblem(problem);
        TransformerEntity transformer1 = new TransformerEntity();
        transformer1.setCapacity(transformerCapacity1);
        transformer1.setModel(transformerModel1);
        TransformerEntity transformer2 = new TransformerEntity();
        transformer2.setModel(transformerModel2);
        transformer2.setCapacity(transformerCapacity2);
        TransformerEntity transformer3 = new TransformerEntity();
        transformer3.setCapacity(transformerCapacity3);
        transformer3.setModel(transformerModel3);
        List<TransformerEntity> transformers = new ArrayList<>();
        transformers.add(transformer1);
        transformers.add(transformer2);
        transformers.add(transformer3);
        transformerRepository.save(transformers);
        TurbineEntity turbine1 = new TurbineEntity();
        turbine1.setModel(turbineModel1);
        turbine1.setCount(turbineCount1);
        turbine1.setFactoryDate(turbineFactoryDate1);
        turbine1.setTurnsOrFlow(turbineTurnsOrFlow1);
        TurbineEntity turbine2 = new TurbineEntity();
        turbine2.setModel(turbineModel2);
        turbine2.setTurnsOrFlow(turbineTurnsOrFlow2);
        turbine2.setFactoryDate(turbineFactoryDate2);
        turbine2.setCount(turbineCount2);
        TurbineEntity turbine3 = new TurbineEntity();
        turbine3.setModel(turbineModel3);
        turbine3.setTurnsOrFlow(turbineTurnsOrFlow3);
        turbine3.setFactoryDate(turbineFactoryDate3);
        turbine3.setCount(turbineCount3);
        List<TurbineEntity> turbines = new ArrayList<>();
        turbines.add(turbine1);
        turbines.add(turbine2);
        turbines.add(turbine3);
        turbineRepository.save(turbines);
        GeneratorEntity generator1 = new GeneratorEntity();
        generator1.setFactoryDate(generatorFactoryDate1);
        generator1.setModel(generatorModel1);
        generator1.setPower(generatorPower1);
        GeneratorEntity generator2 = new GeneratorEntity();
        generator2.setFactoryDate(generatorFactoryDate2);
        generator2.setModel(generatorModel2);
        generator2.setPower(generatorPower2);
        GeneratorEntity generator3 = new GeneratorEntity();
        generator3.setFactoryDate(generatorFactoryDate3);
        generator3.setModel(generatorModel3);
        generator3.setPower(generatorPower3);
        List<GeneratorEntity> generators = new ArrayList<>();
        generators.add(generator1);
        generators.add(generator2);
        generators.add(generator3);
        generatorRepository.save(generators);
        hydropowerEntity.setTransformers(transformers);
        hydropowerEntity.setTurbines(turbines);
        hydropowerEntity.setGenerators(generators);
        hydropowerRepository.save(hydropowerEntity);
    }

    /**
     * 保存水塘基本属性
     */
    public void savePond(PondEntity pondEntity, String mainFunction, String lastDredgingTime, String waterArea,
                         String waterCapacity, String imagePath) {
        pondEntity.setImage(imagePath);
        pondEntity.setLastDredgingTime(lastDredgingTime);
        pondEntity.setMainFunction(mainFunction);
        pondEntity.setWaterArea(waterArea);
        pondEntity.setWaterCapacity(waterCapacity);
        pondRepository.save(pondEntity);
    }

    /**
     * 保存泵站及其水泵、电动机、变压器信息
     */
    public void savePumpStation(PumpStationEntity pumpStationEntity, String watercourseLocation, String irrigateArea,
                                String paddyFieldArea, String drainageArea, String irrigateFee, String drainageFee,
                                String annualFee, String nature, String machineArea, String totalInstalledCapacity,
                                String riverElevation, String poolHeight, String internalImagePath,
                                String externalImagePath, String problem, String transformerCapacity1,
                                String transformerCapacity2, String transformerCapacity3, String transformerModel1,
                                String transformerModel2, String transformerModel3, String pumpCount1, String pumpCount2,
                                String pumpCount3, String pumpFactoryDate1, String pumpFactoryDate2, String pumpFactoryDate3,
                                String pumpLiftOrFlow1, String pumpLiftOrFlow2, String pumpLiftOrFlow3, String pumpModel1,
                                String pumpModel2, String pumpModel3, String electricFactoryDate1, String electricFactoryDate2,
                                String electricFactoryDate3, String electricMotorModel1, String electricMotorModel2,
                                String electricMotorModel3, String electricPower1, String electricPower2, String electricPower3) {
        pumpStationEntity.setAnnualFee(annualFee);
        pumpStationEntity.setDrainageArea(drainageArea);
        pumpStationEntity.setDrainageFee(drainageFee);
        pumpStationEntity.setRiverLocation(watercourseLocation);
        pumpStationEntity.setIrrigateArea(irrigateArea);
        pumpStationEntity.setPaddyFieldArea(paddyFieldArea);
        pumpStationEntity.setIrrigateFee(irrigateFee);
        pumpStationEntity.setNature(nature);
        pumpStationEntity.setMachineArea(machineArea);
        pumpStationEntity.setTotalInstalledCapacity(totalInstalledCapacity);
        pumpStationEntity.setRiverElevation(riverElevation);
        pumpStationEntity.setPoolHeight(poolHeight);
        pumpStationEntity.setInternalImage(internalImagePath);
        pumpStationEntity.setExternalImage(externalImagePath);
        pumpStationEntity.setProblem(problem);
        TransformerEntity transformer1 = new TransformerEntity();
        transformer1.setCapacity(transformerCapacity1);
        transformer1.setModel(transformerModel1);
        TransformerEntity transformer2 = new TransformerEntity();
        transformer2.setCapacity(transformerCapacity2);
        transformer2.setModel(transformerModel2);
        TransformerEntity transformer3 = new TransformerEntity();
        transformer3.setCapacity(transformerCapacity3);
        transformer3.setModel(transformerModel3);
        List<TransformerEntity> transformers = new ArrayList<>();
        transformers.add(transformer1);
        transformers.add(transformer2);
        transformers.add(transformer3);
        transformerRepository.save(transformers);
        PumpEntity pump1 = new PumpEntity();
        pump1.setFactoryDate(pumpFactoryDate1);
        pump1.setModel(pumpModel1);
        pump1.setCount(pumpCount1);
        pump1.setLiftOrFlow(pumpLiftOrFlow1);
        PumpEntity pump2 = new PumpEntity();
        pump2.setFactoryDate(pumpFactoryDate2);
        pump2.setModel(pumpModel2);
        pump2.setCount(pumpCount2);
        pump2.setLiftOrFlow(pumpLiftOrFlow2);
        PumpEntity pump3 = new PumpEntity();
        pump3.setFactoryDate(pumpFactoryDate3);
        pump3.setModel(pumpModel3);
        pump3.setCount(pumpCount3);
        pump3.setLiftOrFlow(pumpLiftOrFlow3);
        List<PumpEntity> pumps = new ArrayList<>();
        pumps.add(pump1);
        pumps.add(pump2);
        pumps.add(pump3);
        pumpRepository.save(pumps);
        ElectricMotorEntity electricMotor1 = new ElectricMotorEntity();
        electricMotor1.setFactoryDate(electricFactoryDate1);
        electricMotor1.setModel(electricMotorModel1);
        electricMotor1.setPower(electricPower1);
        ElectricMotorEntity electricMotor2 = new ElectricMotorEntity();
        electricMotor2.setFactoryDate(electricFactoryDate2);
        electricMotor2.setModel(electricMotorModel2);
        electricMotor2.setPower(electricPower2);
        ElectricMotorEntity electricMotor3 = new ElectricMotorEntity();
        electricMotor3.setFactoryDate(electricFactoryDate3);
        electricMotor3.setModel(electricMotorModel3);
        electricMotor3.setPower(electricPower3);
        List<ElectricMotorEntity> electricMotors = new ArrayList<>();
        electricMotors.add(electricMotor1);
        electricMotors.add(electricMotor2);
        electricMotors.add(electricMotor3);
        electricMotorRepository.save(electricMotors);
        pumpStationEntity.setTransformers(transformers);
        pumpStationEntity.setElectricMotors(electricMotors);
        pumpStationEntity.setPumps(pumps);
        pumpStationRepository.save(pumpStationEntity);
    }

    /**
     * 保存水闸基本信息
     */
    public void saveSluice(SluiceEntity sluiceEntity, String watercourseLocation, String model, String holeCount, String door,
                           String hoistTonnage, String holeHeight, String holeWidth, String doorHeight, String doorWidth,
                           String hoistModel, String buildingSituation, String doorSituation, String hoistSituation, String imagePath) {
        sluiceEntity.setWatercourseLocation(watercourseLocation);
        sluiceEntity.setModel(model);
        sluiceEntity.setHoistModel(hoistModel);
        sluiceEntity.setHoleCount(holeCount);
        sluiceEntity.setDoor(door);
        sluiceEntity.setHoistTonnage(hoistTonnage);
        sluiceEntity.setHoleHeight(holeHeight);
        sluiceEntity.setHoleWidth(holeWidth);
        sluiceEntity.setDoorHeight(doorHeight);
        sluiceEntity.setDoorWidth(doorWidth);
        sluiceEntity.setBuildingSituation(buildingSituation);
        sluiceEntity.setDoorSituation(doorSituation);
        sluiceEntity.setHoistSituation(hoistSituation);
        sluiceEntity.setImage(imagePath);
        sluiceRepository.save(sluiceEntity);
    }

    /**
     * 保存河道基本信息
     */
    public void saveWaterCourse(WatercourseEntity watercourseEntity, String length, String lastDredgingTime, String estuaryHeight,
                                String estuaryWidth, String leftWidth, String rightWidth, String hediHeight, String hediWidth,
                                String flowVillages, String nature, String sectionImagePath, String startImagePath, String middleImagePath,
                                String endImagePath, String endpointLatitude, String endpointLongitude, Long timeStamp) {
        Timestamp time = new Timestamp(timeStamp);
        watercourseEntity.setLastDredgingTime(lastDredgingTime);
        watercourseEntity.setEstuaryHeight(estuaryHeight);
        watercourseEntity.setLength(length);
        watercourseEntity.setEstuaryWidth(estuaryWidth);
        watercourseEntity.setNature(nature);
        watercourseEntity.setLeftWidth(leftWidth);
        watercourseEntity.setRightWidth(rightWidth);
        watercourseEntity.setHediHeight(hediHeight);
        watercourseEntity.setHediWidth(hediWidth);
        watercourseEntity.setFlowVillages(flowVillages);
        watercourseEntity.setSectionImage(sectionImagePath);
        watercourseEntity.setStartImage(startImagePath);
        watercourseEntity.setMiddleImage(middleImagePath);
        watercourseEntity.setEndImage(endImagePath);
        LocationEntity endpointLocation = new LocationEntity();
        endpointLocation.setLatitude(endpointLatitude);
        endpointLocation.setLongitude(endpointLongitude);
        endpointLocation.setIsDelete(0);
        endpointLocation.setUpdateTime(time);
        endpointLocation.setCreateTime(time);
        locationRepository.save(endpointLocation);
        watercourseEntity.setEndpointLocation(endpointLocation);
        watercourseRepository.save(watercourseEntity);
    }

    /**
     * 保存水厂基本信息
     */
    public void saveWaterWorks(WaterWorksEntity waterWorksEntity, String provideAmount, String waterModel, String haveCleaner,
                               String isRegularCheck, String dayProvideAmount, String provideVillageCount, String providePopulation,
                               String haveProtectArea, String imagePath) {
        waterWorksEntity.setProvideAmount(provideAmount);
        waterWorksEntity.setWaterModel(waterModel);
        waterWorksEntity.setHaveCleaner(haveCleaner);
        waterWorksEntity.setIsRegularCheck(isRegularCheck);
        waterWorksEntity.setDayProvideAmount(dayProvideAmount);
        waterWorksEntity.setProvideVillageCount(provideVillageCount);
        waterWorksEntity.setProvidePopulation(providePopulation);
        waterWorksEntity.setHaveProtectArea(haveProtectArea);
        waterWorksEntity.setImage(imagePath);
        waterWorksRepository.save(waterWorksEntity);
    }

    /**
     * 根据种类查找设施
     *
     * @param category 工程种类
     */
    public Page<WaterConservationEntity> getConservationsByCategory(List<Integer> townIds, String category, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndIsDeleteAndTownIdIn(category, 0, townIds, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndTownId(String category, Integer townId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndTownIdAndIsDelete(category, townId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndVillageId(String category, Integer villageId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndVillageIdAndIsDelete(category, villageId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndGroupId(String category, Integer groupId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndGroupIdAndIsDelete(category, groupId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndManageModel(String manageModel, List<Integer> townIds, String category, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndCategoryAndIsDeleteAndTownIdIn(manageModel, category, 0, townIds, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndManageModelAndTownId(String manageModel, String category, Integer townId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndCategoryAndTownIdAndIsDelete(manageModel, category, townId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByManageModelAndCategoryAndVillageId(String manageModel, String category, Integer villageId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndCategoryAndVillageIdAndIsDelete(manageModel, category, villageId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByManageModelAndCategoryAndGroupId(String manageModel, String category, Integer groupId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndCategoryAndGroupIdAndIsDelete(manageModel, category, groupId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndSituation(String situation, List<Integer> townIds, String category, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findBySituationAndCategoryAndIsDeleteAndTownIdIn(situation, category, 0, townIds, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndSituationAndTownId(String situation, String category, Integer townId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findBySituationAndCategoryAndTownIdAndIsDelete(situation, category, townId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsBySituationAndCategoryAndVillageId(String situation, String category, Integer villageId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findBySituationAndCategoryAndVillageIdAndIsDelete(situation, category, villageId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsBySituationAndCategoryAndGroupId(String situation, String category, Integer groupId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findBySituationAndCategoryAndGroupIdAndIsDelete(situation, category, groupId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndSituationAndManageModel(String situation, String manageModel, List<Integer> townIds, String category, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndSituationAndCategoryAndIsDeleteAndTownIdIn(manageModel, situation, category, 0, townIds, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndSituationAndManageModelAndTownId(String situation, String manageModel, String category, Integer townId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndSituationAndCategoryAndTownIdAndIsDelete(manageModel, situation, category, townId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsBySituationAndCategoryAndManageModelAndVillageId(String situation, String manageModel, String category, Integer villageId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndSituationAndCategoryAndVillageIdAndIsDelete(manageModel, situation, category, villageId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsBySituationAndCategoryAndManageModelAndGroupId(String situation, String manageModel, String category, Integer groupId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByManageModelAndSituationAndCategoryAndGroupIdAndIsDelete(manageModel, situation, category, groupId, 0, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndDate(List<Integer> townIds, String category, Long startDate, Long endDate, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        Timestamp startTime = new Timestamp(startDate);
        Timestamp endTime = new Timestamp(endDate);
        return waterConservationRepository.findByCategoryAndIsDeleteAndTownIdInAndUpdateTimeBetween(category, 0, townIds, startTime, endTime, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndCode(List<Integer> townIds, String category, String code, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndIsDeleteAndTownIdInAndCodeLike(category, 0, townIds, code, pageable);
    }

    public Page<WaterConservationEntity> getConservationsByCategoryAndName(List<Integer> townIds, String category, String name, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByCategoryAndIsDeleteAndTownIdInAndNameLike(category, 0, townIds, name, pageable);
    }

    public Page<WaterConservationEntity> getConservations(List<Integer> townIds, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return waterConservationRepository.findByIsDeleteAndTownIdIn(0, townIds, pageable);
    }

    /**
     * 删除工程
     *
     * @param projectId 工程id
     * @param timeStamp 时间戳
     */
    public void deleteProject(Integer projectId, Long timeStamp) {
        WaterConservationEntity waterConservation = waterConservationRepository.findById(projectId);
        Timestamp time = new Timestamp(timeStamp);
        waterConservation.setIsDelete(1);
        waterConservation.setUpdateTime(time);
        waterConservation.getLocation().setIsDelete(1);
        waterConservation.getLocation().setUpdateTime(time);
        locationRepository.save(waterConservation.getLocation());
        waterConservationRepository.save(waterConservation);
    }

    /**
     * 修改工程的公共属性
     */
    public void modifyCommonItems(WaterConservationEntity waterConservation, String remark, String name, String code, String manageModel,
                                  Integer townId, Integer villageId, Integer groupId, String situation,
                                  String constructTime, String constructUnit, String propertyOwner, String manager,
                                  String longitude, String latitude, Long timeStamp) {
        Timestamp time = new Timestamp(timeStamp);
        waterConservation.setRemark(remark);
        waterConservation.setName(name);
        waterConservation.setCode(code);
        waterConservation.setManageModel(manageModel);
        waterConservation.setTown(townRepository.findById(townId));
        waterConservation.setVillage(villageRepository.findById(villageId));
        waterConservation.setGroup(groupRepository.findById(groupId));
        waterConservation.setSituation(situation);
        waterConservation.setConstructTime(constructTime);
        waterConservation.setConstructUnit(constructUnit);
        waterConservation.setPropertyOwner(propertyOwner);
        waterConservation.setManager(manager);
        waterConservation.getLocation().setLongitude(longitude);
        waterConservation.getLocation().setLatitude(latitude);
        waterConservation.getLocation().setUpdateTime(time);
        locationRepository.save(waterConservation.getLocation());
        waterConservationRepository.save(waterConservation);
    }

    /**
     * 修改渡槽
     */
    public void modifyAqueduct(AqueductEntity aqueduct, String crossWatercourseLocation, String crossCount,
                               String sectionSize, String structureAndMaterial, String crossLength, String imagePath) {
        aqueduct.setCrossWatercourseLocation(crossWatercourseLocation);
        aqueduct.setCrossCount(crossCount);
        aqueduct.setSectionSize(sectionSize);
        aqueduct.setStructureAndMaterial(structureAndMaterial);
        aqueduct.setImage(imagePath);
        aqueduct.setCrossLength(crossLength);
        aqueductRepository.save(aqueduct);
    }

    /**
     * 修改桥梁
     */
    public void modifyBridge(BridgeEntity bridgeEntity, String watercourseLocation, String crossCount,
                             String structureAndMaterial, String loadStandard, String crossLength, String width,
                             String length, String imagePath) {
        bridgeEntity.setWatercourseLocation(watercourseLocation);
        bridgeEntity.setCrossCount(crossCount);
        bridgeEntity.setStructureAndMaterial(structureAndMaterial);
        bridgeEntity.setLength(length);
        bridgeEntity.setLoadStandard(loadStandard);
        bridgeEntity.setCrossLength(crossLength);
        bridgeEntity.setWidth(width);
        bridgeEntity.setImage(imagePath);
        bridgeRepository.save(bridgeEntity);
    }

    /**
     * 修改渠道
     */
    public void modifyChannel(ChannelEntity channelEntity, String headOrPumpStation, String buildingMatchRate, String length, String goodConditionRate, String imagePath, String sectionSize, String seepageCanalLength, String liningSectionSize, String sumLength, String planeSketchPath, String canalLength1, String canalLength2, String canalLength3, String canalLength4, String canalLength5, String canalLength6, String canalLength7, String canalLength8, String canalLiningMaterial1, String canalLiningMaterial2, String canalLiningMaterial3, String canalLiningMaterial4, String canalLiningMaterial5, String canalLiningMaterial6, String canalLiningMaterial7, String canalLiningMaterial8, String canalLiningSectionSize1, String canalLiningSectionSize2, String canalLiningSectionSize3, String canalLiningSectionSize4, String canalLiningSectionSize5, String canalLiningSectionSize6, String canalLiningSectionSize7, String canalLiningSectionSize8, String canalModel1, String canalModel2, String canalModel3, String canalModel4, String canalModel5, String canalModel6, String canalModel7, String canalModel8, String canalSectionSize1, String canalSectionSize2, String canalSectionSize3, String canalSectionSize4, String canalSectionSize5, String canalSectionSize6, String canalSectionSize7, String canalSectionSize8, String canalSeepageLength1, String canalSeepageLength2, String canalSeepageLength3, String canalSeepageLength4, String canalSeepageLength5, String canalSeepageLength6, String canalSeepageLength7, String canalSeepageLength8, String sumSectionSize, String sumLiningSectionSize, String sumSeepageCanalLength) {
        channelEntity.setHeadOrPumpStation(headOrPumpStation);
        channelEntity.setBuildingMatchRate(buildingMatchRate);
        channelEntity.setLength(length);
        channelEntity.setGoodConditionRate(goodConditionRate);
        channelEntity.setLiningSectionSize(liningSectionSize);
        channelEntity.setImage(imagePath);
        channelEntity.setSectionSize(sectionSize);
        channelEntity.setSeepageCanalLength(seepageCanalLength);
        channelEntity.setSumLength(sumLength);
        channelEntity.setPlaneSketch(planeSketchPath);
        channelEntity.setSumLiningSectionSize(sumLiningSectionSize);
        channelEntity.setSumSectionSize(sumSectionSize);
        channelEntity.setSumSeepageCanalLength(sumSeepageCanalLength);
        List<CanalEntity> canals = channelEntity.getCanals();
        CanalEntity canal1 = canals.get(0);
        CanalEntity canal2 = canals.get(1);
        CanalEntity canal3 = canals.get(2);
        CanalEntity canal4 = canals.get(3);
        CanalEntity canal5 = canals.get(4);
        CanalEntity canal6 = canals.get(5);
        CanalEntity canal7 = canals.get(6);
        CanalEntity canal8 = canals.get(7);
        canal1.setModel(canalModel1);
        canal1.setLiningMaterial(canalLiningMaterial1);
        canal1.setLiningSectionSize(canalLiningSectionSize1);
        canal1.setSeepageCanalLength(canalSeepageLength1);
        canal1.setSectionSize(canalSectionSize1);
        canal1.setLength(canalLength1);
        canalRepository.save(canal1);
        canal2.setModel(canalModel2);
        canal2.setLiningMaterial(canalLiningMaterial2);
        canal2.setLiningSectionSize(canalLiningSectionSize2);
        canal2.setSeepageCanalLength(canalSeepageLength2);
        canal2.setLength(canalLength2);
        canal2.setSectionSize(canalSectionSize2);
        canalRepository.save(canal2);
        canal3.setModel(canalModel3);
        canal3.setLiningMaterial(canalLiningMaterial3);
        canal3.setLiningSectionSize(canalLiningSectionSize3);
        canal3.setSeepageCanalLength(canalSeepageLength3);
        canal3.setLength(canalLength3);
        canal3.setSectionSize(canalSectionSize3);
        canalRepository.save(canal3);
        canal4.setModel(canalModel4);
        canal4.setLiningMaterial(canalLiningMaterial4);
        canal4.setLiningSectionSize(canalLiningSectionSize4);
        canal4.setSeepageCanalLength(canalSeepageLength4);
        canal4.setLength(canalLength4);
        canal4.setSectionSize(canalSectionSize4);
        canalRepository.save(canal4);
        canal5.setModel(canalModel5);
        canal5.setLiningMaterial(canalLiningMaterial5);
        canal5.setLiningSectionSize(canalLiningSectionSize5);
        canal5.setSeepageCanalLength(canalSeepageLength5);
        canal5.setLength(canalLength5);
        canal5.setSectionSize(canalSectionSize5);
        canalRepository.save(canal5);
        canal6.setModel(canalModel6);
        canal6.setLiningMaterial(canalLiningMaterial6);
        canal6.setLiningSectionSize(canalLiningSectionSize6);
        canal6.setSeepageCanalLength(canalSeepageLength6);
        canal6.setLength(canalLength6);
        canal6.setSectionSize(canalSectionSize6);
        canalRepository.save(canal6);
        canal7.setModel(canalModel7);
        canal7.setLiningMaterial(canalLiningMaterial7);
        canal7.setLiningSectionSize(canalLiningSectionSize7);
        canal7.setSeepageCanalLength(canalSeepageLength7);
        canal7.setLength(canalLength7);
        canal7.setSectionSize(canalSectionSize7);
        canalRepository.save(canal7);
        canal8.setModel(canalModel8);
        canal8.setLiningMaterial(canalLiningMaterial8);
        canal8.setLiningSectionSize(canalLiningSectionSize8);
        canal8.setSeepageCanalLength(canalSeepageLength8);
        canal8.setLength(canalLength8);
        canal8.setSectionSize(canalSectionSize8);
        canalRepository.save(canal8);
        channelEntity.setCanals(canals);
        channelRepository.save(channelEntity);
    }

    /**
     * 修改涵洞
     */
    public void modifyCulvert(CulvertEntity culvertEntity, String watercourseLocation, String culvertModel, String sectionSize,
                              String length, String holeModel, String doorMaterial, String hoistModel, String holeMaterial,
                              String imagePath) {
        culvertEntity.setWatercourseLocation(watercourseLocation);
        culvertEntity.setCulvertModel(culvertModel);
        culvertEntity.setSectionSize(sectionSize);
        culvertEntity.setLength(length);
        culvertEntity.setHoleModel(holeModel);
        culvertEntity.setDoorMaterial(doorMaterial);
        culvertEntity.setHoistModel(hoistModel);
        culvertEntity.setHoleMaterial(holeMaterial);
        culvertEntity.setImage(imagePath);
        culvertRepository.save(culvertEntity);
    }

    /**
     * 修改塘坝
     */
    public void modifyDam(DamEntity damEntity, String isRegistered, String features, String mainFunction, String isAccountability,
                          String feeResources, String maintainPersonFee, String isCertificated, String development,
                          String manageRageLine, String pondDamManageLine, String protectGround, String twoLinesBuilding,
                          String specifiedManage, String imagePath, String protectRageManagement) {
        damEntity.setImage(imagePath);
        damEntity.setIsRegistered(isRegistered);
        damEntity.setFeatures(features);
        damEntity.setManageRageLine(manageRageLine);
        damEntity.setMaintainPersonFee(maintainPersonFee);
        damEntity.setMainFunctions(mainFunction);
        damEntity.setIsAccountability(isAccountability);
        damEntity.setFeeResources(feeResources);
        damEntity.setIsCertificated(isCertificated);
        damEntity.setDevelopment(development);
        damEntity.setPondDamManageRageLine(pondDamManageLine);
        damEntity.setProtectGround(protectGround);
        damEntity.setTwoLinesBuilding(twoLinesBuilding);
        damEntity.setSpecifiedMange(specifiedManage);
        damEntity.setProtectRageManagement(protectRageManagement);
        damRepository.save(damEntity);
    }

    /**
     * 修改深水井
     */
    public void modifyDeepWells(DeepWellsEntity deepWellsEntity, String irrigateArea, String deepPump, String diameter,
                                String depth, String material, String imagePath) {
        deepWellsEntity.setIrrigateArea(irrigateArea);
        deepWellsEntity.setDeepPump(deepPump);
        deepWellsEntity.setDiameter(diameter);
        deepWellsEntity.setDepth(depth);
        deepWellsEntity.setMaterial(material);
        deepWellsEntity.setImage(imagePath);
        deepWellsRepository.save(deepWellsEntity);
    }

    /**
     * 修改管滴灌
     */
    public void modifyDripIrrigationPipe(DripIrrigationPipeEntity dripIrrigationPipeEntity, String irrigateArea, String intakeWay,
                                         String waterResource, String sumLength, String sumDiameter, String imagePath,
                                         String planeSketchPath, String pipeDiameter1, String pipeDiameter2, String pipeDiameter3,
                                         String pipeDiameter4, String pipeDiameter5, String pipeDiameter6, String pipeLength1,
                                         String pipeLength2, String pipeLength3, String pipeLength4, String pipeLength5, String pipeLength6,
                                         String pipeMaterial1, String pipeMaterial2, String pipeMaterial3, String pipeMaterial4,
                                         String pipeMaterial5, String pipeMaterial6, String pipeModel1, String pipeModel2, String pipeModel3,
                                         String pipeModel4, String pipeModel5, String pipeModel6, String length) {
        dripIrrigationPipeEntity.setIrrigateArea(irrigateArea);
        dripIrrigationPipeEntity.setIntakeWay(intakeWay);
        dripIrrigationPipeEntity.setWaterResource(waterResource);
        dripIrrigationPipeEntity.setSumLength(sumLength);
        dripIrrigationPipeEntity.setSumDiameter(sumDiameter);
        dripIrrigationPipeEntity.setImage(imagePath);
        dripIrrigationPipeEntity.setSketch(planeSketchPath);
        dripIrrigationPipeEntity.setLength(length);
        List<PipeEntity> pipes = dripIrrigationPipeEntity.getPipes();
        PipeEntity pipe1 = pipes.get(0);
        PipeEntity pipe2 = pipes.get(1);
        PipeEntity pipe3 = pipes.get(2);
        PipeEntity pipe4 = pipes.get(3);
        PipeEntity pipe5 = pipes.get(4);
        PipeEntity pipe6 = pipes.get(5);
        pipe1.setMaterial(pipeMaterial1);
        pipe1.setDiameter(pipeDiameter1);
        pipe1.setModel(pipeModel1);
        pipe1.setLength(pipeLength1);
        pipeRepository.save(pipe1);
        pipe2.setMaterial(pipeMaterial2);
        pipe2.setDiameter(pipeDiameter2);
        pipe2.setModel(pipeModel2);
        pipe2.setLength(pipeLength2);
        pipeRepository.save(pipe2);
        pipe3.setMaterial(pipeMaterial3);
        pipe3.setDiameter(pipeDiameter3);
        pipe3.setModel(pipeModel3);
        pipe3.setLength(pipeLength3);
        pipeRepository.save(pipe3);
        pipe4.setMaterial(pipeMaterial4);
        pipe4.setDiameter(pipeDiameter4);
        pipe4.setModel(pipeModel4);
        pipe4.setLength(pipeLength4);
        pipeRepository.save(pipe4);
        pipe5.setMaterial(pipeMaterial5);
        pipe5.setDiameter(pipeDiameter5);
        pipe5.setModel(pipeModel5);
        pipe5.setLength(pipeLength5);
        pipeRepository.save(pipe5);
        pipe6.setMaterial(pipeMaterial6);
        pipe6.setDiameter(pipeDiameter6);
        pipe6.setModel(pipeModel6);
        pipe6.setLength(pipeLength6);
        pipeRepository.save(pipe6);
        dripIrrigationPipeEntity.setPipes(pipes);
        dripIrrigationPipeRepository.save(dripIrrigationPipeEntity);
    }


    /**
     * 修改大口井
     */
    public void modifyGreatWells(GreatWellsEntity greatWellsEntity, String irrigateArea, String waterCapacity, String size,
                                 String depth, String modelAndMaterial, String imagePath) {
        greatWellsEntity.setImage(imagePath);
        greatWellsEntity.setIrrigateArea(irrigateArea);
        greatWellsEntity.setWaterCapacity(waterCapacity);
        greatWellsEntity.setSize(size);
        greatWellsEntity.setDepth(depth);
        greatWellsEntity.setModelAndMaterial(modelAndMaterial);
        greatWellsRepository.save(greatWellsEntity);
    }


    /**
     * 修改水电站
     */
    public void modifyHydropower(HydropowerEntity hydropowerEntity, String irrigateArea, String paddyFieldArea, String drainageArea,
                                 String irrigateFee, String drainageFee, String annualFee, String watercourseLocation,
                                 String machineArea, String affiliation, String sumElectricCapacity, String averageCapacity,
                                 String internalImagePath, String externalImagePath, String problem, String transformerCapacity1,
                                 String transformerCapacity2, String transformerCapacity3, String transformerModel1,
                                 String transformerModel2, String transformerModel3, String turbineCount1, String turbineCount2,
                                 String turbineCount3, String turbineFactoryDate1, String turbineFactoryDate2,
                                 String turbineFactoryDate3, String turbineModel1, String turbineModel2, String turbineModel3,
                                 String turbineTurnsOrFlow1, String turbineTurnsOrFlow2, String turbineTurnsOrFlow3,
                                 String generatorFactoryDate1, String generatorFactoryDate2, String generatorFactoryDate3,
                                 String generatorModel1, String generatorModel2, String generatorModel3, String generatorPower1,
                                 String generatorPower2, String generatorPower3) {
        hydropowerEntity.setIrrigateArea(irrigateArea);
        hydropowerEntity.setPaddyFieldArea(paddyFieldArea);
        hydropowerEntity.setDrainageFee(drainageFee);
        hydropowerEntity.setDrainageArea(drainageArea);
        hydropowerEntity.setIrrigateFee(irrigateFee);
        hydropowerEntity.setAnnualFee(annualFee);
        hydropowerEntity.setRiverLocation(watercourseLocation);
        hydropowerEntity.setMachineArea(machineArea);
        hydropowerEntity.setAffiliation(affiliation);
        hydropowerEntity.setSumElectricCapacity(sumElectricCapacity);
        hydropowerEntity.setAverageCapacity(averageCapacity);
        hydropowerEntity.setInternalImage(internalImagePath);
        hydropowerEntity.setExternalImage(externalImagePath);
        hydropowerEntity.setProblem(problem);
        List<TransformerEntity> transformers = hydropowerEntity.getTransformers();
        List<TurbineEntity> turbines = hydropowerEntity.getTurbines();
        List<GeneratorEntity> generators = hydropowerEntity.getGenerators();
        TransformerEntity transformer1 = transformers.get(0);
        TransformerEntity transformer2 = transformers.get(1);
        TransformerEntity transformer3 = transformers.get(2);
        TurbineEntity turbine1 = turbines.get(0);
        TurbineEntity turbine2 = turbines.get(1);
        TurbineEntity turbine3 = turbines.get(2);
        GeneratorEntity generator1 = generators.get(0);
        GeneratorEntity generator2 = generators.get(1);
        GeneratorEntity generator3 = generators.get(2);
        transformer1.setModel(transformerModel1);
        transformer1.setCapacity(transformerCapacity1);
        transformerRepository.save(transformer1);
        transformer2.setModel(transformerModel2);
        transformer2.setCapacity(transformerCapacity2);
        transformerRepository.save(transformer2);
        transformer3.setModel(transformerModel3);
        transformer3.setCapacity(transformerCapacity3);
        transformerRepository.save(transformer3);
        turbine1.setCount(turbineCount1);
        turbine1.setModel(turbineModel1);
        turbine1.setFactoryDate(turbineFactoryDate1);
        turbine1.setTurnsOrFlow(turbineTurnsOrFlow1);
        turbineRepository.save(turbine1);
        turbine2.setCount(turbineCount2);
        turbine2.setModel(turbineModel2);
        turbine2.setFactoryDate(turbineFactoryDate2);
        turbine2.setTurnsOrFlow(turbineTurnsOrFlow2);
        turbineRepository.save(turbine2);
        turbine3.setCount(turbineCount3);
        turbine3.setModel(turbineModel3);
        turbine3.setFactoryDate(turbineFactoryDate3);
        turbine3.setTurnsOrFlow(turbineTurnsOrFlow3);
        turbineRepository.save(turbine3);
        generator1.setFactoryDate(generatorFactoryDate1);
        generator1.setModel(generatorModel1);
        generator1.setPower(generatorPower1);
        generatorRepository.save(generator1);
        generator2.setFactoryDate(generatorFactoryDate2);
        generator2.setModel(generatorModel2);
        generator2.setPower(generatorPower2);
        generatorRepository.save(generator2);
        generator3.setFactoryDate(generatorFactoryDate3);
        generator3.setModel(generatorModel3);
        generator3.setPower(generatorPower3);
        generatorRepository.save(generator3);
        hydropowerEntity.setTransformers(transformers);
        hydropowerEntity.setTurbines(turbines);
        hydropowerEntity.setGenerators(generators);
        hydropowerRepository.save(hydropowerEntity);
    }

    /**
     * 修改水塘
     */
    public void modifyPond(PondEntity pondEntity, String mainFunction, String lastDredgingTime, String waterArea,
                           String waterCapacity, String imagePath) {
        pondEntity.setImage(imagePath);
        pondEntity.setMainFunction(mainFunction);
        pondEntity.setLastDredgingTime(lastDredgingTime);
        pondEntity.setWaterArea(waterArea);
        pondEntity.setWaterCapacity(waterCapacity);
        pondRepository.save(pondEntity);
    }

    /**
     * 修改泵站
     */
    public void modifyPumpStation(PumpStationEntity pumpStationEntity, String watercourseLocation, String irrigateArea,
                                  String paddyFieldArea, String drainageArea, String irrigateFee, String drainageFee,
                                  String annualFee, String nature, String machineArea, String totalInstalledCapacity,
                                  String riverElevation, String poolHeight, String internalImagePath, String externalImagePath,
                                  String problem, String transformerCapacity1, String transformerCapacity2,
                                  String transformerCapacity3, String transformerModel1, String transformerModel2,
                                  String transformerModel3, String pumpCount1, String pumpCount2, String pumpCount3,
                                  String pumpFactoryDate1, String pumpFactoryDate2, String pumpFactoryDate3,
                                  String pumpLiftOrFlow1, String pumpLiftOrFlow2, String pumpLiftOrFlow3, String pumpModel1,
                                  String pumpModel2, String pumpModel3, String electricFactoryDate1, String electricFactoryDate2,
                                  String electricFactoryDate3, String electricMotorModel1, String electricMotorModel2,
                                  String electricMotorModel3, String electricPower1, String electricPower2, String electricPower3) {
        pumpStationEntity.setRiverLocation(watercourseLocation);
        pumpStationEntity.setIrrigateArea(irrigateArea);
        pumpStationEntity.setPaddyFieldArea(paddyFieldArea);
        pumpStationEntity.setDrainageArea(drainageArea);
        pumpStationEntity.setIrrigateFee(irrigateFee);
        pumpStationEntity.setDrainageFee(drainageFee);
        pumpStationEntity.setRiverElevation(riverElevation);
        pumpStationEntity.setAnnualFee(annualFee);
        pumpStationEntity.setNature(nature);
        pumpStationEntity.setMachineArea(machineArea);
        pumpStationEntity.setTotalInstalledCapacity(totalInstalledCapacity);
        pumpStationEntity.setProblem(problem);
        pumpStationEntity.setPoolHeight(poolHeight);
        pumpStationEntity.setInternalImage(internalImagePath);
        pumpStationEntity.setExternalImage(externalImagePath);
        List<TransformerEntity> transformers = pumpStationEntity.getTransformers();
        List<PumpEntity> pumps = pumpStationEntity.getPumps();
        List<ElectricMotorEntity> electricMotors = pumpStationEntity.getElectricMotors();
        TransformerEntity transformer1 = transformers.get(0);
        TransformerEntity transformer2 = transformers.get(1);
        TransformerEntity transformer3 = transformers.get(2);
        transformer1.setModel(transformerModel1);
        transformer1.setCapacity(transformerCapacity1);
        transformerRepository.save(transformer1);
        transformer2.setModel(transformerModel2);
        transformer2.setCapacity(transformerCapacity2);
        transformerRepository.save(transformer2);
        transformer3.setModel(transformerModel3);
        transformer3.setCapacity(transformerCapacity3);
        transformerRepository.save(transformer3);
        PumpEntity pump1 = pumps.get(0);
        PumpEntity pump2 = pumps.get(1);
        PumpEntity pump3 = pumps.get(2);
        pump1.setFactoryDate(pumpFactoryDate1);
        pump1.setLiftOrFlow(pumpLiftOrFlow1);
        pump1.setModel(pumpModel1);
        pump1.setCount(pumpCount1);
        pumpRepository.save(pump1);
        pump2.setFactoryDate(pumpFactoryDate2);
        pump2.setLiftOrFlow(pumpLiftOrFlow2);
        pump2.setModel(pumpModel2);
        pump2.setCount(pumpCount2);
        pumpRepository.save(pump2);
        pump3.setFactoryDate(pumpFactoryDate3);
        pump3.setLiftOrFlow(pumpLiftOrFlow3);
        pump3.setModel(pumpModel3);
        pump3.setCount(pumpCount3);
        pumpRepository.save(pump3);
        ElectricMotorEntity electricMotor1 = electricMotors.get(0);
        ElectricMotorEntity electricMotor2 = electricMotors.get(1);
        ElectricMotorEntity electricMotor3 = electricMotors.get(2);
        electricMotor1.setFactoryDate(electricFactoryDate1);
        electricMotor1.setPower(electricPower1);
        electricMotor1.setModel(electricMotorModel1);
        electricMotorRepository.save(electricMotor1);
        electricMotor2.setFactoryDate(electricFactoryDate2);
        electricMotor2.setPower(electricPower2);
        electricMotor2.setModel(electricMotorModel2);
        electricMotorRepository.save(electricMotor2);
        electricMotor3.setFactoryDate(electricFactoryDate3);
        electricMotor3.setPower(electricPower3);
        electricMotor3.setModel(electricMotorModel3);
        electricMotorRepository.save(electricMotor3);
        pumpStationEntity.setTransformers(transformers);
        pumpStationEntity.setElectricMotors(electricMotors);
        pumpStationEntity.setPumps(pumps);
        pumpStationRepository.save(pumpStationEntity);
    }

    /**
     * 修改水闸
     */
    public void modifySluice(SluiceEntity sluiceEntity, String watercourseLocation, String model, String holeCount,
                             String door, String hoistTonnage, String holeHeight, String holeWidth, String doorHeight,
                             String doorWidth, String hoistModel, String buildingSituation, String doorSituation,
                             String hoistSituation, String imagePath) {
        sluiceEntity.setWatercourseLocation(watercourseLocation);
        sluiceEntity.setModel(model);
        sluiceEntity.setHoleCount(holeCount);
        sluiceEntity.setDoor(door);
        sluiceEntity.setHoistSituation(hoistSituation);
        sluiceEntity.setHoistTonnage(hoistTonnage);
        sluiceEntity.setHoleHeight(holeHeight);
        sluiceEntity.setHoleWidth(holeWidth);
        sluiceEntity.setDoorHeight(doorHeight);
        sluiceEntity.setDoorWidth(doorWidth);
        sluiceEntity.setHoistModel(hoistModel);
        sluiceEntity.setBuildingSituation(buildingSituation);
        sluiceEntity.setDoorSituation(doorSituation);
        sluiceEntity.setImage(imagePath);
        sluiceRepository.save(sluiceEntity);
    }

    /**
     * 修改河道
     */
    public void modifyWaterCourse(WatercourseEntity watercourseEntity, String length, String lastDredgingTime,
                                  String estuaryHeight, String estuaryWidth, String leftWidth, String rightWidth,
                                  String hediHeight, String hediWidth, String flowVillages, String nature,
                                  String sectionImagePath, String startImagePath, String middleImagePath,
                                  String endImagePath, String endpointLatitude, String endpointLongitude,
                                  Long timeStamp) {
        Timestamp time = new Timestamp(timeStamp);
        watercourseEntity.setLength(length);
        watercourseEntity.setLastDredgingTime(lastDredgingTime);
        watercourseEntity.setEstuaryHeight(estuaryHeight);
        watercourseEntity.setEstuaryWidth(estuaryWidth);
        watercourseEntity.setLeftWidth(leftWidth);
        watercourseEntity.setRightWidth(rightWidth);
        watercourseEntity.setHediWidth(hediWidth);
        watercourseEntity.setHediHeight(hediHeight);
        watercourseEntity.setFlowVillages(flowVillages);
        watercourseEntity.setNature(nature);
        watercourseEntity.setEndImage(endImagePath);
        watercourseEntity.setSectionImage(sectionImagePath);
        watercourseEntity.setStartImage(startImagePath);
        watercourseEntity.setMiddleImage(middleImagePath);
        watercourseEntity.getEndpointLocation().setLatitude(endpointLatitude);
        watercourseEntity.getEndpointLocation().setLongitude(endpointLongitude);
        watercourseEntity.getEndpointLocation().setUpdateTime(time);
        locationRepository.save(watercourseEntity.getEndpointLocation());
        watercourseRepository.save(watercourseEntity);
    }

    /**
     * 修改水厂
     */
    public void modifyWaterWorks(WaterWorksEntity waterWorksEntity, String provideAmount, String waterModel, String haveCleaner,
                                 String isRegularCheck, String dayProvideAmount, String provideVillageCount,
                                 String providePopulation, String haveProtectArea, String imagePath) {
        waterWorksEntity.setProvideAmount(provideAmount);
        waterWorksEntity.setWaterModel(waterModel);
        waterWorksEntity.setHaveCleaner(haveCleaner);
        waterWorksEntity.setIsRegularCheck(isRegularCheck);
        waterWorksEntity.setDayProvideAmount(dayProvideAmount);
        waterWorksEntity.setProvideVillageCount(provideVillageCount);
        waterWorksEntity.setProvidePopulation(providePopulation);
        waterWorksEntity.setImage(imagePath);
        waterWorksEntity.setHaveProtectArea(haveProtectArea);
        waterWorksRepository.save(waterWorksEntity);
    }

    /**
     * 根据id获取工程
     *
     * @param projectId 工程id
     * @return 工程
     */
    public WaterConservationEntity getConservation(Integer projectId) {
        return waterConservationRepository.findById(projectId);
    }

    /**
     * 获取工程汇总列表
     *
     * @param accessedTownIds 权限乡镇id
     * @param category        种类
     * @return list
     */
    public List<WaterConservationEntity> getConservationsByCategory(List<Integer> accessedTownIds, String category) {
        return waterConservationRepository.findByCategoryAndIsDeleteAndTownIdIn(category, 0, accessedTownIds);
    }
}
