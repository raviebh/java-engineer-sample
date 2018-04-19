package com.att.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.att.demo.model.Account;
import com.att.demo.model.ServiceResponse;
import com.att.demo.resource.AccountAggregator;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
@Api(value = "AccountControllerApis", produces=MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
	
	@Autowired
	AccountAggregator accountAggregator;
	
	
	@RequestMapping(value="/createAccount", method=RequestMethod.POST)
	@ApiOperation(value ="Service to create Account")
	@ApiResponses(value=
		{@ApiResponse(code=201, message="Created", response=ServiceResponse.class),
		@ApiResponse(code=409, message="Conflicted", response=ServiceResponse.class),
		@ApiResponse(code=400, message="BadRequest", response=ServiceResponse.class)
		})
	public ServiceResponse createAccount(@ApiParam(name="Account", value="Account pojo") @RequestBody Account account,
			HttpServletResponse servletResponse){
		
		ServiceResponse serviceResponse=new ServiceResponse();
		
		if(account != null){
			String response=accountAggregator.createAccount(account);
			
			if("SUCCESS".equalsIgnoreCase(response)){
				serviceResponse.setType("SUCCESS");
				servletResponse.setStatus(HttpStatus.SC_CREATED);
			}else{
				serviceResponse.setType("ERROR");
				serviceResponse.setMessage("Unable to create. A Account with name already exist");
				servletResponse.setStatus(HttpStatus.SC_CONFLICT);
			}
		}else{
			serviceResponse.setType("ERROR");
			serviceResponse.setMessage("Please pass proper request");
			servletResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		
		return serviceResponse;
		
	}
	
	@ApiOperation(value ="Service to Get the Account by account Id")
	@ApiResponses(value=
		{@ApiResponse(code=201, message="OK", response=ServiceResponse.class),
		@ApiResponse(code=404, message="Not Found", response=ServiceResponse.class),
		@ApiResponse(code=400, message="BadRequest", response=ServiceResponse.class)
		})
	@RequestMapping(value="/getAccount/{accountId}", method=RequestMethod.GET)
	public ServiceResponse getAccountById(@ApiParam(name="Account Id", value="Account Id number") @PathVariable String accountId,
			HttpServletResponse servletResponse){
		
		ServiceResponse serviceResponse=new ServiceResponse();
		if(!StringUtils.isEmpty(accountId)){
			
			Account account= accountAggregator.getAccountById(accountId);
			if(account!= null){
				serviceResponse.setAccount(account);
				servletResponse.setStatus(HttpStatus.SC_OK);
			}else{
				serviceResponse.setCode("NOT_FOUND");
				serviceResponse.setMessage("Account with id "+ accountId + " not found" );
				servletResponse.setStatus(HttpStatus.SC_NOT_FOUND);
			}
			
		}else{
			serviceResponse.setType("ERROR");
			serviceResponse.setMessage("Please pass proper account Id");
			servletResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
		}
		return serviceResponse;
		
	}

}
