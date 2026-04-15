package com.project.airBnbApp.service;

import com.project.airBnbApp.entity.Booking;

public interface CheckOutService {

    String getCheckOutSession(Booking booking, String successUrl , String failureUrl);

}
