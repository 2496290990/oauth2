package com.eleven.entity;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojinhui
 * @date 2021/3/13 16:55
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oauth2Result {

    /** token */
    @SerializedName("access_token")
    private String accessToken;

    /** token 类型 */
    @SerializedName("token_type")
    private String tokenType;

    /** 过期时间 */
    @SerializedName("expires_in")
    private int expiresIn;

    /** 范围 */
    @SerializedName("scope")
    private String scope;


}
