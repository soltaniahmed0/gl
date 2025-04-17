@Service
public class SnackOrderStatusService {
    @Autowired
    private OrderSnackRepository repository;

    public SnacksOrders markAsReady(int id) {
        SnacksOrders order = repository.findById(id).orElseThrow();
        order.setReady(true);
        return repository.save(order);
    }
}
