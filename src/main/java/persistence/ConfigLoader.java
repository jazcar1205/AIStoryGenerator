package persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * Used for getting information from teh config properties file.
 */
public class ConfigLoader
{
    private static final String fileName = "src/resources/config.properties";

    /**
     * Will load a string from the file
     * corresponding to the provided key.
     * @param key
     * @return
     */
    public static String getKey(String key)
    {
	  Properties prop = new Properties();
	  try (FileInputStream fis = new FileInputStream(fileName))
	  {
		prop.load(fis);
	  } catch (FileNotFoundException ex)
	  {
		System.err.println("File not found: " + fileName);
	  } catch (IOException ex)
	  {
		System.err.println(ex.getMessage());
	  }
	  return prop.getProperty(key);
    }
}
