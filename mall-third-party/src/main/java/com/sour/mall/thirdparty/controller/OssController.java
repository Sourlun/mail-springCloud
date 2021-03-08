package com.sour.mall.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.sour.mall.common.utils.R;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对象存储 controller
 *  说明文档: 服务端签名后直传: https://help.aliyun.com/document_detail/31926.html?spm=a2c4g.11186623.6.1741.71747403dq7G1f
 *  前端 -> 后端获取签名 -> 前端使用签名向阿里云服务器传文件
 *
 * @author xgl
 * @date 2021/3/7 14:06
 **/
@RestController
@RequestMapping("/oss")
public class OssController {


    @Value("${spring.alicloud.oss.endpoint}")
    private String endpoint;

    @Value("${spring.alicloud.oss.bucket}")
    private String bucket;

    @Value("${spring.alicloud.access-key}")
    private String accessId;

    @Value("${spring.alicloud.access-key}")
    private String accessKey;

    @Autowired
    private OSS ossClient;

    /**
     * 获取签名
     * @path /oss/policy
     *
     * @author xgl
     * @date 2021/3/7 14:09
     **/
    @RequestMapping("/policy")
    public R policy() {

        R r;

        // host的格式为 bucketname.endpoint
        String host = "https://" + bucket + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = "http://88.88.88.88:8888";

        // 用户上传文件时指定的前缀。
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", callbackUrl);
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            respMap.put("callback", base64CallbackBody);

            r = R.ok().put("data", respMap);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            r = R.error(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return r;
    }

}
