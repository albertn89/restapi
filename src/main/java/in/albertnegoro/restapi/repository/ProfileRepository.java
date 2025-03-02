package in.albertnegoro.restapi.repository;

import in.albertnegoro.restapi.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

}
