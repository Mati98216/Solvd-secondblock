package com.solvd.laba.xml;

import javax.xml.XMLConstants;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import static com.solvd.laba.Main.logger;

public class XMLValidator {
    public static void main(String[] args) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("src/main/resources/schema.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("src/main/resources/xmlfile.xml")));
            logger.info("XML is valid.");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("XML is not valid: " + e.getMessage());
        }
    }
}

