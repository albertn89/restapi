package in.albertnegoro.restapi.service;

import in.albertnegoro.restapi.dto.ProfileDTO;

public interface ProfileService {
    /**
     * It will save the user details to database
     *
     * @param profileDTO
     * @return ProfileDTO
     */
    ProfileDTO createProfile(ProfileDTO profileDTO);
}
