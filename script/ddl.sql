CREATE TABLE UserInfo
(
    id          VARCHAR(255) NOT NULL COMMENT '主键',
    user_name   VARCHAR(255) NOT NULL COMMENT '用户名',
    avatar_url  VARCHAR(255) COMMENT '头像链接',
    phone       VARCHAR(255) COMMENT '手机号',
    email       VARCHAR(255) COMMENT '邮箱',
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_phone (phone),
    UNIQUE KEY uk_email (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户信息表';