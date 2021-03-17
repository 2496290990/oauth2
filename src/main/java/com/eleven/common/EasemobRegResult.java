package com.eleven.common;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/17 22:18
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EasemobRegResult {


    /**
     * path : /users
     * uri : https://a1.easemob.com/1102210107065543/alumni/users
     * timestamp : 1615990703975
     * organization : 1102210107065543
     * application : ce5d0cb1-6e58-4d0e-8915-514651316078
     * entities : [{"uuid":"a23e2fa0-872b-11eb-9b95-2b05e5e42cb3","type":"user","created":1615990704032,"modified":1615990704032,"username":"790771525","activated":true,"nickname":"小A"}]
     * action : post
     * data : []
     * duration : 101
     * applicationName : alumni
     */

    private String id;

    private String userAccount;

    @SerializedName("path")
    private String path;
    @SerializedName("uri")
    private String uri;
    @SerializedName("timestamp")
    private long timestamp;
    @SerializedName("organization")
    private String organization;
    @SerializedName("application")
    private String application;
    @SerializedName("action")
    private String action;
    @SerializedName("duration")
    private int duration;
    @SerializedName("applicationName")
    private String applicationName;
    /**
     * uuid : a23e2fa0-872b-11eb-9b95-2b05e5e42cb3
     * type : user
     * created : 1615990704032
     * modified : 1615990704032
     * username : 790771525
     * activated : true
     * nickname : 小A
     */

    @SerializedName("entities")
    private List<Entities> entities;
    @SerializedName("data")
    private List data;


}
