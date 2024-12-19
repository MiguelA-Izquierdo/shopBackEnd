package com.app.userService.user.application.service;

import com.app.userService.user.domain.event.UserCreatedEvent;
import com.app.userService.user.domain.event.UserDeletedEvent;
import com.app.userService.user.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserEventFactory {

  @Value("${messaging.exchange.user}")
  private String userExchange;

  @Value("${messaging.queue.user.created}")
  private String userCreatedQueue;

  @Value("${messaging.queue.user.deleted}")
  private String userDeletedQueue;

  @Value("${messaging.routing.key.user.created}")
  private String userCreatedRoutingKey;

  @Value("${messaging.routing.key.user.deleted}")
  private String userDeletedRoutingKey;

  public UserCreatedEvent createUserCreatedEvent(User user) {
    return new UserCreatedEvent(
      userExchange,
      userCreatedQueue,
      userCreatedRoutingKey,
      user.getId().getValue(),
      user.getName().getValue(),
      user.getLastName().getValue(),
      user.getEmail().getEmail()
    );
  }

  public UserDeletedEvent createUserDeletedEvent(User user) {
    return new UserDeletedEvent(
      userExchange,
      userDeletedQueue,
      userDeletedRoutingKey,
      user.getId().getValue(),
      user.getName().getValue(),
      user.getLastName().getValue(),
      user.getEmail().getEmail()
    );
  }
}