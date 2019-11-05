package com.whalecloud.serviceimpl;

import com.google.gson.Gson;
import com.whalecloud.domain.re.Code;
import com.whalecloud.domain.re.CodeExample;
import com.whalecloud.dto.blockchain.code.AuthorizationInformation;
import com.whalecloud.mapper.re.CodeMapper;
import com.whalecloud.service.CodeService;
import com.whalecloud.util.CommonUtil;
import com.whalecloud.util.SeedUtil;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/10/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeMapper codeMapper;


    @Override
    public void add(String phone, String code) {
        CodeExample codeExample = new CodeExample();
        CodeExample.Criteria criteria = codeExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        int count = codeMapper.countByExample(codeExample);
        List<Code> codes = codeMapper.selectByExample(codeExample);


        //如果已经存在，就更新操作，否则就插入
        if (codes.size() > 0) {
            Code codeObject = codes.get(0);
            codeObject.setCode(code);

            codeMapper.updateByPrimaryKeySelective(codeObject);
        } else {
            //将手机号和验证码一起塞入数据库
            Code codeObject = new Code();
            codeObject.setId(SeedUtil.getId());
            codeObject.setPhone(phone);
            codeObject.setCode(code);
            codeMapper.insert(codeObject);
        }

    }

    @Override
    public Boolean isTrue(String phone, String code) throws Exception {

        CodeExample codeExample = new CodeExample();
        CodeExample.Criteria criteria = codeExample.createCriteria();
        criteria.andPhoneEqualTo(phone).andCodeEqualTo(code);
        //统计是否正确
        int count = codeMapper.countByExample(codeExample);

        //匹配出手机号的信息
        CodeExample example = new CodeExample();
        CodeExample.Criteria criteria1 = codeExample.createCriteria();
        criteria1.andPhoneEqualTo(phone);
        List<Code> codes = codeMapper.selectByExample(example);


        Gson gson = new Gson();
        //如果大于0的话，就证明手机号和验证码是正确的，否则不正确
        if (count > 0) {
            if (codes.size() > 0) {

                Code code1 = codes.get(0);
                //区块链--插入鉴权记录
                HttpParamers paramers = HttpParamers.httpPostParamers();
                AuthorizationInformation authorizationInformation = CommonUtil.fitAuthorizationInfo(phone, code, code1.getCode(), "success");
                String objectOneJson = gson.toJson(authorizationInformation);
                paramers.setJsonParamer(objectOneJson);
                String response1 = "";
                try {
                    HttpService httpService = new HttpService("http://39.99.148.130:8013");
                    response1 = httpService.service("/api/createAuthentication", paramers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return true;
        } else {
            if (codes.size() > 0) {
                Code code1 = codes.get(0);
                //区块链--插入鉴权记录
                HttpParamers paramers = HttpParamers.httpPostParamers();
                AuthorizationInformation authorizationInformation = CommonUtil.fitAuthorizationInfo(phone, code, code1.getCode(), "failure");

                String objectOneJson = gson.toJson(authorizationInformation);
                paramers.setJsonParamer(objectOneJson);
                String response1 = "";
                try {
                    HttpService httpService = new HttpService("http://39.99.148.130:8013");
                    response1 = httpService.service("/api/createAuthentication", paramers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return false;
        }

    }
}


