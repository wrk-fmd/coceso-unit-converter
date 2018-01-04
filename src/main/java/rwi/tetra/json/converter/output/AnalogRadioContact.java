package rwi.tetra.json.converter.output;

import org.apache.commons.lang3.builder.ToStringBuilder;
import rwi.tetra.json.converter.ToStringStyle;

public class AnalogRadioContact {
    private final String id;

    public AnalogRadioContact(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.STYLE)
                .append("id", id)
                .toString();
    }
}
