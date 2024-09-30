package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface AssetStatusService {
    Result getListForPage(int pno, int psize, String assetStatus, String sortField, String sortType);
}
