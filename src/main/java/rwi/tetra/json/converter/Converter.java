package rwi.tetra.json.converter;

import org.apache.commons.lang3.StringUtils;
import rwi.tetra.json.converter.input.InputFormat;
import rwi.tetra.json.converter.input.InputPerson;
import rwi.tetra.json.converter.input.InputUnit;
import rwi.tetra.json.converter.output.AnalogRadioContact;
import rwi.tetra.json.converter.output.OutputFormat;
import rwi.tetra.json.converter.output.OutputUnit;
import rwi.tetra.json.converter.output.SmsContact;
import rwi.tetra.json.converter.output.TetraContact;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
    private final NumberNormalizer numberNormalizer;
    private final boolean createTetraContacts;
    private final boolean createSmsContacts;
    private final boolean createAnalogContacts;

    Converter(final boolean createTetraContacts, final boolean createSmsContacts, final boolean createAnalogContacts) {
        this.createTetraContacts = createTetraContacts;
        this.createSmsContacts = createSmsContacts;
        this.createAnalogContacts = createAnalogContacts;
        this.numberNormalizer = new NumberNormalizer();
    }

    public OutputFormat convert(final InputFormat input) {
        List<OutputUnit> units = input.getUnits()
                .stream()
                .map(this::convertUnit)
                .collect(Collectors.toUnmodifiableList());
        return new OutputFormat(units);
    }

    private OutputUnit convertUnit(final InputUnit inputUnit) {
        List<SmsContact> smsContacts;
        if (createSmsContacts) {
            smsContacts = inputUnit.getCrew()
                    .stream()
                    .flatMap(this::createSmsContact)
                    .filter(contact -> StringUtils.isNotBlank(contact.getPhoneNumber()))
                    .collect(Collectors.toUnmodifiableList());
        } else {
            smsContacts = List.of();
        }

        List<TetraContact> tetraCrewContacts = inputUnit.getCrew()
                .stream()
                .flatMap(this::createTetraContact)
                .collect(Collectors.toUnmodifiableList());

        List<AnalogRadioContact> radioContacts = List.of();
        List<TetraContact> tetraFromAniList = List.of();
        if (StringUtils.isNotBlank(inputUnit.getAni())) {
            tetraFromAniList = Stream.of(inputUnit.getAni().split("[,; ]"))
                    .filter(StringUtils::isNotBlank)
                    .map(issi -> new TetraContact(inputUnit.getCall(), issi))
                    .collect(Collectors.toUnmodifiableList());
            if (createAnalogContacts) {
                radioContacts = Stream.of(inputUnit.getAni().split("[,; ]"))
                        .filter(StringUtils::isNotBlank)
                        .map(AnalogRadioContact::new)
                        .collect(Collectors.toUnmodifiableList());
            }
        }

        List<TetraContact> mergedTetraContactList;
        if (createTetraContacts) {
            mergedTetraContactList = Stream.concat(tetraCrewContacts.stream(), tetraFromAniList.stream())
                    .distinct()
                    .collect(Collectors.toUnmodifiableList());
        } else {
            mergedTetraContactList = List.of();
        }

        return new OutputUnit(inputUnit.getId() + "", inputUnit.getCall(), radioContacts, smsContacts, mergedTetraContactList);
    }

    private Stream<SmsContact> createSmsContact(final InputPerson person) {
        return Arrays.stream(person.getContact().split("\n"))
                .map(numberNormalizer::normalize)
                .map(number -> new SmsContact(String.format("%s %s", person.getFirstname(), person.getLastname()), number));
    }

    private Stream<TetraContact> createTetraContact(final InputPerson person) {
        return Arrays.stream(person.getContact().split("\n"))
                .filter(number -> number.startsWith("tetra:"))
                .map(issi -> issi.replace("tetra:", ""))
                .map(StringUtils::trimToEmpty)
                .filter(StringUtils::isNotBlank)
                .map(number -> new TetraContact(String.format("%s %s", person.getFirstname(), person.getLastname()), number));
    }
}
