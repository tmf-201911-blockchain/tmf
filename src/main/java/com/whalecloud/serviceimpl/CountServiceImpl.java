package com.whalecloud.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.mapper.re.CountMapper;
import com.whalecloud.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(rollbackFor = Exception.class)
public class CountServiceImpl implements CountService {

    @Autowired
    CountMapper countMapper;

    /**
     * 查询总的基站数量
     *
     * @return
     */
    @Override
    public int getTotalCount(String resourceType) {
        return countMapper.getTotalCount(resourceType);
    }

    /**
     * 查询联通的基站数量
     *
     * @return
     */
    @Override
    public int getUnicomCount(String resourceType) {
        return countMapper.getUnicomCount(resourceType);
    }

    /**
     * 查询电信的基站数量
     *
     * @return
     */
    @Override
    public int getTelecomCount(String resourceType) {
        return countMapper.getTelecomCount(resourceType);
    }

    //联通使用中
    @Override
    public int getUnicomUsing(String resourceType) {
        return countMapper.getUnicomUsing(resourceType);
    }

    //联通建设中
    @Override
    public int getUnicomBuilding(String resourceType) {
        return countMapper.getUnicomBuilding(resourceType);
    }

    //联通规划中
    @Override
    public int getUnicomPlaning(String resourceType) {
        return countMapper.getUnicomPlaning(resourceType);
    }

    //联通维护中
    @Override
    public int getUnicomMaintenance(String resourceType) {
        return countMapper.getUnicomMaintenance(resourceType);
    }

    //电信使用中
    @Override
    public int getTelecomUsing(String resourceType) {
        return countMapper.getTelecomUsing(resourceType);
    }

    //电信建设中
    @Override
    public int getTelecomBuilding(String resourceType) {
        return countMapper.getTelecomBuilding(resourceType);
    }

    //电信规划中
    @Override
    public int getTelecomPlaning(String resourceType) {
        return countMapper.getTelecomPlaning(resourceType);
    }

    //电信维护中
    @Override
    public int getTelecomMaintenance(String resourceType) {
        return countMapper.getTelecomMaintenance(resourceType);
    }

    //联通租入
    @Override
    public int getUnicomRentIn(String resourceType) {
        return countMapper.getUnicomRentIn(resourceType);
    }

    //联通租出
    @Override
    public int getUnicomRentOut(String resourceType) {
        return countMapper.getUnicomRentOut(resourceType);
    }

    //电信租入
    @Override
    public int getTelecomRentIn(String resourceType) {
        return countMapper.getTelecomRentIn(resourceType);
    }

    //电信租出
    @Override
    public int getTelecomRentOut(String resourceType) {
        return countMapper.getTelecomRentOut(resourceType);
    }


    //联通基站租赁走势统计
    @Override
    public List<Integer> getUnicomRentMonth(String resourceType) {

        List<Integer> list = new ArrayList<>();
        //一月份租赁总数
        int rentUnicomInJan = countMapper.getUnicomRentInJanuary(resourceType);
        //二月份租赁总数
        int rentUnicomInFeb = countMapper.getUnicomRentInFebruary(resourceType);
        //三月份租赁总数
        int rentUnicomInMar = countMapper.getUnicomRentInMarch(resourceType);
        //四月份租赁总数
        int rentUnicomInApr = countMapper.getUnicomRentInApril(resourceType);
        //五月份租赁总数
        int rentUnicomInMay = countMapper.getUnicomRentInMay(resourceType);
        //六月份租赁总数
        int rentUnicomInJun = countMapper.getUnicomRentInJune(resourceType);
        //七月份租赁总数
        int rentUnicomInJul = countMapper.getUnicomRentInJuly(resourceType);
        //八月份租赁总数
        int rentUnicomInAug = countMapper.getUnicomRentInAugust(resourceType);
        //九月份租赁总数
        int rentUnicomInSep = countMapper.getUnicomRentInSeptember(resourceType);
        //10月份租赁总数
        int rentUnicomInOtc = countMapper.getUnicomRentInOctober(resourceType);
        //11月份租赁总数
        int rentUnicomInNov = countMapper.getUnicomRentInNovember(resourceType);
        //12月份租赁总数
        int rentUnicomInDec = countMapper.getUnicomRentInDecember(resourceType);
        list.add(rentUnicomInJan);
        list.add(rentUnicomInFeb);
        list.add(rentUnicomInMar);
        list.add(rentUnicomInApr);
        list.add(rentUnicomInMay);
        list.add(rentUnicomInJun);
        list.add(rentUnicomInJul);
        list.add(rentUnicomInAug);
        list.add(rentUnicomInSep);
        list.add(rentUnicomInOtc);
        list.add(rentUnicomInNov);
        list.add(rentUnicomInDec);

        return list;
    }

