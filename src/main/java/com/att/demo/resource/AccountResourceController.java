package com.att.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.att.demo.exception.CustomError;
import com.att.demo.model.Account;
import com.att.demo.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/account")
@Api(value="Account Resource")
public class AccountResourceController {
	
	@Autowired
	AccountService accountService;
	
	@ApiOperation(value = "Find a Account with an Account ID",response = Account.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Sucessful retrieval of Account information based on account id"),
					@ApiResponse(code = 204, message = "Acount Details not found for this account Id"),		
					@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
					@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")		
					})
	@GetMapping("/findById/{accountId}")
	public ResponseEntity<?> getAccount(@PathVariable("accountId") Long accountId) {		
		Account account =  accountService.findById(accountId);
		if(account == null) {
			CustomError status = new CustomError("Account with id " + accountId +" not found", HttpStatus.NO_CONTENT.toString());
			status.setErrorCode(HttpStatus.NO_CONTENT.toString());			
			return new ResponseEntity<CustomError>(status, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find All Accounts",response = Account.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Sucessful retrieval of All Account information"),
					@ApiResponse(code = 204, message = "Acount Details not found"),
					@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
					@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")	
					})
	@GetMapping("/findAllAccounts")
	public ResponseEntity<?> findAllAccounts() {		
		List<Account> accounts =  accountService.findAllAccounts();		
		if (accounts == null || accounts.isEmpty()) {			
			CustomError status = new CustomError(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.toString());
			status.setErrorCode(HttpStatus.NO_CONTENT.toString());			
			return new ResponseEntity<CustomError>(status, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add a Account")
	@PostMapping(path ="/createAccount", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAccount(@RequestBody final Account account) {	
		CustomError status = null;
			
		if (accountService.isAccountExist(account)) {			
			status = new CustomError("Unable to create. A Account with name " +account.getName()  + " already exist", HttpStatus.CONFLICT.toString());
			return new ResponseEntity<CustomError>(status, HttpStatus.CONFLICT);
		}
		accountService.saveAccount(account);	
		return new ResponseEntity<String>("Account added Succesfully", HttpStatus.CREATED);
	}

}
