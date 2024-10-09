package com.example.demo.inputs;

import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.Reality;
import com.example.demo.domain.ports.CreateMediaInputPort;
import com.example.demo.domain.ports.MediaInputPort;
import com.example.demo.inputs.mappers.MediaInputMapper;
import com.example.demo.inputs.mappers.RealityInputMapper;
import com.example.demo.inputs.models.MediaResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/media")
public class MediaInputAdapter {

    // todo: not private ?
    public final MediaInputPort mediaInputPort;
    public final CreateMediaInputPort createMediaInputPort;

    @GetMapping("/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = MediaInputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    @PostMapping()
    public MediaResponse addMedia(@RequestBody Media media) {
        Media addedMedia = createMediaInputPort.addMedia(media);
        return MediaInputMapper.mapMediaToMediaResponse(addedMedia);
    }

}
