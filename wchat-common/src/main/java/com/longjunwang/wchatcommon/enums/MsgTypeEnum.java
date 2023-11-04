package com.longjunwang.wchatcommon.enums;

public enum MsgTypeEnum {
    TEXT(1,"文本", "textMsgHandle");

    private Integer type;
    private String desc;

    private String beanName;
    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getBeanName() {
        return beanName;
    }

    MsgTypeEnum(Integer type, String desc, String beanName) {
        this.type = type;
        this.desc = desc;
        this.beanName = beanName;
    }
}
