package ph.com.adcpp.utils.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ph.com.adcpp.commons.request.PaginatedRequest;

public final class PaginationUtil {

    public static <S extends PaginatedRequest, E extends JpaRepository> Page<E> getAllObjects(S request, E repository) {
        Sort sort;

        if (request.getSortValue().equals(Sort.Direction.DESC.name())) {
            sort = Sort.by(request.getSortKey()).descending();
        } else {
            sort = Sort.by(request.getSortKey()).ascending();
        }

        Pageable pageRequest = PageRequest.of(request.getPageIndex(), request.getPageSize(), sort);
        return repository.findAll(pageRequest);
    }
}
