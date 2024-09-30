package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.util.Result;

public interface AssetTypeService {
    Result getListForPage(int pno, int psize, String assetType, String sortField, String sortType);

    void insterAssetType(AssetType assetType);


    AssetType selectAssetTypeById(Long id);

    void updateAssetType(AssetType assetType);

    void deleteAssetTypeById(Long id);



}
