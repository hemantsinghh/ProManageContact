package com.promanagecontact.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RemoveMessage {
    public static void removeMessage(){
        try{
            HttpSession httpSession = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
            httpSession.invalidate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
