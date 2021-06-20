package com.shy.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("shy2210278285@aliyun.com","shy011115");
    }
}
