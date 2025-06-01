package com.golearnix.events;

import com.golearnix.domain.User;


public record UserDeletedEvent(User oldUser) {

}
