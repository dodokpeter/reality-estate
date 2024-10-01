package domain.ports;

import com.example.demo.entities.RealityDTO;

import java.util.List;

public interface RealitiesInputPort {
    List<RealityDTO> getRealities();
}
