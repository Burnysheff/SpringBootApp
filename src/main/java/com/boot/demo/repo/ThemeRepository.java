package com.boot.demo.repo;

import com.boot.demo.model.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, Long> {
    Theme findThemeByDirect(String food);

    Theme findFeedById(Long id);
}
