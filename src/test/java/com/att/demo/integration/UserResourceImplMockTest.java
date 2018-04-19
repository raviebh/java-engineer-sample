package com.att.demo.integration;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.att.demo.model.User;
import com.att.demo.resource.UserResourceImpl;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class UserResourceImplMockTest {

    private UserResourceImpl userResource;
   
    @Mock
    private User user;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        userResource=new UserResourceImpl();
     
    }
    @Test
    public void shoulUserResourceImpl() throws Exception {
    	userResource.findById(2);
    }
  
   
    
}