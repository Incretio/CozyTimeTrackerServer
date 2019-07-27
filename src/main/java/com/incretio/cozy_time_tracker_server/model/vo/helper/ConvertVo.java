package com.incretio.cozy_time_tracker_server.model.vo.helper;

import com.incretio.cozy_time_tracker_server.model.vo.BaseVo;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertVo {
    public <T extends BaseVo, V extends ToVoConvertable<T>> T toVo(V model) {
        return model.toVo();
    }

    public <T extends BaseVo, V extends ToVoConvertable<T>> List<T> toVo(List<V> model) {
        return model.stream()
                    .map(ToVoConvertable::toVo)
                    .collect(Collectors.toList());
    }
}


