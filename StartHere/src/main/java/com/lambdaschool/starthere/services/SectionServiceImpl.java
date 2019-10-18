package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Section;
import com.lambdaschool.starthere.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class SectionServiceImpl implements SectionService
{
    @Autowired
    private SectionRepository sectionRepository;

    // findAll - paging and sorting
    @Override
    public List<Section> findAll(Pageable pageable)
    {
        List<Section> list = new ArrayList<>();
        sectionRepository.findAll(pageable).iterator()
                .forEachRemaining(list::add);
        return list;
    }
}
