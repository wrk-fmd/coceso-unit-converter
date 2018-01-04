package rwi.tetra.json.converter;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import rwi.tetra.json.converter.input.InputFormat;
import rwi.tetra.json.converter.input.InputPerson;
import rwi.tetra.json.converter.input.InputUnit;
import rwi.tetra.json.converter.output.AnalogRadioContact;
import rwi.tetra.json.converter.output.OutputFormat;
import rwi.tetra.json.converter.output.OutputUnit;
import rwi.tetra.json.converter.output.SmsContact;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {
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
                .map(this::createSmsContact)
                .filter(contact -> StringUtils.isNotBlank(contact.getPhoneNumber()))
                .collect(Collectors.toList());
        List<AnalogRadioContact> radioContacts = ImmutableList.of();
        if (StringUtils.isNotBlank(inputUnit.getAni())) {
            radioContacts = ImmutableList.of(new AnalogRadioContact(inputUnit.getAni()));
        }

        return new OutputUnit(inputUnit.getId() + "", inputUnit.getCall(), radioContacts, smsContacts, ImmutableList.of());
    }

    private SmsContact createSmsContact(final InputPerson person) {
        return new SmsContact(String.format("%s %s", person.getFirstname(), person.getLastname()), numberNormalizer.normalize(person.getContact()));
    }
}
