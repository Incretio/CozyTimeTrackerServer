package com.incretio.cozy_time_tracker_server.service;

import com.incretio.cozy_time_tracker_server.model.remote.vo.TagVo;
import com.incretio.cozy_time_tracker_server.model.remote.vo.helper.ConvertVo;
import com.incretio.cozy_time_tracker_server.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagVo> getAll() {
        return ConvertVo.toVo(tagRepository.getAllInManager());
    }

}
