import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue
    public int cat_id;

    @NotBlank(message = "Le nom de la catégorie ne peut pas être vide.")
    private String name;

    @Lob
    @Column(name = "img", columnDefinition = "longblob")
    private byte[] img;

    @Override
    public String toString() {
        return "Category{" +
                "cat_id=" + cat_id +
                ", name='" + name + '\'' +
                '}';
    }
}
