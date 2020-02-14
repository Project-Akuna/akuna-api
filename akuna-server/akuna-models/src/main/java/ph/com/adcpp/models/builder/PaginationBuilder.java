package ph.com.adcpp.models.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ph.com.adcpp.commons.request.PaginatedRequest;

import java.util.stream.Collectors;

@Component
public class PaginationBuilder {

    private ObjectMapper mapper;

    public PaginationBuilder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @SuppressWarnings("unchecked")
    public <R extends PaginatedRequest, S, T extends JpaRepository> Object buildPage(R request, T repository, Class<S> type) {
        Sort sort;

        if (request.getSortValue().equals(Sort.Direction.DESC.name())) {
            sort = Sort.by(request.getSortKey()).descending();
        } else {
            sort = Sort.by(request.getSortKey()).ascending();
        }
        Pageable pageRequest = PageRequest.of(request.getPageIndex(), request.getPageSize(), sort);
        return repository.findAll(pageRequest).get()
                .map(obj -> mapObject(obj, type))
                .collect(Collectors.toList());
    }

    public <S> S mapObject(Object obj, Class<S> type) {
        return mapper.convertValue(obj, type);
    }
}
