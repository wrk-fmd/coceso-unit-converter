package rwi.tetra.json.converter.output;

import org.apache.commons.lang3.builder.ToStringBuilder;
import rwi.tetra.json.converter.ToStringStyle;

public class TetraContact {
    private final String owner;
    private final String issi;

    public TetraContact(final String owner, final String issi) {
        this.owner = owner;
        this.issi = issi;
    }

    public String getOwner() {
        return owner;
    }

    public String getIssi() {
        return issi;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.STYLE)
                .append("owner", owner)
                .append("issi", issi)
                .toString();
    }
}
