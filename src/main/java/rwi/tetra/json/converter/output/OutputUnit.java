package rwi.tetra.json.converter.output;

import org.apache.commons.lang3.builder.ToStringBuilder;
import rwi.tetra.json.converter.ToStringStyle;

import java.util.List;

public class OutputUnit {
    private final String id;
    private final String call;
    private final List<AnalogRadioContact> analogRadioIds;
    private final List<SmsContact> smsContacts;
    private final List<TetraContact> tetraContacts;

    public OutputUnit(
            final String id, final String call,
            final List<AnalogRadioContact> analogRadioIds,
            final List<SmsContact> smsContacts,
            final List<TetraContact> tetraContacts) {
        this.id = id;
        this.call = call;
        this.analogRadioIds = analogRadioIds;
        this.smsContacts = smsContacts;
        this.tetraContacts = tetraContacts;
    }

    public String getId() {
        return id;
    }

    public String getCall() {
        return call;
    }

    public List<AnalogRadioContact> getAnalogRadioIds() {
        return analogRadioIds;
    }

    public List<SmsContact> getSmsContacts() {
        return smsContacts;
    }

    public List<TetraContact> getTetraContacts() {
        return tetraContacts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.STYLE)
                .append("id", id)
                .append("call", call)
                .append("analogRadioIds", analogRadioIds)
                .append("smsContacts", smsContacts)
                .append("tetraContacts", tetraContacts)
                .toString();
    }
}
