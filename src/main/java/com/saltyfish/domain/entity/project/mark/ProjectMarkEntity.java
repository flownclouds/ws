package com.saltyfish.domain.entity.project.mark;

import com.saltyfish.domain.entity.base.SuperBean;
import com.saltyfish.domain.entity.project.conservation.*;

import javax.persistence.*;

/**
 * Created by weck on 16/9/2.
 */
@Entity
@Table(name = "project_mark", catalog = "exciting")
public class ProjectMarkEntity extends SuperBean {
    private static final Long serialVersionUID = -3094094589759825424L;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "aqueduct_id", referencedColumnName = "id")
    private AqueductEntity aqueduct;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bridge_id", referencedColumnName = "id")
    private BridgeEntity bridge;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private ChannelEntity channel;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "culvert_id", referencedColumnName = "id")
    private CulvertEntity culvert;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dam_id", referencedColumnName = "id")
    private DamEntity dam;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "deep_wells_id", referencedColumnName = "id")
    private DeepWellsEntity deepWells;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "drip_irrigation_pipe_id", referencedColumnName = "id")
    private DripIrrigationPipeEntity dripIrrigationPipe;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "great_wells_id", referencedColumnName = "id")
    private GreatWellsEntity greatWells;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "hydropower_id", referencedColumnName = "id")
    private HydropowerEntity hydropower;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pond_id", referencedColumnName = "id")
    private PondEntity pond;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pump_station_id", referencedColumnName = "id")
    private PumpStationEntity pumpStation;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sluice_id", referencedColumnName = "id")
    private SluiceEntity sluice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "watercourse_id", referencedColumnName = "id")
    private WatercourseEntity watercourse;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "water_works_id", referencedColumnName = "id")
    private WaterWorksEntity waterWorks;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public AqueductEntity getAqueduct() {
        return aqueduct;
    }

    public void setAqueduct(AqueductEntity aqueduct) {
        this.aqueduct = aqueduct;
    }

    public BridgeEntity getBridge() {
        return bridge;
    }

    public void setBridge(BridgeEntity bridge) {
        this.bridge = bridge;
    }

    public ChannelEntity getChannel() {
        return channel;
    }

    public void setChannel(ChannelEntity channel) {
        this.channel = channel;
    }

    public CulvertEntity getCulvert() {
        return culvert;
    }

    public void setCulvert(CulvertEntity culvert) {
        this.culvert = culvert;
    }

    public DamEntity getDam() {
        return dam;
    }

    public void setDam(DamEntity dam) {
        this.dam = dam;
    }

    public DeepWellsEntity getDeepWells() {
        return deepWells;
    }

    public void setDeepWells(DeepWellsEntity deepWells) {
        this.deepWells = deepWells;
    }

    public DripIrrigationPipeEntity getDripIrrigationPipe() {
        return dripIrrigationPipe;
    }

    public void setDripIrrigationPipe(DripIrrigationPipeEntity dripIrrigationPipe) {
        this.dripIrrigationPipe = dripIrrigationPipe;
    }

    public GreatWellsEntity getGreatWells() {
        return greatWells;
    }

    public void setGreatWells(GreatWellsEntity greatWells) {
        this.greatWells = greatWells;
    }

    public HydropowerEntity getHydropower() {
        return hydropower;
    }

    public void setHydropower(HydropowerEntity hydropower) {
        this.hydropower = hydropower;
    }

    public PondEntity getPond() {
        return pond;
    }

    public void setPond(PondEntity pond) {
        this.pond = pond;
    }

    public PumpStationEntity getPumpStation() {
        return pumpStation;
    }

    public void setPumpStation(PumpStationEntity pumpStation) {
        this.pumpStation = pumpStation;
    }

    public SluiceEntity getSluice() {
        return sluice;
    }

    public void setSluice(SluiceEntity sluice) {
        this.sluice = sluice;
    }

    public WatercourseEntity getWatercourse() {
        return watercourse;
    }

    public void setWatercourse(WatercourseEntity watercourse) {
        this.watercourse = watercourse;
    }

    public WaterWorksEntity getWaterWorks() {
        return waterWorks;
    }

    public void setWaterWorks(WaterWorksEntity waterWorks) {
        this.waterWorks = waterWorks;
    }
}
