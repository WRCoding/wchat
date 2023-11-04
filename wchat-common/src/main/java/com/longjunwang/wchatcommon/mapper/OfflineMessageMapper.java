package com.longjunwang.wchatcommon.mapper;

import com.longjunwang.wchatcommon.entity.OfflineMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OfflineMessageMapper {

    int insert(OfflineMessage offlineMessage);
}
