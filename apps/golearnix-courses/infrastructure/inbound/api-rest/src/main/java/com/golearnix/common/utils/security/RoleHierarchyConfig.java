package com.golearnix.common.utils.security;

import com.golearnix.common.annotations.UtilConfiguration;
import com.golearnix.domain.enums.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;

@UtilConfiguration
public class RoleHierarchyConfig {

  @Bean
  public RoleHierarchy roleHierarchy() {

    String hierarchyDefinition = String.join("\n",
        UserRole.ROLE_ADMIN.name()      + " > " + UserRole.ROLE_INSTRUCTOR.name(),
        UserRole.ROLE_INSTRUCTOR.name() + " > " + UserRole.ROLE_STUDENT.name()
    );

    return RoleHierarchyImpl.fromHierarchy(hierarchyDefinition);
  }

  @Bean
  public MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {

    DefaultMethodSecurityExpressionHandler handler = new DefaultMethodSecurityExpressionHandler();
    handler.setRoleHierarchy(roleHierarchy);

    return handler;
  }

}
