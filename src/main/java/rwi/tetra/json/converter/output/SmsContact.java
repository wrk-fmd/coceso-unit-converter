package rwi.tetra.json.converter.output;

import org.apache.commons.lang3.builder.ToStringBuilder;
import rwi.tetra.json.converter.ToStringStyle;

public class SmsContact {
    private final String owner;
    private final String phoneNumber;

    public SmsContact(final String owner, final String phoneNumber) {
        this.owner = owner;
        this.phoneNumber = phoneNumber;
    }

    public String getOwner() {
        return owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.STYLE)
                .append("owner", owner)
                .append("phoneNumber", phoneNumber)
                .toString();
    }
}
