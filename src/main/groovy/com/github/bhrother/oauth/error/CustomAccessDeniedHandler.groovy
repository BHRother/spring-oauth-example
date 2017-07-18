package com.github.bhrother.oauth.error

import groovy.util.logging.Slf4j
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by brunohenriquerother on 18/07/2017.
 *
 * Intercept and retrieve the 403 page for access denied.
 */
@Slf4j
@Component
class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  void handle(HttpServletRequest httpServletRequest,
                     HttpServletResponse httpServletResponse,
                     AccessDeniedException e) throws IOException, ServletException {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication()

    if (auth != null) {
      log.info("User '${auth.getName()}' attempted to access the protected URL: ${httpServletRequest.getRequestURI()}")
    }

    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403")
  }
}
