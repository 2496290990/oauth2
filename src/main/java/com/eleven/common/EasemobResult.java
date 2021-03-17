package com.eleven.common;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaojinhui
 * @date 2021/3/17 21:46
 * @apiNote 获取环信token信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasemobResult {

    /**
     * application : ce5d0cb1-6e58-4d0e-8915-514651316078
     * access_token : YWMtHaNoaIcnEeuDngWe5KrRGQAAAAAAAAAAAAAAAAAAAAHOXQyxblhNDokVUUZRMWB4AgMAAAF4QG-rrQBPGgAulZzgGLmXNYHN1qRQkpyGnvETpgChBiaz0QM-fykF6A
     * expires_in : 5184000
     */

    @SerializedName("application")
    private String application;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expiresIn;

}
