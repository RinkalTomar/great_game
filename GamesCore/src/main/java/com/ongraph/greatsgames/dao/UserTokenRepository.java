package com.ongraph.greatsgames.dao;

import com.ongraph.greatsgames.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    public Boolean existsByToken(String token);

    public UserToken getUserTokenByToken(String token);
}
