package com.example.demo.configuration.interceptor;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Código para ejecutar antes de manejar la solicitud
        String authorizationHeader = request.getHeader("Authorization");
        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }
        try{
            FirebaseAuth.getInstance().verifyIdToken(authorizationHeader);
            System.out.println("Funcionaaa" + authorizationHeader);
            return true;
        }catch (Exception e){
            System.out.println("No esta funcionando");
            System.out.println(authorizationHeader);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Código para ejecutar después de manejar la solicitud, pero antes de renderizar la vista (si corresponde)





    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Código para ejecutar después de que se complete la solicitud, incluyendo la renderización de la vista (si corresponde)
    }
}
