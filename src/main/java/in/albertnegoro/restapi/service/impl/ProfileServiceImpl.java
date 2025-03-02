package in.albertnegoro.restapi.service.impl;

import in.albertnegoro.restapi.dto.ProfileDTO;
import in.albertnegoro.restapi.entity.ProfileEntity;
import in.albertnegoro.restapi.repository.ProfileRepository;
import in.albertnegoro.restapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    /**
     * It will save the user details to datbase
     *
     * @param profileDTO
     * @return ProfileDTO
     */
    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        profileEntity = profileRepository.save(profileEntity);
        log.info("Printing the profile entity details {}", profileEntity);

        return mapToProfileDTO(profileEntity);
    }

    /**
     * Mapper method to map values from ProfileEntity to ProfileDTO
     *
     * @param profileEntity
     * @return ProfileDTO
     */
    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    /**
     * Mapper method to map value from ProfileDTO to ProfileEntity
     *
     * @param profileDTO
     * @return ProfileEntity
     */
    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
