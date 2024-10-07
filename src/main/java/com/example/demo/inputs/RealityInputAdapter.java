package com.example.demo.inputs;

import com.example.demo.domain.ports.CreateRealitiesInputPort;
import com.example.demo.domain.ports.UpdateRealitiesInputPort;
import com.example.demo.inputs.mappers.RealityInputMapper;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.RealitiesInputPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/realities")
public class RealityInputAdapter {

    private final RealitiesInputPort realitiesInputPort;
    private final CreateRealitiesInputPort createRealitiesInputPort;
    private final UpdateRealitiesInputPort updateRealitiesInputPort;

    @GetMapping
    public List<RealityResponse> getRealities() {
        List<Reality> realities = realitiesInputPort.getRealities();
        return RealityInputMapper.mapRealityListToRealityResponseList(realities);

    }

    @GetMapping("/paginated")
    public Page<RealityResponse> getPage(Pageable page) {
        Page<Reality> realityPage = realitiesInputPort.getRealitiesByPage(page);
        List<Reality> realityList = realityPage.getContent();
        List<RealityResponse> realityResponseList = RealityInputMapper.mapRealityListToRealityResponseList(realityList);
        return new PageImpl<>(realityResponseList, page, realityResponseList.size());
    }

    @GetMapping("/{realityId}")
    public RealityResponse getRealityById(@PathVariable Long realityId) {
        return RealityInputMapper.mapRealityToRealityResponse(realitiesInputPort.getRealityById(realityId));
    }

    // todo: finish add + update

    @PostMapping()
    public void addReality(@RequestBody Reality reality) {
        createRealitiesInputPort.addReality(reality);
    }

    @PostMapping("/{realityId}")
    public void updateReality(@RequestBody Reality reality, @PathVariable Long realityId) {
        updateRealitiesInputPort.updateReality(reality, realityId);
    }
}
