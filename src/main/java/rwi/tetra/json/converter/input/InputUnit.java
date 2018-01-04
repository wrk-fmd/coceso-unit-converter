package rwi.tetra.json.converter.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static rwi.tetra.json.converter.ToStringStyle.STYLE;

public class InputUnit {

    private Integer id;
    private String call;
    private String ani;
    private List<InputPerson> crew;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getCall() {
        return call;
    }

    public void setCall(final String call) {
        this.call = call;
    }

    public String getAni() {
        return ani;
    }

    public void setAni(final String ani) {
        this.ani = ani;
    }

    public List<InputPerson> getCrew() {
        return crew;
    }

    public void setCrew(final List<InputPerson> crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, STYLE)
                .append("id", id)
                .append("call", call)
                .append("ani", ani)
                .append("crew", crew)
                .toString();
    }
}
