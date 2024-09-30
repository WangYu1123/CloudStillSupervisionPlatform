package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.util.Result;

public interface DeptService {
    Result getListForPage(int pno, int psize, Long pid, String name, String sortField, String sortType);

    void insertDept(Dept dept);

    Dept selectDeptById(Long id);

    void updateDeptById(Dept dept);

    void deleteDeptById(Long id);


    Result getDeptListByPid(Long pid);

}
