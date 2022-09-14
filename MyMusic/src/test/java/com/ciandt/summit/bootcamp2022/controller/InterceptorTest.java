package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.client.TokenFeignClient;
import com.ciandt.summit.bootcamp2022.config.Interceptor;
import com.ciandt.summit.bootcamp2022.dto.Data;
import com.ciandt.summit.bootcamp2022.dto.UserDTO;
import com.ciandt.summit.bootcamp2022.dto.UsernameDto;
import com.ciandt.summit.bootcamp2022.entity.Playlist;
import com.ciandt.summit.bootcamp2022.entity.User;
import com.ciandt.summit.bootcamp2022.entity.UserType;
import com.ciandt.summit.bootcamp2022.services.exceptions.CommomUserException;
import com.ciandt.summit.bootcamp2022.utils.exceptions.InvalidLogDataException;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = Interceptor.class)
public class InterceptorTest {

    @InjectMocks
    private Interceptor interceptor;

    private UsernameDto usernameDto;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void setUp() throws Exception {
        usernameDto = new UsernameDto(new Data("joao"));
        httpServletRequest = Mockito.mock(HttpServletRequest.class);
        httpServletResponse = Mockito.mock(HttpServletResponse.class);
        interceptor = new Interceptor();
    }

    @Test
    public void shouldReturnUserInvalidLofDataException() throws Exception {
        Assertions.assertThrows(InvalidLogDataException.class, () -> {
            interceptor.preHandle(httpServletRequest,httpServletResponse, null);
        });

    }


}
