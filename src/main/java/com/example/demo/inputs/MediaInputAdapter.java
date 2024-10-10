package com.example.demo.inputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.ports.CreateMediaInputPort;
import com.example.demo.domain.ports.MediaInputPort;
import com.example.demo.domain.ports.UpdateMediaInputPort;
import com.example.demo.inputs.mappers.MediaInputMapper;
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
    public final UpdateMediaInputPort updateMediaInputPort;

    @GetMapping("/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = MediaInputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    // todo: refactor the ports folder ?
    @PostMapping("/{realityId}")
    public MediaResponse addMedia(@RequestBody Media media, @PathVariable Long realityId) {
        Media addedMedia = createMediaInputPort.addMedia(media, realityId);
        return MediaInputMapper.mapMediaToMediaResponse(addedMedia);
    }

    // todo: what does PATCH mapping do ?
    // todo: media's enum is numeric and not string ?
    @PutMapping("/{mediaId}")
    public MediaResponse updateMedia(@RequestBody Media media, @PathVariable Long mediaId) throws MediaNotFoundException {
        Media updatedMedia = updateMediaInputPort.updateMedia(media, mediaId);
        return MediaInputMapper.mapMediaToMediaResponse(updatedMedia);
    }
}
