package com.org.oztt.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.org.oztt.base.dao.BaseDao;
import com.org.oztt.base.page.Pagination;
import com.org.oztt.base.page.PagingResult;
import com.org.oztt.dao.TGoodsDao;
import com.org.oztt.entity.TGoods;

@Repository
public class TGoodsDaoImpl extends BaseDao implements TGoodsDao {

    public int deleteByPrimaryKey(Long no) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int insert(TGoods record) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int insertSelective(TGoods record) {
        // TODO Auto-generated method stub
        return 0;
    }

    public TGoods selectByPrimaryKey(Long no) {
        // TODO Auto-generated method stub
        return null;
    }

    public int updateByPrimaryKeySelective(TGoods record) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int updateByPrimaryKey(TGoods record) {
        // TODO Auto-generated method stub
        return 0;
    }

    public TGoods selectByGoodsId(String goodsId) {
        return selectOne("com.org.oztt.dao.TGoodsDao.selectByGoodsId", goodsId);
    }

    public List<TGoods> selectByParams(TGoods record) {
        return select("com.org.oztt.dao.TGoodsDao.selectByParams", record);
    }

    public List<TGoods> getFirstThreeNewArravail() {
        return select("com.org.oztt.dao.TGoodsDao.getFirstThreeNewArravail");
    }

    public List<TGoods> getAllNewArravail() {
        return select("com.org.oztt.dao.TGoodsDao.getAllNewArravail");
    }

    public List<TGoods> getGoodsListForMain(Map<String, String> map) {
        return select("com.org.oztt.dao.TGoodsDao.getGoodsListForMain", map);
    }

    public PagingResult<TGoods> getGoodsByParamForPage(Pagination pagination) {
        return selectPagination("com.org.oztt.dao.TGoodsDao.getGoodsByParamForPage",
                "com.org.oztt.dao.TGoodsDao.getGoodsByParamForPageCount", pagination);
    }

    @Override
    public List<TGoods> getFiveHotSeller(TGoods record) {
        return select("com.org.oztt.dao.TGoodsDao.getFiveHotSeller", record);
    }

    @Override
    public List<TGoods> getGoodsBySearchParam(String goodsParam) {
        return select("com.org.oztt.dao.TGoodsDao.getGoodsBySearchParam", goodsParam);
    }

}
