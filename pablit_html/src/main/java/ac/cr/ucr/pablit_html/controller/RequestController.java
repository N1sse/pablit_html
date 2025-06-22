package ac.cr.ucr.pablit_html.controller;

import ac.cr.ucr.pablit_html.model.Request;
import ac.cr.ucr.pablit_html.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {

    private final RequestService requestService;
    public RequestController(RequestService requestService) {
        this.requestService=requestService;
    }

    @PostMapping   //Necesita al requestService para usar al metodo makeRequest
    public Request makeRequest(@RequestBody Request request){return requestService.makeRequest(request); }

    @GetMapping
    public List<Request> getRequests(){return requestService.getAllRequests(); }
}
