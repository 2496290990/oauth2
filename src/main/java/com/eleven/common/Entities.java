package com.eleven.common;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojinhui
 * @date 2021/3/17 22:40
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entities {

    private String id;
    private String easemobRegId;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("type")
    private String type;
    @SerializedName("created")
    private Long created;
    @SerializedName("modified")
    private Long modified;
    @SerializedName("username")
    private String username;
    @SerializedName("activated")
    private Boolean activated;
    @SerializedName("nickname")
    private String nickname;
}
