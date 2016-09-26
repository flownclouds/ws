package com.saltyfish.common.bean;

import com.saltyfish.domain.entity.unit.TownEntity;

/**
 * Created by weck on 16/9/25.
 */
public class ConservationSummary {
    private TownEntity town;
    private Integer pumpStationCounts;
    private Integer aqueductCounts;
    private Integer bridgeCounts;
    private Integer channelCounts;
    private Integer culvertCounts;
    private Integer damCounts;
    private Integer deepWellsCounts;
    private Integer dripIrrigationPipeCounts;
    private Integer greatWellsCounts;
    private Integer hydropowerCounts;
    private Integer pondCounts;
    private Integer sluiceCounts;
    private Integer watercourseCounts;
    private Integer waterWorksCounts;
    private Double pumpStationPower;
    private Double channelLength;
    private Double dripIrrigationPipeLength;
    private Double watercourseLength;
    private Double hydropowerElectircity;

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }

    public Integer getPumpStationCounts() {
        return pumpStationCounts;
    }

    public void setPumpStationCounts(Integer pumpStationCounts) {
        this.pumpStationCounts = pumpStationCounts;
    }

    public Integer getAqueductCounts() {
        return aqueductCounts;
    }

    public void setAqueductCounts(Integer aqueductCounts) {
        this.aqueductCounts = aqueductCounts;
    }

    public Integer getBridgeCounts() {
        return bridgeCounts;
    }

    public void setBridgeCounts(Integer bridgeCounts) {
        this.bridgeCounts = bridgeCounts;
    }

    public Integer getChannelCounts() {
        return channelCounts;
    }

    public void setChannelCounts(Integer channelCounts) {
        this.channelCounts = channelCounts;
    }

    public Integer getCulvertCounts() {
        return culvertCounts;
    }

    public void setCulvertCounts(Integer culvertCounts) {
        this.culvertCounts = culvertCounts;
    }

    public Integer getDamCounts() {
        return damCounts;
    }

    public void setDamCounts(Integer damCounts) {
        this.damCounts = damCounts;
    }

    public Integer getDeepWellsCounts() {
        return deepWellsCounts;
    }

    public void setDeepWellsCounts(Integer deepWellsCounts) {
        this.deepWellsCounts = deepWellsCounts;
    }

    public Integer getGreatWellsCounts() {
        return greatWellsCounts;
    }

    public void setGreatWellsCounts(Integer greatWellsCounts) {
        this.greatWellsCounts = greatWellsCounts;
    }

    public Integer getHydropowerCounts() {
        return hydropowerCounts;
    }

    public void setHydropowerCounts(Integer hydropowerCounts) {
        this.hydropowerCounts = hydropowerCounts;
    }

    public Integer getPondCounts() {
        return pondCounts;
    }

    public void setPondCounts(Integer pondCounts) {
        this.pondCounts = pondCounts;
    }

    public Integer getSluiceCounts() {
        return sluiceCounts;
    }

    public void setSluiceCounts(Integer sluiceCounts) {
        this.sluiceCounts = sluiceCounts;
    }

    public Integer getWatercourseCounts() {
        return watercourseCounts;
    }

    public void setWatercourseCounts(Integer watercourseCounts) {
        this.watercourseCounts = watercourseCounts;
    }

    public Integer getWaterWorksCounts() {
        return waterWorksCounts;
    }

    public void setWaterWorksCounts(Integer waterWorksCounts) {
        this.waterWorksCounts = waterWorksCounts;
    }

    public Double getPumpStationPower() {
        return pumpStationPower;
    }

    public void setPumpStationPower(Double pumpStationPower) {
        this.pumpStationPower = pumpStationPower;
    }

    public Double getChannelLength() {
        return channelLength;
    }

    public void setChannelLength(Double channelLength) {
        this.channelLength = channelLength;
    }

    public Double getWatercourseLength() {
        return watercourseLength;
    }

    public void setWatercourseLength(Double watercourseLength) {
        this.watercourseLength = watercourseLength;
    }

    public Double getHydropowerElectircity() {
        return hydropowerElectircity;
    }

    public void setHydropowerElectircity(Double hydropowerElectircity) {
        this.hydropowerElectircity = hydropowerElectircity;
    }

    public Integer getDripIrrigationPipeCounts() {
        return dripIrrigationPipeCounts;
    }

    public void setDripIrrigationPipeCounts(Integer dripIrrigationPipeCounts) {
        this.dripIrrigationPipeCounts = dripIrrigationPipeCounts;
    }

    public Double getDripIrrigationPipeLength() {
        return dripIrrigationPipeLength;
    }

    public void setDripIrrigationPipeLength(Double dripIrrigationPipeLength) {
        this.dripIrrigationPipeLength = dripIrrigationPipeLength;
    }
}
