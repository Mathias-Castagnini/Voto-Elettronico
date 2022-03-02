package logger;

import java.util.logging.*;

public class VotoLogger {
	private static Logger logger = Logger.getLogger("logger");
	
	public static void init() {
		FileHandler fh;
		try {
			fh= new FileHandler("log/logfile.log", true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Logger Start");
		}catch(Exception e) {
			logger.log(Level.WARNING, "Exception: ",e);
		}
	}
	
	public static void writeToLog(String msg) {
		logger.info(msg);
	}
	public static void writeToLog(String msg, Level l, Exception e) {
		logger.log(l,msg,e);
	}
}
