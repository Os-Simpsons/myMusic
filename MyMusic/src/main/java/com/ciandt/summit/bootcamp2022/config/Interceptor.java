package com.ciandt.summit.bootcamp2022.config;

import com.ciandt.summit.bootcamp2022.client.TokenFeignClient;
import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.services.PlaylistServiceImpl;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private TokenFeignClient tokenFeignClient;

    private static Logger logger = LogManager.getLogger(PlaylistServiceImpl.class);

    @Override
    public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UsernameDto usernameDto = new UsernameDto(new Data(request.getHeader("name"),request.getHeader("token")));

        try {
            if (request.getHeader("token") == null || request.getHeader("name") == null) {
                logger.info("Token not send");
                throw new InvalidLogDataException("Token or name not send!");
            }
            else if (tokenFeignClient.authorizeToken(usernameDto).equals("ok")) {
                logger.info("Token Ok!");
                return true;
            } else {
                logger.error("Validation Not Ok.");
                throw new InvalidLogDataException("Invalid or Expired Token!");
            }
        } catch (FeignException e) {
            throw new InvalidLogDataException("Invalid Token!");
        }

    }

}
