package back.ahwhew.service.resultService;

import back.ahwhew.dto.GifDTO;
import back.ahwhew.entity.GifEntity;
import back.ahwhew.repository.GifRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class GifService {

    private final GifRepository gifRepository;

    // 현업에서는 랜덤을 만들때 하나의 인스턴스를 만들고 재사용함. 여기서는 계속 새로운 인스턴스 생성, 다음 멘토링전까지 현업에서 왜그러는지 찾기
    private static final Random RANDOM = new Random();

    @Autowired
    public GifService(GifRepository gifRepository) {
        this.gifRepository = gifRepository;
    }

    public String getRandomGifUrl(String classifyTag) {
        // 주어진 태그와 일치하는 모든 GifEntity 검색
        List<GifEntity> gifEntities = gifRepository.findAllByTag(classifyTag);

        // 일치하는 GifEntity가 없는 경우 null을 반환하거나 해당하는 방식으로 처리
        if (gifEntities == null || gifEntities.isEmpty()) {
            return null;
        }

        // 랜덤하게 GifEntity 선택
        GifEntity randomGifEntity = gifEntities.get(RANDOM.nextInt(gifEntities.size()));

        // GifEntity의 gifUrl 반환
        return randomGifEntity.getGifUrl();
    }
}