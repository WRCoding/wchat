create database wchat;
CREATE TABLE user_info(
    id          VARCHAR(255) NOT NULL COMMENT '主键',
    user_name   VARCHAR(255) NOT NULL COMMENT '用户名',
    avatar_url  VARCHAR(255) COMMENT '头像链接',
    phone       VARCHAR(255) COMMENT '手机号',
    email       VARCHAR(255) COMMENT '邮箱',
    password    VARCHAR(255) NOT NULL COMMENT '密码',
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_phone (phone),
    UNIQUE KEY uk_email (email)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息表';

CREATE TABLE message (
     msg_id      VARCHAR(255) NOT NULL COMMENT '消息id',
     send_id     VARCHAR(255) NOT NULL COMMENT '发送者id',
     receive_id  VARCHAR(255) NOT NULL COMMENT '接收者id',
     msgType    INT          NOT NULL COMMENT '消息类型',
     content    VARCHAR(255) NOT NULL COMMENT '消息内容',
     create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     unique KEY (msg_id),
     INDEX      idx_sendId (send_id),
     INDEX      idx_receiveId (receive_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='消息表';