package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader
{
    private static final String fileName = "src/resources/config.properties";

    public static String getKey(String key)
    {
	  Properties prop = new Properties();
	  try (FileInputStream fis = new FileInputStream(fileName))
	  {
		prop.load(fis);
	  } catch (FileNotFoundException ex)
	  {
		System.out.println("File not found: " + fileName);
	  } catch (IOException ex)
	  {
		System.out.println(ex.getMessage());
	  }
	  return prop.getProperty(key);
    }
}
