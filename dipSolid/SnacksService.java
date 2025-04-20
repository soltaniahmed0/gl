
import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Entity.Snack;
import com.example.Backend.Repository.LostAndFoundRepository;
import com.example.Backend.Repository.SnacksRepository;
import com.example.Backend.Services.ISnacksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnacksService implements ISnacksService {

    @Autowired
    private SnacksRepository snacksRepository;

    @Override
    public List<Snack> getsnacks() {
        return snacksRepository.findAll();
    }

    @Override
    public void addsnack(Snack snack) {
        snacksRepository.save(snack);
    }

    @Override
    public Snack getsnack(int id) {
        return snacksRepository.findById(id).orElse(null);
    }

    @Override
    public Snack update(Snack snack) {

        return snacksRepository.save(snack);

    }

    @Override
    public void deleteSnack(int id) {
        snacksRepository.deleteById(id);
    }

}
