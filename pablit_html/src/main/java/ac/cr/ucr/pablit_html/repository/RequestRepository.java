package ac.cr.ucr.pablit_html.repository;

import ac.cr.ucr.pablit_html.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
