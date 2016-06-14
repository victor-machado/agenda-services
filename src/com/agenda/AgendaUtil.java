package com.agenda;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

public class AgendaUtil {

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/agenda_compromisso?user=root&password=mysql";
	
	public static String senha(String plainText) throws UnsupportedEncodingException {
	    byte[] utf8 = plainText.getBytes("UTF-8");
	    return "*" + DigestUtils.shaHex(DigestUtils.sha(utf8)).toUpperCase();
	  }
	
}
