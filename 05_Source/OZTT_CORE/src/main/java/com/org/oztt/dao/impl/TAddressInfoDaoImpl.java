package com.org.oztt.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.org.oztt.base.dao.BaseDao;
import com.org.oztt.dao.TAddressInfoDao;
import com.org.oztt.entity.TAddressInfo;

@Repository
public class TAddressInfoDaoImpl extends BaseDao implements TAddressInfoDao {

    @Override
    public int deleteByPrimaryKey(Long id) {
        return update("com.org.oztt.dao.TAddressInfoDao.deleteByPrimaryKey", id);
    }

    @Override
    public int insert(TAddressInfo record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int insertSelective(TAddressInfo record) {
        return insert("com.org.oztt.dao.TAddressInfoDao.insertSelective", record);
    }

    @Override
    public TAddressInfo selectByPrimaryKey(Long id) {
        return selectOne("com.org.oztt.dao.TAddressInfoDao.selectByPrimaryKey", id);
    }

    @Override
    public int updateByPrimaryKeySelective(TAddressInfo record) {
        return update("com.org.oztt.dao.TAddressInfoDao.updateByPrimaryKeySelective", record);
    }

    @Override
    public int updateByPrimaryKey(TAddressInfo record) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<TAddressInfo> getAllAddress(String customerno, String deliveryMethod) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("customerno", customerno);
        param.put("deliverymethod", deliveryMethod);
        return select("com.org.oztt.dao.TAddressInfoDao.getAllAddress", param);
    }

}
