package com.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AuthToken contains token and user details
 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthToken {
    String token;
    String user;
}
