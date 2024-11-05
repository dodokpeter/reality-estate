package com.example.demo.inputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
import com.example.demo.domain.exceptions.RealityNotFoundException;
import com.example.demo.domain.models.Media;
import com.example.demo.domain.models.MediaType;
import com.example.demo.domain.ports.media.CreateMediaInputPort;
import com.example.demo.domain.ports.media.MediaInputPort;
import com.example.demo.domain.ports.media.UpdateMediaInputPort;
import com.example.demo.inputs.mappers.MediaInputMapper;
import com.example.demo.inputs.models.MediaResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/media")
public class MediaInputAdapter {

    private final MediaInputPort mediaInputPort;
    private final CreateMediaInputPort createMediaInputPort;
    private final UpdateMediaInputPort updateMediaInputPort;

    private final MediaInputMapper mediaInputMapper;

    @GetMapping("/reality/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = mediaInputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    @GetMapping("/{mediaId}")
    public MediaResponse getMediaById(@PathVariable Long mediaId) {
        return mediaInputMapper.mapMediaToMediaResponse(mediaInputPort.getMediaById(mediaId));
    }

    @GetMapping("/reality-media/{realityId}")
    public List<MediaResponse> getMediaByMediaType(@PathVariable Long realityId, @RequestParam(required = false) MediaType mediaType) {
        List<Media> media = mediaType == null ? mediaInputPort.getMediaByRealityId(realityId) : mediaInputPort.getMediaByType(realityId, mediaType);
        return mediaInputMapper.mapMediaToMediaResponse(media);
    }

    @PostMapping("/{realityId}")
    public MediaResponse addMedia(@RequestBody Media media, @PathVariable Long realityId) throws RealityNotFoundException {
        Media addedMedia = createMediaInputPort.addMedia(media, realityId);
        return mediaInputMapper.mapMediaToMediaResponse(addedMedia);
    }

    @PutMapping("/{mediaId}")
    public MediaResponse updateMedia(@RequestBody Media media, @PathVariable Long mediaId) throws MediaNotFoundException {
        Media updatedMedia = updateMediaInputPort.updateMedia(media, mediaId);
        return mediaInputMapper.mapMediaToMediaResponse(updatedMedia);
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable Long mediaId) {
        mediaInputPort.deleteMediaById(mediaId);
        return ResponseEntity.noContent().build();
    }
}
