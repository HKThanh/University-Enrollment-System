package vn.edu.iuh.fit.authservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.authservices.models.Token;

public interface TokenRepository extends JpaRepository<Token, String> {

}