package com.example.demo.inputs;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.MediaInputPort;
import com.example.demo.inputs.mappers.MediaInputMapper;
import com.example.demo.inputs.models.MediaResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/media")
public class MediaInputAdapter {

    public final MediaInputPort mediaInputPort;

    @GetMapping("/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = MediaInputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    @GetMapping("/{mediaId}")
    public MediaResponse getMediaById(@PathVariable Long mediaId) {
        return MediaInputMapper.mapMediaToMediaResponse(mediaInputPort.getMediaById(mediaId))   ;

    }
}
