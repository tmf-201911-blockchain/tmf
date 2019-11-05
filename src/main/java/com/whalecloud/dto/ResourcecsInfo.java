package com.whalecloud.dto;

import java.util.Date;

/**
 * @author zhaoyanac
 * @date 2019/10/15
 */
public class ResourcecsInfo {
    private String resourceid;

    private String resourcename;

    private String resourcetype;

    private String towerid;

    private String owner;

    private String lessor;

    private String lessee;

    private Date effectivetime;

    private String nep;

    private String status;

    private String importance;

    private Double rate;

    private String latitude;

    private String longitude;

    private String areacode;

    private String address;

    private String path;

    public ResourcecsInfo() {
    }

    public ResourcecsInfo(String resourceid, String resourcename, String resourcetype, String towerid, String owner, String lessor, String lessee, Date effectivetime, String nep, String status, String importance, Double rate, String latitude, String longitude, String areacode, String address, String path) {
        this.resourceid = resourceid;
        this.resourcename = resourcename;
        this.resourcetype = resourcetype;
        this.towerid = towerid;
        this.owner = owner;
        this.lessor = lessor;
        this.lessee = lessee;
        this.effectivetime = effectivetime;
        this.nep = nep;
        this.status = status;
        this.importance = importance;
        this.rate = rate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.areacode = areacode;
        this.address = address;
        this.path = path;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getTowerid() {
        return towerid;
    }

    public void setTowerid(String towerid) {
        this.towerid = towerid;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLessor() {
        return lessor;
    }

    public void setLessor(String lessor) {
        this.lessor = lessor;
    }

    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public Date getEffectivetime() {
        return effectivetime;
    }

    public void setEffectivetime(Date effectivetime) {
        this.effectivetime = effectivetime;
    }

    public String getNep() {
        return nep;
    }

    public void setNep(String nep) {
        this.nep = nep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


