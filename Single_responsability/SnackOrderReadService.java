@Service
public class SnackOrderReadService {
    @Autowired
    private OrderSnackRepository repository;

    public List<SnacksOrders> getAll() {
        return repository.findAll();
    }

    public List<SnacksOrders> getByEmployee(int employeeId) {
        return repository.findByEmployee_Id(employeeId);
    }

    public List<SnacksOrders> getNotReady() {
        return repository.findByReady(false);
    }

    public List<SnacksOrders> getNotReadyByEmployee(int employeeId) {
        return repository.findByEmployee_IdAndReady(employeeId, false);
    }
}
