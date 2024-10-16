package com.example.demo.inputs;

import com.example.demo.domain.exceptions.MediaNotFoundException;
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

    @GetMapping("/{realityId}")
    public List<MediaResponse> getMediaByRealityId(@PathVariable Long realityId) {
        List<Media> media = mediaInputPort.getMediaByRealityId(realityId);
        List<MediaResponse> mediaResponses = MediaInputMapper.mapMediaToMediaResponse(media);
        return mediaResponses;
    }

    @GetMapping("/{mediaId}")
    public MediaResponse getMediaById(@PathVariable Long mediaId) {
        return MediaInputMapper.mapMediaToMediaResponse(mediaInputPort.getMediaById(mediaId));
    }

    @GetMapping("/media")
    public List<MediaResponse> getMediaByMediaType(@RequestParam(required = false) MediaType mediaType) {

        if (mediaType == null) {
            List<Media> media;
            media = mediaInputPort.getAllMedia();
            return MediaInputMapper.mapMediaToMediaResponse(media);
        } else {
            List<Media> media;
            media = mediaInputPort.getMediaByType(mediaType);
            return MediaInputMapper.mapMediaToMediaResponse(media);
        }

//        List<Media> media;
//        if (mediaType == null) {
//            media = mediaInputPort.getAllMedia();
//            return MediaInputMapper.mapMediaToMediaResponse(media);
//        }
//            media = mediaInputPort.getMediaByType(mediaType);
//            return MediaInputMapper.mapMediaToMediaResponse(media);
//
//
//        List<Media> media;
//        if (mediaType == null) {
//            media = mediaInputPort.getAllMedia();
//            return MediaInputMapper.mapMediaToMediaResponse(media);
//        } else {
//
//            media = mediaInputPort.getMediaByType(mediaType);
//            return MediaInputMapper.mapMediaToMediaResponse(media);
//        }
//       return MediaInputMapper.mapMediaToMediaResponse(media);

    }

    @PostMapping("/{realityId}")
    public MediaResponse addMedia(@RequestBody Media media, @PathVariable Long realityId) {
        Media addedMedia = createMediaInputPort.addMedia(media, realityId);
        return MediaInputMapper.mapMediaToMediaResponse(addedMedia);
    }

    // todo: what does PATCH mapping do ?
    @PutMapping("/{mediaId}")
    public MediaResponse updateMedia(@RequestBody Media media, @PathVariable Long mediaId) throws MediaNotFoundException {
        Media updatedMedia = updateMediaInputPort.updateMedia(media, mediaId);
        return MediaInputMapper.mapMediaToMediaResponse(updatedMedia);
    }

    @DeleteMapping("/{mediaId}")
    public ResponseEntity<Void> deleteMediaById(@PathVariable Long mediaId) {
        mediaInputPort.deleteMediaById(mediaId);
        return ResponseEntity.noContent().build();
    }
}
