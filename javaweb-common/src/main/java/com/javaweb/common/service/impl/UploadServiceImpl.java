package com.javaweb.common.service.impl;

import com.javaweb.common.service.IUploadService;
import com.javaweb.common.utils.CommonUtils;
import com.javaweb.common.utils.JsonResult;
import com.javaweb.common.utils.UploadUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
}
