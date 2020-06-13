package com.javaweb.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.javaweb.common.service.IUploadService;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 人员角色表 服务实现类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-02-26
 */
@Service
public class UploadServiceImpl implements IUploadService {

    @Autowired
    private UploadUtils uploadUtils;

    /**
     * 上传图片
     *
     * @param request 网络请求
     * @param name    目录名
     * @return
     */
    @Override
    public JsonResult uploadImage(HttpServletRequest request, String name) {
        UploadUtils uploadUtils = new UploadUtils();
        Map<String, Object> result = uploadUtils.uploadFile(request, name);
        List<String> imageList = (List<String>) result.get("image");
        String imageUrl = CommonUtils.getImageURL(imageList.get(0));
        return JsonResult.success("上传成功", imageUrl);
    }

    /**
     * 上传图片
     *
     * @param request
     * @param name    目录名
     * @return
     */
    @Override
    public JsonResult uploadFile(HttpServletRequest request, String name) {
//        UploadUtils uploadUtils = new UploadUtils();
        uploadUtils.setDirName("files");
        Map<String, Object> result = uploadUtils.uploadFile(request, name);
        List<String> nameList = (List<String>) result.get("name");
        List<String> imageList = (List<String>) result.get("image");
        String imageUrl = CommonUtils.getImageURL(imageList.get(0));
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", nameList.get(0));
        map.put("fileUrl", imageUrl);
        return JsonResult.success("上传成功", map);
    }

    /**
     * 上传编辑器图片
     *
     * @param request 网络请求
     * @param name    目录名
     * @return
     */
    @Override
    public String kindeditorImage(HttpServletRequest request, String name) {
        UploadUtils uploadUtils = new UploadUtils();
        Map<String, Object> result = uploadUtils.uploadFile(request, name);
        List<String> imageList = (List<String>) result.get("image");
        String imageUrl = CommonUtils.getImageURL(imageList.get(0));

        // 返回结果
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", imageUrl);
        return obj.toJSONString();
    }
}
