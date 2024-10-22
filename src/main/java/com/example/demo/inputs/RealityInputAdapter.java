package com.example.demo.inputs;

import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.ports.realities.CreateRealitiesInputPort;
import com.example.demo.domain.ports.realities.UpdateRealitiesInputPort;
import com.example.demo.inputs.mappers.RealityInputMapper;
import com.example.demo.inputs.mappers.UserInputMapper;
import com.example.demo.inputs.models.RealityResponse;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.realities.RealitiesInputPort;
import com.example.demo.inputs.models.UserResponse;
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

    private final RealityInputMapper realityInputMapper;
    private final UserInputMapper userInputMapper;

    @GetMapping
    public List<RealityResponse> getRealities() {
        List<Reality> realities = realitiesInputPort.getRealities();
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
    public RealityResponse getRealityById(@PathVariable Long realityId) {
        return realityInputMapper.mapRealityToRealityResponse(realitiesInputPort.getRealityById(realityId));
    }

    // todo: can a reality input adapter return a userResponse
    @GetMapping("/{realityId}/owner")
    public UserResponse getOwnerOfReality(@PathVariable Long realityId) throws RealityNotFoundException {
        return userInputMapper.mapUserToUserResponse(realitiesInputPort.getOwnerOfReality(realityId));
    }

    @PostMapping()
    public RealityResponse addReality(@RequestBody Reality reality) {
        Reality addedReality = createRealitiesInputPort.addReality(reality);
        return realityInputMapper.mapRealityToRealityResponse(addedReality);
    }

    // todo: check if the reality is added by the user that owns the reality
    @PostMapping("/{realityId}")
    public RealityResponse updateReality(@RequestBody Reality reality, @PathVariable Long realityId) throws RealityNotFoundException {
        Reality updatedReality = updateRealitiesInputPort.updateReality(reality, realityId);
        return realityInputMapper.mapRealityToRealityResponse(updatedReality);
    }
}
