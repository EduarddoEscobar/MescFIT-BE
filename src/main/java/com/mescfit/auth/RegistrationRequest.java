package com.mescfit.auth;

public record RegistrationRequest(String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String picture,
                                  Boolean TFA,
                                  Boolean subscribe) {

}
