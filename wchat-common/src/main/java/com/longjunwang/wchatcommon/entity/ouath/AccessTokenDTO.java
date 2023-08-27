package com.longjunwang.wchatcommon.entity.ouath;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
}
