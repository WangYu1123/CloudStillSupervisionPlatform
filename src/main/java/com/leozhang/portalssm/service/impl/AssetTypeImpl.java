package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.entity.AssetTypeExample;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.entity.SexExample;
import com.leozhang.portalssm.mapper.AssetTypeMapper;
import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AssetTypeImpl implements AssetTypeService {

    @Autowired
    private AssetTypeMapper assetTypeMapper;

    @Override
    public Result getListForPage(int pno, int psize, String assetType, String sortField, String sortType) {
        //开启分⻚查询模式，⽤⻚号和每⻚多少条作为参数
        Page<AssetType> p = PageHelper.startPage(pno, psize);
        //使⽤条件查询对象，他可以模拟任何的where条件，以及排序等需要的查询
        AssetTypeExample assetTypeExample = new AssetTypeExample();
        //开启where条件对象
        AssetTypeExample.Criteria criteria = assetTypeExample.createCriteria();
        //判断当传⼊的sexName不为空的时候就拼接条件并且使⽤模糊查询
        if(assetType.trim().length()>0){
            criteria.andAssetTypeNameLike("%"+assetType+"%");
        }
        //判断排序字段不为空就加⼊排序依据 参数需要使⽤ChangeChar将驼峰字段转换为下划线字段
        //并且传⼊的原则是 (排序字段 排序⽅式)如(id desc)
        if(sortField.trim().length()>0){
            assetTypeExample.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<AssetType> list = assetTypeMapper.selectByExample(assetTypeExample);
        //返回对象格式固定第⼀个参数为状态码 200代表成功 500代表失败，
        //第⼆个参数代表返回的具体数据
        //第三个参数代表描述语⾔
        //第四个参数代表查询数据的总条数
        return Result.end(200,list,"查询成功",p.getTotal());
    }

    @Override
    public void insterAssetType(AssetType assetType) {
        assetTypeMapper.insert(assetType);
    }

    @Override
    public AssetType selectAssetTypeById(Long id) {
        return assetTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAssetType(AssetType assetType) {
        assetTypeMapper.updateByPrimaryKey(assetType);
    }

    @Override
    public void deleteAssetTypeById(Long id) {
        assetTypeMapper.deleteByPrimaryKey(id);
    }
}
