package com.ongraph.greatsgames.services;


public interface IEmailEventService {

    Long queueAccountVerificationEmail(String email, String verificationToken) throws Exception;

    Long queuePasswordRestEmail(String email, String verificationToken) throws Exception;
}
