package rwi.tetra.json.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwi.tetra.json.converter.input.InputFormat;
import rwi.tetra.json.converter.output.OutputFormat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static final boolean CREATE_TETRA_CONTACTS = true;
    public static final boolean CREATE_SMS_CONTACTS = false;
    public static final boolean CREATE_ANALOG_CONTACTS = false;

    public static void main(String... args) {
        LOG.debug("Starting JSON converter with arguments: {}", (Object) args);

        if (args.length != 2) {
            System.out.println("Usage: java Main input.json output.json");
            System.exit(-1);
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Converter converter = new Converter(CREATE_TETRA_CONTACTS, CREATE_SMS_CONTACTS, CREATE_ANALOG_CONTACTS);

        InputFormat input = null;
        try (FileReader fileReader = new FileReader(inputFileName)) {
            BufferedReader reader = new BufferedReader(fileReader);
            input = gson.fromJson(reader, InputFormat.class);
        } catch (IOException e) {
            LOG.error("Input file does not exist.");
            System.exit(-2);
        } catch (JsonSyntaxException e) {
            LOG.error("Input is not a valid JSON.");
            System.exit(-3);
        }

        LOG.debug("Got input with {} units.", input.getUnits().size());

        OutputFormat outputFormat = converter.convert(input);

        try (FileWriter fileWriter = new FileWriter(outputFileName)) {
            gson.toJson(outputFormat, fileWriter);
            LOG.info("Output file written.");
        } catch (IOException e) {
            LOG.error("Failed to write output file.");
        }
    }
}
