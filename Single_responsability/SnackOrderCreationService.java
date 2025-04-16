@Service
public class SnackOrderCreationService {
    @Autowired
    private OrderSnackRepository repository;

    public SnacksOrders create(SnacksOrders order) {
        return repository.save(order);
    }
}
