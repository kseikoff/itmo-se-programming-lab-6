package yamlsTools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * A class that implements the save command, it saves the collection to a file in the specified path.
 *
 * @author Dmitrii Chebanenko
 */
public class YAMLWriter {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).findAndRegisterModules();
    /**
     * Method that saves collection to a file in the specified path.
     *
     * @param object - object that is saved to a file
     * @throws IOException - the method throws an exception if the input of the object parameter fails
     */
    public void writeYAML(Object object, String file) throws IOException {
        File newFile = new File(GlobalPath.getPath() + file);
        newFile.createNewFile();
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.writeValue(newFile, object);
    }
}