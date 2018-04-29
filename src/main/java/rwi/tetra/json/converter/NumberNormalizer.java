package rwi.tetra.json.converter;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberNormalizer {
    private static final Logger LOG = LoggerFactory.getLogger(NumberNormalizer.class);

    private static final String ALLOWED_CHARACTERS = "+0123456789";
    private static final String VALID_PREFIX = "+43";

    private final CharMatcher charMatcher;

    public NumberNormalizer() {
        charMatcher = CharMatcher.anyOf(ALLOWED_CHARACTERS);
    }

    public String normalize(final String inputNumber) {
        String normalizedString = "";
        if (StringUtils.isNotBlank(inputNumber)) {
            normalizedString = charMatcher.retainFrom(inputNumber);
            if (normalizedString.matches("^0[1-9][0-9]+")) {
                normalizedString = normalizedString.replaceFirst("0", VALID_PREFIX);
            }

            if (normalizedString.matches("^00[1-9][0-9]+")) {
                normalizedString = normalizedString.replaceFirst("00", "+");
            }

            if (!normalizedString.startsWith(VALID_PREFIX)) {
                LOG.warn("Number is dropped: input={}, normalized={}", inputNumber, normalizedString);
                normalizedString = "";
            }
        } else {
            LOG.debug("Tried to normalize invalid number: '{}'", inputNumber);
        }

        return normalizedString;
    }
}
