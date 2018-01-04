package rwi.tetra.json.converter.input;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static rwi.tetra.json.converter.ToStringStyle.STYLE;

public class InputFormat {
    @SerializedName("data")
    private List<InputUnit> units;

    public List<InputUnit> getUnits() {
        return units;
    }

    public void setUnits(final List<InputUnit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, STYLE)
                .append("units", units)
                .toString();
    }
}
