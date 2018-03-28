package rwi.tetra.json.converter;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwi.tetra.json.converter.output.OutputFormat;
import rwi.tetra.json.converter.output.OutputUnit;
import rwi.tetra.json.converter.output.SmsContact;
import rwi.tetra.json.converter.output.TetraContact;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String... args) {
        LOG.debug("Starting JSON converter with arguments: {}", (Object) args);


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Converter converter = new Converter();

        OutputFormat outputFormat = new OutputFormat(ImmutableList.of(
                new OutputUnit(
                        "Test Device 1",
                        "Callsign 1",
                        ImmutableList.of(),
                        ImmutableList.of(new SmsContact("Someone", "+436761234567")),
                        ImmutableList.of(new TetraContact("KHD 123", "1234"))
                )
        ));

        try (FileWriter fileWriter = new FileWriter("test-output.json")) {
            gson.toJson(outputFormat, fileWriter);
            LOG.info("Output file written.");
        } catch (IOException e) {
            LOG.error("Failed to write output file.");
        }
    }
}
