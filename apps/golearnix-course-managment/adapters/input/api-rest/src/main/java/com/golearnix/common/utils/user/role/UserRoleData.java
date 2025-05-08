package com.golearnix.common.utils.user.role;

import com.golearnix.common.annotations.Util;
import com.golearnix.domain.enums.UserRole;

@Util("role")
public class UserRoleData {

  public final String ADMIN      = UserRole.ROLE_ADMIN.name();
  public final String INSTRUCTOR = UserRole.ROLE_INSTRUCTOR.name();
  public final String STUDENT    = UserRole.ROLE_STUDENT.name();

}
