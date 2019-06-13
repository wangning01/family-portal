package com.family.portal.security.handler;

import com.family.portal.security.util.TokenFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PortalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        PrintWriter pw = null;
        try {
            String accessToken = TokenFactory.createAccessToken(authentication);
            String refreshToken = TokenFactory.createRefreshToken(authentication);

            response.addHeader("Content-Type", "application/json");
            pw = new PrintWriter((response.getOutputStream()));

            Map<String, String> model = new HashMap<>();
            model.put("jwtToken", accessToken);
            model.put("refreshToken", refreshToken);
            String jsonStr = new ObjectMapper().writeValueAsString(model);

            pw.write(jsonStr);

        } finally {
            if (pw != null)
                pw.close();
        }
    }
}
