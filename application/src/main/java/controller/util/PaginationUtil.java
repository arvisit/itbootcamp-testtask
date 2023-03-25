package controller.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import constants.ApplicationConstants;

@Component
public class PaginationUtil {
    public Optional<Pageable> getPaginationRest(HttpServletRequest req) {
        String pageStr = req.getParameter("page");

        if (pageStr == null) {
            return Optional.ofNullable(null);
        }

        int page = Integer.parseInt(pageStr);
        return Optional
                .of(PageRequest.of(page, ApplicationConstants.PAGINATION_SIZE, Sort.by(Sort.Direction.ASC, "email")));
    }
}
