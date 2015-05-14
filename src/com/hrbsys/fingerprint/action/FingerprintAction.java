package com.hrbsys.fingerprint.action;

import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.bean.ZWLRMD;
import com.hrbsys.fingerprint.service.FingerprintService;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Li
 */
public class FingerprintAction
{
    private String sort;
    private String order;
    private int page;
    private int rows;
    private String operationType;
    private String fingerprintId1;
    private String fingerprintId2;
    private String[] studentNumbers;
    private String[] fingerprintListIds;
    private BANJI squad;
    private ZWLRLB fingerprintList;
    private ZWLRMD fingerprintRoster;
    private Xsxx studentInfo;
    private Map<String, Object> map;
    private List list;
    private FingerprintService fingerprintService;
    private Map<String, String> fileConfig;

    public String queryFingerprintList() throws IllegalArgumentException, IllegalAccessException
    {
        map = fingerprintService.queryFingerprintList(fingerprintList, sort, order, page, rows);
        return "map";
    }

    public String querySquad()
    {
        list = fingerprintService.querySquad();
        return "list";
    }

    public String queryStudent()
    {
        list = fingerprintService.queryStudent(squad);
        return "list";
    }

    public String queryFingerprintRoster()
    {
        list = fingerprintService.queryFingerprintRoster(fingerprintRoster);
        return "list";
    }

    public String saveOrUpdateFingerprintList()
    {
        map = fingerprintService.saveOrUpdateFingerprintList(operationType, fingerprintList, studentNumbers);
        return "map";
    }

    public String queryUnregisteredStudent()
    {
        map = fingerprintService.queryUnregisteredStudent(fingerprintList);
        return "map";
    }

    public String saveFingerprintRegister() throws IOException
    {
        map = fingerprintService.saveFingerprintRegister(fileConfig, studentInfo, fingerprintId1, fingerprintId2);
        return "map";
    }

    public String deleteFingerprintRegister()
    {
        map = fingerprintService.deleteFingerprintRegister(fileConfig, studentNumbers);
        return "map";
    }

    public String deleteFingerprintList()
    {
        map = fingerprintService.deleteFingerprintList(fingerprintListIds);
        return "map";
    }

    public void setFingerprintList(ZWLRLB fingerprintList) {
        this.fingerprintList = fingerprintList;
    }

    public ZWLRLB getFingerprintList() {
        return fingerprintList;
    }

    public void setFingerprintId1(String fingerprintId1) {
        this.fingerprintId1 = fingerprintId1;
    }

    public void setFingerprintId2(String fingerprintId2) {
        this.fingerprintId2 = fingerprintId2;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setStudentInfo(Xsxx studentInfo) {
        this.studentInfo = studentInfo;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setStudentNumbers(String[] studentNumbers) {
        this.studentNumbers = studentNumbers;
    }

    public void setFingerprintListIds(String[] fingerprintListIds) {
        this.fingerprintListIds = fingerprintListIds;
    }

    public void setSquad(BANJI squad) {
        this.squad = squad;
    }

    public void setFingerprintRoster(ZWLRMD fingerprintRoster) {
        this.fingerprintRoster = fingerprintRoster;
    }

    public void setFingerprintService(FingerprintService fingerprintService) {
        this.fingerprintService = fingerprintService;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public List getList() {
        return list;
    }

    public void setFileConfig(Map<String, String> fileConfig) {
        this.fileConfig = fileConfig;
    }
}