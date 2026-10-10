package com.example.demo.contact.service;

import com.example.demo.DTO.ContactRequestDto;

public interface IContactService {
	
	boolean saveContact(ContactRequestDto contactRequestDto);

}
