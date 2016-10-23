package omaftiyak.javacourse.lab2.reader;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.List;


class JSONModelSerDe<T> implements ModelSerDe<T> {

    private final ObjectMapper mapper = new ObjectMapper();
    private final CollectionType collectionType;

    public JSONModelSerDe(Class<T> modelClass) {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.collectionType = mapper.getTypeFactory().constructCollectionType(List.class, modelClass);
    }

    @Override
    public List<T> read(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), collectionType);
    }

    @Override
    public void write(String filePath, List<T> models) throws IOException {
        mapper.writeValue(new File(filePath), models);
    }

}
