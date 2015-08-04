package com.lnet.tms.utility;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordMatcher implements CredentialsMatcher {
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String submittedPassword = String.valueOf(((UsernamePasswordToken) token).getPassword());
        String storedCredentials = info.getCredentials().toString();

        try {
            return PasswordHash.validatePassword(submittedPassword, storedCredentials);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return false;
    }

    private String getSubmittedPassword(AuthenticationToken token) {
        return token != null ? token.getCredentials().toString() : null;
    }

    private String getStoredPassword(AuthenticationInfo storedAccountInfo) {
        return storedAccountInfo != null ? storedAccountInfo.getCredentials().toString() : null;
    }
}
