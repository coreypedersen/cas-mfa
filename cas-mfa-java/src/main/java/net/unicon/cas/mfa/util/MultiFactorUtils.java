package net.unicon.cas.mfa.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.unicon.cas.mfa.web.support.MultiFactorAuthenticationSupportingWebApplicationService;

import org.jasig.cas.authentication.Authentication;

/**
 * Utility methods to ease implementation of multifactor behavior.
 * @author Misagh Moayyed
 */
public final class MultiFactorUtils {
    /**
     * Private constructor.
     */
    private MultiFactorUtils() {
    }

    /**
     * Convert the object given into a {@link Collection} instead.
     * @param obj the object to convert into a collection
     * @return The collection instance containing the object provided
     */
    @SuppressWarnings("unchecked")
    public static Set<Object> convertValueToCollection(final Object obj) {
        final Set<Object> c = new HashSet<Object>();

        if (obj instanceof Collection) {
            c.addAll((Collection<Object>) obj);
        } else if (obj instanceof Map) {
            throw new UnsupportedOperationException(Map.class.getCanonicalName() + " is not supoorted");
        } else if (obj.getClass().isArray()) {
            for (final Object object : (Object[]) obj) {
                c.add(object);
            }
        } else {
            c.add(obj);
        }
        return c;
    }

    /**
     * Retrieves the collection of authentication methods available in the list
     * of authentication attributes. The authentication attribute that refers to the set of methods satisfied is
     * by the name of  {@link MultiFactorAuthenticationSupportingWebApplicationService#CONST_PARAM_AUTHN_METHOD}.
     *
     * @param authentication the authentication that houses the methods.
     * @return collection of fulfilled authentication methods
     */
    public static Set<String> getSatisfiedAuthenticationMethods(final Authentication authentication) {
        if (authentication.getAttributes().containsKey(MultiFactorAuthenticationSupportingWebApplicationService.CONST_PARAM_AUTHN_METHOD)) {
            final Object methods = authentication.getAttributes().get(
                    MultiFactorAuthenticationSupportingWebApplicationService.CONST_PARAM_AUTHN_METHOD);
            if (methods != null) {
                final Set<Object> valuesAsACollection = convertValueToCollection(methods);
                return new HashSet<String>(Arrays.asList(valuesAsACollection.toArray(new String[] {})));
            }
        }
        return Collections.emptySet();
    }
}