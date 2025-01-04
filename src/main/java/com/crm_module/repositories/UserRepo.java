package com.crm_module.repositories;

import com.crm_module.models.users.User;

public interface UserRepo<T extends User> extends BaseRepo<T> {
    boolean isUserNameExists(String username);
}
