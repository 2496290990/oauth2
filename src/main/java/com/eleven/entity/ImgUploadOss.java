package com.eleven.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author zhaojinhui
 * @date 2021/2/19 17:27
 * @apiNote
 */
@Data
@ToString
public class ImgUploadOss {

    /** 返回的状态码 */
    private int code;

    /** 上传的id */
    private int id;

    /** 上传的hashId */
    private String imgid;

    /** 存储的相对路径 */
    private String relativePath;

    /** 可供访问的 http 图片地址 */
    private String url;

    /** 缩略图地址 */
    private String thumbnailUrl;

    /** 图片宽度 px 单位 */
    private int width;

    /**图片高度 px 单位*/
    private int height;

    /**图片的删除路径 */
    private String delete;
}
