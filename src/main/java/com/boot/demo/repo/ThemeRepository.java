package com.boot.demo.repo;

import com.boot.demo.model.Theme;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;

@Repository
public interface ThemeRepository extends CrudRepository<Theme, Long> {
    Theme findThemeByDirect(String food);

    Theme findFeedById(Long id);
}
