package domain.models;

import com.example.demo.entities.Media;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Reality {
    private Long id;

    private String type;
    private String location;
    private int price;
    private int rooms;
    private int area;
    private String description;

    private List<Media> medias;
}
