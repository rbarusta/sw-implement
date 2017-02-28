package com.company;

import Services.Authentication.SWAuthenticationService;
import Utils.Responses.IResponse;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try{
            SWAuthenticationService auth = new SWAuthenticationService("demo","123456789", "http://services.test.sw.com.mx");

            IResponse res =  auth.Token();
            System.out.println(res.Data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
