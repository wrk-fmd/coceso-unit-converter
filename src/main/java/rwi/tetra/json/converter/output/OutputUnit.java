package rwi.tetra.json.converter.output;

import java.util.List;

public class OutputUnit {
    private final String call;
    private final List<AnalogRadioContact> analogRadioIds;
    private final List<SmsContact> smsContacts;
    private final List<TetraContact> tetraContacts;

    public OutputUnit(
            final String call,
            final List<AnalogRadioContact> analogRadioIds,
            final List<SmsContact> smsContacts,
            final List<TetraContact> tetraContacts) {
        this.call = call;
        this.analogRadioIds = analogRadioIds;
        this.smsContacts = smsContacts;
        this.tetraContacts = tetraContacts;
    }
}
