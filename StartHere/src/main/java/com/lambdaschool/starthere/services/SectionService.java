package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Section;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectionService
{
    // findAll - paging and sorting
    List<Section> findAll(Pageable pageable);
}