    //电信基站租赁走势统计
    @Override
    public List<Integer> getTelecomRentMonth(String resourceType) {

        List<Integer> list = new ArrayList<>();
        //一月份租赁总数
        int rentTelecomInJan = countMapper.getTelecomRentInJanuary(resourceType);
        //二月份租赁总数countMapper.
        int rentTelecomInFeb = countMapper.getTelecomRentInFebruary(resourceType);
        //三月份租赁总数countMapper.
        int rentTelecomInMar = countMapper.getTelecomRentInMarch(resourceType);
        //四月份租赁总数countMapper.
        int rentTelecomInApr = countMapper.getTelecomRentInApril(resourceType);
        //五月份租赁总数countMapper.
        int rentTelecomInMay = countMapper.getTelecomRentInMay(resourceType);
        //六月份租赁总数countMapper.
        int rentTelecomInJun = countMapper.getTelecomRentInJune(resourceType);
        //七月份租赁总数countMapper.
        int rentTelecomInJul = countMapper.getTelecomRentInJuly(resourceType);
        //八月份租赁总数countMapper.
        int rentTelecomInAug = countMapper.getTelecomRentInAugust(resourceType);
        //九月份租赁总数countMapper.
        int rentTelecomInSep = countMapper.getTelecomRentInSeptember(resourceType);
        //10月份租赁总数countMapper.
        int rentTelecomInOtc = countMapper.getTelecomRentInOctober(resourceType);
        //11月份租赁总数countMapper.
        int rentTelecomInNov = countMapper.getTelecomRentInNovember(resourceType);
        //12月份租赁总数countMapper.
        int rentTelecomInDec = countMapper.getTelecomRentInDecember(resourceType);

        list.add(rentTelecomInJan);
        list.add(rentTelecomInFeb);
        list.add(rentTelecomInMar);
        list.add(rentTelecomInApr);
        list.add(rentTelecomInMay);
        list.add(rentTelecomInJun);
        list.add(rentTelecomInJul);
        list.add(rentTelecomInAug);
        list.add(rentTelecomInSep);
        list.add(rentTelecomInOtc);
        list.add(rentTelecomInNov);
        list.add(rentTelecomInDec);

        return list;
    }


    //根据条件查询联通租入租出详情
    @Override
    public QueryResult<Progress> getUnicomRentDetailByCondition(String resourceName, String leaseStatus, String status,String resourceType,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Progress> unicomRentDetailByCondition = countMapper.getUnicomRentDetailByCondition(resourceName, leaseStatus, status,resourceType);
        List<Progress> result = unicomRentDetailByCondition.getResult();
        long total = unicomRentDetailByCondition.getTotal();
        int pages = unicomRentDetailByCondition.getPages();

        if (result == null) {
            return new QueryResult<Progress>();
        }
        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);

