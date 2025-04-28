package dipSolid;

import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Entity.Snack;
import com.example.Backend.Services.Cross_Origin;
import com.example.Backend.Services.LostAndFoundService;
import com.example.Backend.Services.SnacksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/snacks")
@RestController
public class SnacksController {
    private ISnacksService snacksService;

    @Autowired
    public SnacksController(ISnacksService snacksService) {
        this.snacksService = snacksService;
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/getitem")
    public Snack getItem(@RequestParam int id) {
        return snacksService.getsnack(id);
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @GetMapping("/getitems")

    public List<Snack> getItems() {
        List<Snack> res = snacksService.getsnacks();
        return res;
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PostMapping("/add")
    public void add(@RequestBody Snack snack) {

        snacksService.addsnack(snack);
    }

    @CrossOrigin(origins = Cross_Origin.url)
    @PutMapping("/update")
    public void update(@RequestBody Snack snack) {

        snacksService.update(snack);

    }

    @CrossOrigin(origins = Cross_Origin.url)
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        snacksService.deleteSnack(id);

    }

}
