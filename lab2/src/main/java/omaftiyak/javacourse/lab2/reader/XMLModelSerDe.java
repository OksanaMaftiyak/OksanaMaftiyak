package omaftiyak.javacourse.lab2.reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class XMLModelSerDe<T> implements ModelSerDe<T> {

    private final Class<T> modelClass;

    public XMLModelSerDe(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public List<T> read(String filePath) throws IOException {
        JAXBContext jaxbContext = buildContext();
        Unmarshaller unmarshaller;
        try {
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException("Could not create unmarshaller", e);
        }
        try {
            ModelContainer<T> modelContainer = (ModelContainer<T>) unmarshaller.unmarshal(new File(filePath));
            return modelContainer.getModels();
        } catch (JAXBException e) {
            throw new IOException("Could not read", e);
        }
    }

    @Override
    public void write(String filePath, List<T> content) throws IOException {
        JAXBContext jaxbContext = buildContext();
        Marshaller marshaller;
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            throw new RuntimeException("Could not create marshaller", e);
        }
        try {
            marshaller.marshal(new ModelContainer<>(content), new File(filePath));
        } catch (JAXBException e) {
            throw new IOException("Could not write", e);
        }
    }

    private JAXBContext buildContext() {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ModelContainer.class, modelClass);
        } catch (JAXBException e) {
            throw new RuntimeException("Could not create JAXB context");
        }
        return jaxbContext;
    }

    @XmlRootElement(name = "list")
    @XmlAccessorType(value = XmlAccessType.FIELD)
    private static class ModelContainer<T> {

        @XmlElement(name = "model")
        private List<T> models;

        private ModelContainer(List<T> models) {
            this.models = models;
        }

        public ModelContainer() {
            models = new ArrayList<>();
        }

        public List<T> getModels() {
            return models;
        }

        public void setModels(List<T> models) {
            this.models = models;
        }

    }

}
