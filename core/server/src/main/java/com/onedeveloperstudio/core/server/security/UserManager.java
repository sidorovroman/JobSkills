package com.onedeveloperstudio.core.server.security;

import com.onedeveloperstudio.core.common.dto.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * User: y.zakharov
 * Date: 20.08.14
 */
@Service
public class UserManager {
  private HashMap<String, User> users;

  public UserManager() {
    users = new HashMap<String, User>();
    users.put("john", new User("john", "1", "ROLE_USER"));
    users.put("bob", new User("bob", "2", "ROLE_USER, ROLE_ADMIN"));
  }

  public User getUser(String username) throws UsernameNotFoundException {
    if( !users.containsKey( username ) ){
      throw new UsernameNotFoundException( username + " not found" );
    }

    return users.get( username );
  }
}
