package com.incretio.cozy_time_tracker_server.model.vo.helper;

import com.incretio.cozy_time_tracker_server.model.vo.BaseVo;

public interface ToVoConvertable<T extends BaseVo> {
    T toVo();
}
