package com.students.webappwithsecurity.service;

import org.apache.logging.log4j.*;
import org.apache.logging.log4j.message.MapMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MakeLogMessageService implements LogMessageService {
    private final MapMessage mapMessage = new MapMessage<>();
    private final Marker marker = MarkerManager.getMarker("MarkerLog");
    Logger logger = LogManager.getLogger(MakeLogMessageService.class);
    public MakeLogMessageService() {}
    public void makeMessage(String str1, String str2) {
        mapMessage.clear();
        mapMessage.with("path", str1);
        mapMessage.with("message", str2);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals("anonymousUser")) return;
        UserDetails user = (UserDetails) authentication.getPrincipal();
        mapMessage.with("user", user.getUsername());
        logger.info(marker, mapMessage);
    }
}
