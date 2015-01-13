package com.infy.enr.post.myshopee.support;


import java.io.IOException;
import java.util.Date;



import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class ErrorLogger {

	private static final Logger LOGGER = Logger.getLogger("errorLogger");
	private static SimpleLayout layout = new SimpleLayout();
	private static FileAppender appender;
	static{
		try {
			appender= new FileAppender(layout,"ErrorLogFile.txt", true);
		} catch (IOException exception) {
			//exception.printStackTrace();
		}
	}
	public static void logError (String className,String methodName,String exception) {
		LOGGER.addAppender(appender);
		LOGGER.setLevel((Level) Level.INFO);
		LOGGER.info(new Date().toString());
		LOGGER.info("ClassName :"+className);
		LOGGER.info("MethodName :"+methodName );
		LOGGER.info("Exception :" +exception);
		LOGGER.info("-----------------------------------------------------------------------------------");
	}
}
//ErrorLogger.logError(InfyCupService.class.getName(), "validateTeamName()", e.getMessage());
