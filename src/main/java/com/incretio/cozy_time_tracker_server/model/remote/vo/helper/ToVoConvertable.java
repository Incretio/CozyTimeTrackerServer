package com.incretio.cozy_time_tracker_server.model.remote.vo.helper;

import com.incretio.cozy_time_tracker_server.model.remote.vo.BaseVo;

public interface ToVoConvertable<T extends BaseVo> {
    T toVo();
}
