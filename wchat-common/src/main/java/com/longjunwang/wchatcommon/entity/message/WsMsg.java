package com.longjunwang.wchatcommon.entity.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc: WsMsh
 *
 * @author ink
 * date:2023-09-24 10:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WsMsg {

    private String sendId;
    private Integer msgType;
    private Object content;
}
