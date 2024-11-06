package com.example.demo.inputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.exceptions.UserNotFoundException;
import com.example.demo.domain.ports.realities.CreateRealitiesInputPort;
import com.example.demo.domain.ports.realities.UpdateRealitiesInputPort;
import com.example.demo.inputs.mappers.RealityInputMapper;
import com.example.demo.inputs.mappers.UserInputMapper;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.realities.RealitiesInputPort;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
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

    private final RealityInputMapper realityInputMapper;
    private final UserInputMapper userInputMapper;

    @GetMapping
    public List<RealityResponse> getRealities(
            @RequestParam(required = false) Integer minPrice ,
            @RequestParam(required = false) Integer maxPrice
    ) {
        List<Reality> realities = realitiesInputPort.getRealities(minPrice, maxPrice);
        return realityInputMapper.mapRealityListToRealityResponseList(realities);

    }

    @GetMapping("/paginated")
    public Page<RealityResponse> getPage(Pageable page) {
        Page<Reality> realityPage = realitiesInputPort.getRealitiesByPage(page);
        List<Reality> realityList = realityPage.getContent();
        List<RealityResponse> realityResponseList = realityInputMapper.mapRealityListToRealityResponseList(realityList);
        return new PageImpl<>(realityResponseList, page, realityResponseList.size());
    }

    @GetMapping("/{realityId}")
    public RealityResponse getRealityById(@PathVariable Long realityId) throws RealityNotFoundException {
        return realityInputMapper.mapRealityToRealityResponse(realitiesInputPort.getRealityById(realityId));
    }

    // get all realities owned by a specific user
    @GetMapping("/owner/{userId}")
    public List<RealityResponse> getRealitiesByOwner(@PathVariable Long userId) {
        return realityInputMapper.mapRealityListToRealityResponseList(realitiesInputPort.getRealitiesByOwner(userId));
    }

    @PostMapping()
    public RealityResponse addReality(@RequestBody Reality reality) {
        Reality addedReality = createRealitiesInputPort.addReality(reality);
        return realityInputMapper.mapRealityToRealityResponse(addedReality);
    }

    @PostMapping("/{realityId}")
    public RealityResponse updateReality(@RequestBody Reality reality, @PathVariable Long realityId, @RequestParam(required = true) Long userId) throws RealityNotFoundException {
        Reality updatedReality = updateRealitiesInputPort.updateReality(reality, realityId, userId);
        return realityInputMapper.mapRealityToRealityResponse(updatedReality);
    }

    @PostMapping("/{realityId}/assign/{userId}")
    public RealityResponse assignUser(@PathVariable Long userId, @PathVariable Long realityId) throws UserNotFoundException, RealityNotFoundException {
        Reality updatedReality = updateRealitiesInputPort.assignUser(userId, realityId);
        return realityInputMapper.mapRealityToRealityResponse(updatedReality);
    }
}