        return queryResult;
    }


    //根据条件查询电信租入租出详情
    @Override
    public QueryResult<Progress> getTelecomRentDetailByCondition(String resourceName, String leaseStatus, String status,String resourceType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Progress> telecomRentDetailByCondition = countMapper.getTelecomRentDetailByCondition(resourceName, leaseStatus, status,resourceType);
        List<Progress> result = telecomRentDetailByCondition.getResult();
        long total = telecomRentDetailByCondition.getTotal();
        int pages = telecomRentDetailByCondition.getPages();

        if (result == null) {
            return new QueryResult<Progress>();
        }
        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);

        return queryResult;
    }

    @Override
    public List<Map<String, Object>> getMapCount(String resourceType) {
        List<Map<String, Object>> list=new ArrayList<>();

        int dc = countMapper.getDC(resourceType);
        int dcUse = countMapper.getDCUse(resourceType);
        int dcBuild = countMapper.getDCBuild(resourceType);
        int dcPlan = countMapper.getDCPlan(resourceType);
        int dcMaintain = countMapper.getDCMaintain(resourceType);

        Map<String, Object> dcMap=new HashMap<>();
        dcMap.put("name","东城区");
        dcMap.put("value",dc);
        dcMap.put("use",dcUse);
        dcMap.put("build",dcBuild);
        dcMap.put("plan",dcPlan);
        dcMap.put("maintain",dcMaintain);
        dcMap.put("lng",116.42272);
        dcMap.put("lat",39.934579);


        int yq = countMapper.getYQ(resourceType);
        int yqUse = countMapper.getYQUse(resourceType);
        int yqBuild = countMapper.getYQBuild(resourceType);
        int yqPlan = countMapper.getYQPlan(resourceType);
        int yqMaintain = countMapper.getYQMaintain(resourceType);

        Map<String, Object> yqMap=new HashMap<>();
        yqMap.put("name","延庆区");
        yqMap.put("value",yq);
        yqMap.put("use",yqUse);
        yqMap.put("build",yqBuild);
        yqMap.put("plan",yqPlan);
        yqMap.put("maintain",yqMaintain);
        yqMap.put("lng",115.981186);
        yqMap.put("lat",40.462706);


        int hr = countMapper.getHR(resourceType);
        int hrUse = countMapper.getHRUse(resourceType);
        int hrBuild = countMapper.getHRBuild(resourceType);
        int hrPlan = countMapper.getHRPlan(resourceType);
        int hrMaintain = countMapper.getHRMaintain(resourceType);

        Map<String, Object> hrMap=new HashMap<>();
        hrMap.put("name","怀柔区");
        hrMap.put("value",hr);
        hrMap.put("use",hrUse);
        hrMap.put("build",hrBuild);
        hrMap.put("plan",hrPlan);
        hrMap.put("maintain",hrMaintain);
        hrMap.put("lng",116.63853);
        hrMap.put("lat",40.322563);


        int my = countMapper.getMY(resourceType);
        int myUse = countMapper.getMYUse(resourceType);
        int myBuild = countMapper.getMYBuild(resourceType);
        int myPlan = countMapper.getMYPlan(resourceType);
        int myMaintain = countMapper.getMYMaintain(resourceType);

        Map<String, Object> myMap=new HashMap<>();
        myMap.put("name","密云区");
        myMap.put("value",my);
        myMap.put("use",myUse);
        myMap.put("build",myBuild);
        myMap.put("plan",myPlan);
        myMap.put("maintain",myMaintain);
        myMap.put("lng",116.849551);
        myMap.put("lat",40.382999);


        int cp = countMapper.getCP(resourceType);
        int cpUse = countMapper.getCPUse(resourceType);
        int cpBuild = countMapper.getCPBuild(resourceType);
        int cpPlan = countMapper.getCPPlan(resourceType);
        int cpMaintain = countMapper.getCPMaintain(resourceType);

        Map<String, Object> cpMap=new HashMap<>();
        cpMap.put("name","昌平区");
        cpMap.put("value",cp);
        cpMap.put("use",cpUse);
        cpMap.put("build",cpBuild);
        cpMap.put("plan",cpPlan);
        cpMap.put("maintain",cpMaintain);
        cpMap.put("lng",116.237832);
        cpMap.put("lat",40.226854);


        int sy = countMapper.getSY(resourceType);
        int syUse = countMapper.getSYUse(resourceType);
        int syBuild = countMapper.getSYBuild(resourceType);
        int syPlan = countMapper.getSYPlan(resourceType);
        int syMaintain = countMapper.getSYMaintain(resourceType);

        Map<String, Object> syMap=new HashMap<>();
        syMap.put("name","顺义区");
        syMap.put("value",sy);
        syMap.put("use",syUse);
        syMap.put("build",syBuild);
        syMap.put("plan",syPlan);
        syMap.put("maintain",syMaintain);
        syMap.put("lng",116.663242);
        syMap.put("lat",40.1362);


        int pg = countMapper.getPG(resourceType);
        int pgUse = countMapper.getPGUse(resourceType);
        int pgBuild = countMapper.getPGBuild(resourceType);
        int pgPlan = countMapper.getPGPlan(resourceType);
        int pgMaintain = countMapper.getPGMaintain(resourceType);

        Map<String, Object> pgMap=new HashMap<>();
        pgMap.put("name","平谷区");
        pgMap.put("value",pg);
        pgMap.put("use",pgUse);
        pgMap.put("build",pgBuild);
        pgMap.put("plan",pgPlan);
        pgMap.put("maintain",pgMaintain);
        pgMap.put("lng",117.128025);
        pgMap.put("lat",40.147115);


        int mtg = countMapper.getMTG(resourceType);
        int mtgUse = countMapper.getMTGUse(resourceType);
        int mtgBuild = countMapper.getMTGBuild(resourceType);
        int mtgPlan = countMapper.getMTGPlan(resourceType);
        int mtgMaintain = countMapper.getMTGMaintain(resourceType);

        Map<String, Object> mtgMap=new HashMap<>();
        mtgMap.put("name","门头沟区");
        mtgMap.put("value",mtg);
        mtgMap.put("use",mtgUse);
        mtgMap.put("build",mtgBuild);
        mtgMap.put("plan",mtgPlan);
        mtgMap.put("maintain",mtgMaintain);
        mtgMap.put("lng",116.108179);
        mtgMap.put("lat",39.94648);


        int hd = countMapper.getHD(resourceType);
        int hdUse = countMapper.getHDUse(resourceType);
        int hdBuild = countMapper.getHDBuild(resourceType);
        int hdPlan = countMapper.getHDPlan(resourceType);
        int hdMaintain = countMapper.getHDMaintain(resourceType);

        Map<String, Object> hdMap=new HashMap<>();
        hdMap.put("name","海淀区");
        hdMap.put("value",hd);
        hdMap.put("use",hdUse);
        hdMap.put("build",hdBuild);
        hdMap.put("plan",hdPlan);
        hdMap.put("maintain",hdMaintain);
        hdMap.put("lng",116.304872);
        hdMap.put("lat",39.96553);


        int sjs = countMapper.getSJS(resourceType);
        int sjsUse = countMapper.getSJSUse(resourceType);
        int sjsBuild = countMapper.getSJSBuild(resourceType);
        int sjsPlan = countMapper.getSJSPlan(resourceType);
        int sjsMaintain = countMapper.getSJSMaintain(resourceType);

        Map<String, Object> sjsMap=new HashMap<>();
        sjsMap.put("name","石景山区");
        sjsMap.put("value",sjs);
        sjsMap.put("use",sjsUse);
        sjsMap.put("build",sjsBuild);
        sjsMap.put("plan",sjsPlan);
        sjsMap.put("maintain",sjsMaintain);
        sjsMap.put("lng",116.229612);
        sjsMap.put("lat",39.912017);


        int xc = countMapper.getXC(resourceType);
        int xcUse = countMapper.getXCUse(resourceType);
        int xcBuild = countMapper.getXCBuild(resourceType);
        int xcPlan = countMapper.getXCPlan(resourceType);
        int xcMaintain = countMapper.getXCMaintain(resourceType);

        Map<String, Object> xcMap=new HashMap<>();
        xcMap.put("name","西城区");
        xcMap.put("value",xc);
        xcMap.put("use",xcUse);
        xcMap.put("build",xcBuild);
        xcMap.put("plan",xcPlan);
        xcMap.put("maintain",xcMaintain);
        xcMap.put("lng",116.372397);
        xcMap.put("lat",39.918561);


        int cy = countMapper.getCY(resourceType);
        int cyUse = countMapper.getCYUse(resourceType);
        int cyBuild = countMapper.getCYBuild(resourceType);
        int cyPlan = countMapper.getCYPlan(resourceType);
        int cyMaintain = countMapper.getCYMaintain(resourceType);

        Map<String, Object> cyMap=new HashMap<>();
        cyMap.put("name","朝阳区");
        cyMap.put("value",cy);
        cyMap.put("use",cyUse);
        cyMap.put("build",cyBuild);
        cyMap.put("plan",cyPlan);
        cyMap.put("maintain",cyMaintain);
        cyMap.put("lng",116.449767);
        cyMap.put("lat",39.927254);


        int tz = countMapper.getTZ(resourceType);
        int tzUse = countMapper.getTZUse(resourceType);
        int tzBuild = countMapper.getTZBuild(resourceType);
        int tzPlan = countMapper.getTZPlan(resourceType);
        int tzMaintain = countMapper.getTZMaintain(resourceType);

        Map<String, Object> tzMap=new HashMap<>();
        tzMap.put("name","通州区");
        tzMap.put("value",tz);
        tzMap.put("use",tzUse);
        tzMap.put("build",tzBuild);
        tzMap.put("plan",tzPlan);
        tzMap.put("maintain",tzMaintain);
        tzMap.put("lng",116.662928);
        tzMap.put("lat",39.917001);


        int dx = countMapper.getDX(resourceType);
        int dxUse = countMapper.getDXUse(resourceType);
        int dxBuild = countMapper.getDXBuild(resourceType);
        int dxPlan = countMapper.getDXPlan(resourceType);
        int dxMaintain = countMapper.getDXMaintain(resourceType);

        Map<String, Object> dxMap=new HashMap<>();
        dxMap.put("name","大兴区");
        dxMap.put("value",dx);
        dxMap.put("use",dxUse);
        dxMap.put("build",dxBuild);
        dxMap.put("plan",dxPlan);
        dxMap.put("maintain",dxMaintain);
        dxMap.put("lng",116.348053);
        dxMap.put("lat",39.732833);


        int fs = countMapper.getFS(resourceType);
        int fsUse = countMapper.getFSUse(resourceType);
        int fsBuild = countMapper.getFSBuild(resourceType);
        int fsPlan = countMapper.getFSPlan(resourceType);
        int fsMaintain = countMapper.getFSMaintain(resourceType);

        Map<String, Object> fsMap=new HashMap<>();
        fsMap.put("name","房山区");
        fsMap.put("value",fs);
        fsMap.put("use",fsUse);
        fsMap.put("build",fsBuild);
        fsMap.put("plan",fsPlan);
        fsMap.put("maintain",fsMaintain);
        fsMap.put("lng",116.149892);
        fsMap.put("lat",39.755039);


        int ft = countMapper.getFT(resourceType);
        int ftUse = countMapper.getFTUse(resourceType);
        int ftBuild = countMapper.getFTBuild(resourceType);
        int ftPlan = countMapper.getFTPlan(resourceType);
        int ftMaintain = countMapper.getFTMaintain(resourceType);

        Map<String, Object> ftMap=new HashMap<>();
        ftMap.put("name","丰台区");
        ftMap.put("value",ft);
        ftMap.put("use",ftUse);
        ftMap.put("build",ftBuild);
        ftMap.put("plan",ftPlan);
        ftMap.put("maintain",ftMaintain);
        ftMap.put("lng",116.293105);
        ftMap.put("lat",39.865042);

        list.add(dcMap);
        list.add(yqMap);
        list.add(hrMap);
        list.add(myMap);
        list.add(cpMap);
        list.add(syMap);
        list.add(pgMap);
        list.add(mtgMap);
        list.add(hdMap);
        list.add(sjsMap);
        list.add(xcMap);
        list.add(cyMap);
        list.add(tzMap);
        list.add(dxMap);
        list.add(fsMap);
        list.add(ftMap);

        return list;
    }

    @Override
    public List<Map<String, Integer>> getCountByResourceType(String resourceType) {
        List<Map<String, Integer>> list=new ArrayList<>();
        Map<String, Integer> countMap=new HashMap<>();
        int totalCount = countMapper.getTotalCount(resourceType);
        int unicomCount = countMapper.getUnicomCount(resourceType);
        int telecomCount = countMapper.getTelecomCount(resourceType);

        countMap.put("totalCount",totalCount);
        countMap.put("unicomCount",unicomCount);
        countMap.put("telecomCount",telecomCount);

        list.add(countMap);
        return list;
    }

}
