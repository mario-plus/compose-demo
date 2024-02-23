package mario.controller;

import mario.entity.SeataResource;
import mario.service.SeataResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxz
 * @date 2024年02月23日 9:51
 */
@RestController
@RequestMapping("/resource")
public class SeataResourceController {

    private SeataResourceService seataResourceService;

    @Autowired
    public void setSeataResourceService(SeataResourceService seataResourceService) {
        this.seataResourceService = seataResourceService;
    }

    @GetMapping("/getResource")
    public SeataResource getResource(@RequestParam("id") int id) {
        return seataResourceService.selectById(id);
    }


    @GetMapping("/disCount")
    public void disCount(@RequestParam("id") int id, @RequestParam("count") int count) throws Exception {
        seataResourceService.disCountById(id, count);
    }
}
