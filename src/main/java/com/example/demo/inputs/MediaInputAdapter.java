package com.example.demo.inputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.media.CreateMediaInputPort;
import com.example.demo.domain.ports.media.MediaInputPort;
import com.example.demo.domain.ports.media.UpdateMediaInputPort;
import com.example.demo.inputs.mappers.InputMapper;
import com.example.demo.inputs.models.MediaResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/media")
public class MediaInputAdapter {

    private final MediaInputPort mediaInputPort;
    private final CreateMediaInputPort createMediaInputPort;
    private final UpdateMediaInputPort updateMediaInputPort;

    private final InputMapper inputMapper;

    @GetMapping("/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = inputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    @PostMapping("/{realityId}")
    public MediaResponse addMedia(@RequestBody Media media, @PathVariable Long realityId) {
        Media addedMedia = createMediaInputPort.addMedia(media, realityId);
        return inputMapper.mapMediaToMediaResponse(addedMedia);
    }

    @PutMapping("/{mediaId}")
    public MediaResponse updateMedia(@RequestBody Media media, @PathVariable Long mediaId) throws MediaNotFoundException {
        Media updatedMedia = updateMediaInputPort.updateMedia(media, mediaId);
        return inputMapper.mapMediaToMediaResponse(updatedMedia);
    }
}
