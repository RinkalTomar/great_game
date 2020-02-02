package com.ongraph.greatsgames.services;

import com.ongraph.greatsgames.beans.dto.AppUser;
import com.ongraph.greatsgames.beans.dto.CurrentUser;
import org.springframework.stereotype.Service;

public interface ICoreService {
    CurrentUser getCurrentUser();

    CurrentUser toCurrentUser(AppUser u);

    Boolean hasPermission(String permissions);

    Boolean isAuthenticated();

    Boolean isCurrentUser(Long id);

    Boolean isCurrentUser(String username);

    Boolean isSuperAdmin();
}
