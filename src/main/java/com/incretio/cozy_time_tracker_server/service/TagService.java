package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.model.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagService {

    @Autowired
    private TagRepository tagsRepository;
    @Autowired
    private ConvertVo convertVo;

    public List<TagVo> getAll() {
        return convertVo.toVo(tagsRepository.getAll());
    }

}
