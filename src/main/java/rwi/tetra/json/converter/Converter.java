package rwi.tetra.json.converter;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwi.tetra.json.converter.input.InputFormat;
import rwi.tetra.json.converter.input.InputPerson;
import rwi.tetra.json.converter.input.InputUnit;
import rwi.tetra.json.converter.output.AnalogRadioContact;
import rwi.tetra.json.converter.output.OutputFormat;
import rwi.tetra.json.converter.output.OutputUnit;
import rwi.tetra.json.converter.output.SmsContact;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
    private static final Logger LOG = LoggerFactory.getLogger(Converter.class);

    private final NumberNormalizer numberNormalizer;

    Converter() {
        numberNormalizer = new NumberNormalizer();
    }

    public OutputFormat convert(final InputFormat input) {
        List<OutputUnit> units = input.getUnits()
                .stream()
                .map(this::convertUnit)
                .collect(Collectors.toList());
        return new OutputFormat(units);
    }

    private OutputUnit convertUnit(final InputUnit inputUnit) {
        List<SmsContact> smsContacts = inputUnit.getCrew()
                .stream()
                .flatMap(this::createSmsContact)
                .filter(contact -> StringUtils.isNotBlank(contact.getPhoneNumber()))
                .collect(Collectors.toList());
        List<AnalogRadioContact> radioContacts = ImmutableList.of();
        if (StringUtils.isNotBlank(inputUnit.getAni())) {
            radioContacts = ImmutableList.of(new AnalogRadioContact(inputUnit.getAni()));
        }

        return new OutputUnit(inputUnit.getId() + "", inputUnit.getCall(), radioContacts, smsContacts, ImmutableList.of());
    }

    private List<SmsContact> splitToMultiple(final SmsContact contact) {
        String[] numbers = contact.getPhoneNumber()
                .split("\n");

        List<SmsContact> contacts = Arrays.stream(numbers)
                .map(number -> new SmsContact(contact.getOwner(), number))
                .collect(Collectors.toList());
        if (contacts.size() > 0) {
            LOG.info("Splitted contact {} to contacts {}.", contact, contacts);
        }

        return contacts;
    }

    private Stream<SmsContact> createSmsContact(final InputPerson person) {
        return Arrays.stream(person.getContact().split("\n"))
                .map(numberNormalizer::normalize)
                .map(number -> new SmsContact(String.format("%s %s", person.getFirstname(), person.getLastname()), number));
    }
}
