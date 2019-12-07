package com.incretio.cozy_time_tracker_server.web;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotAuthorizedException;

public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }

    public static void checkPermission(HttpServletResponse response, int userId) {
        if (userId == 1) {
            return;
        }
        throw new NotAuthorizedException(response);
    }
}
