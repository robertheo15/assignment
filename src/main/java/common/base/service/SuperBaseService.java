package common.base.service;


import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SuperBaseService<T> {

    void delete(Long id);

    Page<T> findAll(int pageNo, int pageSize, String sortBy, String sortDir);

    Optional<T> findById(Long id);
}
