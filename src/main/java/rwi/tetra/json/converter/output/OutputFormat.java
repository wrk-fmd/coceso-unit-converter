package rwi.tetra.json.converter.output;

import org.apache.commons.lang3.builder.ToStringBuilder;
import rwi.tetra.json.converter.ToStringStyle;

import java.util.List;

public class OutputFormat {
    private final List<OutputUnit> units;

    public OutputFormat(final List<OutputUnit> units) {
        this.units = units;
    }

    public List<OutputUnit> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.STYLE)
                .append("units", units)
                .toString();
    }
}
