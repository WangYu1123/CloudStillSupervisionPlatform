package com.leozhang.portalssm.entity;

public class RoomWarn {
    private Long id;

    private Integer maxDisk;

    private Integer maxTemperature;

    private Integer maxRequest;

    private Integer maxCpuUse;

    private Long roomId;

    private Integer lowPower;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxDisk() {
        return maxDisk;
    }

    public void setMaxDisk(Integer maxDisk) {
        this.maxDisk = maxDisk;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Integer maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Integer getMaxRequest() {
        return maxRequest;
    }

    public void setMaxRequest(Integer maxRequest) {
        this.maxRequest = maxRequest;
    }

    public Integer getMaxCpuUse() {
        return maxCpuUse;
    }

    public void setMaxCpuUse(Integer maxCpuUse) {
        this.maxCpuUse = maxCpuUse;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getLowPower() {
        return lowPower;
    }

    public void setLowPower(Integer lowPower) {
        this.lowPower = lowPower;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", maxDisk=").append(maxDisk);
        sb.append(", maxTemperature=").append(maxTemperature);
        sb.append(", maxRequest=").append(maxRequest);
        sb.append(", maxCpuUse=").append(maxCpuUse);
        sb.append(", roomId=").append(roomId);
        sb.append(", lowPower=").append(lowPower);
        sb.append("]");
        return sb.toString();
    }
}