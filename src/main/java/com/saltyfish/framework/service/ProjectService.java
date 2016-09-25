package com.saltyfish.framework.service;

import com.saltyfish.common.bean.device.*;
import com.saltyfish.common.bean.division.Canal;
import com.saltyfish.common.bean.division.Pipe;
import com.saltyfish.domain.entity.conservation.*;
import com.saltyfish.domain.entity.superbean.ConservationEntity;
import com.saltyfish.domain.repository.ConservationRepository;
import com.saltyfish.domain.repository.auth.UserRepository;
import com.saltyfish.domain.repository.conservation.*;
import com.saltyfish.domain.repository.unit.GroupRepository;
import com.saltyfish.domain.repository.unit.TownRepository;
import com.saltyfish.domain.repository.unit.VillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weck on 16/9/24.
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
    private AqueductRepository aqueductRepository;

    @Autowired
    private BridgeRepository bridgeRepository;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CulvertRepository culvertRepository;

    @Autowired
    private DamRepository damRepository;

    @Autowired
    private DeepWellsRepository deepWellsRepository;

    @Autowired
    private DripIrrigationPipeRepository dripIrrigationPipeRepository;

    @Autowired
    private GreatWellsRepository greatWellsRepository;

    @Autowired
    private HydropowerRepository hydropowerRepository;

    @Autowired
    private PondRepository pondRepository;

    @Autowired
    private PumpStationRepository pumpStationRepository;

    @Autowired
    private SluiceRepository sluiceRepository;

    @Autowired
    private WatercourseRepository watercourseRepository;

    @Autowired
    private WaterWorksRepository waterworksRepository;

    @Autowired
    private ConservationRepository conservationRepository;


    public void setCommonProperty(ConservationEntity wc, Integer userId, String category, String remark, String name, String code, String manageModel, Integer townId, Integer villageId, Integer groupId, String situation, String constructTime, String constructUnit, String propertyOwner, String manager, String longitude, String latitude, Long timeStamp) {
        wc.setCountyEntity(userRepository.findById(userId).getCounty());
        wc.setCategory(category);
        wc.setRemark(remark);
        wc.setName(name);
        wc.setCode(code);
        wc.setManageModel(manageModel);
        wc.setTownEntity(townRepository.findById(townId));
        wc.setVillageEntity(villageRepository.findById(villageId));
        wc.setGroupEntity(groupRepository.findById(groupId));
        wc.setSituation(situation);
        wc.setConstructTime(constructTime);
        wc.setConstructUnit(constructUnit);
        wc.setPropertyOwner(propertyOwner);
        wc.setManager(manager);
        wc.setIsDelete(0);
        wc.setLongitude(longitude);
        wc.setLatitude(latitude);
        wc.setCreateTime(timeStamp);
        wc.setUpdateTime(timeStamp);
        conservationRepository.save(wc);
    }

    public void saveAqueduct(AqueductEntity aqueduct, String crossWatercourseLocation, String crossCount, String sectionSize, String structureAndMaterial, String crossLength, String imagePath) {
        aqueduct.setCrossWatercourseLocation(crossWatercourseLocation);
        aqueduct.setCrossCount(crossCount);
        aqueduct.setSectionSize(sectionSize);
        aqueduct.setStructureAndMaterial(structureAndMaterial);
        aqueduct.setCrossLength(crossLength);
        aqueduct.setImage(imagePath);
        aqueductRepository.save(aqueduct);
    }

    public void saveBridge(BridgeEntity bridgeEntity, String watercourseLocation, String crossCount, String structureAndMaterial,
                           String loadStandard, String crossLength, String width, String length, String imagePath) {
        bridgeEntity.setWatercourseLocation(watercourseLocation);
        bridgeEntity.setCrossCount(crossCount);
        bridgeEntity.setStructureAndMaterial(structureAndMaterial);
        bridgeEntity.setLoadStandard(loadStandard);
        bridgeEntity.setCrossLength(crossLength);
        bridgeEntity.setWidth(width);
        bridgeEntity.setLength(length);
        bridgeEntity.setImage(imagePath);
        bridgeRepository.save(bridgeEntity);
    }

    public void saveChannel(ChannelEntity channelEntity, String headOrPumpStation, String buildingMatchRate, String length,
                            String goodConditionRate, String imagePath, String sectionSize, String seepageCanalLength,
                            String liningSectionSize, String sumLength, String planeSketchPath, String canalLength1,
                            String canalLength2, String canalLength3, String canalLength4, String canalLength5, String canalLength6,
                            String canalLength7, String canalLength8, String canalLiningMaterial1, String canalLiningMaterial2,
                            String canalLiningMaterial3, String canalLiningMaterial4, String canalLiningMaterial5,
                            String canalLiningMaterial6, String canalLiningMaterial7, String canalLiningMaterial8,
                            String canalLiningSectionSize1, String canalLiningSectionSize2, String canalLiningSectionSize3,
                            String canalLiningSectionSize4, String canalLiningSectionSize5, String canalLiningSectionSize6,
                            String canalLiningSectionSize7, String canalLiningSectionSize8, String canalModel1, String canalModel2,
                            String canalModel3, String canalModel4, String canalModel5, String canalModel6, String canalModel7,
                            String canalModel8, String canalSectionSize1, String canalSectionSize2, String canalSectionSize3,
                            String canalSectionSize4, String canalSectionSize5, String canalSectionSize6, String canalSectionSize7,
                            String canalSectionSize8, String canalSeepageLength1, String canalSeepageLength2, String canalSeepageLength3,
                            String canalSeepageLength4, String canalSeepageLength5, String canalSeepageLength6, String canalSeepageLength7,
                            String canalSeepageLength8, String sumSectionSize, String sumLiningSectionSize, String sumSeepageCanalLength) {
        channelEntity.setHeadOrPumpStation(headOrPumpStation);
        channelEntity.setSumSectionSize(sumSectionSize);
        channelEntity.setSumLiningSectionSize(sumLiningSectionSize);
        channelEntity.setSumSeepageCanalLength(sumSeepageCanalLength);
        channelEntity.setBuildingMatchRate(buildingMatchRate);
        channelEntity.setLength(length);
        channelEntity.setGoodConditionRate(goodConditionRate);
        channelEntity.setImage(imagePath);
        channelEntity.setSectionSize(sectionSize);
        channelEntity.setSeepageCanalLength(seepageCanalLength);
        channelEntity.setLiningSectionSize(liningSectionSize);
        channelEntity.setSumLength(sumLength);
        channelEntity.setPlaneSketch(planeSketchPath);
        Canal canal1 = new Canal();
        Canal canal2 = new Canal();
        Canal canal3 = new Canal();
        Canal canal4 = new Canal();
        Canal canal5 = new Canal();
        Canal canal6 = new Canal();
        Canal canal7 = new Canal();
        Canal canal8 = new Canal();
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
        List<Canal> canals = new ArrayList<>();
        canals.add(canal1);
        canals.add(canal2);
        canals.add(canal3);
        canals.add(canal4);
        canals.add(canal5);
        canals.add(canal6);
        canals.add(canal7);
        canals.add(canal8);
        channelEntity.setCanals(canals);
        channelRepository.save(channelEntity);
    }

    public void saveCulvert(CulvertEntity culvertEntity, String watercourseLocation, String culvertModel, String sectionSize, String length, String holeModel,
                            String doorMaterial, String hoistModel, String holeMaterial, String imagePath) {
        culvertEntity.setWatercourseLocation(watercourseLocation);
        culvertEntity.setCulvertModel(culvertModel);
        culvertEntity.setSectionSize(sectionSize);
        culvertEntity.setLength(length);
        culvertEntity.setHoistModel(hoistModel);
        culvertEntity.setHoleModel(holeModel);
        culvertEntity.setDoorMaterial(doorMaterial);
        culvertEntity.setHoleMaterial(holeMaterial);
        culvertEntity.setImage(imagePath);
        culvertRepository.save(culvertEntity);
    }

    public void saveDam(DamEntity damEntity, String isRegistered, String features, String mainFunction, String isAccountability,
                        String feeResources, String maintainPersonFee, String isCertificated, String development,
                        String manageRageLine, String pondDamManageLine, String protectGround, String twoLinesBuilding,
                        String specifiedManage, String imagePath, String protectRageManagement) {
        damEntity.setIsRegistered(isRegistered);
        damEntity.setMainFunctions(mainFunction);
        damEntity.setFeatures(features);
        damEntity.setIsAccountability(isAccountability);
        damEntity.setIsCertificated(isCertificated);
        damEntity.setFeeResources(feeResources);
        damEntity.setMaintainPersonFee(maintainPersonFee);
        damEntity.setDevelopment(development);
        damEntity.setManageRageLine(manageRageLine);
        damEntity.setPondDamManageRageLine(pondDamManageLine);
        damEntity.setProtectGround(protectGround);
        damEntity.setTwoLinesBuilding(twoLinesBuilding);
        damEntity.setSpecifiedMange(specifiedManage);
        damEntity.setImage(imagePath);
        damEntity.setProtectRageManagement(protectRageManagement);
        damRepository.save(damEntity);
    }

    public void saveDeepWells(DeepWellsEntity deepWellsEntity, String irrigateArea, String deepPump, String diameter,
                              String depth, String material, String imagePath) {
        deepWellsEntity.setIrrigateArea(irrigateArea);
        deepWellsEntity.setDeepPump(deepPump);
        deepWellsEntity.setDiameter(diameter);
        deepWellsEntity.setDepth(depth);
        deepWellsEntity.setMaterial(material);
        deepWellsEntity.setImage(imagePath);
        deepWellsRepository.save(deepWellsEntity);
    }

    public void saveDripIrrigationPipe(DripIrrigationPipeEntity dripIrrigationPipeEntity, String irrigateArea, String intakeWay,
                                       String waterResource, String sumLength, String sumDiameter, String imagePath,
                                       String planeSketchPath, String pipeDiameter1, String pipeDiameter2, String pipeDiameter3,
                                       String pipeDiameter4, String pipeDiameter5, String pipeDiameter6, String pipeLength1,
                                       String pipeLength2, String pipeLength3, String pipeLength4, String pipeLength5,
                                       String pipeLength6, String pipeMaterial1, String pipeMaterial2, String pipeMaterial3,
                                       String pipeMaterial4, String pipeMaterial5, String pipeMaterial6, String pipeModel1,
                                       String pipeModel2, String pipeModel3, String pipeModel4, String pipeModel5,
                                       String pipeModel6, String length) {
        dripIrrigationPipeEntity.setIrrigateArea(irrigateArea);
        dripIrrigationPipeEntity.setIntakeWay(intakeWay);
        dripIrrigationPipeEntity.setImage(imagePath);
        dripIrrigationPipeEntity.setWaterResource(waterResource);
        dripIrrigationPipeEntity.setSumLength(sumLength);
        dripIrrigationPipeEntity.setSumDiameter(sumDiameter);
        dripIrrigationPipeEntity.setSketch(planeSketchPath);
        dripIrrigationPipeEntity.setLength(length);
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Pipe pipe4 = new Pipe();
        Pipe pipe5 = new Pipe();
        Pipe pipe6 = new Pipe();
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
        List<Pipe> pipes = new ArrayList<>();
        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);
        pipes.add(pipe4);
        pipes.add(pipe5);
        pipes.add(pipe6);
        dripIrrigationPipeEntity.setPipes(pipes);
        dripIrrigationPipeRepository.save(dripIrrigationPipeEntity);
    }

    public void saveGreatWells(GreatWellsEntity greatWellsEntity, String irrigateArea, String waterCapacity,
                               String size, String depth, String modelAndMaterial, String imagePath) {
        greatWellsEntity.setWaterCapacity(waterCapacity);
        greatWellsEntity.setImage(imagePath);
        greatWellsEntity.setIrrigateArea(irrigateArea);
        greatWellsEntity.setSize(size);
        greatWellsEntity.setDepth(depth);
        greatWellsEntity.setModelAndMaterial(modelAndMaterial);
        greatWellsRepository.save(greatWellsEntity);
    }

    public void saveHydropower(HydropowerEntity hydropowerEntity, String irrigateArea, String paddyFieldArea, String drainageArea,
                               String irrigateFee, String drainageFee, String annualFee, String watercourseLocation,
                               String machineArea, String affiliation, String sumElectricCapacity, String averageCapacity,
                               String internalImagePath, String externalImagePath, String problem, String transformerCapacity1,
                               String transformerCapacity2, String transformerCapacity3, String transformerModel1,
                               String transformerModel2, String transformerModel3, String turbineCount1, String turbineCount2,
                               String turbineCount3, String turbineFactoryDate1, String turbineFactoryDate2, String turbineFactoryDate3,
                               String turbineModel1, String turbineModel2, String turbineModel3, String turbineTurnsOrFlow1,
                               String turbineTurnsOrFlow2, String turbineTurnsOrFlow3, String generatorFactoryDate1, String generatorFactoryDate2,
                               String generatorFactoryDate3, String generatorModel1, String generatorModel2, String generatorModel3,
                               String generatorPower1, String generatorPower2, String generatorPower3) {
        hydropowerEntity.setIrrigateArea(irrigateArea);
        hydropowerEntity.setPaddyFieldArea(paddyFieldArea);
        hydropowerEntity.setDrainageArea(drainageArea);
        hydropowerEntity.setSumElectricCapacity(sumElectricCapacity);
        hydropowerEntity.setIrrigateFee(irrigateFee);
        hydropowerEntity.setDrainageFee(drainageFee);
        hydropowerEntity.setAnnualFee(annualFee);
        hydropowerEntity.setAffiliation(affiliation);
        hydropowerEntity.setRiverLocation(watercourseLocation);
        hydropowerEntity.setMachineArea(machineArea);
        hydropowerEntity.setAverageCapacity(averageCapacity);
        hydropowerEntity.setInternalImage(internalImagePath);
        hydropowerEntity.setExternalImage(externalImagePath);
        hydropowerEntity.setProblem(problem);
        Transformer transformer1 = new Transformer();
        transformer1.setCapacity(transformerCapacity1);
        transformer1.setModel(transformerModel1);
        Transformer transformer2 = new Transformer();
        transformer2.setModel(transformerModel2);
        transformer2.setCapacity(transformerCapacity2);
        Transformer transformer3 = new Transformer();
        transformer3.setCapacity(transformerCapacity3);
        transformer3.setModel(transformerModel3);
        List<Transformer> transformers = new ArrayList<>();
        transformers.add(transformer1);
        transformers.add(transformer2);
        transformers.add(transformer3);
        hydropowerEntity.setTransformers(transformers);
        Turbine turbine1 = new Turbine();
        turbine1.setModel(turbineModel1);
        turbine1.setCount(turbineCount1);
        turbine1.setFactoryDate(turbineFactoryDate1);
        turbine1.setTurnsOrFlow(turbineTurnsOrFlow1);
        Turbine turbine2 = new Turbine();
        turbine2.setModel(turbineModel2);
        turbine2.setTurnsOrFlow(turbineTurnsOrFlow2);
        turbine2.setFactoryDate(turbineFactoryDate2);
        turbine2.setCount(turbineCount2);
        Turbine turbine3 = new Turbine();
        turbine3.setModel(turbineModel3);
        turbine3.setTurnsOrFlow(turbineTurnsOrFlow3);
        turbine3.setFactoryDate(turbineFactoryDate3);
        turbine3.setCount(turbineCount3);
        List<Turbine> turbines = new ArrayList<>();
        turbines.add(turbine1);
        turbines.add(turbine2);
        turbines.add(turbine3);
        hydropowerEntity.setTurbines(turbines);
        Generator generator1 = new Generator();
        generator1.setFactoryDate(generatorFactoryDate1);
        generator1.setModel(generatorModel1);
        generator1.setPower(generatorPower1);
        Generator generator2 = new Generator();
        generator2.setFactoryDate(generatorFactoryDate2);
        generator2.setModel(generatorModel2);
        generator2.setPower(generatorPower2);
        Generator generator3 = new Generator();
        generator3.setFactoryDate(generatorFactoryDate3);
        generator3.setModel(generatorModel3);
        generator3.setPower(generatorPower3);
        List<Generator> generators = new ArrayList<>();
        generators.add(generator1);
        generators.add(generator2);
        generators.add(generator3);
        hydropowerEntity.setGenerators(generators);
        hydropowerRepository.save(hydropowerEntity);
    }

    public void savePond(PondEntity pondEntity, String mainFunction, String lastDredgingTime, String waterArea, String waterCapacity,
                         String imagePath) {
        pondEntity.setMainFunction(mainFunction);
        pondEntity.setLastDredgingTime(lastDredgingTime);
        pondEntity.setWaterCapacity(waterCapacity);
        pondEntity.setWaterArea(waterArea);
        pondEntity.setImage(imagePath);
        pondRepository.save(pondEntity);
    }

    public void savePumpStation(PumpStationEntity pumpStationEntity, String watercourseLocation, String irrigateArea, String paddyFieldArea,
                                String drainageArea, String irrigateFee, String drainageFee, String annualFee, String nature,
                                String machineArea, String totalInstalledCapacity, String riverElevation, String poolHeight,
                                String internalImagePath, String externalImagePath, String problem, String transformerCapacity1,
                                String transformerCapacity2, String transformerCapacity3, String transformerModel1,
                                String transformerModel2, String transformerModel3, String pumpCount1, String pumpCount2,
                                String pumpCount3, String pumpFactoryDate1, String pumpFactoryDate2, String pumpFactoryDate3,
                                String pumpLiftOrFlow1, String pumpLiftOrFlow2, String pumpLiftOrFlow3, String pumpModel1,
                                String pumpModel2, String pumpModel3, String electricFactoryDate1, String electricFactoryDate2,
                                String electricFactoryDate3, String electricMotorModel1, String electricMotorModel2,
                                String electricMotorModel3, String electricPower1, String electricPower2, String electricPower3) {
        pumpStationEntity.setRiverLocation(watercourseLocation);
        pumpStationEntity.setIrrigateFee(irrigateFee);
        pumpStationEntity.setPaddyFieldArea(paddyFieldArea);
        pumpStationEntity.setIrrigateArea(irrigateArea);
        pumpStationEntity.setDrainageFee(drainageFee);
        pumpStationEntity.setDrainageArea(drainageArea);
        pumpStationEntity.setAnnualFee(annualFee);
        pumpStationEntity.setNature(nature);
        pumpStationEntity.setMachineArea(machineArea);
        pumpStationEntity.setTotalInstalledCapacity(totalInstalledCapacity);
        pumpStationEntity.setRiverElevation(riverElevation);
        pumpStationEntity.setPoolHeight(poolHeight);
        pumpStationEntity.setInternalImage(internalImagePath);
        pumpStationEntity.setProblem(problem);
        pumpStationEntity.setExternalImage(externalImagePath);
        Transformer transformer1 = new Transformer();
        transformer1.setCapacity(transformerCapacity1);
        transformer1.setModel(transformerModel1);
        Transformer transformer2 = new Transformer();
        transformer2.setModel(transformerModel2);
        transformer2.setCapacity(transformerCapacity2);
        Transformer transformer3 = new Transformer();
        transformer3.setCapacity(transformerCapacity3);
        transformer3.setModel(transformerModel3);
        List<Transformer> transformers = new ArrayList<>();
        transformers.add(transformer1);
        transformers.add(transformer2);
        transformers.add(transformer3);
        pumpStationEntity.setTransformers(transformers);
        Pump pump1 = new Pump();
        pump1.setFactoryDate(pumpFactoryDate1);
        pump1.setModel(pumpModel1);
        pump1.setCount(pumpCount1);
        pump1.setLiftOrFlow(pumpLiftOrFlow1);
        Pump pump2 = new Pump();
        pump2.setFactoryDate(pumpFactoryDate2);
        pump2.setModel(pumpModel2);
        pump2.setCount(pumpCount2);
        pump2.setLiftOrFlow(pumpLiftOrFlow2);
        Pump pump3 = new Pump();
        pump3.setFactoryDate(pumpFactoryDate3);
        pump3.setModel(pumpModel3);
        pump3.setCount(pumpCount3);
        pump3.setLiftOrFlow(pumpLiftOrFlow3);
        List<Pump> pumps = new ArrayList<>();
        pumps.add(pump1);
        pumps.add(pump2);
        pumps.add(pump3);
        pumpStationEntity.setPumps(pumps);
        ElectricMotor electricMotor1 = new ElectricMotor();
        electricMotor1.setFactoryDate(electricFactoryDate1);
        electricMotor1.setModel(electricMotorModel1);
        electricMotor1.setPower(electricPower1);
        ElectricMotor electricMotor2 = new ElectricMotor();
        electricMotor2.setFactoryDate(electricFactoryDate2);
        electricMotor2.setModel(electricMotorModel2);
        electricMotor2.setPower(electricPower2);
        ElectricMotor electricMotor3 = new ElectricMotor();
        electricMotor3.setFactoryDate(electricFactoryDate3);
        electricMotor3.setModel(electricMotorModel3);
        electricMotor3.setPower(electricPower3);
        List<ElectricMotor> electricMotors = new ArrayList<>();
        electricMotors.add(electricMotor1);
        electricMotors.add(electricMotor2);
        electricMotors.add(electricMotor3);
        pumpStationEntity.setElectricMotors(electricMotors);
        pumpStationRepository.save(pumpStationEntity);
    }

    public void saveSluice(SluiceEntity sluiceEntity, String watercourseLocation, String model, String holeCount, String door,
                           String hoistTonnage, String holeHeight, String holeWidth, String doorHeight, String doorWidth,
                           String hoistModel, String buildingSituation, String doorSituation, String hoistSituation, String imagePath) {
        sluiceEntity.setWatercourseLocation(watercourseLocation);
        sluiceEntity.setModel(model);
        sluiceEntity.setHoleCount(holeCount);
        sluiceEntity.setDoor(door);
        sluiceEntity.setHoistModel(hoistModel);
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

    public void saveWatercourse(WatercourseEntity watercourseEntity, String length, String lastDredgingTime, String estuaryHeight,
                                String estuaryWidth, String leftWidth, String rightWidth, String hediHeight, String hediWidth,
                                String flowVillages, String nature, String sectionImagePath, String startImagePath,
                                String middleImagePath, String endImagePath, String endpointLatitude, String endpointLongitude) {
        watercourseEntity.setLength(length);
        watercourseEntity.setLastDredgingTime(lastDredgingTime);
        watercourseEntity.setEstuaryHeight(estuaryHeight);
        watercourseEntity.setEndImage(endImagePath);
        watercourseEntity.setEstuaryWidth(estuaryWidth);
        watercourseEntity.setLeftWidth(leftWidth);
        watercourseEntity.setRightWidth(rightWidth);
        watercourseEntity.setHediHeight(hediHeight);
        watercourseEntity.setHediWidth(hediWidth);
        watercourseEntity.setFlowVillages(flowVillages);
        watercourseEntity.setSectionImage(sectionImagePath);
        watercourseEntity.setNature(nature);
        watercourseEntity.setStartImage(startImagePath);
        watercourseEntity.setMiddleImage(middleImagePath);
        watercourseEntity.setEndpointLatitude(endpointLatitude);
        watercourseEntity.setEndpointLongitude(endpointLongitude);
        watercourseRepository.save(watercourseEntity);
    }

    public void saveWaterWorks(WaterWorksEntity waterWorksEntity, String provideAmount, String waterModel, String haveCleaner,
                               String isRegularCheck, String dayProvideAmount, String provideVillageCount, String providePopulation,
                               String haveProtectArea, String imagePath) {
        waterWorksEntity.setProvideAmount(provideAmount);
        waterWorksEntity.setWaterModel(waterModel);
        waterWorksEntity.setHaveCleaner(haveCleaner);
        waterWorksEntity.setIsRegularCheck(isRegularCheck);
        waterWorksEntity.setProvidePopulation(providePopulation);
        waterWorksEntity.setProvideVillageCount(provideVillageCount);
        waterWorksEntity.setHaveCleaner(haveCleaner);
        waterWorksEntity.setImage(imagePath);
        waterWorksEntity.setHaveProtectArea(haveProtectArea);
        waterWorksEntity.setDayProvideAmount(dayProvideAmount);
        waterworksRepository.save(waterWorksEntity);
    }

    public void modifyCommonProperty(ConservationEntity wc, Integer userId, String remark, String name, String code, String manageModel, Integer townId, Integer villageId, Integer groupId, String situation, String constructTime, String constructUnit, String propertyOwner, String manager, String longitude, String latitude, Long timeStamp) {
        wc.setCountyEntity(userRepository.findById(userId).getCounty());
        wc.setRemark(remark);
        wc.setName(name);
        wc.setCode(code);
        wc.setManageModel(manageModel);
        wc.setTownEntity(townRepository.findById(townId));
        wc.setVillageEntity(villageRepository.findById(villageId));
        wc.setGroupEntity(groupRepository.findById(groupId));
        wc.setSituation(situation);
        wc.setConstructTime(constructTime);
        wc.setConstructUnit(constructUnit);
        wc.setPropertyOwner(propertyOwner);
        wc.setManager(manager);
        wc.setIsDelete(0);
        wc.setLongitude(longitude);
        wc.setLatitude(latitude);
        wc.setUpdateTime(timeStamp);
        conservationRepository.save(wc);
    }

    public ConservationEntity getConservation(String projectId) {
        return conservationRepository.findById(projectId);
    }

    public void deleteProject(String projectId, Long timeStamp) {
        ConservationEntity conservation = conservationRepository.findById(projectId);
        conservation.setIsDelete(1);
        conservation.setUpdateTime(timeStamp);
        conservationRepository.save(conservation);
    }

    public Page<ConservationEntity> getConservationsInAccessedTowns(Integer userId, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return conservationRepository.findByIsDeleteAndTownEntityIn(0, userRepository.findById(userId).getTowns(), pageable);
    }

    public Page<ConservationEntity> getConservations(Integer userId, String category, Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(page - 1, size, sort);
        return conservationRepository.findByIsDeleteAndCategoryAndTownEntityIn(0, category, userRepository.findById(userId).getTowns(), pageable);
    }

    public List<ConservationEntity> getConservations(Integer userId, String category) {
        return conservationRepository.findByIsDeleteAndCategoryAndTownEntityIn(0, category, userRepository.findById(userId).getTowns());
    }

    public List<AqueductEntity> getAqueducts(Integer userId) {
        return aqueductRepository.findByIsDeleteAndTownEntityIn(0, userRepository.findById(userId).getTowns());
    }

    public List<BridgeEntity> getBridges(Integer userId) {
        return bridgeRepository.findByIsDeleteAndTownEntityIn(0, userRepository.findById(userId).getTowns());
    }

    public List<ChannelEntity> getChannels(Integer userId) {
        return channelRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<CulvertEntity> getCulverts(Integer userId) {
        return culvertRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<DamEntity> getDams(Integer userId) {
        return damRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<DeepWellsEntity> getDeepWells(Integer userId) {
        return deepWellsRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<DripIrrigationPipeEntity> getDripIrrigationPipe(Integer userId) {
        return dripIrrigationPipeRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<GreatWellsEntity> getGreatWells(Integer userId) {
        return greatWellsRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<HydropowerEntity> getHydropowers(Integer userId) {
        return hydropowerRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<PondEntity> getPonds(Integer userId) {
        return pondRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<PumpStationEntity> getPumpStations(Integer userId) {
        return pumpStationRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<SluiceEntity> getSluices(Integer userId) {
        return sluiceRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<WatercourseEntity> getWatercourses(Integer userId) {
        return watercourseRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }

    public List<WaterWorksEntity> getWaterWorks(Integer userId) {
        return waterworksRepository.findByIsDeleteAndTownEntityIn(0,userRepository.findById(userId).getTowns());
    }
}
