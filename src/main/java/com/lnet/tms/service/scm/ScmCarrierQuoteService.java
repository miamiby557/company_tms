package com.lnet.tms.service.scm;

import com.lnet.tms.common.ImportResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.dao.base.BaseExacctDao;
import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.scm.ScmCarrierDao;
import com.lnet.tms.dao.scm.ScmCarrierLineDao;
import com.lnet.tms.dao.scm.ScmCarrierQuoteDao;
import com.lnet.tms.model.base.BaseExacct;
import com.lnet.tms.model.base.BaseRegion;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.scm.ScmCarrierLine;
import com.lnet.tms.model.scm.ScmCarrierQuote;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.ExcelUtils;
import com.lnet.tms.utility.StringUtils;
import com.lnet.tms.web.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ScmCarrierQuoteService extends CrudService<ScmCarrierQuote,UUID,ScmCarrierQuoteDao>{
    @Autowired
    public void setBaseDao(ScmCarrierQuoteDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private ScmCarrierLineDao scmCarrierLineDao;

    @Autowired
    private ScmCarrierLineService scmCarrierLineService;

    @Autowired
    private BaseRegionDao baseRegionDao;

    @Autowired
    private BaseExacctDao baseExacctDao;

    @Autowired
    private FileManager fileManager;

    @Autowired
    private ScmCarrierDao scmCarrierDao;

    @Transactional
    public List<ImportResult> readExcel(MultipartFile file)throws IOException {
        //SysFile sysFile =  fileManager.upload(file);
        ExcelUtils.Data data=  ExcelUtils.readExcel(file.getInputStream(),0);
        List<Map<String,Object>> mapList=data.getRows();

        List<ScmCarrierQuote>quotes=new ArrayList<>();
        List<ImportResult>results=new ArrayList<>();
        Integer rowsNum=data.getRowsNum();

        //判断模板是否正确
        List<ExcelUtils.Column>columns=data.getColumns();
        boolean template=true;
        if(columns!=null && columns.size()==12) {
            for (ExcelUtils.Column column : columns) {
                String title = column.getTitle();
                if (!("承运商编码".equals(title) || "始发城市".equals(title) || "目的城市".equals(title) || "运输方式".equals(title) || "计费方式".equals(title) || "费用科目".equals(title) ||
                        "最低收费".equals(title) || "最小区间".equals(title) || "最大区间".equals(title) || "单价".equals(title) || "时效".equals(title) || "备注".equals(title))) {
                    template=false;
                }
            }
        }else{
            template=false;
        }
        if(!template){
            ImportResult importResult=new ImportResult();
            importResult.setRowIndex(-1);
            importResult.setImportResult("异常");
            importResult.setFailReason("模板错误！");
            results.add(importResult);
            return results;
        }
        //判断是否填入数据
        if(rowsNum>0){
            for(int i=0;i<rowsNum;i++){
                ScmCarrierLine line=new ScmCarrierLine();
                ScmCarrierQuote quote=new ScmCarrierQuote();
                quote.setScmCarrierLine(line);
                quotes.add(quote);

                ImportResult importResult=new ImportResult();
                importResult.setRowIndex(i);
                importResult.setImportResult("成功");
                importResult.setFailReason("");
                results.add(importResult);
            }
        }else{
            ImportResult importResult=new ImportResult();
            importResult.setRowIndex(0);
            importResult.setImportResult("异常");
            importResult.setFailReason("无数据！");
            results.add(importResult);
            return results;
        }
        for(Map<String, Object> map:mapList){
            int index = 0;
            for(Map.Entry<String,Object>entry:map.entrySet()){
                if("rowIndex".equals(entry.getKey().trim())){
                    index=Integer.parseInt(String.valueOf(entry.getValue()).trim())-1;
                }
            }
            for(Map.Entry<String,Object>entry:map.entrySet()){
                String key=entry.getKey().trim();
                String value="";
                if (!StringUtils.isEmpty(entry.getValue())) {
                    value=entry.getValue().toString().trim();
                    if(value.matches("\\d+\\.0")){
                        value=value.split("\\.")[0];//整数读入时会以.0结尾
                    }
                }
                if("rowIndex".equals(key)){
                    continue;
                }else if("承运商编码".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"承运商编码不能为空；");
                    }else{
                        ScmCarrier scmCarrier= scmCarrierDao.getByField("code",value);
                        if(scmCarrier!=null){
                            quotes.get(index).getScmCarrierLine().setCarrierId(scmCarrier.getCarrierId());
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"承运商编码不存在；");
                        }
                    }
                }else if("始发城市".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"始发城市不能为空；");
                    }else {
                        BaseRegion baseRegion = baseRegionDao.getByField("name", value);
                        if (baseRegion != null) {
                            quotes.get(index).getScmCarrierLine().setStartCity(value);
                            quotes.get(index).getScmCarrierLine().setStartCityId(baseRegion.getRegionId());
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"始发城市名称不正确；");
                        }
                    }
                }else if("目的城市".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"目的城市不能为空；");
                    }else {
                        BaseRegion baseRegion = baseRegionDao.getByField("name", value);
                        if (baseRegion != null) {
                            quotes.get(index).getScmCarrierLine().setDestCity(value);
                            quotes.get(index).getScmCarrierLine().setDestCityId(baseRegion.getRegionId());
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"目的城市名称不正确；");
                        }
                    }
                }else if("运输方式".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"运输方式不能为空；");
                    }else {
                        if(!value.matches("\\d+(\\.0)?")){
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"运输方式请填写对应数值；");
                        }else{
                            Integer transportType = (int) Double.parseDouble(value);
                            if(transportType>9 || transportType<1){
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason()+"运输方式不存在的数值；");
                            }else{
                                quotes.get(index).getScmCarrierLine().setTransportType(transportType);
                            }
                        }
                    }
                }else if("计费方式".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"计费方式不能为空；");
                    }else {
                        if(!value.matches("\\d+(\\.0)?")){
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"计费方式请填写对应数值；");
                        }else{
                            Integer calculateType = (int) Double.parseDouble(value);
                            if(calculateType>7 || calculateType<1){
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason()+"计费方式不存在的数值；");
                            }else{
                                quotes.get(index).setCalculateType(calculateType);
                            }
                        }
                    }
                }else if("费用科目".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"费用科目不能为空；");
                    }else {
                        if (!value.contains("应付_")) {
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"费用科目不正确；");
                        }else{
                            BaseExacct baseExacct = baseExacctDao.getByField("name", value);
                            if (baseExacct != null) {
                                quotes.get(index).setExacctId(baseExacct.getExacctId());
                            }else{
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason()+"费用科目不正确；");
                            }
                        }
                    }
                }else if("最低收费".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"最低收费不能为空；");
                    }else {
                        if (value.matches("(\\d+)(\\.\\d+)?")) {
                            quotes.get(index).setMinimumFee(Double.parseDouble(value));
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"最低收费格式不正确；");
                        }
                    }
                }else if("最小区间".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"最小区间不能为空；");
                    }else {
                        if (value.matches("(\\d+)(\\.\\d+)?")) {
                            quotes.get(index).setMinimumCondiction(Double.parseDouble(value));
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"最小区间格式不正确；");
                        }
                    }
                }else if("最大区间".equals(key)){
                    if(!StringUtils.isEmpty(value)) {
                        if(value.matches("(\\d+)(\\.\\d+)?")){
                            Double minimumCondition=quotes.get(index).getMinimumCondiction();
                            Double maxmumCondition=Double.parseDouble(value);
                            if(minimumCondition!=null && minimumCondition.compareTo(maxmumCondition)>=0){
                                results.get(index).setImportResult("失败");
                                results.get(index).setFailReason(results.get(index).getFailReason()+"最大区间须大于最小区间；");
                            }else{
                                quotes.get(index).setMaxmumCondiction(maxmumCondition);
                            }
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"最大区间格式不正确；");
                        }
                    }
                }else if("单价".equals(key)){
                    if(!StringUtils.isEmpty(value)) {
                        if(value.matches("(\\d+)(\\.\\d+)?")){
                            quotes.get(index).setUnitPrice(Double.parseDouble(value));
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"单价格式不正确；");
                        }
                    }
                }else if("时效".equals(key)){
                    if(StringUtils.isEmpty(value)){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"时效不能为空；");
                    }else {
                        if(value.matches("[1-9]\\d*D\\d*")){
                            String[]arr=value.split("D");
                            Double timeLine=0.0;
                            if(arr.length==1){
                                timeLine=Double.parseDouble(arr[0])*24;
                            }else if(arr.length==2){
                                if(Double.parseDouble(arr[1])>23){
                                    results.get(index).setImportResult("失败");
                                    results.get(index).setFailReason(results.get(index).getFailReason()+"时效中小时数不能超过23；");
                                }
                                timeLine=Double.parseDouble(arr[0])*24+Double.parseDouble(arr[1]);
                            }
                            quotes.get(index).getScmCarrierLine().setTimeline(timeLine);
                        }else{
                            results.get(index).setImportResult("失败");
                            results.get(index).setFailReason(results.get(index).getFailReason()+"时效格式错误；");
                        }
                    }
                }else if("备注".equals(key)){
                    if(!StringUtils.isEmpty(value) && value.length()>250){
                        results.get(index).setImportResult("失败");
                        results.get(index).setFailReason(results.get(index).getFailReason()+"备注长度不超过250；");
                    }else{
                        quotes.get(index).setRemark(value);
                    }
                }
            }
        }
        for(int i =0;i<results.size();i++){
            ImportResult result=results.get(i);
            if("成功".equals(result.getImportResult())){
                ScmCarrierQuote quote=quotes.get(i);
                quote.setIsActive(true);
                JsonResult jsonResult=this.createScmCarrier(quote);
                if(!jsonResult.getSuccess()){
                    result.setImportResult("失败");
                    result.setFailReason(jsonResult.getMessage());
                }
            }
        }
        return results;
    }

    private boolean isInRange(Double targerMinValue,Double targetMaxValue, Double minValue, Double maxValue) {
        if(minValue==null) minValue = 0.0;
        if(targetMaxValue==null){
            if(maxValue==null){
                return true;
            }else{
                return targerMinValue<maxValue;
            }
        }else{
            if (maxValue == null){
                return targetMaxValue>minValue;
            }else{
                if(targetMaxValue>maxValue){
                    return targerMinValue<maxValue;
                }else if(targetMaxValue<maxValue){
                    return targetMaxValue>minValue;
                }else{
                    return true;
                }
            }
        }
    }

    private boolean existRange(ScmCarrierQuote model,List<ScmCarrierLine> carrierLines ){
        Boolean flag=false;
        if(carrierLines!=null&&carrierLines.size()>0){
            ScmCarrierLine line= carrierLines.get(0);
            Map<String,Object> quoteMap=new HashMap<>();
            quoteMap.put("carrierLineId",line.getCarrierLineId());
            quoteMap.put("calculateType", model.getCalculateType());
            quoteMap.put("exacctId", model.getExacctId());
            List<ScmCarrierQuote> quotes =  getDao().getAllByField(quoteMap);
            if(model.getCarrierQuoteId()!=null){
                quotes =getDao().getByFieldExistId(quoteMap, model.getCarrierQuoteId());
            }
            if(quotes!=null&&quotes.size()>0){
                for(ScmCarrierQuote quote:quotes){
                    //如果输入的  区间最大或者最小值已经在包含区间
                    flag=isInRange(model.getMinimumCondiction(),model.getMaxmumCondiction(),quote.getMinimumCondiction(),quote.getMaxmumCondiction());
                    if(flag)
                        return true;
                }
            }
        }
        return flag;
    }

    @Transactional
    public JsonResult createScmCarrier(@RequestBody ScmCarrierQuote model) {
        ScmCarrierLine line= model.getScmCarrierLine();
        Map<String,Object> map=new HashMap<>();
        map.put("carrierId",line.getCarrierId());
        map.put("startCityId",line.getStartCityId());
        map.put("destCityId",line.getDestCityId());
        map.put("transportType",line.getTransportType());
        List<ScmCarrierLine> carrierLines =  scmCarrierLineDao.getAllByField(map);

        if(existRange(model,carrierLines)){
            return JsonResult.error("报价区间已存在");
        }
        if(model.getMinimumCondiction()!=null && model.getMaxmumCondiction()!=null && model.getMinimumCondiction().compareTo(model.getMaxmumCondiction())>=0){
            return JsonResult.error("最大区间须大于最小区间");
        }

        UUID lineId;
        if(carrierLines==null|| carrierLines.size()==0){//新增线路
            line.setCarrierId(line.getCarrierId());
            line.setStartCityId(line.getStartCityId());
            line.setDestCityId(line.getDestCityId());
            line.setTransportType(line.getTransportType());
            lineId=scmCarrierLineService.create(line);
        }else{
            lineId=carrierLines.get(0).getCarrierLineId();
        }
        model.setScmCarrierLine(null);
        model.setCarrierLineId(lineId);
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        super.create(model);
        return JsonResult.success();
    }

    @Transactional
    public JsonResult updateScmCarrierQuote(@RequestBody ScmCarrierQuote model) {
        ScmCarrierLine line= model.getScmCarrierLine();
        Map<String,Object> map=new HashMap<>();
        map.put("carrierId",line.getCarrierId());
        map.put("startCityId",line.getStartCityId());
        map.put("destCityId",line.getDestCityId());
        map.put("transportType",line.getTransportType());
        List<ScmCarrierLine> carrierLines =  scmCarrierLineService.getAllByField(map);
        if(existRange(model,carrierLines)){
            return JsonResult.error("报价区间已存在");
        }
        UUID lineId;
        if(carrierLines==null|| carrierLines.size()==0){//新增线路
            line.setCarrierId(line.getCarrierId());
            line.setStartCityId(line.getStartCityId());
            line.setDestCityId(line.getDestCityId());
            line.setTransportType(line.getTransportType());
            lineId=scmCarrierLineService.create(line);
        }else{
            lineId=carrierLines.get(0).getCarrierLineId();
        }
        model.setScmCarrierLine(null);
        model.setCarrierLineId(lineId);

        if(model.getCarrierQuoteId()==null){
            model.setCreateDate(DateUtils.getTimestampNow());
            model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
            getDao().create(model);
        }else {
            model.setModifyDate(DateUtils.getTimestampNow());
            model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
            super.merge(model);
        }
        return JsonResult.success();
    }
}
